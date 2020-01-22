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
 * Returns the current authorization state
 * This is an offline request
 * For informational purposes only
 * Use updateAuthorizationState instead to maintain the current authorization state
 */
suspend fun TdAbsHandler.getAuthorizationState() = sync<AuthorizationState>(
    GetAuthorizationState()
)

suspend fun TdAbsHandler.getAuthorizationStateOrNull() = syncOrNull<AuthorizationState>(
    GetAuthorizationState()
)

fun TdAbsHandler.getAuthorizationState(
    block: (suspend CoroutineScope.(AuthorizationState) -> Unit)
) = send(
    GetAuthorizationState(),block = block
)
