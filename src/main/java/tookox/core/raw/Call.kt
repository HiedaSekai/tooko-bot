@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Creates a new call
 *
 * @userId - Identifier of the user to be called
 * @protocol - Description of the call protocols supported by the client
 */
suspend fun TdAbsHandler.createCall(
    userId: Int = 0,
    protocol: CallProtocol? = null
) = sync<CallId>(
    CreateCall(
        userId,
        protocol
    )
)

suspend fun TdAbsHandler.createCallOrNull(
    userId: Int = 0,
    protocol: CallProtocol? = null
) = syncOrNull<CallId>(
    CreateCall(
        userId,
        protocol
    )
)

fun TdAbsHandler.createCall(
    userId: Int = 0,
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
    callId: Int = 0,
    protocol: CallProtocol? = null
) = sync<Ok>(
    AcceptCall(
        callId,
        protocol
    )
)

suspend fun TdAbsHandler.acceptCallOrNull(
    callId: Int = 0,
    protocol: CallProtocol? = null
) = syncOrNull<Ok>(
    AcceptCall(
        callId,
        protocol
    )
)

fun TdAbsHandler.acceptCall(
    callId: Int = 0,
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
    callId: Int = 0,
    isDisconnected: Boolean = false,
    duration: Int = 0,
    connectionId: Long = 0L
) = sync<Ok>(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    )
)

suspend fun TdAbsHandler.discardCallOrNull(
    callId: Int = 0,
    isDisconnected: Boolean = false,
    duration: Int = 0,
    connectionId: Long = 0L
) = syncOrNull<Ok>(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    )
)

fun TdAbsHandler.discardCall(
    callId: Int = 0,
    isDisconnected: Boolean = false,
    duration: Int = 0,
    connectionId: Long = 0L,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DiscardCall(
        callId,
        isDisconnected,
        duration,
        connectionId
    ),block = block
)
