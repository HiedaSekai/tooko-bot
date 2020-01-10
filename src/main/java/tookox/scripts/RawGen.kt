package tookox.scripts

import cn.hutool.http.HttpUtil
import td.TdApi
import java.io.File
import java.lang.reflect.Constructor

object RawGen {

    val scheme by lazy {

        val file = File("target/td_api.tl")

        val url = "https://raw.githubusercontent.com/tdlib/td/master/td/generate/scheme/td_api.tl"

        if (!file.isFile) {

            HttpUtil.downloadFile(url, file)

        }

        file.readText()

    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun main(args: Array<String>) {

        val target = "src/main/java/tookox/core/utils/RawTypes.kt"

        val raw = File(target).writer()

        raw.write("""
@file:Suppress("unused")
package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import tookox.core.*
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

            val tlParams = scheme
                    .substringAfter("\n${clazz.simpleName.substring(0, 1).toLowerCase()}${clazz.simpleName.substring(1)} ")
                    .substringBefore(" = ")
                    .split(' ')
                    .map { it.substringBefore(':') }

            val rawParams = constructor.parameters

            for (index in rawParams.indices) {

                var name = tlParams[index]
                val clzName = javaToKotlin(rawParams[index].type.simpleName)

                if (!params.isBlank()) {

                    params += ", "
                    call += ", "

                }

                while (name.contains('_')) {

                    val before = name.substringBefore('_')
                    val after = name.substringAfter('_')

                    name = before + after.substring(0,1).toUpperCase() + after.substring(1)

                }

                if (name == "chatId") {

                    params += "${name}: Number"

                    call += "${name}.toLong()"

                } else {

                    params += "${name}: $clzName"

                    call += name

                }

            }

            val sync = scheme
                    .substringBefore("\n${clazz.simpleName.substring(0, 1).toLowerCase()}${clazz.simpleName.substring(1)}")
                    .substringAfterLast('\n')
                    .contains("Can be called synchronously")


            var name = clazz.simpleName

            name = name.substring(0, 1).toLowerCase() + name.substring(1)

            val returnType = fmtSchemeClazz(scheme
                    .substringAfter("\n${clazz.simpleName.substring(0, 1).toLowerCase()}${clazz.simpleName.substring(1)}")
                    .substringBefore(";")
                    .substringAfter(" = "))


            var fn = "fun"

            if (constructor.parameterCount == 1) fn = "infix $fn"

            if (!sync) fn = "suspend $fn"

            var paramsWithCallback = params

            if (paramsWithCallback.isNotBlank()) paramsWithCallback += ", "

            paramsWithCallback += "block: (suspend CoroutineScope.($returnType) -> Unit)"

            if (sync) {

                raw.write("""
$fn TdAbsHandler.$name($params) = syncRaw<$returnType>(${clazz.simpleName}($call))
""")

            } else {

                raw.write("""
$fn TdAbsHandler.$name($params) = sync<$returnType>(${clazz.simpleName}($call))
$fn TdAbsHandler.${name}OrNull($params) = syncOrNull<$returnType>(${clazz.simpleName}($call))
fun TdAbsHandler.$name($paramsWithCallback) = send(${clazz.simpleName}($call), 1, block)
""")

            }

        }

        raw.flush()

        raw.close()

    }

    fun fmtSchemeClazz(name: String): String {

        return name
                .replace("int32", "Int")
                .replace("int53", "Long")
                .replace("int64", "Long")
                .replace("string", "String")
                .replace("boolFalse", "Boolean")
                .replace("boolTrue", "Bollean")
                .replace("double", "Double")
                .replace("bytes", "ByteArray")

                .replace("vector<", "Array<")

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