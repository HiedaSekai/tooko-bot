@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Sends a request to TON lite server through Telegram servers
 * Can be called before authorization
 *
 * @request - The request
 */
suspend fun TdAbsHandler.sendTonLiteServerRequest(
    request: ByteArray
) = sync<TonLiteServerResponse>(
    SendTonLiteServerRequest(
        request
    )
)

suspend fun TdAbsHandler.sendTonLiteServerRequestOrNull(
    request: ByteArray
) = syncOrNull<TonLiteServerResponse>(
    SendTonLiteServerRequest(
        request
    )
)

fun TdAbsHandler.sendTonLiteServerRequest(
    request: ByteArray,
    block: (suspend CoroutineScope.(TonLiteServerResponse) -> Unit)
) = send(
    SendTonLiteServerRequest(
        request
    ),block = block
)

/**
 * Returns a salt to be used with locally stored password to access a local TON-based wallet
 */
suspend fun TdAbsHandler.getTonWalletPasswordSalt() = sync<TonWalletPasswordSalt>(
    GetTonWalletPasswordSalt()
)

suspend fun TdAbsHandler.getTonWalletPasswordSaltOrNull() = syncOrNull<TonWalletPasswordSalt>(
    GetTonWalletPasswordSalt()
)

fun TdAbsHandler.getTonWalletPasswordSalt(
    block: (suspend CoroutineScope.(TonWalletPasswordSalt) -> Unit)
) = send(
    GetTonWalletPasswordSalt(),block = block
)
