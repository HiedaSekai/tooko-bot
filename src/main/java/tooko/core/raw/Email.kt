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

suspend fun TdAbsHandler.getRecoveryEmailAddressOrNull(
    password: String? = null
) = syncOrNull<RecoveryEmailAddress>(
    GetRecoveryEmailAddress(
        password
    )
)

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

suspend fun TdAbsHandler.requestPasswordRecoveryOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    RequestPasswordRecovery()
)

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

suspend fun TdAbsHandler.sendEmailAddressVerificationCodeOrNull(
    emailAddress: String? = null
) = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    SendEmailAddressVerificationCode(
        emailAddress
    )
)

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

suspend fun TdAbsHandler.resendEmailAddressVerificationCodeOrNull() = syncOrNull<EmailAddressAuthenticationCodeInfo>(
    ResendEmailAddressVerificationCode()
)

fun TdAbsHandler.resendEmailAddressVerificationCode(
    block: (suspend CoroutineScope.(EmailAddressAuthenticationCodeInfo) -> Unit)
) = send(
    ResendEmailAddressVerificationCode(),block = block
)
