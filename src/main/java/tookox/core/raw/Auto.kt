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
 * Returns auto-download settings presets for the currently logged in user
 */
suspend fun TdAbsHandler.getAutoDownloadSettingsPresets() = sync<AutoDownloadSettingsPresets>(
    GetAutoDownloadSettingsPresets()
)

suspend fun TdAbsHandler.getAutoDownloadSettingsPresetsOrNull() = syncOrNull<AutoDownloadSettingsPresets>(
    GetAutoDownloadSettingsPresets()
)

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

suspend fun TdAbsHandler.setAutoDownloadSettingsOrNull(
    settings: AutoDownloadSettings? = null,
    type: NetworkType? = null
) = syncOrNull<Ok>(
    SetAutoDownloadSettings(
        settings,
        type
    )
)

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
