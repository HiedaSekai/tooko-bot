@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import tooko.td.TdApi.*
import tookox.core.client.*

suspend fun TdAbsHandler.getMessage(chatId: Long, messageId: Long) = sync<Message>(GetMessage(chatId, messageId))

fun TdAbsHandler.getMessage(chatId: Number, messageId: Long, block: (suspend CoroutineScope.(Message) -> Unit)) = send<Message>(GetMessage(chatId.toLong(), messageId), 1, block)