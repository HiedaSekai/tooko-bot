@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.close() = sync<Ok>(
    Close()
)

/**
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.closeOrNull() = syncOrNull<Ok>(
    Close()
)

/**
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
fun TdAbsHandler.close(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    Close(),block = block
)

/**
 * Closes the TDLib instance, destroying all local data without a proper logout
 * The current user session will remain in the list of all active sessions
 * All local data will be destroyed
 * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.destroy() = sync<Ok>(
    Destroy()
)

/**
 * Closes the TDLib instance, destroying all local data without a proper logout
 * The current user session will remain in the list of all active sessions
 * All local data will be destroyed
 * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.destroyOrNull() = syncOrNull<Ok>(
    Destroy()
)

/**
 * Closes the TDLib instance, destroying all local data without a proper logout
 * The current user session will remain in the list of all active sessions
 * All local data will be destroyed
 * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
 */
fun TdAbsHandler.destroy(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    Destroy(),block = block
)

/**
 * Returns t.me URLs recently visited by a newly registered user
 *
 * @referrer - Google Play referrer to identify the user
 */
suspend fun TdAbsHandler.getRecentlyVisitedTMeUrls(
    referrer: String? = null
) = sync<TMeUrls>(
    GetRecentlyVisitedTMeUrls(
        referrer
    )
)

/**
 * Returns t.me URLs recently visited by a newly registered user
 *
 * @referrer - Google Play referrer to identify the user
 */
suspend fun TdAbsHandler.getRecentlyVisitedTMeUrlsOrNull(
    referrer: String? = null
) = syncOrNull<TMeUrls>(
    GetRecentlyVisitedTMeUrls(
        referrer
    )
)

/**
 * Returns t.me URLs recently visited by a newly registered user
 *
 * @referrer - Google Play referrer to identify the user
 */
fun TdAbsHandler.getRecentlyVisitedTMeUrls(
    referrer: String? = null,
    block: (suspend CoroutineScope.(TMeUrls) -> Unit)
) = send(
    GetRecentlyVisitedTMeUrls(
        referrer
    ),block = block
)

/**
 * Succeeds after a specified amount of time has passed
 * Can be called before authorization
 * Can be called before initialization
 *
 * @seconds - Number of seconds before the function returns
 */
suspend fun TdAbsHandler.setAlarm(
    seconds: Double = 0.0
) = sync<Ok>(
    SetAlarm(
        seconds
    )
)

/**
 * Succeeds after a specified amount of time has passed
 * Can be called before authorization
 * Can be called before initialization
 *
 * @seconds - Number of seconds before the function returns
 */
suspend fun TdAbsHandler.setAlarmOrNull(
    seconds: Double = 0.0
) = syncOrNull<Ok>(
    SetAlarm(
        seconds
    )
)

/**
 * Succeeds after a specified amount of time has passed
 * Can be called before authorization
 * Can be called before initialization
 *
 * @seconds - Number of seconds before the function returns
 */
fun TdAbsHandler.setAlarm(
    seconds: Double = 0.0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetAlarm(
        seconds
    ),block = block
)
