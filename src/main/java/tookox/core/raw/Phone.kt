@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Shares the phone number of the current user with a mutual contact
 * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
 *
 * @userId - Identifier of the user with whom to share the phone number
 *           The user must be a mutual contact
 */
suspend fun TdAbsHandler.sharePhoneNumber(
    userId: Int = 0
) = sync<Ok>(
    SharePhoneNumber(
        userId
    )
)

/**
 * Shares the phone number of the current user with a mutual contact
 * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
 *
 * @userId - Identifier of the user with whom to share the phone number
 *           The user must be a mutual contact
 */
suspend fun TdAbsHandler.sharePhoneNumberOrNull(
    userId: Int = 0
) = syncOrNull<Ok>(
    SharePhoneNumber(
        userId
    )
)

/**
 * Shares the phone number of the current user with a mutual contact
 * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
 *
 * @userId - Identifier of the user with whom to share the phone number
 *           The user must be a mutual contact
 */
fun TdAbsHandler.sharePhoneNumber(
    userId: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SharePhoneNumber(
        userId
    ),block = block
)
