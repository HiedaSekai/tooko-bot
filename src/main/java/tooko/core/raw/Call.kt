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
 * Creates a new call
 *
 * @userId - Identifier of the user to be called
 * @protocol - Description of the call protocols supported by the client
 */
suspend fun TdAbsHandler.createCall(
    userId: Int,
    protocol: CallProtocol? = null
) = sync<CallId>(
    CreateCall(
        userId,
        protocol
    )
)

suspend fun TdAbsHandler.createCallOrNull(
    userId: Int,
    protocol: CallProtocol? = null
) = syncOrNull<CallId>(
    CreateCall(
        userId,
        protocol
    )
)

fun TdAbsHandler.createCall(
    userId: Int,
    protocol: CallProtocol? = null,
    block: (suspend CoroutineScope.(CallId) -> Unit)
) = send(
    CreateCall(
        userId,
        protocol
    ),block = block
)

/**
 * Accepts an incoming call
 *
 * @callId - Call identifier
 * @protocol - Description of the call protocols supported by the client
 */
suspend fun TdAbsHandler.acceptCall(
    callId: Int,
    protocol: CallProtocol? = null
) = sync<Ok>(
    AcceptCall(
        callId,
        protocol
    )
)

suspend fun TdAbsHandler.acceptCallOrNull(
    callId: Int,
    protocol: CallProtocol? = null
) = syncOrNull<Ok>(
    AcceptCall(
        callId,
        protocol
    )
)

fun TdAbsHandler.acceptCall(
    callId: Int,
    protocol: CallProtocol? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AcceptCall(
        callId,
        protocol
    ),block = block
)

/**
 * Discards a call
 *
 * @callId - Call identifier
 * @isDisconnected - True, if the user was disconnected
 * @duration - The call duration, in seconds
 * @connectionId - Identifier of the connection used during the call
 */
suspend fun TdAbsHandler.discardCall(
    callId: Int,
    isDisconnected: Boolean,
    duration: Int,
    connectionId: Long
) = sync<Ok>(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    )
)

suspend fun TdAbsHandler.discardCallOrNull(
    callId: Int,
    isDisconnected: Boolean,
    duration: Int,
    connectionId: Long
) = syncOrNull<Ok>(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    )
)

fun TdAbsHandler.discardCall(
    callId: Int,
    isDisconnected: Boolean,
    duration: Int,
    connectionId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    ),block = block
)
