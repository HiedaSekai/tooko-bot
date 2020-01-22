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

suspend fun TdAbsHandler.editInlineMessageReplyMarkupOrNull(
    inlineMessageId: String? = null,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Ok>(
    EditInlineMessageReplyMarkup(
        inlineMessageId,
        replyMarkup
    )
)

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
    botUserId: Int,
    chatId: Long,
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

suspend fun TdAbsHandler.getInlineQueryResultsOrNull(
    botUserId: Int,
    chatId: Long,
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

fun TdAbsHandler.getInlineQueryResults(
    botUserId: Int,
    chatId: Long,
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
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
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

suspend fun TdAbsHandler.answerInlineQueryOrNull(
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
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

fun TdAbsHandler.answerInlineQuery(
    inlineQueryId: Long,
    isPersonal: Boolean,
    results: Array<InputInlineQueryResult>,
    cacheTime: Int,
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
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = sync<Ok>(
    SetInlineGameScore(
        inlineMessageId,
        editMessage,
        userId,
        score,
        force
    )
)

suspend fun TdAbsHandler.setInlineGameScoreOrNull(
    inlineMessageId: String? = null,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean
) = syncOrNull<Ok>(
    SetInlineGameScore(
        inlineMessageId,
        editMessage,
        userId,
        score,
        force
    )
)

fun TdAbsHandler.setInlineGameScore(
    inlineMessageId: String? = null,
    editMessage: Boolean,
    userId: Int,
    score: Int,
    force: Boolean,
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
