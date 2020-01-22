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

import td.TdApi.*

/**
 * Returns all entities (mentions, hashtags, cashtags, bot commands, URLs, and email addresses) contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @text - The text in which to look for entites
 */
fun getTextEntities(
    text: String? = null
) = syncRaw(
    GetTextEntities(
        text
    )
)

/**
 * Returns all entities (mentions, hashtags, cashtags, bot commands, URLs, and email addresses) contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetTextEntities
) = tooko.core.syncRaw<TextEntities>(f)

/**
 * Parses Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @text - The text which should be parsed
 * @parseMode - Text parse mode
 */
fun parseTextEntities(
    text: String? = null,
    parseMode: TextParseMode? = null
) = syncRaw(
    ParseTextEntities(
        text,
        parseMode
    )
)

/**
 * Parses Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities contained in the text
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: ParseTextEntities
) = tooko.core.syncRaw<FormattedText>(f)

/**
 * Returns the MIME type of a file, guessed by its extension
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @fileName - The name of the file or path to the file
 */
fun getFileMimeType(
    fileName: String? = null
) = syncRaw(
    GetFileMimeType(
        fileName
    )
)

/**
 * Returns the MIME type of a file, guessed by its extension
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetFileMimeType
) = tooko.core.syncRaw<Text>(f)

/**
 * Returns the extension of a file, guessed by its MIME type
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @mimeType - The MIME type of the file
 */
fun getFileExtension(
    mimeType: String? = null
) = syncRaw(
    GetFileExtension(
        mimeType
    )
)

/**
 * Returns the extension of a file, guessed by its MIME type
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetFileExtension
) = tooko.core.syncRaw<Text>(f)

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
fun cleanFileName(
    fileName: String? = null
) = syncRaw(
    CleanFileName(
        fileName
    )
)

/**
 * Removes potentially dangerous characters from the name of a file
 * The encoding of the file name is supposed to be UTF-8
 * Returns an empty string on failure
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: CleanFileName
) = tooko.core.syncRaw<Text>(f)

/**
 * Returns a string stored in the local database from the specified localization target and language pack by its key
 * Returns a 404 error if the string is not found
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @languagePackDatabasePath - Path to the language pack database in which strings are stored
 * @localizationTarget - Localization target to which the language pack belongs
 * @languagePackId - Language pack identifier
 * @key - Language pack key of the string to be returned
 */
fun getLanguagePackString(
    languagePackDatabasePath: String? = null,
    localizationTarget: String? = null,
    languagePackId: String? = null,
    key: String? = null
) = syncRaw(
    GetLanguagePackString(
        languagePackDatabasePath,
        localizationTarget,
        languagePackId,
        key
    )
)

/**
 * Returns a string stored in the local database from the specified localization target and language pack by its key
 * Returns a 404 error if the string is not found
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetLanguagePackString
) = tooko.core.syncRaw<LanguagePackStringValue>(f)

/**
 * Converts a JSON-serialized string to corresponding JsonValue object
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @json - The JSON-serialized string
 */
fun getJsonValue(
    json: String? = null
) = syncRaw(
    GetJsonValue(
        json
    )
)

/**
 * Converts a JSON-serialized string to corresponding JsonValue object
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetJsonValue
) = tooko.core.syncRaw<JsonValue>(f)

/**
 * Converts a JsonValue object to corresponding JSON-serialized string
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @jsonValue - The JsonValue object
 */
fun getJsonString(
    jsonValue: JsonValue? = null
) = syncRaw(
    GetJsonString(
        jsonValue
    )
)

/**
 * Converts a JsonValue object to corresponding JSON-serialized string
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetJsonString
) = tooko.core.syncRaw<Text>(f)

/**
 * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @payload - JSON-encoded push notification payload
 */
fun getPushReceiverId(
    payload: String? = null
) = syncRaw(
    GetPushReceiverId(
        payload
    )
)

/**
 * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetPushReceiverId
) = tooko.core.syncRaw<PushReceiverId>(f)

/**
 * Sets new log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @logStream - New log stream
 */
fun setLogStream(
    logStream: LogStream? = null
) = syncRaw(
    SetLogStream(
        logStream
    )
)

/**
 * Sets new log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: SetLogStream
) = tooko.core.syncRaw<Ok>(f)

/**
 * Returns information about currently used log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun getLogStream() = syncRaw(
    GetLogStream()
)

/**
 * Returns information about currently used log stream for internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetLogStream
) = tooko.core.syncRaw<LogStream>(f)

/**
 * Sets the verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @newVerbosityLevel - New value of the verbosity level for logging
 *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
 */
fun setLogVerbosityLevel(
    newVerbosityLevel: Int
) = syncRaw(
    SetLogVerbosityLevel(
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: SetLogVerbosityLevel
) = tooko.core.syncRaw<Ok>(f)

/**
 * Returns current verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun getLogVerbosityLevel() = syncRaw(
    GetLogVerbosityLevel()
)

/**
 * Returns current verbosity level of the internal logging of TDLib
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetLogVerbosityLevel
) = tooko.core.syncRaw<LogVerbosityLevel>(f)

/**
 * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun getLogTags() = syncRaw(
    GetLogTags()
)

/**
 * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetLogTags
) = tooko.core.syncRaw<LogTags>(f)

/**
 * Sets the verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 * @newVerbosityLevel - New verbosity level
 */
fun setLogTagVerbosityLevel(
    tag: String? = null,
    newVerbosityLevel: Int
) = syncRaw(
    SetLogTagVerbosityLevel(
        tag,
        newVerbosityLevel
    )
)

/**
 * Sets the verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: SetLogTagVerbosityLevel
) = tooko.core.syncRaw<Ok>(f)

/**
 * Returns current verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @tag - Logging tag to change verbosity level
 */
fun getLogTagVerbosityLevel(
    tag: String? = null
) = syncRaw(
    GetLogTagVerbosityLevel(
        tag
    )
)

/**
 * Returns current verbosity level for a specified TDLib internal log tag
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: GetLogTagVerbosityLevel
) = tooko.core.syncRaw<LogVerbosityLevel>(f)

/**
 * Adds a message to TDLib internal log
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
 * @text - Text of a message to log
 */
fun addLogMessage(
    verbosityLevel: Int,
    text: String? = null
) = syncRaw(
    AddLogMessage(
        verbosityLevel,
        text
    )
)

/**
 * Adds a message to TDLib internal log
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 */
fun syncRaw(
        f: AddLogMessage
) = tooko.core.syncRaw<Ok>(f)
