package tookox.core.client

import cn.hutool.core.util.RuntimeUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.core.util.ZipUtil
import kotlinx.coroutines.coroutineScope
import tooko.main.Env
import tooko.main.Fn
import tooko.td.TdApi.*
import tookox.core.finishEvent
import tookox.core.fromPrivate
import tookox.core.shift
import tookox.core.utils.makeAnswer
import tookox.core.utils.readDataFrom
import tookox.core.utils.writeDataTo
import java.io.File
import java.util.*

open class TdBot(val botToken: String) : TdClient(initDataDir(botToken)), TdBotAbsHandler {

    val botUserId = botToken.substringBefore(':').toInt()

    override val sudo: TdBot get() = this

    val payloads = HashMap<String, TdBotAbsHandler>()
    val functions = HashMap<String, TdBotAbsHandler>()
    val callbacks = HashMap<Int, TdBotAbsHandler>()

    val persistHandlers = HashMap<Int, TdBotAbsHandler>()
    val persists = HashMap<Int, TdPerstst>()

    override suspend fun onLogin() {

        readDataFrom("user_persists")?.forEach {

            val userId = it[0].toInt()

            val persistId = it[1].toInt()

            val subId = it[2].toInt()

            val allowFuction = it[3].toInt() == 1

            val allowCancel = it[4].toInt() == 1

            val createAt = it[5].toInt()

            val data = it.shift(5).toList()

            val handler = (persistHandlers[persistId] ?: error("Invalid Persist ID #${persistId}"))

            runCatching {

                handler.onPersistReStore(userId, subId, data)

            }.onFailure {

                return@forEach

            }

            sudo.persists[userId] = TdPerstst(userId, persistId, subId, allowFuction, allowCancel, createAt)

        }

    }

    override suspend fun onDestroy() {

        super<TdBotAbsHandler>.onDestroy()

        val dataList = LinkedList<List<String>>()

        persists.forEach { (userId, persist) ->

            val data = LinkedList<String>()

            data.add("$userId")

            data.add("${persist.persistId}")

            data.add("${persist.subId}")

            data.add("${persist.allowFuction}")

            data.add("${persist.allowCancel}")

            data.add("${persist.createAt}")

            data.add("${persist.persistId}")

            runCatching {

                onPersistStore(userId, persist.subId, data)

            }.onFailure {

                return@forEach

            }

            dataList.add(data)

        }

        writeDataTo("user_persists", dataList)

    }

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = coroutineScope {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            sendUnit(CheckAuthenticationBotToken(botToken)) onError {

                onAuthorizationFailed(it)

            }

        }

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope {

        if (auth && userId == me.id) return@coroutineScope

        val persist = if (message.fromPrivate && persists.containsKey(userId)) {

            persists[userId]

        } else null

        run predict@{

            if (message.content !is MessageText) return@predict

            val content = (message.content as MessageText).text

            var param = content.text

            run fn@{

                Env.FUN_PREFIX.forEach {

                    if (!param.startsWith(it)) return@forEach

                    param = param.substring(it.length)

                    return@fn

                }

                return@predict

            }

            var function = if (!param.contains(' ')) {

                param.also {

                    param = ""

                }

            } else {

                param.substringBefore(' ').also {

                    param = param.substringAfter(' ')

                }

            }

            val validSuffix = "@${me.username}"

            if (function.endsWith(validSuffix)) {

                function = function.substring(0, function.length - validSuffix.length)

            }

            val params: Array<String>

            val originParams: Array<String>

            if (param.isBlank()) {

                originParams = arrayOf()
                params = originParams

            } else {

                originParams = param.split(' ').toTypedArray()
                params = param.replace("  ", " ").split(' ').toTypedArray()

            }

            if (persist != null) run persist@{

                val handler = persistHandlers[persist.persistId] ?: error("Invalid Persist ID #${persist.persistId}")

                if (function == "cancel" && persist.allowCancel) {

                    sudo removePersist userId

                    handler.onPersistCancel(userId, chatId, message, persist.subId)

                    handler.onPersistRemoveOrCancel(userId, persist.subId)

                    handler.onSendCanceledMessage(userId)

                    return@coroutineScope

                }

                if (persist.allowFuction) {

                    sudo removePersist userId

                    handler.onPersistCancel(userId, chatId, message, persist.subId)

                    handler.onPersistRemoveOrCancel(userId, persist.subId)

                    handler.onSendCanceledMessage(userId)

                    return@persist

                }

                handler.onPersistFunction(userId, chatId, message, persist.subId, function, param, params, originParams)

                return@coroutineScope

            }

            if ("start" == function) {

                if (params.isEmpty()) {

                    onLaunch(userId, chatId, message)

                } else {

                    // TODO: handle start payload

                }

            } else if (!functions.containsKey(function)) {

                handlers.filterIsInstance<TdBotAbsHandler>().forEach {

                    it.onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                }

            } else {

                functions[function]!!.onFunction(userId, chatId, message, function, param, params, originParams)

            }

            return@coroutineScope

        }

        if (persist == null) return@coroutineScope

        val handler = (persistHandlers[persist.persistId] ?: error("Invalid Persist ID #${persist.persistId}"))

        handler.onPersistMessage(userId, chatId, message, persist.subId)

        finishEvent()

    }

