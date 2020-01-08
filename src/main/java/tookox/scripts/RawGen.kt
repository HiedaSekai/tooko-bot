package tookox.scripts

import tooko.td.TdApi
import java.io.File
import java.lang.reflect.Constructor

object RawGen {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun main(args: Array<String>) {

        /*

        需要添加Java编译参数 -parameters

         */

        val src = File("src/main/java/tooko/td/TdApi.java").readText()

        val target = "src/main/java/tookox/core/utils/RawTypes.kt"

        val raw = File(target).writer()

        raw.write("""
@file:Suppress("unused")
package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import tooko.td.TdApi.*
import tookox.core.client.*
""")

        for (clazz in TdApi::class.java.classes) {

            if (clazz == TdApi.Function::class.java || !TdApi.Function::class.java.isAssignableFrom(clazz)) {

                continue

            }

            lateinit var constructor: Constructor<out TdApi.Function>

            if (clazz.constructors.size == 1) {

                constructor = clazz.constructors[0] as Constructor<out TdApi.Function>

            } else {

                for (it in clazz.constructors) {

                    if (it.parameterCount == 0) continue

                    constructor = it as Constructor<out TdApi.Function>

                }

            }

            var params = ""
            var call = ""

            constructor.parameters.forEach {

                if (!params.isBlank()) {

                    params += ", "
                    call += ", "

                }

                if (it.name == "chatId") {

                    params += "${it.name}: Number"

                    call += "${it.name}.toLong()"

                } else {

                    params += "${it.name}: ${(javaToKotlin(it.type.simpleName))}"

                    call += it.name

                }

            }

            var name = clazz.simpleName

            name = name.substring(0, 1).toLowerCase() + name.substring(1)

            val returnType = javaToKotlin(getReturnType(src, clazz.simpleName))

            val fn = if (constructor.parameterCount == 1) {

                "suspend infix fun"

            } else {

                "suspend fun"

            }

            var paramsWithCallback = params

            if (paramsWithCallback.isNotBlank()) paramsWithCallback += ", "

            paramsWithCallback += "block: (suspend CoroutineScope.($returnType) -> Unit)"

            raw.write("""

$fn TdAbsHandler.$name($params) = sync<$returnType>(${clazz.simpleName}($call))
$fn TdAbsHandler.${name}OrNull($params) = syncOrNull<$returnType>(${clazz.simpleName}($call))
fun TdAbsHandler.$name($paramsWithCallback) = send(${clazz.simpleName}($call), 1, block)
""")

        }

        raw.flush()

        raw.close()

    }

    fun getReturnType(src: String, name: String): String {

        return src.substringBefore("public static class $name extends Function")
                .substringAfter("Returns {@link ")
                .substringBefore(' ')

    }

    fun javaToKotlin(name: String): String {

        var kname = name

                .replace("int[]", "IntArray")
                .replace("long[]", "LongArray")

                .replace("int", "Int")
                .replace("long", "Long")
                .replace("double", "Double")
                .replace("boolean", "Boolean")

                .replace("byte[]", "ByteArray")

        while (kname.endsWith("[]")) {

            kname = "Array<${kname.substring(0, kname.length - 2)}>"

        }

        return kname

    }

}