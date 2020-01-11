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
 * Changes user answer to a poll
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @optionIds - 0-based identifiers of options, chosen by the user
 *              Currently user can't choose more than 1 option
 */
suspend fun TdAbsHandler.setPollAnswer(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray
) = sync<Ok>(
    SetPollAnswer(
        chatId,
        messageId,
        optionIds
    )
)

suspend fun TdAbsHandler.setPollAnswerOrNull(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray
) = syncOrNull<Ok>(
    SetPollAnswer(
        chatId,
        messageId,
        optionIds
    )
)

fun TdAbsHandler.setPollAnswer(
    chatId: Long,
    messageId: Long,
    optionIds: IntArray,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetPollAnswer(
        chatId,
        messageId,
        optionIds
    ),block = block
)

/**
 * Stops a poll
 * A poll in a message can be stopped when the message has can_be_edited flag set
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @replyMarkup - The new message reply markup
 *                For bots only
 */
suspend fun TdAbsHandler.stopPoll(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
) = sync<Ok>(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    )
)

suspend fun TdAbsHandler.stopPollOrNull(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Ok>(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    )
)

fun TdAbsHandler.stopPoll(
    chatId: Long,
    messageId: Long,
    replyMarkup: ReplyMarkup? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    ),block = block
)
