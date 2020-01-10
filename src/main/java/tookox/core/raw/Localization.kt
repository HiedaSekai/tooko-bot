@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about the current localization target
 * This is an offline request if only_local is true
 * Can be called before authorization
 *
 * @onlyLocal - If true, returns only locally available information without sending network requests
 */
suspend fun TdAbsHandler.getLocalizationTargetInfo(
    onlyLocal: Boolean = false
) = sync<LocalizationTargetInfo>(
    GetLocalizationTargetInfo(
        onlyLocal
    )
)

/**
 * Returns information about the current localization target
 * This is an offline request if only_local is true
 * Can be called before authorization
 *
 * @onlyLocal - If true, returns only locally available information without sending network requests
 */
suspend fun TdAbsHandler.getLocalizationTargetInfoOrNull(
    onlyLocal: Boolean = false
) = syncOrNull<LocalizationTargetInfo>(
    GetLocalizationTargetInfo(
        onlyLocal
    )
)

/**
 * Returns information about the current localization target
 * This is an offline request if only_local is true
 * Can be called before authorization
 *
 * @onlyLocal - If true, returns only locally available information without sending network requests
 */
fun TdAbsHandler.getLocalizationTargetInfo(
    onlyLocal: Boolean = false,
    block: (suspend CoroutineScope.(LocalizationTargetInfo) -> Unit)
) = send(
    GetLocalizationTargetInfo(
        onlyLocal
    ),block = block
)
