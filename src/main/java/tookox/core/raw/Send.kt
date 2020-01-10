@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Sends a notification about a screenshot taken in a chat
 * Supported only in private and secret chats
 *
 * @chatId - Chat identifier
 */
suspend fun TdAbsHandler.sendChatScreenshotTakenNotification(
    chatId: Long
) = sync<Ok>(
    SendChatScreenshotTakenNotification(
        chatId
    )
)

suspend fun TdAbsHandler.sendChatScreenshotTakenNotificationOrNull(
    chatId: Long
) = syncOrNull<Ok>(
    SendChatScreenshotTakenNotification(
        chatId
    )
)

fun TdAbsHandler.sendChatScreenshotTakenNotification(
    chatId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SendChatScreenshotTakenNotification(
        chatId
    ),block = block
)

/**
 * Sends a notification about user activity in a chat
 *
 * @chatId - Chat identifier
 * @action - The action description
 */
suspend fun TdAbsHandler.sendChatAction(
    chatId: Long,
    action: ChatAction? = null
) = sync<Ok>(
    SendChatAction(
        chatId,
        action
    )
)

suspend fun TdAbsHandler.sendChatActionOrNull(
    chatId: Long,
    action: ChatAction? = null
) = syncOrNull<Ok>(
    SendChatAction(
        chatId,
        action
    )
)

fun TdAbsHandler.sendChatAction(
    chatId: Long,
    action: ChatAction? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SendChatAction(
        chatId,
        action
    ),block = block
)

/**
 * Sends a call rating
 *
 * @callId - Call identifier
 * @rating - Call rating
 * @comment - An optional user comment if the rating is less than 5
 * @problems - List of the exact types of problems with the call, specified by the user
 */
suspend fun TdAbsHandler.sendCallRating(
    callId: Int,
    rating: Int,
    comment: String? = null,
    problems: Array<CallProblem>
) = sync<Ok>(
    SendCallRating(
        callId,
        rating,
        comment,
        problems
    )
)

suspend fun TdAbsHandler.sendCallRatingOrNull(
    callId: Int,
    rating: Int,
    comment: String? = null,
    problems: Array<CallProblem>
) = syncOrNull<Ok>(
    SendCallRating(
        callId,
        rating,
        comment,
        problems
    )
)

fun TdAbsHandler.sendCallRating(
    callId: Int,
    rating: Int,
    comment: String? = null,
    problems: Array<CallProblem>,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SendCallRating(
        callId,
        rating,
        comment,
        problems
    ),block = block
)

/**
 * Sends debug information for a call
 *
 * @callId - Call identifier
 * @debugInformation - Debug information in application-specific format
 */
suspend fun TdAbsHandler.sendCallDebugInformation(
    callId: Int,
    debugInformation: String? = null
) = sync<Ok>(
    SendCallDebugInformation(
        callId,
        debugInformation
    )
)

suspend fun TdAbsHandler.sendCallDebugInformationOrNull(
    callId: Int,
    debugInformation: String? = null
) = syncOrNull<Ok>(
    SendCallDebugInformation(
        callId,
        debugInformation
    )
)

fun TdAbsHandler.sendCallDebugInformation(
    callId: Int,
    debugInformation: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SendCallDebugInformation(
        callId,
        debugInformation
    ),block = block
)

/**
 * Sends a Telegram Passport authorization form, effectively sharing data with the service
 * This method must be called after getPassportAuthorizationFormAvailableElements if some previously available elements need to be used
 *
 * @autorizationFormId - Authorization form identifier
 * @types - Types of Telegram Passport elements chosen by user to complete the authorization form
 */
suspend fun TdAbsHandler.sendPassportAuthorizationForm(
    autorizationFormId: Int,
    types: Array<PassportElementType>
) = sync<Ok>(
    SendPassportAuthorizationForm(
        autorizationFormId,
        types
    )
)

suspend fun TdAbsHandler.sendPassportAuthorizationFormOrNull(
    autorizationFormId: Int,
    types: Array<PassportElementType>
) = syncOrNull<Ok>(
    SendPassportAuthorizationForm(
        autorizationFormId,
        types
    )
)

fun TdAbsHandler.sendPassportAuthorizationForm(
    autorizationFormId: Int,
    types: Array<PassportElementType>,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SendPassportAuthorizationForm(
        autorizationFormId,
        types
    ),block = block
)
