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

package tooko.tl

sealed class TlType(open val default: String) {
    abstract val kotlinType: String
    abstract val javaType: String
}

sealed class TlPrimitiveType(override val kotlinType: String,override val javaType: String, override val default: String) : TlType(default)

object TlIntType : TlPrimitiveType("Int","int", "0")
object TlLongType : TlPrimitiveType("Long","long", "0L")
object TlByteType : TlPrimitiveType("Byte","byte", "0")
object TlDoubleType : TlPrimitiveType("Double","double", "0.0")
object TlBooleanType : TlPrimitiveType("Boolean","boolean", "false")

data class TlRefType(override val kotlinType: String, override val javaType: String = kotlinType) : TlType("null")

data class TlArrayType(val type: TlType) : TlType(type.arrayDefault) {
    override val kotlinType: String = type.arrayKotlinType
    override val javaType: String = type.arrayJavaType
}

val TlType.arrayKotlinType: String get() = if (this is TlPrimitiveType) "${kotlinType}Array" else "Array<${kotlinType}>"
val TlType.arrayJavaType: String get() = "${javaType}[]"
val TlType.arrayDefault: String get() = if (this is TlPrimitiveType) "${kotlinType.decapitalize()}ArrayOf()" else "emptyArray()"

fun String.toTlType(): TlType = when (val type = capitalize()) {
    "Int32"          -> TlIntType
    "Int53", "Int64" -> TlLongType
    "Double"         -> TlDoubleType
    "Bool"           -> TlBooleanType
    "Bytes"          -> TlArrayType(TlByteType)
    else             -> when {
        type.startsWith("Vector") -> TlArrayType(type.drop(7).dropLast(1).capitalize().toTlType())
        else                      -> TlRefType(type)
    }
}
