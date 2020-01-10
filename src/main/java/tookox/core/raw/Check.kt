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
    encryptionKey: ByteArray = byteArrayOf()
) = sync<Ok>(
    CheckDatabaseEncryptionKey(
        encryptionKey
    )
)

/**
 * Checks the database encryption key for correctness
 * Works only when the current authorization state is authorizationStateWaitEncryptionKey
 *
 * @encryptionKey - Encryption key to check or set up
 */
suspend fun TdAbsHandler.checkDatabaseEncryptionKeyOrNull(
    encryptionKey: ByteArray = byteArrayOf()
) = syncOrNull<Ok>(
    CheckDatabaseEncryptionKey(
        encryptionKey
    )
)

/**
 * Checks the database encryption key for correctness
 * Works only when the current authorization state is authorizationStateWaitEncryptionKey
 *
 * @encryptionKey - Encryption key to check or set up
 */
fun TdAbsHandler.checkDatabaseEncryptionKey(
    encryptionKey: ByteArray = byteArrayOf(),
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

/**
 * Checks the authentication code
 * Works only when the current authorization state is authorizationStateWaitCode
 *
 * @code - The verification code received via SMS, Telegram message, phone call, or flash call
 */
suspend fun TdAbsHandler.checkAuthenticationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationCode(
        code
    )
)

/**
 * Checks the authentication code
 * Works only when the current authorization state is authorizationStateWaitCode
 *
 * @code - The verification code received via SMS, Telegram message, phone call, or flash call
 */
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

/**
 * Checks the authentication password for correctness
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @password - The password to check
 */
suspend fun TdAbsHandler.checkAuthenticationPasswordOrNull(
    password: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationPassword(
        password
    )
)

/**
 * Checks the authentication password for correctness
 * Works only when the current authorization state is authorizationStateWaitPassword
 *
 * @password - The password to check
 */
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

/**
 * Checks the authentication token of a bot
 * To log in as a bot
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
 *
 * @token - The bot token
 */
suspend fun TdAbsHandler.checkAuthenticationBotTokenOrNull(
    token: String? = null
) = syncOrNull<Ok>(
    CheckAuthenticationBotToken(
        token
    )
)

/**
 * Checks the authentication token of a bot
 * To log in as a bot
 * Works only when the current authorization state is authorizationStateWaitPhoneNumber
 * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
 *
 * @token - The bot token
 */
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
    chatId: Long = 0L,
    username: String? = null
) = sync<CheckChatUsernameResult>(
    CheckChatUsername(
        chatId,
        username
    )
)

/**
 * Checks whether a username can be set for a chat
 *
 * @chatId - Chat identifier
 *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
 * @username - Username to be checked
 */
suspend fun TdAbsHandler.checkChatUsernameOrNull(
    chatId: Long = 0L,
    username: String? = null
) = syncOrNull<CheckChatUsernameResult>(
    CheckChatUsername(
        chatId,
        username
    )
)

/**
 * Checks whether a username can be set for a chat
 *
 * @chatId - Chat identifier
 *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
 * @username - Username to be checked
 */
fun TdAbsHandler.checkChatUsername(
    chatId: Long = 0L,
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

/**
 * Checks whether the maximum number of owned public chats has been reached
 * Returns corresponding error if the limit was reached
 *
 * @type - Type of the public chats, for which to check the limit
 */
suspend fun TdAbsHandler.checkCreatedPublicChatsLimitOrNull(
    type: PublicChatType? = null
) = syncOrNull<Ok>(
    CheckCreatedPublicChatsLimit(
        type
    )
)

/**
 * Checks whether the maximum number of owned public chats has been reached
 * Returns corresponding error if the limit was reached
 *
 * @type - Type of the public chats, for which to check the limit
 */
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
    preCheckoutQueryId: Long = 0L,
    errorMessage: String? = null
) = sync<Ok>(
    AnswerPreCheckoutQuery(
        preCheckoutQueryId,
        errorMessage
    )
)

/**
 * Sets the result of a pre-checkout query
 * For bots only
 *
 * @preCheckoutQueryId - Identifier of the pre-checkout query
 * @errorMessage - An error message, empty on success
 */
suspend fun TdAbsHandler.answerPreCheckoutQueryOrNull(
    preCheckoutQueryId: Long = 0L,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    AnswerPreCheckoutQuery(
        preCheckoutQueryId,
        errorMessage
    )
)

/**
 * Sets the result of a pre-checkout query
 * For bots only
 *
 * @preCheckoutQueryId - Identifier of the pre-checkout query
 * @errorMessage - An error message, empty on success
 */
fun TdAbsHandler.answerPreCheckoutQuery(
    preCheckoutQueryId: Long = 0L,
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

/**
 * Checks the authentication code sent to confirm a new phone number of the user
 *
 * @code - Verification code received by SMS, phone call or flash call
 */
suspend fun TdAbsHandler.checkChangePhoneNumberCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckChangePhoneNumberCode(
        code
    )
)

/**
 * Checks the authentication code sent to confirm a new phone number of the user
 *
 * @code - Verification code received by SMS, phone call or flash call
 */
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

/**
 * Checks the phone number verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkPhoneNumberVerificationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckPhoneNumberVerificationCode(
        code
    )
)

/**
 * Checks the phone number verification code for Telegram Passport
 *
 * @code - Verification code
 */
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

/**
 * Checks the email address verification code for Telegram Passport
 *
 * @code - Verification code
 */
suspend fun TdAbsHandler.checkEmailAddressVerificationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckEmailAddressVerificationCode(
        code
    )
)

/**
 * Checks the email address verification code for Telegram Passport
 *
 * @code - Verification code
 */
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

/**
 * Checks phone number confirmation code
 *
 * @code - The phone number confirmation code
 */
suspend fun TdAbsHandler.checkPhoneNumberConfirmationCodeOrNull(
    code: String? = null
) = syncOrNull<Ok>(
    CheckPhoneNumberConfirmationCode(
        code
    )
)

/**
 * Checks phone number confirmation code
 *
 * @code - The phone number confirmation code
 */
fun TdAbsHandler.checkPhoneNumberConfirmationCode(
    code: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CheckPhoneNumberConfirmationCode(
        code
    ),block = block
)
