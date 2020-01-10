@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Informs the server about the number of pending bot updates if they haven't been processed for a long time
 * For bots only
 *
 * @pendingUpdateCount - The number of pending updates
 * @errorMessage - The last error message
 */
suspend fun TdAbsHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int,
    errorMessage: String? = null
) = sync<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

suspend fun TdAbsHandler.setBotUpdatesStatusOrNull(
    pendingUpdateCount: Int,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

fun TdAbsHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int,
    errorMessage: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    ),block = block
)
