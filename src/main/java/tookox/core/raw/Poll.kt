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
    chatId: Long = 0L,
    messageId: Long = 0L,
    optionIds: IntArray = intArrayOf()
) = sync<Ok>(
    SetPollAnswer(
        chatId,
        messageId,
        optionIds
    )
)

/**
 * Changes user answer to a poll
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @optionIds - 0-based identifiers of options, chosen by the user
 *              Currently user can't choose more than 1 option
 */
suspend fun TdAbsHandler.setPollAnswerOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    optionIds: IntArray = intArrayOf()
) = syncOrNull<Ok>(
    SetPollAnswer(
        chatId,
        messageId,
        optionIds
    )
)

/**
 * Changes user answer to a poll
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @optionIds - 0-based identifiers of options, chosen by the user
 *              Currently user can't choose more than 1 option
 */
fun TdAbsHandler.setPollAnswer(
    chatId: Long = 0L,
    messageId: Long = 0L,
    optionIds: IntArray = intArrayOf(),
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
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null
) = sync<Ok>(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    )
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
suspend fun TdAbsHandler.stopPollOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null
) = syncOrNull<Ok>(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    )
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
fun TdAbsHandler.stopPoll(
    chatId: Long = 0L,
    messageId: Long = 0L,
    replyMarkup: ReplyMarkup? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    StopPoll(
        chatId,
        messageId,
        replyMarkup
    ),block = block
)
