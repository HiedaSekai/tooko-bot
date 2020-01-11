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

import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import tookox.core.client.*

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
suspend fun TdAbsHandler.getLanguagePackString(
    languagePackDatabasePath: String? = null,
    localizationTarget: String? = null,
    languagePackId: String? = null,
    key: String? = null
) = sync<LanguagePackStringValue>(
    GetLanguagePackString(
        languagePackDatabasePath,
        localizationTarget,
        languagePackId,
        key
    )
)

suspend fun TdAbsHandler.getLanguagePackStringOrNull(
    languagePackDatabasePath: String? = null,
    localizationTarget: String? = null,
    languagePackId: String? = null,
    key: String? = null
) = syncOrNull<LanguagePackStringValue>(
    GetLanguagePackString(
        languagePackDatabasePath,
        localizationTarget,
        languagePackId,
        key
    )
)

fun TdAbsHandler.getLanguagePackString(
    languagePackDatabasePath: String? = null,
    localizationTarget: String? = null,
    languagePackId: String? = null,
    key: String? = null,
    block: (suspend CoroutineScope.(LanguagePackStringValue) -> Unit)
) = send(
    GetLanguagePackString(
        languagePackDatabasePath,
        localizationTarget,
        languagePackId,
        key
    ),block = block
)

/**
 * Returns information about a language pack
 * Returned language pack identifier may be different from a provided one
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier
 */
suspend fun TdAbsHandler.getLanguagePackInfo(
    languagePackId: String? = null
) = sync<LanguagePackInfo>(
    GetLanguagePackInfo(
        languagePackId
    )
)

suspend fun TdAbsHandler.getLanguagePackInfoOrNull(
    languagePackId: String? = null
) = syncOrNull<LanguagePackInfo>(
    GetLanguagePackInfo(
        languagePackId
    )
)

fun TdAbsHandler.getLanguagePackInfo(
    languagePackId: String? = null,
    block: (suspend CoroutineScope.(LanguagePackInfo) -> Unit)
) = send(
    GetLanguagePackInfo(
        languagePackId
    ),block = block
)

/**
 * Returns strings from a language pack in the current localization target by their keys
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier of the strings to be returned
 * @keys - Language pack keys of the strings to be returned
 *         Leave empty to request all available strings
 */
suspend fun TdAbsHandler.getLanguagePackStrings(
    languagePackId: String? = null,
    keys: Array<String>
) = sync<LanguagePackStrings>(
    GetLanguagePackStrings(
        languagePackId,
        keys
    )
)

suspend fun TdAbsHandler.getLanguagePackStringsOrNull(
    languagePackId: String? = null,
    keys: Array<String>
) = syncOrNull<LanguagePackStrings>(
    GetLanguagePackStrings(
        languagePackId,
        keys
    )
)

fun TdAbsHandler.getLanguagePackStrings(
    languagePackId: String? = null,
    keys: Array<String>,
    block: (suspend CoroutineScope.(LanguagePackStrings) -> Unit)
) = send(
    GetLanguagePackStrings(
        languagePackId,
        keys
    ),block = block
)

/**
 * Fetches the latest versions of all strings from a language pack in the current localization target from the server
 * This method doesn't need to be called explicitly for the current used/base language packs
 * Can be called before authorization
 *
 * @languagePackId - Language pack identifier
 */
suspend fun TdAbsHandler.synchronizeLanguagePack(
    languagePackId: String? = null
) = sync<Ok>(
    SynchronizeLanguagePack(
        languagePackId
    )
)

suspend fun TdAbsHandler.synchronizeLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(
    SynchronizeLanguagePack(
        languagePackId
    )
)

fun TdAbsHandler.synchronizeLanguagePack(
    languagePackId: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SynchronizeLanguagePack(
        languagePackId
    ),block = block
)

/**
 * Deletes all information about a language pack in the current localization target
 * The language pack which is currently in use (including base language pack) or is being synchronized can't be deleted
 * Can be called before authorization
 *
 * @languagePackId - Identifier of the language pack to delete
 */
suspend fun TdAbsHandler.deleteLanguagePack(
    languagePackId: String? = null
) = sync<Ok>(
    DeleteLanguagePack(
        languagePackId
    )
)

suspend fun TdAbsHandler.deleteLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(
    DeleteLanguagePack(
        languagePackId
    )
)

fun TdAbsHandler.deleteLanguagePack(
    languagePackId: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteLanguagePack(
        languagePackId
    ),block = block
)
