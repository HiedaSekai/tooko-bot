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
 * Converts a JSON-serialized string to corresponding JsonValue object
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @json - The JSON-serialized string
 */
suspend fun TdAbsHandler.getJsonValue(
    json: String? = null
) = sync<JsonValue>(
    GetJsonValue(
        json
    )
)

suspend fun TdAbsHandler.getJsonValueOrNull(
    json: String? = null
) = syncOrNull<JsonValue>(
    GetJsonValue(
        json
    )
)

fun TdAbsHandler.getJsonValue(
    json: String? = null,
    block: (suspend CoroutineScope.(JsonValue) -> Unit)
) = send(
    GetJsonValue(
        json
    ),block = block
)

/**
 * Returns application config, provided by the server
 * Can be called before authorization
 */
suspend fun TdAbsHandler.getApplicationConfig() = sync<JsonValue>(
    GetApplicationConfig()
)

suspend fun TdAbsHandler.getApplicationConfigOrNull() = syncOrNull<JsonValue>(
    GetApplicationConfig()
)

fun TdAbsHandler.getApplicationConfig(
    block: (suspend CoroutineScope.(JsonValue) -> Unit)
) = send(
    GetApplicationConfig(),block = block
)
