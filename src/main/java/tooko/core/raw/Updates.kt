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
 * Returns all updates needed to restore current TDLib state, i.e
 * All actual UpdateAuthorizationState/UpdateUser/UpdateNewChat and others
 * This is especially useful if TDLib is run in a separate process
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdAbsHandler.getCurrentState() = sync<Updates>(
    GetCurrentState()
)

suspend fun TdAbsHandler.getCurrentStateOrNull() = syncOrNull<Updates>(
    GetCurrentState()
)

fun TdAbsHandler.getCurrentState(
    block: (suspend CoroutineScope.(Updates) -> Unit)
) = send(
    GetCurrentState(),block = block
)
