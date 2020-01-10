@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Sets the phone number of the user and sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.setAuthenticationPhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync<Ok>(
    SetAuthenticationPhoneNumber(
        phoneNumber,
        settings
    )
)

/**
 * Sets the phone number of the user and sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.setAuthenticationPhoneNumberOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<Ok>(
    SetAuthenticationPhoneNumber(
        phoneNumber,
        settings
    )
)

/**
 * Sets the phone number of the user and sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
fun TdAbsHandler.setAuthenticationPhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetAuthenticationPhoneNumber(
        phoneNumber,
        settings
    ),block = block
)

/**
 * Re-sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
 */
suspend fun TdAbsHandler.resendAuthenticationCode() = sync<Ok>(
    ResendAuthenticationCode()
)

/**
 * Re-sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
 */
suspend fun TdAbsHandler.resendAuthenticationCodeOrNull() = syncOrNull<Ok>(
    ResendAuthenticationCode()
)

/**
 * Re-sends an authentication code to the user
 * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
 */
fun TdAbsHandler.resendAuthenticationCode(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ResendAuthenticationCode(),block = block
)

/**
 * Requests QR code authentication by scanning a QR code on another logged in device
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 *
 * @otherUserIds - List of user identifiers of other users currently using the client
 */
suspend fun TdAbsHandler.requestQrCodeAuthentication(
    otherUserIds: IntArray = intArrayOf()
) = sync<Ok>(
    RequestQrCodeAuthentication(
        otherUserIds
    )
)

/**
 * Requests QR code authentication by scanning a QR code on another logged in device
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 *
 * @otherUserIds - List of user identifiers of other users currently using the client
 */
suspend fun TdAbsHandler.requestQrCodeAuthenticationOrNull(
    otherUserIds: IntArray = intArrayOf()
) = syncOrNull<Ok>(
    RequestQrCodeAuthentication(
        otherUserIds
    )
)

/**
 * Requests QR code authentication by scanning a QR code on another logged in device
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 *
 * @otherUserIds - List of user identifiers of other users currently using the client
 */
fun TdAbsHandler.requestQrCodeAuthentication(
    otherUserIds: IntArray = intArrayOf(),
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RequestQrCodeAuthentication(
        otherUserIds
    ),block = block
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 */
suspend fun TdAbsHandler.requestAuthenticationPasswordRecovery() = sync<Ok>(
    RequestAuthenticationPasswordRecovery()
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 */
suspend fun TdAbsHandler.requestAuthenticationPasswordRecoveryOrNull() = syncOrNull<Ok>(
    RequestAuthenticationPasswordRecovery()
)

/**
 * Requests to send a password recovery code to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 */
fun TdAbsHandler.requestAuthenticationPasswordRecovery(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RequestAuthenticationPasswordRecovery(),block = block
)

/**
 * Recovers the password with a password recovery code sent to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdAbsHandler.recoverAuthenticationPassword(
    recoveryCode: String? = null
) = sync<Ok>(
    RecoverAuthenticationPassword(
        recoveryCode
    )
)

/**
 * Recovers the password with a password recovery code sent to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @recoveryCode - Recovery code to check
 */
suspend fun TdAbsHandler.recoverAuthenticationPasswordOrNull(
    recoveryCode: String? = null
) = syncOrNull<Ok>(
    RecoverAuthenticationPassword(
        recoveryCode
    )
)

/**
 * Recovers the password with a password recovery code sent to an email address that was previously set up
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @recoveryCode - Recovery code to check
 */
fun TdAbsHandler.recoverAuthenticationPassword(
    recoveryCode: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RecoverAuthenticationPassword(
        recoveryCode
    ),block = block
)

/**
 * Changes the phone number of the user and sends an authentication code to the user's new phone number
 * On success, returns information about the sent code
 *
 * @phoneNumber - The new phone number of the user in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.changePhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync<AuthenticationCodeInfo>(
    ChangePhoneNumber(
        phoneNumber,
        settings
    )
)

/**
 * Changes the phone number of the user and sends an authentication code to the user's new phone number
 * On success, returns information about the sent code
 *
 * @phoneNumber - The new phone number of the user in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.changePhoneNumberOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<AuthenticationCodeInfo>(
    ChangePhoneNumber(
        phoneNumber,
        settings
    )
)

/**
 * Changes the phone number of the user and sends an authentication code to the user's new phone number
 * On success, returns information about the sent code
 *
 * @phoneNumber - The new phone number of the user in international format
 * @settings - Settings for the authentication of the user's phone number
 */
fun TdAbsHandler.changePhoneNumber(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    ChangePhoneNumber(
        phoneNumber,
        settings
    ),block = block
)

/**
 * Re-sends the authentication code sent to confirm a new phone number for the user
 * Works only if the previously received authenticationCodeInfo next_code_type was not null
 */
suspend fun TdAbsHandler.resendChangePhoneNumberCode() = sync<AuthenticationCodeInfo>(
    ResendChangePhoneNumberCode()
)

/**
 * Re-sends the authentication code sent to confirm a new phone number for the user
 * Works only if the previously received authenticationCodeInfo next_code_type was not null
 */
suspend fun TdAbsHandler.resendChangePhoneNumberCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendChangePhoneNumberCode()
)

/**
 * Re-sends the authentication code sent to confirm a new phone number for the user
 * Works only if the previously received authenticationCodeInfo next_code_type was not null
 */
fun TdAbsHandler.resendChangePhoneNumberCode(
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    ResendChangePhoneNumberCode(),block = block
)

/**
 * Sends a code to verify a phone number to be added to a user's Telegram Passport
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.sendPhoneNumberVerificationCode(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync<AuthenticationCodeInfo>(
    SendPhoneNumberVerificationCode(
        phoneNumber,
        settings
    )
)

/**
 * Sends a code to verify a phone number to be added to a user's Telegram Passport
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.sendPhoneNumberVerificationCodeOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<AuthenticationCodeInfo>(
    SendPhoneNumberVerificationCode(
        phoneNumber,
        settings
    )
)

/**
 * Sends a code to verify a phone number to be added to a user's Telegram Passport
 *
 * @phoneNumber - The phone number of the user, in international format
 * @settings - Settings for the authentication of the user's phone number
 */
fun TdAbsHandler.sendPhoneNumberVerificationCode(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    SendPhoneNumberVerificationCode(
        phoneNumber,
        settings
    ),block = block
)

/**
 * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
 */
suspend fun TdAbsHandler.resendPhoneNumberVerificationCode() = sync<AuthenticationCodeInfo>(
    ResendPhoneNumberVerificationCode()
)

/**
 * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
 */
suspend fun TdAbsHandler.resendPhoneNumberVerificationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendPhoneNumberVerificationCode()
)

