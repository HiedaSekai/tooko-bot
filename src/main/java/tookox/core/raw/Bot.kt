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

package tookox.core.raw

import kotlinx.coroutines.CoroutineScope
import td.TdApi.Ok
import td.TdApi.SetBotUpdatesStatus
import tookox.core.client.*

/**
 * Informs the server about the number of pending bot updates if they haven't been processed for a long time
 * For bots only
 *
 * @pendingUpdateCount - The number of pending updates
 * @errorMessage - The last error message
 */
suspend fun TdAbsHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int,
    errorMessage: String? = null
) = sync<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

suspend fun TdAbsHandler.setBotUpdatesStatusOrNull(
    pendingUpdateCount: Int,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    )
)

fun TdAbsHandler.setBotUpdatesStatus(
    pendingUpdateCount: Int,
    errorMessage: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetBotUpdatesStatus(
        pendingUpdateCount,
        errorMessage
    ),block = block
)
