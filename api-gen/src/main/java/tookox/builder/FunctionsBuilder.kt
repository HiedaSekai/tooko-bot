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

package tookox.builder

import tookox.tl.*
import java.util.*
import kotlin.Comparator

private val customMapping = mapOf(
    "SetName" to "User",
    "SetBio" to "User",
    "DeleteSavedCredentials" to "Payment",
    "GetPushReceiverId" to "Notification"
)

private val ignoredTypes = setOf(
    "T",
    "Ok",
    "Top",
    "Basic",
    "Chats",
    "Count",
    "Error",
    "Formatted",
    "Found",
    "Http",
    "Imported",
    "Messages",
    "Public",
    "Push",
    "Recovery",
    "Saved",
    "Scope",
    "Seconds",
    "Sessions",
    "Stickers",
    "Temporary",
    "Update",
    "Users",
    "Animations",
    "Backgrounds",
    "Draft",
    "Emojis",
    "Hashtags"
)

private val additionalTypes = setOf("Emoji", "Group", "Hashtag")

private val comporator = Comparator<Pair<String, Int>> { t1, t2 ->
    when (val c1 = t1.second.compareTo(t2.second)) {
        0    -> t2.first.length.compareTo(t1.first.length)
        else -> c1
    }
}

fun Iterable<String>.findByType(type: String): String? =
    map { it to type.indexOf(it) }
        .filter { it.second != -1 }
        .sortedWith(comporator)
        .firstOrNull()
        ?.first

fun List<TlData>.types(): List<String> =
    filter { it is TlObject || it is TlAbstract }
        .map { it.type.decapitalize().takeWhile(Char::isLowerCase).capitalize() }
        .distinct()

fun List<TlData>.groupFunctions(): Map<String, List<TlFunction>> {
    val types = (types() - ignoredTypes + additionalTypes).sorted().toSet()
    return filterIsInstance<TlFunction>().map { it to (it.type.capitalize() to it.returnType.capitalize()) }
        .groupBy { (_, pair) ->
            val (funcType, returnType) = pair
            val type = customMapping[funcType]
                ?: types.findByType(returnType)
                ?: types.findByType(funcType)
                ?: "Util"
            type
        }
        .mapValues { (_, list) -> list.map { it.first } }
}

fun StringBuilder.buildFunction(function: TlFunction, metadata: TlDataMetadata) {
    buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("suspend fun TdAbsHandler.").append(function.type.decapitalize())
    buildParameters(function.metadata.properties.map { it.toParameter(metadata) }, addEmptyBrackets = true)
    append(" = sync<${function.returnType.capitalize()}>")
    withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ",\n") { it.name.snakeToCamel() }
        } else append("()")
    }
    append("\n")
}

fun StringBuilder.buildNullaableFunction(function: TlFunction, metadata: TlDataMetadata) {
    //buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("suspend fun TdAbsHandler.").append(function.type.decapitalize()).append("OrNull")
    buildParameters(function.metadata.properties.map { it.toParameter(metadata) }, addEmptyBrackets = true)
    append(" = syncOrNull<${function.returnType.capitalize()}>")
    withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ",\n") { it.name.snakeToCamel() }
        } else append("()")
    }
    append("\n")
}

fun StringBuilder.buildCallbackFunction(function: TlFunction, metadata: TlDataMetadata) {
    //buildDescription(function.descriptionsWithProperties())
    buildAnnotations(function.metadata.additions)
    append("fun TdAbsHandler.").append(function.type.decapitalize())
    val params = LinkedList(function.metadata.properties.map { it.toParameter(metadata) })
    params.add("block: (suspend CoroutineScope.(${function.returnType}) -> Unit)")
    buildParameters(params, addEmptyBrackets = true)
    append(" = send")
    withRoundBrackets {
        append(function.type.capitalize())
        if (function.metadata.properties.isNotEmpty()) withRoundBrackets {
            function.metadata.properties.joinTo(this, ",\n") { it.name.snakeToCamel() }
        } else append("()")
        append(",block = block")
    }
    append("\n")
}

fun StringBuilder.buildHeader() {
    suppress("unused")
    //useExperimentalAnnotationsForFile()
    append("\n")
    buildPackage("tookox.core.raw")
    append("\n")
    buildImport("kotlinx.coroutines")
    buildImport("td.TdApi")
    buildImport("tookox.core.client")
}