/**
 * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
 */
fun TdAbsHandler.resendPhoneNumberVerificationCode(
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    ResendPhoneNumberVerificationCode(),block = block
)

/**
 * Sends phone number confirmation code
 * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
 *
 * @hash - Value of the "hash" parameter from the link
 * @phoneNumber - Value of the "phone" parameter from the link
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.sendPhoneNumberConfirmationCode(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = sync<AuthenticationCodeInfo>(
    SendPhoneNumberConfirmationCode(
        hash,
        phoneNumber,
        settings
    )
)

/**
 * Sends phone number confirmation code
 * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
 *
 * @hash - Value of the "hash" parameter from the link
 * @phoneNumber - Value of the "phone" parameter from the link
 * @settings - Settings for the authentication of the user's phone number
 */
suspend fun TdAbsHandler.sendPhoneNumberConfirmationCodeOrNull(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<AuthenticationCodeInfo>(
    SendPhoneNumberConfirmationCode(
        hash,
        phoneNumber,
        settings
    )
)

/**
 * Sends phone number confirmation code
 * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
 *
 * @hash - Value of the "hash" parameter from the link
 * @phoneNumber - Value of the "phone" parameter from the link
 * @settings - Settings for the authentication of the user's phone number
 */
fun TdAbsHandler.sendPhoneNumberConfirmationCode(
    hash: String? = null,
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null,
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    SendPhoneNumberConfirmationCode(
        hash,
        phoneNumber,
        settings
    ),block = block
)

/**
 * Resends phone number confirmation code
 */
suspend fun TdAbsHandler.resendPhoneNumberConfirmationCode() = sync<AuthenticationCodeInfo>(
    ResendPhoneNumberConfirmationCode()
)

/**
 * Resends phone number confirmation code
 */
suspend fun TdAbsHandler.resendPhoneNumberConfirmationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendPhoneNumberConfirmationCode()
)

/**
 * Resends phone number confirmation code
 */
fun TdAbsHandler.resendPhoneNumberConfirmationCode(
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    ResendPhoneNumberConfirmationCode(),block = block
)
