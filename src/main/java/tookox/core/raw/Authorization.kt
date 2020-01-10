@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.CoroutineScope
import td.TdApi.AuthorizationState
import td.TdApi.GetAuthorizationState
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

/**
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 */
suspend fun TdAbsHandler.getAuthorizationStateOrNull() = syncOrNull<AuthorizationState>(
    GetAuthorizationState()
)

/**
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 */
fun TdAbsHandler.getAuthorizationState(
    block: (suspend CoroutineScope.(AuthorizationState) -> Unit)
) = send(
    GetAuthorizationState(),block = block
)
