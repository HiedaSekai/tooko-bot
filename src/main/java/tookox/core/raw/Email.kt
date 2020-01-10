@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns a 2-step verification recovery email address that was previously set up
 * This method can be used to verify a password provided by the user
 *
 * @password - The password for the current user
 */
suspend fun TdAbsHandler.getRecoveryEmailAddress(
    password: String? = null
) = sync<RecoveryEmailAddress>(
    GetRecoveryEmailAddress(
        password
    )
)

/**
 * Returns a 2-step verification recovery email address that was previously set up
 * This method can be used to verify a password provided by the user
 *
 * @password - The password for the current user
 */
suspend fun TdAbsHandler.getRecoveryEmailAddressOrNull(
    password: String? = null
) = syncOrNull<RecoveryEmailAddress>(
    GetRecoveryEmailAddress(
        password
    )
)

/**
 * Returns a 2-step verification recovery email address that was previously set up
 * This method can be used to verify a password provided by the user
 *
 * @password - The password for the current user
 */
fun TdAbsHandler.getRecoveryEmailAddress(
    password: String? = null,
    block: (suspend CoroutineScope.(RecoveryEmailAddress) -> Unit)
) = send(
    GetRecoveryEmailAddress(
        password
    ),block = block
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 */
suspend fun TdAbsHandler.requestPasswordRecovery() = sync<EmailAddressAuthenticationCodeInfo>(
    RequestPasswordRecovery()
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 */
suspend fun TdAbsHandler.requestPasswordRecoveryOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    RequestPasswordRecovery()
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 */
fun TdAbsHandler.requestPasswordRecovery(
    block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)
) = send(
    RequestPasswordRecovery(),block = block
)

/**
 * Sends a code to verify an email address to be added to a user's Telegram Passport
 *
 * @emailAddress - Email address
 */
suspend fun TdAbsHandler.sendEmailAddressVerificationCode(
    emailAddress: String? = null
) = sync<EmailAddressAuthenticationCodeInfo>(
    SendEmailAddressVerificationCode(
        emailAddress
    )
)

/**
 * Sends a code to verify an email address to be added to a user's Telegram Passport
 *
 * @emailAddress - Email address
 */
suspend fun TdAbsHandler.sendEmailAddressVerificationCodeOrNull(
    emailAddress: String? = null
) = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    SendEmailAddressVerificationCode(
        emailAddress
    )
)

/**
 * Sends a code to verify an email address to be added to a user's Telegram Passport
 *
 * @emailAddress - Email address
 */
fun TdAbsHandler.sendEmailAddressVerificationCode(
    emailAddress: String? = null,
    block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)
) = send(
    SendEmailAddressVerificationCode(
        emailAddress
    ),block = block
)

/**
 * Re-sends the code to verify an email address to be added to a user's Telegram Passport
 */
suspend fun TdAbsHandler.resendEmailAddressVerificationCode() = sync<EmailAddressAuthenticationCodeInfo>(
    ResendEmailAddressVerificationCode()
)

/**
 * Re-sends the code to verify an email address to be added to a user's Telegram Passport
 */
suspend fun TdAbsHandler.resendEmailAddressVerificationCodeOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    ResendEmailAddressVerificationCode()
)

/**
 * Re-sends the code to verify an email address to be added to a user's Telegram Passport
 */
fun TdAbsHandler.resendEmailAddressVerificationCode(
    block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)
) = send(
    ResendEmailAddressVerificationCode(),block = block
)
