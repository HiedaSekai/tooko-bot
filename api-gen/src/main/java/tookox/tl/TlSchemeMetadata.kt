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

package tookox.tl

data class TlScheme(
        val data: List<TlData>,
        val metadata: TlSchemeMetadata
)

data class TlSchemeMetadata(
    val typesWithNullableProperties: Set<String>,
    val typesWithDefaultsProperties: Set<String>
)

data class TlDataMetadata(
    val withDefault: Boolean,
    val withNullables: Boolean
)

operator fun TlSchemeMetadata.get(data: TlData): TlDataMetadata = TlDataMetadata(
        withDefault = data.type.toLowerCase() in typesWithDefaultsProperties,
        withNullables = data.type.toLowerCase() in typesWithNullableProperties
)

fun List<TlData>.extractMetadata(): TlSchemeMetadata {
    val functions = filterIsInstance<TlFunction>()
    val functionReturnTypes = functions.map { it.returnType.toLowerCase() }.toSet() + "Update"
    val typesWithNullableProperties =
        filter { it.type.toLowerCase() in functionReturnTypes || it.parentType.toLowerCase() in functionReturnTypes }
            .map { it.type.toLowerCase() }
            .toSet()


    fun getAll(init: List<TlData>, set: Set<String> = emptySet()): Set<String> {
        val typesWithDefaults = init.propertyTypes()
        val nested =
            filter { it.type.toLowerCase() !in set }
                .filter { it.type.toLowerCase() in typesWithDefaults || it.parentType.toLowerCase() in typesWithDefaults }
        return when {
            nested.isEmpty() -> set
            else             -> getAll(nested, set + typesWithDefaults + nested.map { it.type.toLowerCase() })
        }
    }

    val typesWithDefaultsProperties = getAll(functions) + functions.map(TlFunction::type).map(String::toLowerCase)

    return TlSchemeMetadata(functions.map { it.type.toLowerCase() }.toSet(), typesWithDefaultsProperties)
}


private fun List<TlData>.propertyTypes(): Set<String> =
    map(TlData::metadata)
        .flatMap(TlMetadata::properties)
        .map(TlProperty::type)
        .map { if (it is TlArrayType) it.type else it }
        .filterIsInstance<TlRefType>()
        .map(TlRefType::kotlinType)
        .filter { it != "String" }
        .map(String::toLowerCase)
        .toSet()
