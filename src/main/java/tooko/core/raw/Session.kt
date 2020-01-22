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
 * Confirms QR code authentication on another device
 * Returns created session on success
 *
 * @link - A link from a QR code
 *         The link must be scanned by the in-app camera
 */
suspend fun TdAbsHandler.confirmQrCodeAuthentication(
    link: String? = null
) = sync<Session>(
    ConfirmQrCodeAuthentication(
        link
    )
)

suspend fun TdAbsHandler.confirmQrCodeAuthenticationOrNull(
    link: String? = null
) = syncOrNull<Session>(
    ConfirmQrCodeAuthentication(
        link
    )
)

fun TdAbsHandler.confirmQrCodeAuthentication(
    link: String? = null,
    block: (suspend CoroutineScope.(Session) -> Unit)
) = send(
    ConfirmQrCodeAuthentication(
        link
    ),block = block
)

/**
 * Returns all active sessions of the current user
 */
suspend fun TdAbsHandler.getActiveSessions() = sync<Sessions>(
    GetActiveSessions()
)

suspend fun TdAbsHandler.getActiveSessionsOrNull() = syncOrNull<Sessions>(
    GetActiveSessions()
)

fun TdAbsHandler.getActiveSessions(
    block: (suspend CoroutineScope.(Sessions) -> Unit)
) = send(
    GetActiveSessions(),block = block
)

/**
 * Terminates a session of the current user
 *
 * @sessionId - Session identifier
 */
suspend fun TdAbsHandler.terminateSession(
    sessionId: Long
) = sync<Ok>(
    TerminateSession(
        sessionId
    )
)

suspend fun TdAbsHandler.terminateSessionOrNull(
    sessionId: Long
) = syncOrNull<Ok>(
    TerminateSession(
        sessionId
    )
)

fun TdAbsHandler.terminateSession(
    sessionId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TerminateSession(
        sessionId
    ),block = block
)

/**
 * Terminates all other sessions of the current user
 */
suspend fun TdAbsHandler.terminateAllOtherSessions() = sync<Ok>(
    TerminateAllOtherSessions()
)

suspend fun TdAbsHandler.terminateAllOtherSessionsOrNull() = syncOrNull<Ok>(
    TerminateAllOtherSessions()
)

fun TdAbsHandler.terminateAllOtherSessions(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TerminateAllOtherSessions(),block = block
)
