@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Sends a callback query to a bot and returns an answer
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @chatId - Identifier of the chat with the message
 * @messageId - Identifier of the message from which the query originated
 * @payload - Query payload
 */
suspend fun TdAbsHandler.getCallbackQueryAnswer(
    chatId: Long = 0L,
    messageId: Long = 0L,
    payload: CallbackQueryPayload? = null
) = sync<CallbackQueryAnswer>(
    GetCallbackQueryAnswer(
        chatId,
        messageId,
        payload
    )
)

suspend fun TdAbsHandler.getCallbackQueryAnswerOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    payload: CallbackQueryPayload? = null
) = syncOrNull<CallbackQueryAnswer>(
    GetCallbackQueryAnswer(
        chatId,
        messageId,
        payload
    )
)

fun TdAbsHandler.getCallbackQueryAnswer(
    chatId: Long = 0L,
    messageId: Long = 0L,
    payload: CallbackQueryPayload? = null,
    block: (suspend CoroutineScope.(CallbackQueryAnswer) -> Unit)
) = send(
    GetCallbackQueryAnswer(
        chatId,
        messageId,
        payload
    ),block = block
)

/**
 * Sets the result of a callback query
 * For bots only
 *
 * @callbackQueryId - Identifier of the callback query
 * @text - Text of the answer
 * @showAlert - If true, an alert should be shown to the user instead of a toast notification
 * @url - URL to be opened
 * @cacheTime - Time during which the result of the query can be cached, in seconds
 */
suspend fun TdAbsHandler.answerCallbackQuery(
    callbackQueryId: Long = 0L,
    text: String? = null,
    showAlert: Boolean = false,
    url: String? = null,
    cacheTime: Int = 0
) = sync<Ok>(
    AnswerCallbackQuery(
        callbackQueryId,
        text,
        showAlert,
        url,
        cacheTime
    )
)

suspend fun TdAbsHandler.answerCallbackQueryOrNull(
    callbackQueryId: Long = 0L,
    text: String? = null,
    showAlert: Boolean = false,
    url: String? = null,
    cacheTime: Int = 0
) = syncOrNull<Ok>(
    AnswerCallbackQuery(
        callbackQueryId,
        text,
        showAlert,
        url,
        cacheTime
    )
)

fun TdAbsHandler.answerCallbackQuery(
    callbackQueryId: Long = 0L,
    text: String? = null,
    showAlert: Boolean = false,
    url: String? = null,
    cacheTime: Int = 0,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerCallbackQuery(
        callbackQueryId,
        text,
        showAlert,
        url,
        cacheTime
    ),block = block
)
