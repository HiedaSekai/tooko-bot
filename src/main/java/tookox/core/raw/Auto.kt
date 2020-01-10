@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns auto-download settings presets for the currently logged in user
 */
suspend fun TdAbsHandler.getAutoDownloadSettingsPresets() = sync<AutoDownloadSettingsPresets>(
    GetAutoDownloadSettingsPresets()
)

/**
 * Returns auto-download settings presets for the currently logged in user
 */
suspend fun TdAbsHandler.getAutoDownloadSettingsPresetsOrNull() = syncOrNull<AutoDownloadSettingsPresets>(
    GetAutoDownloadSettingsPresets()
)

/**
 * Returns auto-download settings presets for the currently logged in user
 */
fun TdAbsHandler.getAutoDownloadSettingsPresets(
    block: (suspend CoroutineScope.(AutoDownloadSettingsPresets) -> Unit)
) = send(
    GetAutoDownloadSettingsPresets(),block = block
)

/**
 * Sets auto-download settings
 *
 * @settings - New user auto-download settings
 * @type - Type of the network for which the new settings are applied
 */
suspend fun TdAbsHandler.setAutoDownloadSettings(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null
) = sync<Ok>(
    SetAutoDownloadSettings(
        settings,
        type
    )
)

/**
 * Sets auto-download settings
 *
 * @settings - New user auto-download settings
 * @type - Type of the network for which the new settings are applied
 */
suspend fun TdAbsHandler.setAutoDownloadSettingsOrNull(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null
) = syncOrNull<Ok>(
    SetAutoDownloadSettings(
        settings,
        type
    )
)

/**
 * Sets auto-download settings
 *
 * @settings - New user auto-download settings
 * @type - Type of the network for which the new settings are applied
 */
fun TdAbsHandler.setAutoDownloadSettings(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetAutoDownloadSettings(
        settings,
        type
    ),block = block
)
