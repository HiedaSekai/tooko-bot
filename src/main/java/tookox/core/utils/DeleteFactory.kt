@file:Suppress("unused")

package tookox.core.utils

import tooko.td.TdApi
import tookox.core.client.TdAbsHandler

infix fun TdAbsHandler.delete(message: TdApi.Message) = delete(message.chatId, message.id)

fun TdAbsHandler.delete(chatId: Number, vararg messageIds: Long) = sendUnit(TdApi.DeleteMessages(chatId.toLong(), messageIds, true))

suspend infix fun TdAbsHandler.syncDelete(message: TdApi.Message) = syncDelete(message.chatId, message.id)

suspend fun TdAbsHandler.syncDelete(chatId: Number, vararg messageIds: Long) = syncUnit(TdApi.DeleteMessages(chatId.toLong(), messageIds, true))

infix fun TdAbsHandler.deleteForSelf(message: TdApi.Message) = deleteForSelf(message.chatId, message.id)

fun TdAbsHandler.deleteForSelf(chatId: Number, vararg messageIds: Long) = sendUnit(TdApi.DeleteMessages(chatId.toLong(), messageIds, false))

suspend infix fun TdAbsHandler.syncDeleteForSelf(message: TdApi.Message) = syncDeleteForSelf(message.chatId, message.id)

suspend fun TdAbsHandler.syncDeleteForSelf(chatId: Number, vararg messageIds: Long) = syncUnit(TdApi.DeleteMessages(chatId.toLong(), messageIds, false))