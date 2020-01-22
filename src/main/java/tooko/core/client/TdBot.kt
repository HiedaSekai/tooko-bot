/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tooko.core.client

import cn.hutool.core.util.ZipUtil
import kotlinx.coroutines.coroutineScope
import td.TdApi.*
import tooko.core.*
import tooko.core.client.TdBotAbsHandler.*
import tooko.core.env.*
import tooko.core.utils.*
import java.util.*

open class TdBot(val botToken: String) : TdClient(initDataDir("data/${botToken.substringBefore(":")}")), TdBotAbsHandler {

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

    override fun onDestroy() {

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

                authing = false

                onAuthorizationFailed(it)

            }

        }

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope function@{

        if (auth && userId == me.id) return@function

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

            try {

                if (persist != null) run persist@{

                    val handler = persistHandlers[persist.persistId]
                            ?: error("Invalid Persist ID #${persist.persistId}")

                    if (function == "cancel" && persist.allowCancel) {

                        sudo removePersist userId

                        handler.onPersistCancel(userId, chatId, message, persist.subId)

                        handler.onPersistRemoveOrCancel(userId, persist.subId)

                        handler.onSendCanceledMessage(userId)

                        return@function

                    }

                    if (persist.allowFuction) {

                        sudo removePersist userId

                        handler.onPersistCancel(userId, chatId, message, persist.subId)

                        handler.onPersistRemoveOrCancel(userId, persist.subId)

                        handler.onSendCanceledMessage(userId)

                        return@persist

                    }

                    handler.onPersistFunction(userId, chatId, message, persist.subId, function, param, params, originParams)

                    return@function

                }

                if ("start" == function) {

                    if (param.isNotBlank()) {

                        val data = param.split('_').toTypedArray()

                        if (data.isNotEmpty() && payloads.containsKey(data[0])) {

                            payloads[data[0]]!!.onStartPayload(userId, chatId, message, data[0], data.shift())

                            return@function

                        }

                    }

                    onLaunch(userId, chatId, message)

                    return@function

                } else if (!functions.containsKey(function)) {

                    handlers.filterIsInstance<TdBotAbsHandler>().forEach {

                        if (this@TdBot == it) return@forEach

                        it.onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                    }

                    onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                } else {

                    functions[function]!!.onFunction(userId, chatId, message, function, param, params, originParams)

                }


            } catch (ex: Reject) {

                return@predict

            }

            return@function


        }

        if (persist == null) return@function

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

        val dataId = data[0] + 128

        val subId = data[1] + 128

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

        val dataId = data[0] + 128

        val subId = data[1] + 128

        if (!callbacks.containsKey(dataId)) {

            sudo makeAnswer "Invalid Data #$id" answerTo id

            return

        }

        callbacks[dataId]!!.onNewCallbackQuery(senderUserId, chatId, messageId, id, subId, Fn.readData(data))

        finishEvent()

    }

    open suspend fun onLaunch(userId: Int, chatId: Long, message: Message) = Unit

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (!message.fromPrivate) return

        val L = userId.langFor

        sudo make L.CNF sendTo chatId

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit
    override suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int) = Unit
    override suspend fun onPersistRemove(userId: Int, subId: Int) = Unit
    override suspend fun onPersistTimeout(userId: Int, subId: Int) = Unit
    override suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int) = Unit
    override fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>) = Unit
    override fun onPersistReStore(userId: Int, subId: Int, data: List<String>) = Unit
    override suspend fun onStartPayload(userId: Int, chatId: Long, message: Message, payload: String, params: Array<String>) = Unit

}