@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Edits the text of an inline text or game message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
suspend fun TdAbsHandler.editInlineMessageText(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Ok>(
    EditInlineMessageText(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    )
)

/**
 * Edits the text of an inline text or game message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
suspend fun TdAbsHandler.editInlineMessageTextOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Ok>(
    EditInlineMessageText(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    )
)

/**
 * Edits the text of an inline text or game message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @inputMessageContent - New text content of the message
 *                        Should be of type InputMessageText
 */
fun TdAbsHandler.editInlineMessageText(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditInlineMessageText(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    ),block = block
)

/**
 * Edits the content of a live location in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 */
suspend fun TdAbsHandler.editInlineMessageLiveLocation(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = sync<Ok>(
    EditInlineMessageLiveLocation(
        inlineMessageId,
        replyMarkup,
        location
    )
)

/**
 * Edits the content of a live location in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 */
suspend fun TdAbsHandler.editInlineMessageLiveLocationOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null
) = syncOrNull<Ok>(
    EditInlineMessageLiveLocation(
        inlineMessageId,
        replyMarkup,
        location
    )
)

/**
 * Edits the content of a live location in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @location - New location content of the message
 *             Pass null to stop sharing the live location
 */
fun TdAbsHandler.editInlineMessageLiveLocation(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    location: Location? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditInlineMessageLiveLocation(
        inlineMessageId,
        replyMarkup,
        location
    ),block = block
)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
suspend fun TdAbsHandler.editInlineMessageMedia(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = sync<Ok>(
    EditInlineMessageMedia(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    )
)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
suspend fun TdAbsHandler.editInlineMessageMediaOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null
) = syncOrNull<Ok>(
    EditInlineMessageMedia(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    )
)

/**
 * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 *                For bots only
 * @inputMessageContent - New content of the message
 *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
 */
fun TdAbsHandler.editInlineMessageMedia(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    inputMessageContent: InputMessageContent? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditInlineMessageMedia(
        inlineMessageId,
        replyMarkup,
        inputMessageContent
    ),block = block
)

/**
 * Edits the caption of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
suspend fun TdAbsHandler.editInlineMessageCaption(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = sync<Ok>(
    EditInlineMessageCaption(
        inlineMessageId,
        replyMarkup,
        caption
    )
)

/**
 * Edits the caption of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
suspend fun TdAbsHandler.editInlineMessageCaptionOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null
) = syncOrNull<Ok>(
    EditInlineMessageCaption(
        inlineMessageId,
        replyMarkup,
        caption
    )
)

/**
 * Edits the caption of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 * @caption - New message content caption
 *            0-GetOption("message_caption_length_max") characters
 */
fun TdAbsHandler.editInlineMessageCaption(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    caption: FormattedText? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditInlineMessageCaption(
        inlineMessageId,
        replyMarkup,
        caption
    ),block = block
)

/**
 * Edits the reply markup of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 */
suspend fun TdAbsHandler.editInlineMessageReplyMarkup(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null
) = sync<Ok>(
    EditInlineMessageReplyMarkup(
        inlineMessageId,
        replyMarkup
    )
)

/**
 * Edits the reply markup of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 */
suspend fun TdAbsHandler.editInlineMessageReplyMarkupOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Ok>(
    EditInlineMessageReplyMarkup(
        inlineMessageId,
        replyMarkup
    )
)

/**
 * Edits the reply markup of an inline message sent via a bot
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @replyMarkup - The new message reply markup
 */
fun TdAbsHandler.editInlineMessageReplyMarkup(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditInlineMessageReplyMarkup(
        inlineMessageId,
        replyMarkup
    ),block = block
)

/**
 * Sends an inline query to a bot and returns its results
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @botUserId - The identifier of the target bot
 * @chatId - Identifier of the chat where the query was sent
 * @userLocation - Location of the user, only if needed
 * @query - Text of the query
 * @offset - Offset of the first entry to return
 */
suspend fun TdAbsHandler.getInlineQueryResults(
    botUserId: Int = 0,
    chatId: Long = 0L,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null
) = sync<InlineQueryResults>(
    GetInlineQueryResults(
        botUserId,
        chatId,
        userLocation,
        query,
        offset
    )
)

/**
 * Sends an inline query to a bot and returns its results
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @botUserId - The identifier of the target bot
 * @chatId - Identifier of the chat where the query was sent
 * @userLocation - Location of the user, only if needed
 * @query - Text of the query
 * @offset - Offset of the first entry to return
 */
suspend fun TdAbsHandler.getInlineQueryResultsOrNull(
    botUserId: Int = 0,
    chatId: Long = 0L,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null
) = syncOrNull<InlineQueryResults>(
    GetInlineQueryResults(
        botUserId,
        chatId,
        userLocation,
        query,
        offset
    )
)

/**
 * Sends an inline query to a bot and returns its results
 * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
 *
 * @botUserId - The identifier of the target bot
 * @chatId - Identifier of the chat where the query was sent
 * @userLocation - Location of the user, only if needed
 * @query - Text of the query
 * @offset - Offset of the first entry to return
 */
fun TdAbsHandler.getInlineQueryResults(
    botUserId: Int = 0,
    chatId: Long = 0L,
    userLocation: Location? = null,
    query: String? = null,
    offset: String? = null,
    block: (suspend CoroutineScope.(InlineQueryResults) -> Unit)
) = send(
    GetInlineQueryResults(
        botUserId,
        chatId,
        userLocation,
        query,
        offset
    ),block = block
)

/**
 * Sets the result of an inline query
 * For bots only
 *
 * @inlineQueryId - Identifier of the inline query
 * @isPersonal - True, if the result of the query can be cached for the specified user
 * @results - The results of the query
 * @cacheTime - Allowed time to cache the results of the query, in seconds
 * @nextOffset - Offset for the next inline query
 *               Pass an empty string if there are no more results
 * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
 * @switchPmParameter - The parameter for the bot start message
 */
suspend fun TdAbsHandler.answerInlineQuery(
    inlineQueryId: Long = 0L,
    isPersonal: Boolean = false,
    results: Array<InputInlineQueryResult> = emptyArray(),
    cacheTime: Int = 0,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null
) = sync<Ok>(
    AnswerInlineQuery(
        inlineQueryId,
        isPersonal,
        results,
        cacheTime,
        nextOffset,
        switchPmText,
        switchPmParameter
    )
)

/**
 * Sets the result of an inline query
 * For bots only
 *
 * @inlineQueryId - Identifier of the inline query
 * @isPersonal - True, if the result of the query can be cached for the specified user
 * @results - The results of the query
 * @cacheTime - Allowed time to cache the results of the query, in seconds
 * @nextOffset - Offset for the next inline query
 *               Pass an empty string if there are no more results
 * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
 * @switchPmParameter - The parameter for the bot start message
 */
suspend fun TdAbsHandler.answerInlineQueryOrNull(
    inlineQueryId: Long = 0L,
    isPersonal: Boolean = false,
    results: Array<InputInlineQueryResult> = emptyArray(),
    cacheTime: Int = 0,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null
) = syncOrNull<Ok>(
    AnswerInlineQuery(
        inlineQueryId,
        isPersonal,
        results,
        cacheTime,
        nextOffset,
        switchPmText,
        switchPmParameter
    )
)

/**
 * Sets the result of an inline query
 * For bots only
 *
 * @inlineQueryId - Identifier of the inline query
 * @isPersonal - True, if the result of the query can be cached for the specified user
 * @results - The results of the query
 * @cacheTime - Allowed time to cache the results of the query, in seconds
 * @nextOffset - Offset for the next inline query
 *               Pass an empty string if there are no more results
 * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
 * @switchPmParameter - The parameter for the bot start message
 */
fun TdAbsHandler.answerInlineQuery(
    inlineQueryId: Long = 0L,
    isPersonal: Boolean = false,
    results: Array<InputInlineQueryResult> = emptyArray(),
    cacheTime: Int = 0,
    nextOffset: String? = null,
    switchPmText: String? = null,
    switchPmParameter: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerInlineQuery(
        inlineQueryId,
        isPersonal,
        results,
        cacheTime,
        nextOffset,
        switchPmText,
        switchPmParameter
    ),block = block
)

/**
 * Updates the game score of the specified user in a game
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
suspend fun TdAbsHandler.setInlineGameScore(
    inlineMessageId: String? = null,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false
) = sync<Ok>(
    SetInlineGameScore(
        inlineMessageId,
        editMessage,
        userId,
        score,
        force
    )
)

/**
 * Updates the game score of the specified user in a game
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
suspend fun TdAbsHandler.setInlineGameScoreOrNull(
    inlineMessageId: String? = null,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false
) = syncOrNull<Ok>(
    SetInlineGameScore(
        inlineMessageId,
        editMessage,
        userId,
        score,
        force
    )
)

/**
 * Updates the game score of the specified user in a game
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @editMessage - True, if the message should be edited
 * @userId - User identifier
 * @score - The new score
 * @force - Pass true to update the score even if it decreases
 *          If the score is 0, the user will be deleted from the high score table
 */
fun TdAbsHandler.setInlineGameScore(
    inlineMessageId: String? = null,
    editMessage: Boolean = false,
    userId: Int = 0,
    score: Int = 0,
    force: Boolean = false,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetInlineGameScore(
        inlineMessageId,
        editMessage,
        userId,
        score,
        force
    ),block = block
)
