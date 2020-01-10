@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns the high scores for a game and some part of the high score table in the range of the specified user
 * For bots only
 *
 * @chatId - The chat that contains the message with the game
 * @messageId - Identifier of the message
 * @userId - User identifier
 */
suspend fun TdAbsHandler.getGameHighScores(
    chatId: Long,
    messageId: Long,
    userId: Int
) = sync<GameHighScores>(
    GetGameHighScores(
        chatId,
        messageId,
        userId
    )
)

suspend fun TdAbsHandler.getGameHighScoresOrNull(
    chatId: Long,
    messageId: Long,
    userId: Int
) = syncOrNull<GameHighScores>(
    GetGameHighScores(
        chatId,
        messageId,
        userId
    )
)

fun TdAbsHandler.getGameHighScores(
    chatId: Long,
    messageId: Long,
    userId: Int,
    block: (suspend CoroutineScope.(GameHighScores) -> Unit)
) = send(
    GetGameHighScores(
        chatId,
        messageId,
        userId
    ),block = block
)

/**
 * Returns game high scores and some part of the high score table in the range of the specified user
 * For bots only
 *
 * @inlineMessageId - Inline message identifier
 * @userId - User identifier
 */
suspend fun TdAbsHandler.getInlineGameHighScores(
    inlineMessageId: String? = null,
    userId: Int
) = sync<GameHighScores>(
    GetInlineGameHighScores(
        inlineMessageId,
        userId
    )
)

suspend fun TdAbsHandler.getInlineGameHighScoresOrNull(
    inlineMessageId: String? = null,
    userId: Int
) = syncOrNull<GameHighScores>(
    GetInlineGameHighScores(
        inlineMessageId,
        userId
    )
)

fun TdAbsHandler.getInlineGameHighScores(
    inlineMessageId: String? = null,
    userId: Int,
    block: (suspend CoroutineScope.(GameHighScores) -> Unit)
) = send(
    GetInlineGameHighScores(
        inlineMessageId,
        userId
    ),block = block
)
