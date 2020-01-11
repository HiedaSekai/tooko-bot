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
 * Returns the value of an option by its name
 * (Check the list of available options on https://core.telegram.org/tdlib/options.) Can be called before authorization
 *
 * @name - The name of the option
 */
suspend fun TdAbsHandler.getOption(
    name: String? = null
) = sync<OptionValue>(
    GetOption(
        name
    )
)

suspend fun TdAbsHandler.getOptionOrNull(
    name: String? = null
) = syncOrNull<OptionValue>(
    GetOption(
        name
    )
)

fun TdAbsHandler.getOption(
    name: String? = null,
    block: (suspend CoroutineScope.(OptionValue) -> Unit)
) = send(
    GetOption(
        name
    ),block = block
)

/**
 * Sets the value of an option
 * (Check the list of available options on https://core.telegram.org/tdlib/options.) Only writable options can be set
 * Can be called before authorization
 *
 * @name - The name of the option
 * @value - The new value of the option
 */
suspend fun TdAbsHandler.setOption(
    name: String? = null,
    value: OptionValue? = null
) = sync<Ok>(
    SetOption(
        name,
        value
    )
)

suspend fun TdAbsHandler.setOptionOrNull(
    name: String? = null,
    value: OptionValue? = null
) = syncOrNull<Ok>(
    SetOption(
        name,
        value
    )
)

fun TdAbsHandler.setOption(
    name: String? = null,
    value: OptionValue? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetOption(
        name,
        value
    ),block = block
)
