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

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Checks the database encryption key for correctness
 * Works only when the current authorization state is authorizationStateWaitEncryptionKey
 *
 * @encryptionKey - Encryption key to check or set up
 */
suspend fun TdAbsHandler.checkDatabaseEncryptionKey(
    encryptionKey: ByteArray
) = sync<Ok>(
    CheckDatabaseEncryptionKey(
        encryptionKey
    )
)

suspend fun TdAbsHandler.checkDatabaseEncryptionKeyOrNull(
    encryptionKey: ByteArray
) = syncOrNull<Ok>(
    CheckDatabaseEncryptionKey(
        encryptionKey
    )
)

fun TdAbsHandler.checkDatabaseEncryptionKey(
    encryptionKey: ByteArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckDatabaseEncryptionKey(
        encryptionKey
    ),block = block
)

/**
 * Checks the authentication code
 * Works only when the current authorization state is authorizationStateWaitCode
 *
 * @code - The verification code received via SMS, Telegram message, phone call, or flash call
 */
suspend fun TdAbsHandler.checkAuthenticationCode(
    code: String? = null
) = sync<Ok>(
    CheckAuthenticationCode(
        code
    )
)

suspend fun TdAbsHandler.checkAuthenticationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationCode(
        code
    )
)

fun TdAbsHandler.checkAuthenticationCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckAuthenticationCode(
        code
    ),block = block
)

/**
 * Checks the authentication password for correctness
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @password - The password to check
 */
suspend fun TdAbsHandler.checkAuthenticationPassword(
    password: String? = null
) = sync<Ok>(
    CheckAuthenticationPassword(
        password
    )
)

suspend fun TdAbsHandler.checkAuthenticationPasswordOrNull(
    password: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationPassword(
        password
    )
)

fun TdAbsHandler.checkAuthenticationPassword(
    password: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckAuthenticationPassword(
        password
    ),block = block
)

/**
 * Checks the authentication token of a bot
 * To log in as a bot
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
 *
 * @token - The bot token
 */
suspend fun TdAbsHandler.checkAuthenticationBotToken(
    token: String? = null
) = sync<Ok>(
    CheckAuthenticationBotToken(
        token
    )
)

suspend fun TdAbsHandler.checkAuthenticationBotTokenOrNull(
    token: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationBotToken(
        token
    )
)

fun TdAbsHandler.checkAuthenticationBotToken(
    token: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckAuthenticationBotToken(
        token
    ),block = block
)

/**
 * Checks whether a username can be set for a chat
 *
 * @chatId - Chat identifier
 *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
 * @username - Username to be checked
 */
suspend fun TdAbsHandler.checkChatUsername(
    chatId: Long,
    username: String? = null
) = sync<CheckChatUsernameResult>(
    CheckChatUsername(
        chatId,
        username
    )
)

suspend fun TdAbsHandler.checkChatUsernameOrNull(
    chatId: Long,
    username: String? = null
) = syncOrNull<CheckChatUsernameResult>(
    CheckChatUsername(
        chatId,
        username
    )
)

fun TdAbsHandler.checkChatUsername(
    chatId: Long,
    username: String? = null,
    block: (suspend CoroutineScope.(CheckChatUsernameResult) -> Unit)
) = send(
    CheckChatUsername(
        chatId,
        username
    ),block = block
)

/**
 * Checks whether the maximum number of owned public chats has been reached
 * Returns corresponding error if the limit was reached
 *
 * @type - Type of the public chats, for which to check the limit
 */
suspend fun TdAbsHandler.checkCreatedPublicChatsLimit(
    type: PublicChatType? = null
) = sync<Ok>(
    CheckCreatedPublicChatsLimit(
        type
    )
)

suspend fun TdAbsHandler.checkCreatedPublicChatsLimitOrNull(
    type: PublicChatType? = null
) = syncOrNull<Ok>(
    CheckCreatedPublicChatsLimit(
        type
    )
)

fun TdAbsHandler.checkCreatedPublicChatsLimit(
    type: PublicChatType? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckCreatedPublicChatsLimit(
        type
    ),block = block
)

/**
 * Sets the result of a pre-checkout query
 * For bots only
 *
 * @preCheckoutQueryId - Identifier of the pre-checkout query
 * @errorMessage - An error message, empty on success
 */
suspend fun TdAbsHandler.answerPreCheckoutQuery(
    preCheckoutQueryId: Long,
    errorMessage: String? = null
) = sync<Ok>(
    AnswerPreCheckoutQuery(
        preCheckoutQueryId,
        errorMessage
    )
)

suspend fun TdAbsHandler.answerPreCheckoutQueryOrNull(
    preCheckoutQueryId: Long,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    AnswerPreCheckoutQuery(
        preCheckoutQueryId,
        errorMessage
    )
)

fun TdAbsHandler.answerPreCheckoutQuery(
    preCheckoutQueryId: Long,
    errorMessage: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerPreCheckoutQuery(
        preCheckoutQueryId,
        errorMessage
    ),block = block
)

/**
 * Checks the authentication code sent to confirm a new phone number of the user
 *
 * @code - Verification code received by SMS, phone call or flash call
 */
suspend fun TdAbsHandler.checkChangePhoneNumberCode(
    code: String? = null
) = sync<Ok>(
    CheckChangePhoneNumberCode(
        code
    )
)

suspend fun TdAbsHandler.checkChangePhoneNumberCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckChangePhoneNumberCode(
        code
    )
)

fun TdAbsHandler.checkChangePhoneNumberCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckChangePhoneNumberCode(
        code
    ),block = block
)

/**
 * Checks the phone number verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkPhoneNumberVerificationCode(
    code: String? = null
) = sync<Ok>(
    CheckPhoneNumberVerificationCode(
        code
    )
)

suspend fun TdAbsHandler.checkPhoneNumberVerificationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckPhoneNumberVerificationCode(
        code
    )
)

fun TdAbsHandler.checkPhoneNumberVerificationCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckPhoneNumberVerificationCode(
        code
    ),block = block
)

/**
 * Checks the email address verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkEmailAddressVerificationCode(
    code: String? = null
) = sync<Ok>(
    CheckEmailAddressVerificationCode(
        code
    )
)

suspend fun TdAbsHandler.checkEmailAddressVerificationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckEmailAddressVerificationCode(
        code
    )
)

fun TdAbsHandler.checkEmailAddressVerificationCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckEmailAddressVerificationCode(
        code
    ),block = block
)

/**
 * Checks phone number confirmation code
 *
 * @code - The phone number confirmation code
 */
suspend fun TdAbsHandler.checkPhoneNumberConfirmationCode(
    code: String? = null
) = sync<Ok>(
    CheckPhoneNumberConfirmationCode(
        code
    )
)

suspend fun TdAbsHandler.checkPhoneNumberConfirmationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckPhoneNumberConfirmationCode(
        code
    )
)

fun TdAbsHandler.checkPhoneNumberConfirmationCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckPhoneNumberConfirmationCode(
        code
    ),block = block
)
