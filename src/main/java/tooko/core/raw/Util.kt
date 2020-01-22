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
 * Closes the TDLib instance
 * All databases will be flushed to disk and properly closed
 * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.close() = sync<Ok>(
    Close()
)

suspend fun TdAbsHandler.closeOrNull() = syncOrNull<Ok>(
    Close()
)

fun TdAbsHandler.close(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    Close(),block = block
)

/**
 * Closes the TDLib instance, destroying all local data without a proper logout
 * The current user session will remain in the list of all active sessions
 * All local data will be destroyed
 * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
 */
suspend fun TdAbsHandler.destroy() = sync<Ok>(
    Destroy()
)

suspend fun TdAbsHandler.destroyOrNull() = syncOrNull<Ok>(
    Destroy()
)

fun TdAbsHandler.destroy(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    Destroy(),block = block
)

/**
 * Returns t.me URLs recently visited by a newly registered user
 *
 * @referrer - Google Play referrer to identify the user
 */
suspend fun TdAbsHandler.getRecentlyVisitedTMeUrls(
    referrer: String? = null
) = sync<TMeUrls>(
    GetRecentlyVisitedTMeUrls(
        referrer
    )
)

suspend fun TdAbsHandler.getRecentlyVisitedTMeUrlsOrNull(
    referrer: String? = null
) = syncOrNull<TMeUrls>(
    GetRecentlyVisitedTMeUrls(
        referrer
    )
)

fun TdAbsHandler.getRecentlyVisitedTMeUrls(
    referrer: String? = null,
    block: (suspend CoroutineScope.(TMeUrls) -> Unit)
) = send(
    GetRecentlyVisitedTMeUrls(
        referrer
    ),block = block
)

/**
 * Succeeds after a specified amount of time has passed
 * Can be called before authorization
 * Can be called before initialization
 *
 * @seconds - Number of seconds before the function returns
 */
suspend fun TdAbsHandler.setAlarm(
    seconds: Double
) = sync<Ok>(
    SetAlarm(
        seconds
    )
)

suspend fun TdAbsHandler.setAlarmOrNull(
    seconds: Double
) = syncOrNull<Ok>(
    SetAlarm(
        seconds
    )
)

fun TdAbsHandler.setAlarm(
    seconds: Double,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetAlarm(
        seconds
    ),block = block
)
