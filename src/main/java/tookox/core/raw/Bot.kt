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
    pendingUpdateCount: Int = 0,
    errorMessage: String? = null
) = sync<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

/**
 * Informs the server about the number of pending bot updates if they haven't been processed for a long time
 * For bots only
 *
 * @pendingUpdateCount - The number of pending updates
 * @errorMessage - The last error message
 */
suspend fun TdAbsHandler.setBotUpdatesStatusOrNull(
    pendingUpdateCount: Int = 0,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

/**
 * Informs the server about the number of pending bot updates if they haven't been processed for a long time
 * For bots only
 *
 * @pendingUpdateCount - The number of pending updates
 * @errorMessage - The last error message
 */
fun TdAbsHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int = 0,
    errorMessage: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    ),block = block
)
