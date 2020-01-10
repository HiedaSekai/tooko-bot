@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a secret chat by its identifier
 * This is an offline request
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdAbsHandler.getSecretChat(
    secretChatId: Int
) = sync<SecretChat>(
    GetSecretChat(
        secretChatId
    )
)

suspend fun TdAbsHandler.getSecretChatOrNull(
    secretChatId: Int
) = syncOrNull<SecretChat>(
    GetSecretChat(
        secretChatId
    )
)

fun TdAbsHandler.getSecretChat(
    secretChatId: Int,
    block: (suspend CoroutineScope.(SecretChat) -> Unit)
) = send(
    GetSecretChat(
        secretChatId
    ),block = block
)

/**
 * Closes a secret chat, effectively transfering its state to secretChatStateClosed
 *
 * @secretChatId - Secret chat identifier
 */
suspend fun TdAbsHandler.closeSecretChat(
    secretChatId: Int
) = sync<Ok>(
    CloseSecretChat(
        secretChatId
    )
)

suspend fun TdAbsHandler.closeSecretChatOrNull(
    secretChatId: Int
) = syncOrNull<Ok>(
    CloseSecretChat(
        secretChatId
    )
)

fun TdAbsHandler.closeSecretChat(
    secretChatId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    CloseSecretChat(
        secretChatId
    ),block = block
)
