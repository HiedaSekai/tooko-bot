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

package tooko.tl.parser

import tooko.tl.*
import java.util.zip.CRC32

fun List<String>.parseTlData(): List<TlData> = parseRawTlData().map(RawTlData::toTlData)

private fun List<String>.parseRawTlData(): List<RawTlData> {
    var isFunction = false
    val data = mutableListOf<RawTlData>()
    val accumulator = mutableListOf(mutableListOf<String>())
    forEach {
        if (it == functionsToken) {
            isFunction = true
        } else {
            val lastList = accumulator.last()
            if (it.first().isLetter()) {
                val type = if (isFunction) RawTlDataType.Function else RawTlDataType.Object
                data += RawTlData(type, lastList, it)
                accumulator += mutableListOf<String>()
            } else lastList += it.substringAfter(addressToken)
        }
    }
    return data
}

private fun String.crc(): Int = CRC32().apply { update(replace("<", " ").replace(">", "").toByteArray()) }.value.toInt()

private fun RawTlData.toTlData(): TlData = when (type) {
    RawTlDataType.Object   -> when (expression.contains(spaceToken)) {
        true  -> toObject()
        false -> toAbstract()
    }
    RawTlDataType.Function -> toFunction()
}

private fun RawTlData.toAbstract(): TlAbstract {
    val map = groupLines()
    val (descriptions, additions) = map.splitByKey(descriptionToken)
    val metadata = TlMetadata(descriptions, additions, emptyList())
    return TlAbstract(expression, metadata)
}

private fun RawTlData.toObject(): TlObject {
    val type = expression.substringBefore(spaceToken).trim()
    val expressionParentType = expression.substringAfter(equalToken).trim()
    val parentType = if (expressionParentType == type.capitalize()) "Object" else expressionParentType
    val metadata = extractMetadata()
    return TlObject(type, parentType, metadata, expression.crc())
}

private fun RawTlData.toFunction(): TlFunction {
    val type = expression.substringBefore(spaceToken).trim()
    val returnType = expression.substringAfter(equalToken).trim()
    val metadata = extractMetadata()
    return TlFunction(type, returnType, metadata, expression.crc())
}

private fun RawTlData.extractMetadata(): TlMetadata {
    val map = groupLines()
    val (descriptions, additions) = map.splitByKey(descriptionToken)
    val propertiesString = expression.substringAfter(spaceToken).substringBefore(equalToken).trim()
    val properties = if (propertiesString.isBlank()) emptyList()
    else propertiesString.split(spaceToken).map { propertyDefinition ->
        val (name, type) = propertyDefinition.split(":")
        val key = if (name == descriptionToken) descriptionParamToken else name
        val (propDescriptions, propAdditions) = map.splitByKey(key)
        TlProperty(name, type.toTlType(), propDescriptions, propAdditions)
    }
    return TlMetadata(descriptions, additions, properties)
}

private fun RawTlData.groupLines() =
    list
        .groupBy { it.substringBefore(spaceToken) }
        .mapValues { (_, value) -> value.map { it.substringAfter(spaceToken).capitalize() } }

private fun Map<String, List<String>>.splitByKey(key: String) = run {
    val descriptions = this[key] ?: error("No $key in lines")
    val additions = this[key + questionToken]?.map(::TlAddition) ?: emptyList()
    val additionsWithSync = if (descriptions.any { it.toLowerCase() == "can be called synchronously" }) {
        additions + TlAddition.Sync
    } else additions
    descriptions to additionsWithSync
}
