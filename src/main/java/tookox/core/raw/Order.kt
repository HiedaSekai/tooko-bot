@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns saved order info, if any
 */
suspend fun TdAbsHandler.getSavedOrderInfo() = sync<OrderInfo>(
    GetSavedOrderInfo()
)

/**
 * Returns saved order info, if any
 */
suspend fun TdAbsHandler.getSavedOrderInfoOrNull() = syncOrNull<OrderInfo>(
    GetSavedOrderInfo()
)

/**
 * Returns saved order info, if any
 */
fun TdAbsHandler.getSavedOrderInfo(
    block: (suspend CoroutineScope.(OrderInfo) -> Unit)
) = send(
    GetSavedOrderInfo(),block = block
)

/**
 * Deletes saved order info
 */
suspend fun TdAbsHandler.deleteSavedOrderInfo() = sync<Ok>(
    DeleteSavedOrderInfo()
)

/**
 * Deletes saved order info
 */
suspend fun TdAbsHandler.deleteSavedOrderInfoOrNull() = syncOrNull<Ok>(
    DeleteSavedOrderInfo()
)

/**
 * Deletes saved order info
 */
fun TdAbsHandler.deleteSavedOrderInfo(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteSavedOrderInfo(),block = block
)
