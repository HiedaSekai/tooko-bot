/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress(
    "unused"
)

package tooko.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tooko.core.client.*

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

suspend fun TdAbsHandler.setAuthenticationPhoneNumberOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<Ok>(
    SetAuthenticationPhoneNumber(
        phoneNumber,
        settings
    )
)

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

suspend fun TdAbsHandler.resendAuthenticationCodeOrNull() = syncOrNull<Ok>(
    ResendAuthenticationCode()
)

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
    otherUserIds: IntArray
) = sync<Ok>(
    RequestQrCodeAuthentication(
        otherUserIds
    )
)

suspend fun TdAbsHandler.requestQrCodeAuthenticationOrNull(
    otherUserIds: IntArray
) = syncOrNull<Ok>(
    RequestQrCodeAuthentication(
        otherUserIds
    )
)

fun TdAbsHandler.requestQrCodeAuthentication(
    otherUserIds: IntArray,
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

suspend fun TdAbsHandler.requestAuthenticationPasswordRecoveryOrNull() = syncOrNull<Ok>(
    RequestAuthenticationPasswordRecovery()
)

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

suspend fun TdAbsHandler.recoverAuthenticationPasswordOrNull(
    recoveryCode: String? = null
) = syncOrNull<Ok>(
    RecoverAuthenticationPassword(
        recoveryCode
    )
)

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

suspend fun TdAbsHandler.changePhoneNumberOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<AuthenticationCodeInfo>(
    ChangePhoneNumber(
        phoneNumber,
        settings
    )
)

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

suspend fun TdAbsHandler.resendChangePhoneNumberCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendChangePhoneNumberCode()
)

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

suspend fun TdAbsHandler.sendPhoneNumberVerificationCodeOrNull(
    phoneNumber: String? = null,
    settings: PhoneNumberAuthenticationSettings? = null
) = syncOrNull<AuthenticationCodeInfo>(
    SendPhoneNumberVerificationCode(
        phoneNumber,
        settings
    )
)

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

suspend fun TdAbsHandler.resendPhoneNumberVerificationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendPhoneNumberVerificationCode()
)

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

suspend fun TdAbsHandler.resendPhoneNumberConfirmationCodeOrNull() = syncOrNull<AuthenticationCodeInfo>(
    ResendPhoneNumberConfirmationCode()
)

fun TdAbsHandler.resendPhoneNumberConfirmationCode(
    block: (suspend CoroutineScope.(AuthenticationCodeInfo) -> Unit)
) = send(
    ResendPhoneNumberConfirmationCode(),block = block
)
