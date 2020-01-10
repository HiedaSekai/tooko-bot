@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 */
suspend fun TdAbsHandler.getAuthorizationState() = sync<AuthorizationState>(
    GetAuthorizationState()
)

suspend fun TdAbsHandler.getAuthorizationStateOrNull() = syncOrNull<AuthorizationState>(
    GetAuthorizationState()
)

fun TdAbsHandler.getAuthorizationState(
    block: (suspend CoroutineScope.(AuthorizationState) -> Unit)
) = send(
    GetAuthorizationState(),block = block
)
