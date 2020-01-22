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

package tooko.builder

import tooko.tl.*

fun StringBuilder.buildParameters(parameters: List<String>, addEmptyBrackets: Boolean = false) {
    if (parameters.isNotEmpty()) withRoundBrackets {
        parameters.joinTo(this, ",\n")
    } else if (addEmptyBrackets) append("()")
}

fun TlProperty.toJavaParamter(): String {

    val propName = name.snakeToCamel()

    return "${type.javaType} $propName"

}

fun TlProperty.toParameter(metadata: TlDataMetadata, prefix: String = "", nullable: Boolean = false): String {
    val (withDefault, withNullables) = metadata
    val propName = name.snakeToCamel()

    val default = if (nullable)

        questionToken + nullToken

    else if (type is TlRefType) {

        if (additions.any { it is TlAddition.Nullable } || withNullables) questionToken + nullToken
        else emptyToken

    } else emptyToken

    return "${inlineAnnotations()}$prefix$propName: ${type.kotlinType}$default".replace("??", "?")
}

fun TlProperty.toField(metadata: TlDataMetadata,nullable: Boolean): String {

    val (withDefault, withNullables) = metadata

    val propName = name.snakeToCamel()

    val prefix: String

    val default = if (type !is TlPrimitiveType) {

        if (type is TlRefType && (additions.any { it is TlAddition.Nullable } || withNullables)) {

            prefix = "var"

            questionToken + nullToken

        } else {

            prefix = "lateinit var"

            emptyToken

        }

    } else {

        prefix = "var"

        questionToken + nullToken

    }

    return "${inlineAnnotations()}$prefix $propName: ${type.kotlinType}$default"

}

fun TlProperty.descriptionLines(): List<String> {
    val link = "$addressToken${name.snakeToCamel()}$spaceToken$dashToken$spaceToken"
    val spaces = (1..link.length).joinToString("") { " " }
    return listOf(link + descriptions.first()) + (descriptions.drop(1) + additions.strings()).map { "$spaces$it" }
}


fun TlProperty.inlineAnnotations(): String =
        additions
                .filterIsInstance<TlAddition.Annotation>()
                .takeIf(List<*>::isNotEmpty)
                ?.map(TlAddition.Annotation::annotation)
                ?.distinct()
                ?.sorted()
                ?.joinToString(spaceToken + addressToken, addressToken, spaceToken)
                ?: ""
