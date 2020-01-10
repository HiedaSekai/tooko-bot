@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns emoji corresponding to a sticker
 * The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
 *
 * @sticker - Sticker file identifier
 */
suspend fun TdAbsHandler.getStickerEmojis(
    sticker: InputFile? = null
) = sync<Emojis>(
    GetStickerEmojis(
        sticker
    )
)

suspend fun TdAbsHandler.getStickerEmojisOrNull(
    sticker: InputFile? = null
) = syncOrNull<Emojis>(
    GetStickerEmojis(
        sticker
    )
)

fun TdAbsHandler.getStickerEmojis(
    sticker: InputFile? = null,
    block: (suspend CoroutineScope.(Emojis) -> Unit)
) = send(
    GetStickerEmojis(
        sticker
    ),block = block
)

/**
 * Searches for emojis by keywords
 * Supported only if the file database is enabled
 *
 * @text - Text to search for
 * @exactMatch - True, if only emojis, which exactly match text needs to be returned
 * @inputLanguageCode - IETF language tag of the user's input language
 *                      Can be empty if unknown
 */
suspend fun TdAbsHandler.searchEmojis(
    text: String? = null,
    exactMatch: Boolean = false,
    inputLanguageCode: String? = null
) = sync<Emojis>(
    SearchEmojis(
        text,
        exactMatch,
        inputLanguageCode
    )
)

suspend fun TdAbsHandler.searchEmojisOrNull(
    text: String? = null,
    exactMatch: Boolean = false,
    inputLanguageCode: String? = null
) = syncOrNull<Emojis>(
    SearchEmojis(
        text,
        exactMatch,
        inputLanguageCode
    )
)

fun TdAbsHandler.searchEmojis(
    text: String? = null,
    exactMatch: Boolean = false,
    inputLanguageCode: String? = null,
    block: (suspend CoroutineScope.(Emojis) -> Unit)
) = send(
    SearchEmojis(
        text,
        exactMatch,
        inputLanguageCode
    ),block = block
)

/**
 * Returns an HTTP URL which can be used to automatically log in to the translation platform and suggest new emoji replacements
 * The URL will be valid for 30 seconds after generation
 *
 * @languageCode - Language code for which the emoji replacements will be suggested
 */
suspend fun TdAbsHandler.getEmojiSuggestionsUrl(
    languageCode: String? = null
) = sync<HttpUrl>(
    GetEmojiSuggestionsUrl(
        languageCode
    )
)

suspend fun TdAbsHandler.getEmojiSuggestionsUrlOrNull(
    languageCode: String? = null
) = syncOrNull<HttpUrl>(
    GetEmojiSuggestionsUrl(
        languageCode
    )
)

fun TdAbsHandler.getEmojiSuggestionsUrl(
    languageCode: String? = null,
    block: (suspend CoroutineScope.(HttpUrl) -> Unit)
) = send(
    GetEmojiSuggestionsUrl(
        languageCode
    ),block = block
)
