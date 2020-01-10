@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.CoroutineScope
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