    override suspend fun handleNewInlineCallbackQuery(id: Long, senderUserId: Int, inlineMessageId: String, chatInstance: Long, payload: CallbackQueryPayload) {

        if (payload is CallbackQueryPayloadGame) return

        var data = (payload as CallbackQueryPayloadData).data

        if (data[0].toInt() == 120 && data[1].toInt() == -38) {

            data = ZipUtil.unZlib(data)

        }

        val dataId = data[0] + 129

        val subId = data[1].toInt()

        if (!callbacks.containsKey(dataId)) {

            sudo makeAnswer "Invalid Data #$id" answerTo id

            return

        }

        callbacks[dataId]!!.onNewInlineCallbackQuery(senderUserId, inlineMessageId, id, subId, Fn.readData(data))

        finishEvent()

    }

    override suspend fun handleNewCallbackQuery(id: Long, senderUserId: Int, chatId: Long, messageId: Long, chatInstance: Long, payload: CallbackQueryPayload) {

        if (payload is CallbackQueryPayloadGame) return

        var data = (payload as CallbackQueryPayloadData).data

        if (data[0].toInt() == 120 && data[1].toInt() == -38) {

            data = ZipUtil.unZlib(data)

        }

        val dataId = data[0] + 129

        val subId = data[1].toInt()

        if (!callbacks.containsKey(dataId)) {

            sudo makeAnswer "Invalid Data #$id" answerTo id

            return

        }

        callbacks[dataId]!!.onNewCallbackQuery(senderUserId, chatId, messageId, id, subId, Fn.readData(data))

        finishEvent()

    }

    open fun onLaunch(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit
    override suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int) = Unit
    override suspend fun onPersistRemove(userId: Int, subId: Int) = Unit
    override suspend fun onPersistTimeout(userId: Int, subId: Int) = Unit
    override suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int) = Unit
    override fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>) = Unit
    override fun onPersistReStore(userId: Int, subId: Int, data: List<String>) = Unit

    companion object {

        private fun initDataDir(botToken: String): TdOptions {

            val dataDir = Env.getFile("data/" + StrUtil.subBefore(botToken, ":", false))

            dataDir.mkdirs()

            mkLink(dataDir, "stickers")
            mkLink(dataDir, "profile_photos")
            mkLink(dataDir, "thumbnails")
            mkLink(dataDir, "wallpapers")


            return TdOptions().databaseDirectory(dataDir.path)

        }

        private fun mkLink(dataDir: File, target: String) {

            val sourceDir = File(dataDir, target)

            val targetDir = Env.getFile("cache/files/$target")

            if (!sourceDir.isDirectory) {

                targetDir.mkdirs()

                RuntimeUtil.execForStr("ln -s " + targetDir.path + " " + sourceDir.path)

            }

        }

    }

}