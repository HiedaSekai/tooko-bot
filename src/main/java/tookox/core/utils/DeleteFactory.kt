@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import td.TdApi.DeleteMessages
import td.TdApi.Message
import tookox.core.client.*
import tookox.core.raw.*

infix fun TdAbsHandler.delete(message: Message) = delete(message.chatId, message.id)

fun TdAbsHandler.delete(chatId: Number, vararg messageIds: Long) = sendUnit(DeleteMessages(chatId.toLong(), messageIds, true))

suspend infix fun TdAbsHandler.syncDelete(message: Message) = syncDelete(message.chatId, message.id)

suspend fun TdAbsHandler.syncDelete(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, true))

infix fun TdAbsHandler.deleteForSelf(message: Message) = deleteForSelf(message.chatId, message.id)

fun TdAbsHandler.deleteForSelf(chatId: Number, vararg messageIds: Long) = sendUnit(DeleteMessages(chatId.toLong(), messageIds, false))

suspend infix fun TdAbsHandler.syncDeleteForSelf(message: Message) = syncDeleteForSelf(message.chatId, message.id)

suspend fun TdAbsHandler.syncDeleteForSelf(chatId: Number, vararg messageIds: Long) = syncUnit(DeleteMessages(chatId.toLong(), messageIds, false))

fun TdAbsHandler.fetchAndDelete(chatId: Number, messageId: Long) {

    getMessage(chatId.toLong(), messageId) {

        delete(chatId, messageId)

    }

}

fun TdAbsHandler.deleteDelay(timeMs: Long = 3000L): suspend CoroutineScope.(Message) -> Unit {

    return {

        delay(timeMs)

        delete(it)

    }

}