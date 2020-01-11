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
 * Sets the parameters for TDLib initialization
 * Works only when the current authorization state is authorizationStateWaitTdlibParameters
 *
 * @parameters - Parameters
 */
suspend fun TdAbsHandler.setTdlibParameters(
    parameters: TdlibParameters? = null
) = sync<Ok>(
    SetTdlibParameters(
        parameters
    )
)

suspend fun TdAbsHandler.setTdlibParametersOrNull(
    parameters: TdlibParameters? = null
) = syncOrNull<Ok>(
    SetTdlibParameters(
        parameters
    )
)

fun TdAbsHandler.setTdlibParameters(
    parameters: TdlibParameters? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetTdlibParameters(
        parameters
    ),block = block
)
