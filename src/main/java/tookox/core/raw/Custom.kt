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
 * Adds a custom server language pack to the list of installed language packs in current localization target
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a language pack to be added
 *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
 */
suspend fun TdAbsHandler.addCustomServerLanguagePack(
    languagePackId: String? = null
) = sync<Ok>(
    AddCustomServerLanguagePack(
        languagePackId
    )
)

suspend fun TdAbsHandler.addCustomServerLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(
    AddCustomServerLanguagePack(
        languagePackId
    )
)

fun TdAbsHandler.addCustomServerLanguagePack(
    languagePackId: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddCustomServerLanguagePack(
        languagePackId
    ),block = block
)

/**
 * Adds or changes a custom local language pack to the current localization target
 *
 * @info - Information about the language pack
 *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
 *         Can be called before authorization
 * @strings - Strings of the new language pack
 */
suspend fun TdAbsHandler.setCustomLanguagePack(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>
) = sync<Ok>(
    SetCustomLanguagePack(
        info,
        strings
    )
)

suspend fun TdAbsHandler.setCustomLanguagePackOrNull(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>
) = syncOrNull<Ok>(
    SetCustomLanguagePack(
        info,
        strings
    )
)

fun TdAbsHandler.setCustomLanguagePack(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString>,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetCustomLanguagePack(
        info,
        strings
    ),block = block
)

/**
 * Edits information about a custom local language pack in the current localization target
 * Can be called before authorization
 *
 * @info - New information about the custom local language pack
 */
suspend fun TdAbsHandler.editCustomLanguagePackInfo(
    info: LanguagePackInfo? = null
) = sync<Ok>(
    EditCustomLanguagePackInfo(
        info
    )
)

suspend fun TdAbsHandler.editCustomLanguagePackInfoOrNull(
    info: LanguagePackInfo? = null
) = syncOrNull<Ok>(
    EditCustomLanguagePackInfo(
        info
    )
)

fun TdAbsHandler.editCustomLanguagePackInfo(
    info: LanguagePackInfo? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EditCustomLanguagePackInfo(
        info
    ),block = block
)

/**
 * Adds, edits or deletes a string in a custom local language pack
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
 * @newString - New language pack string
 */
suspend fun TdAbsHandler.setCustomLanguagePackString(
    languagePackId: String? = null,
    newString: LanguagePackString? = null
) = sync<Ok>(
    SetCustomLanguagePackString(
        languagePackId,
        newString
    )
)

suspend fun TdAbsHandler.setCustomLanguagePackStringOrNull(
    languagePackId: String? = null,
    newString: LanguagePackString? = null
) = syncOrNull<Ok>(
    SetCustomLanguagePackString(
        languagePackId,
        newString
    )
)

fun TdAbsHandler.setCustomLanguagePackString(
    languagePackId: String? = null,
    newString: LanguagePackString? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetCustomLanguagePackString(
        languagePackId,
        newString
    ),block = block
)

/**
 * Sends a custom request
 * For bots only
 *
 * @method - The method name
 * @parameters - JSON-serialized method parameters
 */
suspend fun TdAbsHandler.sendCustomRequest(
    method: String? = null,
    parameters: String? = null
) = sync<CustomRequestResult>(
    SendCustomRequest(
        method,
        parameters
    )
)

suspend fun TdAbsHandler.sendCustomRequestOrNull(
    method: String? = null,
    parameters: String? = null
) = syncOrNull<CustomRequestResult>(
    SendCustomRequest(
        method,
        parameters
    )
)

fun TdAbsHandler.sendCustomRequest(
    method: String? = null,
    parameters: String? = null,
    block: (suspend CoroutineScope.(CustomRequestResult) -> Unit)
) = send(
    SendCustomRequest(
        method,
        parameters
    ),block = block
)

/**
 * Answers a custom query
 * For bots only
 *
 * @customQueryId - Identifier of a custom query
 * @data - JSON-serialized answer to the query
 */
suspend fun TdAbsHandler.answerCustomQuery(
    customQueryId: Long,
    data: String? = null
) = sync<Ok>(
    AnswerCustomQuery(
        customQueryId,
        data
    )
)

suspend fun TdAbsHandler.answerCustomQueryOrNull(
    customQueryId: Long,
    data: String? = null
) = syncOrNull<Ok>(
    AnswerCustomQuery(
        customQueryId,
        data
    )
)

fun TdAbsHandler.answerCustomQuery(
    customQueryId: Long,
    data: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerCustomQuery(
        customQueryId,
        data
    ),block = block
)
