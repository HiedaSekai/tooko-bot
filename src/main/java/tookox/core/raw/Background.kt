@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns backgrounds installed by the user
 *
 * @forDarkTheme - True, if the backgrounds needs to be ordered for dark theme
 */
suspend fun TdAbsHandler.getBackgrounds(
    forDarkTheme: Boolean = false
) = sync<Backgrounds>(
    GetBackgrounds(
        forDarkTheme
    )
)

/**
 * Returns backgrounds installed by the user
 *
 * @forDarkTheme - True, if the backgrounds needs to be ordered for dark theme
 */
suspend fun TdAbsHandler.getBackgroundsOrNull(
    forDarkTheme: Boolean = false
) = syncOrNull<Backgrounds>(
    GetBackgrounds(
        forDarkTheme
    )
)

/**
 * Returns backgrounds installed by the user
 *
 * @forDarkTheme - True, if the backgrounds needs to be ordered for dark theme
 */
fun TdAbsHandler.getBackgrounds(
    forDarkTheme: Boolean = false,
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

/**
 * Constructs a persistent HTTP URL for a background
 *
 * @name - Background name
 * @type - Background type
 */
suspend fun TdAbsHandler.getBackgroundUrlOrNull(
    name: String? = null,
    type: BackgroundType? = null
) = syncOrNull<HttpUrl>(
    GetBackgroundUrl(
        name,
        type
    )
)

/**
 * Constructs a persistent HTTP URL for a background
 *
 * @name - Background name
 * @type - Background type
 */
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

/**
 * Searches for a background by its name
 *
 * @name - The name of the background
 */
suspend fun TdAbsHandler.searchBackgroundOrNull(
    name: String? = null
) = syncOrNull<Background>(
    SearchBackground(
        name
    )
)

/**
 * Searches for a background by its name
 *
 * @name - The name of the background
 */
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
    forDarkTheme: Boolean = false
) = sync<Background>(
    SetBackground(
        background,
        type,
        forDarkTheme
    )
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
suspend fun TdAbsHandler.setBackgroundOrNull(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean = false
) = syncOrNull<Background>(
    SetBackground(
        background,
        type,
        forDarkTheme
    )
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
fun TdAbsHandler.setBackground(
    background: InputBackground? = null,
    type: BackgroundType? = null,
    forDarkTheme: Boolean = false,
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
 * @backgroundId - The background indentifier
 */
suspend fun TdAbsHandler.removeBackground(
    backgroundId: Long = 0L
) = sync<Ok>(
    RemoveBackground(
        backgroundId
    )
)

/**
 * Removes background from the list of installed backgrounds
 *
 * @backgroundId - The background indentifier
 */
suspend fun TdAbsHandler.removeBackgroundOrNull(
    backgroundId: Long = 0L
) = syncOrNull<Ok>(
    RemoveBackground(
        backgroundId
    )
)

/**
 * Removes background from the list of installed backgrounds
 *
 * @backgroundId - The background indentifier
 */
fun TdAbsHandler.removeBackground(
    backgroundId: Long = 0L,
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

/**
 * Resets list of installed backgrounds to its default value
 */
suspend fun TdAbsHandler.resetBackgroundsOrNull() = syncOrNull<Ok>(
    ResetBackgrounds()
)

/**
 * Resets list of installed backgrounds to its default value
 */
fun TdAbsHandler.resetBackgrounds(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ResetBackgrounds(),block = block
)
