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

@file:Suppress("unused")

package tooko.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import td.TdApi.DeleteMessages
import td.TdApi.Message
import tooko.core.client.TdAbsHandler
import tooko.core.raw.fetchMessages

infix fun TdAbsHandler.delete(message: Message) = delete(message.chatId, message.id)

fun TdAbsHandler.delete(chatId: Number, vararg messageIds: Long) = sendUnit(DeleteMessages(chatId.toLong(), messageIds, true))

suspend infix fun TdAbsHandler.syncDelete(message: Message) = syncDelete(message.chatId, message.id)

suspend fun TdAbsHandler.syncDelete(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, true))

infix fun TdAbsHandler.deleteForSelf(message: Message) = deleteForSelf(message.chatId, message.id)

fun TdAbsHandler.deleteForSelf(chatId: Number, vararg messageIds: Long) = sendUnit(DeleteMessages(chatId.toLong(), messageIds, false))

suspend infix fun TdAbsHandler.syncDeleteForSelf(message: Message) = syncDeleteForSelf(message.chatId, message.id)

suspend fun TdAbsHandler.syncDeleteForSelf(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, false))

suspend fun TdAbsHandler.fetchAndDelete(chatId: Number, messageId: Long) {

    fetchMessages(chatId, messageId)

    syncDelete(chatId, messageId)

}

suspend fun TdAbsHandler.delayDelete(message: Message, timeMs: Long = 3000L) = delayDelete(message.chatId, message.id, timeMs)

suspend fun TdAbsHandler.delayDelete(chatId: Number, messageId: Long, timeMs: Long = 3000L) {

    GlobalScope.launch {

        delay(timeMs)

        delete(chatId, messageId)

    }

}

fun TdAbsHandler.deleteDelay(timeMs: Long = 3000L): suspend CoroutineScope.(Message) -> Unit {

    return {

        delay(timeMs)

        delete(it)

    }

}