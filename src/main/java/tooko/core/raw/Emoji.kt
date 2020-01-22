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
    exactMatch: Boolean,
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
    exactMatch: Boolean,
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
    exactMatch: Boolean,
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
