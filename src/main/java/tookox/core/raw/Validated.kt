@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Validates the order information provided by a user and returns the available shipping options for a flexible invoice
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfo - The order information, provided by the user
 * @allowSave - True, if the order information can be saved
 */
suspend fun TdAbsHandler.validateOrderInfo(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean
) = sync<ValidatedOrderInfo>(
    ValidateOrderInfo(
        chatId,
        messageId,
        orderInfo,
        allowSave
    )
)

suspend fun TdAbsHandler.validateOrderInfoOrNull(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean
) = syncOrNull<ValidatedOrderInfo>(
    ValidateOrderInfo(
        chatId,
        messageId,
        orderInfo,
        allowSave
    )
)

fun TdAbsHandler.validateOrderInfo(
    chatId: Long,
    messageId: Long,
    orderInfo: OrderInfo? = null,
    allowSave: Boolean,
    block: (suspend CoroutineScope.(ValidatedOrderInfo) -> Unit)
) = send(
    ValidateOrderInfo(
        chatId,
        messageId,
        orderInfo,
        allowSave
    ),block = block
)
