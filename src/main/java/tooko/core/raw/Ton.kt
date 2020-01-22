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
