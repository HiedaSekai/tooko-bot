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

import td.TdApi.Message
import tooko.core.env.Lang
import tooko.core.fromPrivate
import tooko.core.toLink
import tooko.core.utils.delete
import tooko.core.utils.make
import tooko.core.utils.removeKeyboard
import java.util.*

interface TdBotAbsHandler : TdAbsHandler {

    override val sudo: TdBot

    val Message.fromPrivateOrdelete: Boolean
        get() {

            return if (fromPrivate) true else {

                sudo delete this

                false

            }

        }

    fun initFunction(vararg functions: String) {

        functions.forEach { function ->

            sudo.functions.put(function, this)?.apply {

                error("function name alredy used.")

            }

        }

    }

    fun initData(dataId: Int) {

        sudo.callbacks.put(dataId, this)?.apply {

            error("data id alredy used.")

        }

    }

    fun initPersist(persistId: Int) {

        sudo.persistHandlers.put(persistId, this)?.apply {

            error("perisst id alredy used.")

        }

    }

    fun initStartPayload(payload: String) {

        sudo.payloads.put(payload, this)?.apply {

            error("payload prefix alredy used.")

        }

    }

    fun writePersist(userId: Int, peristId: Int, subId: Int = 0, allowFunction: Boolean = false, allowCancel: Boolean = true) {

        val persist = TdPerstst(userId, peristId, subId, allowCancel, allowCancel)

        sudo.persists[userId] = persist

    }

    suspend infix fun removePersist(userId: Int) {

        sudo.persists.remove(userId)?.apply {

            sudo.persistHandlers[persistId]?.also {

                it.onPersistRemove(userId, subId)
                it.onPersistRemoveOrCancel(userId, subId)

            }

        }

    }

    suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>)

    suspend fun onStartPayload(userId: Int, chatId: Long, message: Message, payload: String, params: Array<String>)

    suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>)
    suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>)

    suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int)

    suspend fun onPersistFunction(userId: Int, chatId: Long, message: Message, subId: Int, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        onPersistMessage(userId, chatId, message, subId)

    }

    suspend fun onPersistRemove(userId: Int, subId: Int)
    suspend fun onPersistTimeout(userId: Int, subId: Int)

    suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int)

    suspend fun onPersistCancel(userId: Int, chatId: Long, message: Message, subId: Int) {
    }

    suspend fun onSendCanceledMessage(userId: Int) {

        sudo make Lang.get(userId).CANCELED withMarkup removeKeyboard() sendTo userId

    }

    suspend fun onSendTimeoutedMessage(userId: Int) {

        sudo make Lang.get(userId).TIMEOUTED withMarkup removeKeyboard() sendTo userId

    }

    fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>)
    fun onPersistReStore(userId: Int, subId: Int, data: List<String>)

    class Reject : RuntimeException("Reject Function")

    fun rejectFunction(): Unit = throw Reject()

    fun String.toStartPayload(vararg payload: String) = toLink("https://t.me/${sudo.me.username}?start=${payload.joinToString("-")}")


}