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

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns all website where the current user used Telegram to log in
 */
suspend fun TdAbsHandler.getConnectedWebsites() = sync<ConnectedWebsites>(
    GetConnectedWebsites()
)

suspend fun TdAbsHandler.getConnectedWebsitesOrNull() = syncOrNull<ConnectedWebsites>(
    GetConnectedWebsites()
)

fun TdAbsHandler.getConnectedWebsites(
    block: (suspend CoroutineScope.(ConnectedWebsites) -> Unit)
) = send(
    GetConnectedWebsites(),block = block
)
