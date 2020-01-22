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
 * Returns all entities (mentions, hashtags, cashtags, bot commands, URLs, and email addresses) contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @text - The text in which to look for entites
 */
suspend fun TdAbsHandler.getTextEntities(
    text: String? = null
) = sync<TextEntities>(
    GetTextEntities(
        text
    )
)

suspend fun TdAbsHandler.getTextEntitiesOrNull(
    text: String? = null
) = syncOrNull<TextEntities>(
    GetTextEntities(
        text
    )
)

fun TdAbsHandler.getTextEntities(
    text: String? = null,
    block: (suspend CoroutineScope.(TextEntities) -> Unit)
) = send(
    GetTextEntities(
        text
    ),block = block
)

/**
 * Parses Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @text - The text which should be parsed
 * @parseMode - Text parse mode
 */
suspend fun TdAbsHandler.parseTextEntities(
    text: String? = null,
    parseMode: TextParseMode? = null
) = sync<FormattedText>(
    ParseTextEntities(
        text,
        parseMode
    )
)

suspend fun TdAbsHandler.parseTextEntitiesOrNull(
    text: String? = null,
    parseMode: TextParseMode? = null
) = syncOrNull<FormattedText>(
    ParseTextEntities(
        text,
        parseMode
    )
)

fun TdAbsHandler.parseTextEntities(
    text: String? = null,
    parseMode: TextParseMode? = null,
    block: (suspend CoroutineScope.(FormattedText) -> Unit)
) = send(
    ParseTextEntities(
        text,
        parseMode
    ),block = block
)

/**
 * Returns the MIME type of a file, guessed by its extension
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @fileName - The name of the file or path to the file
 */
suspend fun TdAbsHandler.getFileMimeType(
    fileName: String? = null
) = sync<Text>(
    GetFileMimeType(
        fileName
    )
)

suspend fun TdAbsHandler.getFileMimeTypeOrNull(
    fileName: String? = null
) = syncOrNull<Text>(
    GetFileMimeType(
        fileName
    )
)

fun TdAbsHandler.getFileMimeType(
    fileName: String? = null,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetFileMimeType(
        fileName
    ),block = block
)

/**
 * Returns the extension of a file, guessed by its MIME type
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @mimeType - The MIME type of the file
 */
suspend fun TdAbsHandler.getFileExtension(
    mimeType: String? = null
) = sync<Text>(
    GetFileExtension(
        mimeType
    )
)

suspend fun TdAbsHandler.getFileExtensionOrNull(
    mimeType: String? = null
) = syncOrNull<Text>(
    GetFileExtension(
        mimeType
    )
)

fun TdAbsHandler.getFileExtension(
    mimeType: String? = null,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetFileExtension(
        mimeType
    ),block = block
)

/**
 * Removes potentially dangerous characters from the name of a file
 * The encoding of the file name is supposed to be UTF-8
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @fileName - File name or path to the file
 */
suspend fun TdAbsHandler.cleanFileName(
    fileName: String? = null
) = sync<Text>(
    CleanFileName(
        fileName
    )
)

suspend fun TdAbsHandler.cleanFileNameOrNull(
    fileName: String? = null
) = syncOrNull<Text>(
    CleanFileName(
        fileName
    )
)

fun TdAbsHandler.cleanFileName(
    fileName: String? = null,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    CleanFileName(
        fileName
    ),block = block
)

/**
 * Converts a JsonValue object to corresponding JSON-serialized string
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @jsonValue - The JsonValue object
 */
suspend fun TdAbsHandler.getJsonString(
    jsonValue: JsonValue? = null
) = sync<Text>(
    GetJsonString(
        jsonValue
    )
)

suspend fun TdAbsHandler.getJsonStringOrNull(
    jsonValue: JsonValue? = null
) = syncOrNull<Text>(
    GetJsonString(
        jsonValue
    )
)

fun TdAbsHandler.getJsonString(
    jsonValue: JsonValue? = null,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetJsonString(
        jsonValue
    ),block = block
)

/**
 * Returns an IETF language tag of the language preferred in the country, which should be used to fill native fields in Telegram Passport personal details
 * Returns a 404 error if unknown
 *
 * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
 */
suspend fun TdAbsHandler.getPreferredCountryLanguage(
    countryCode: String? = null
) = sync<Text>(
    GetPreferredCountryLanguage(
        countryCode
    )
)

suspend fun TdAbsHandler.getPreferredCountryLanguageOrNull(
    countryCode: String? = null
) = syncOrNull<Text>(
    GetPreferredCountryLanguage(
        countryCode
    )
)

fun TdAbsHandler.getPreferredCountryLanguage(
    countryCode: String? = null,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetPreferredCountryLanguage(
        countryCode
    ),block = block
)

/**
 * Uses current user IP to found their country
 * Returns two-letter ISO 3166-1 alpha-2 country code
 * Can be called before authorization
 */
suspend fun TdAbsHandler.getCountryCode() = sync<Text>(
    GetCountryCode()
)

suspend fun TdAbsHandler.getCountryCodeOrNull() = syncOrNull<Text>(
    GetCountryCode()
)

fun TdAbsHandler.getCountryCode(
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetCountryCode(),block = block
)

/**
 * Returns the default text for invitation messages to be used as a placeholder when the current user invites friends to Telegram
 */
suspend fun TdAbsHandler.getInviteText() = sync<Text>(
    GetInviteText()
)

suspend fun TdAbsHandler.getInviteTextOrNull() = syncOrNull<Text>(
    GetInviteText()
)

fun TdAbsHandler.getInviteText(
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetInviteText(),block = block
)

/**
 * Returns an HTTPS link, which can be used to add a proxy
 * Available only for SOCKS5 and MTProto proxies
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdAbsHandler.getProxyLink(
    proxyId: Int
) = sync<Text>(
    GetProxyLink(
        proxyId
    )
)

suspend fun TdAbsHandler.getProxyLinkOrNull(
    proxyId: Int
) = syncOrNull<Text>(
    GetProxyLink(
        proxyId
    )
)

fun TdAbsHandler.getProxyLink(
    proxyId: Int,
    block: (suspend CoroutineScope.(Text) -> Unit)
) = send(
    GetProxyLink(
        proxyId
    ),block = block
)
