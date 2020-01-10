@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
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

/**
 * Adds a custom server language pack to the list of installed language packs in current localization target
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a language pack to be added
 *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
 */
suspend fun TdAbsHandler.addCustomServerLanguagePackOrNull(
    languagePackId: String? = null
) = syncOrNull<Ok>(
    AddCustomServerLanguagePack(
        languagePackId
    )
)

/**
 * Adds a custom server language pack to the list of installed language packs in current localization target
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a language pack to be added
 *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
 */
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
    strings: Array<LanguagePackString> = emptyArray()
) = sync<Ok>(
    SetCustomLanguagePack(
        info,
        strings
    )
)

/**
 * Adds or changes a custom local language pack to the current localization target
 *
 * @info - Information about the language pack
 *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
 *         Can be called before authorization
 * @strings - Strings of the new language pack
 */
suspend fun TdAbsHandler.setCustomLanguagePackOrNull(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString> = emptyArray()
) = syncOrNull<Ok>(
    SetCustomLanguagePack(
        info,
        strings
    )
)

/**
 * Adds or changes a custom local language pack to the current localization target
 *
 * @info - Information about the language pack
 *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
 *         Can be called before authorization
 * @strings - Strings of the new language pack
 */
fun TdAbsHandler.setCustomLanguagePack(
    info: LanguagePackInfo? = null,
    strings: Array<LanguagePackString> = emptyArray(),
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

/**
 * Edits information about a custom local language pack in the current localization target
 * Can be called before authorization
 *
 * @info - New information about the custom local language pack
 */
suspend fun TdAbsHandler.editCustomLanguagePackInfoOrNull(
    info: LanguagePackInfo? = null
) = syncOrNull<Ok>(
    EditCustomLanguagePackInfo(
        info
    )
)

/**
 * Edits information about a custom local language pack in the current localization target
 * Can be called before authorization
 *
 * @info - New information about the custom local language pack
 */
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

/**
 * Adds, edits or deletes a string in a custom local language pack
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
 * @newString - New language pack string
 */
suspend fun TdAbsHandler.setCustomLanguagePackStringOrNull(
    languagePackId: String? = null,
    newString: LanguagePackString? = null
) = syncOrNull<Ok>(
    SetCustomLanguagePackString(
        languagePackId,
        newString
    )
)

/**
 * Adds, edits or deletes a string in a custom local language pack
 * Can be called before authorization
 *
 * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
 * @newString - New language pack string
 */
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

/**
 * Sends a custom request
 * For bots only
 *
 * @method - The method name
 * @parameters - JSON-serialized method parameters
 */
suspend fun TdAbsHandler.sendCustomRequestOrNull(
    method: String? = null,
    parameters: String? = null
) = syncOrNull<CustomRequestResult>(
    SendCustomRequest(
        method,
        parameters
    )
)

/**
 * Sends a custom request
 * For bots only
 *
 * @method - The method name
 * @parameters - JSON-serialized method parameters
 */
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
    customQueryId: Long = 0L,
    data: String? = null
) = sync<Ok>(
    AnswerCustomQuery(
        customQueryId,
        data
    )
)

/**
 * Answers a custom query
 * For bots only
 *
 * @customQueryId - Identifier of a custom query
 * @data - JSON-serialized answer to the query
 */
suspend fun TdAbsHandler.answerCustomQueryOrNull(
    customQueryId: Long = 0L,
    data: String? = null
) = syncOrNull<Ok>(
    AnswerCustomQuery(
        customQueryId,
        data
    )
)

/**
 * Answers a custom query
 * For bots only
 *
 * @customQueryId - Identifier of a custom query
 * @data - JSON-serialized answer to the query
 */
fun TdAbsHandler.answerCustomQuery(
    customQueryId: Long = 0L,
    data: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerCustomQuery(
        customQueryId,
        data
    ),block = block
)
