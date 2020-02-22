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
 * Returns backgrounds installed by the user
 *
 * @forDarkTheme - True, if the backgrounds needs to be ordered for dark theme
 */
suspend fun TdAbsHandler.getBackgrounds(
    forDarkTheme: Boolean
) = sync<Backgrounds>(
    GetBackgrounds(
        forDarkTheme
    )
)

suspend fun TdAbsHandler.getBackgroundsOrNull(
    forDarkTheme: Boolean
) = syncOrNull<Backgrounds>(
    GetBackgrounds(
        forDarkTheme
    )
)

fun TdAbsHandler.getBackgrounds(
    forDarkTheme: Boolean,
    block: (suspend CoroutineScope.(Backgrounds) -> Unit)
) = send(
    GetBackgrounds(
        forDarkTheme
    ),block = block
)

/**
 * Constructs a persistent HTTP URL for a background
 *
 * @name - Background name
 * @type - Background type
 */
suspend fun TdAbsHandler.getBackgroundUrl(
    name: String? = null,
    type: BackgroundType? = null
) = sync<HttpUrl>(
    GetBackgroundUrl(
        name,
        type
    )
)

suspend fun TdAbsHandler.getBackgroundUrlOrNull(
    name: String? = null,
    type: BackgroundType? = null
) = syncOrNull<HttpUrl>(
    GetBackgroundUrl(
        name,
        type
    )
)

fun TdAbsHandler.getBackgroundUrl(
    name: String? = null,
    type: BackgroundType? = null,
    block: (suspend CoroutineScope.(HttpUrl) -> Unit)
) = send(
    GetBackgroundUrl(
        name,
        type
    ),block = block
)

/**
 * Searches for a background by its name
 *
 * @name - The name of the background
 */
suspend fun TdAbsHandler.searchBackground(
    name: String? = null
) = sync<Background>(
    SearchBackground(
        name
    )
)

suspend fun TdAbsHandler.searchBackgroundOrNull(
    name: String? = null
) = syncOrNull<Background>(
    SearchBackground(
        name
    )
)

fun TdAbsHandler.searchBackground(
    name: String? = null,
    block: (suspend CoroutineScope.(Background) -> Unit)
) = send(
    SearchBackground(
        name
    ),block = block
)

/**
 * Changes the background selected by the user
 * Adds background to the list of installed backgrounds
 *
 * @background - The input background to use, null for filled backgrounds
 * @type - Background type
 *         Null for default background
 *         The method will return error 404 if type is null
 * @forDarkTheme - True, if the background is chosen for dark theme
 */
suspend fun TdAbsHandler.setBackground(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean
) = sync<Background>(
    SetBackground(
        background,
        type,
        forDarkTheme
    )
)

suspend fun TdAbsHandler.setBackgroundOrNull(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean
) = syncOrNull<Background>(
    SetBackground(
        background,
        type,
        forDarkTheme
    )
)

fun TdAbsHandler.setBackground(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean,
    block: (suspend CoroutineScope.(Background) -> Unit)
) = send(
    SetBackground(
        background,
        type,
        forDarkTheme
    ),block = block
)

/**
 * Removes background from the list of installed backgrounds
 *
 * @backgroundId - The background identifier
 */
suspend fun TdAbsHandler.removeBackground(
    backgroundId: Long
) = sync<Ok>(
    RemoveBackground(
        backgroundId
    )
)

suspend fun TdAbsHandler.removeBackgroundOrNull(
    backgroundId: Long
) = syncOrNull<Ok>(
    RemoveBackground(
        backgroundId
    )
)

fun TdAbsHandler.removeBackground(
    backgroundId: Long,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveBackground(
        backgroundId
    ),block = block
)

/**
 * Resets list of installed backgrounds to its default value
 */
suspend fun TdAbsHandler.resetBackgrounds() = sync<Ok>(
    ResetBackgrounds()
)

suspend fun TdAbsHandler.resetBackgroundsOrNull() = syncOrNull<Ok>(
    ResetBackgrounds()
)

fun TdAbsHandler.resetBackgrounds(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ResetBackgrounds(),block = block
)
