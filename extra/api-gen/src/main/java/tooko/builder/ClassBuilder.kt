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

fun StringBuilder.buildClass(data: TlData, metadata: TlDataMetadata, paramNullable: Boolean) {

    val declaration = when (data) {
        is TlAbstract -> "public static abstract class "
        is TlClass -> "public static class "
    }

    val className = data.type.capitalize()

    buildDescription(data.descriptionsWithProperties())
    // buildAnnotations(listOf(TlAddition.JvmOverloads))
    append(declaration).append(className)
    append(" extends ").append(data.parentType.capitalize()).append(spaceToken)

    if (data is TlClass) {

        withCurlyBrackets {

            append("\n")

            if (data.metadata.properties.isNotEmpty()) {

                data.metadata.properties.joinTo(this, "\n") {

                    val type = if (it.additions.any { it is TlAddition.Nullable }) {

                        "@Nullable "

                    } else {

                        emptyToken

                    }

                    type + "public ${it.toJavaParamter()};"

                }

                append("\n")

            }

            if (data.metadata.properties.isNotEmpty()) {


                append("\npublic $className() {}\n\n")

                append("public $className(")

                data.metadata.properties.joinTo(this) {

                    val type = if (it.additions.any { it is TlAddition.Nullable }) {

                        "@Nullable "

                    } else {

                        emptyToken

                    }

                    type + it.toJavaParamter()

                }

                append(")")

                append(spaceToken)

                withCurlyBrackets {

                    data.metadata.properties.joinTo(this, "\n", "\n", "\n") {

                        val propName = it.name.snakeToCamel()

                        "this.$propName = $propName;"

                    }

                }

                append("\n")

            }

            buildConstructorField(data.crc)

            append("\n")

        }

    } else append("{}")

    append("\n")

}

fun StringBuilder.buildConstructorField(crc: Int) {

    append("@BsonIgnore @Override\n")
    append("public int getConstructor() { return $crc; }")

}

fun TlData.descriptions(): List<String> = metadata.descriptions + metadata.additions.strings()

fun TlData.descriptionsWithProperties(): List<String> =
        descriptions() + (when (metadata.properties.isEmpty()) {
            true -> emptyList()
            false -> listOf("") + metadata.properties.flatMap(TlProperty::descriptionLines)
        })
