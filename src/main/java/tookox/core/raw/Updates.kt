@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns all updates needed to restore current TDLib state, i.e
 * All actual UpdateAuthorizationState/UpdateUser/UpdateNewChat and others
 * This is especially usefull if TDLib is run in a separate process
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdAbsHandler.getCurrentState() = sync<Updates>(
    GetCurrentState()
)

suspend fun TdAbsHandler.getCurrentStateOrNull() = syncOrNull<Updates>(
    GetCurrentState()
)

fun TdAbsHandler.getCurrentState(
    block: (suspend CoroutineScope.(Updates) -> Unit)
) = send(
    GetCurrentState(),block = block
)
