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
    request: ByteArray = byteArrayOf()
) = sync<TonLiteServerResponse>(
    SendTonLiteServerRequest(
        request
    )
)

/**
 * Sends a request to TON lite server through Telegram servers
 * Can be called before authorization
 *
 * @request - The request
 */
suspend fun TdAbsHandler.sendTonLiteServerRequestOrNull(
    request: ByteArray = byteArrayOf()
) = syncOrNull<TonLiteServerResponse>(
    SendTonLiteServerRequest(
        request
    )
)

/**
 * Sends a request to TON lite server through Telegram servers
 * Can be called before authorization
 *
 * @request - The request
 */
fun TdAbsHandler.sendTonLiteServerRequest(
    request: ByteArray = byteArrayOf(),
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

/**
 * Returns a salt to be used with locally stored password to access a local TON-based wallet
 */
suspend fun TdAbsHandler.getTonWalletPasswordSaltOrNull() = syncOrNull<TonWalletPasswordSalt>(
    GetTonWalletPasswordSalt()
)

/**
 * Returns a salt to be used with locally stored password to access a local TON-based wallet
 */
fun TdAbsHandler.getTonWalletPasswordSalt(
    block: (suspend CoroutineScope.(TonWalletPasswordSalt) -> Unit)
) = send(
    GetTonWalletPasswordSalt(),block = block
)
