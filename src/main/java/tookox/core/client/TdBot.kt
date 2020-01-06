package tookox.core.client

import cn.hutool.core.util.RuntimeUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.core.util.ZipUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import tooko.main.Env
import tooko.main.Fn
import tooko.td.TdApi.*
import tookox.core.finishEvent
import tookox.core.fromPrivate
import tookox.core.utils.makeAnswer
import java.io.File
import java.util.*

open class TdBot(val botToken: String) : TdClient(initDataDir(botToken)), TdBotAbsHandler {

    override val sudo: TdBot get() = this

    var persists = HashMap<Int, TdBotAbsHandler>()
    var payloads = HashMap<String, TdBotAbsHandler>()
    var functions = HashMap<String, TdBotAbsHandler>()
    var callbacks = HashMap<Int, TdBotAbsHandler>()

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) = runBlocking {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            sendUnit(CheckAuthenticationBotToken(botToken)) onError {

                onAuthorizationFailed(it)

            }

        }

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = runBlocking {

        while (!auth) delay(100L)

        if (userId == me.id) return@runBlocking

        if (message.fromPrivate) {

            synchronized(persists) {

                // TODO: handle persist message

            }

            if (message.content !is MessageText) return@runBlocking

            val content = (message.content as MessageText).text

            var param = content.text

            run {

                Env.FUN_PREFIX.forEach {

                    if (param.startsWith(it)) return@forEach

                    param = param.substring(it.length)

                    return@run

                }

                return@runBlocking

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

            if ("start" == function) {

                if (params.isEmpty()) {

                    onLaunch(userId, chatId, message)

                } else {

                    // TODO: handle start payload

                }

                return@runBlocking

            }

            if (!functions.containsKey(function)) {

                handlers.filterIsInstance<TdBotAbsHandler>().forEach {

                    it.onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                }

            } else {

                functions[function]!!.onFunction(userId, chatId, message, function, param, params, originParams)

            }

        }

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