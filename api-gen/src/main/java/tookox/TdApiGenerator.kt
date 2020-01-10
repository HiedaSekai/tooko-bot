package tookox

import cn.hutool.http.HttpUtil
import tookox.builder.buildApi
import tookox.builder.groupFunctions
import tookox.tl.TlAddition
import tookox.tl.TlFunction
import tookox.tl.TlScheme
import tookox.tl.extractMetadata
import tookox.tl.parser.parseTlData
import tookox.tl.parser.readTlScheme
import java.io.File
import kotlin.collections.set

object TdApiGenerator {

    @JvmStatic
    fun main(args: Array<String>) {

        val file = File("target/td_api.tl")

        val url = "https://raw.githubusercontent.com/tdlib/td/master/td/generate/scheme/td_api.tl"

        if (!file.isFile) {

            HttpUtil.downloadFile(url, file)

        }

        val api = generateApi(file.readBytes())

        api.forEach { (path, src) ->

            with(File("src/main/java/$path")) {

                parentFile.mkdirs()

                writeText(src)

            }

        }

    }

    fun generateApi(scheme: ByteArray): Map<String, String> {

        val tlData = scheme.readTlScheme().parseTlData()
        val metadata = tlData.extractMetadata()
        val tlScheme = TlScheme(tlData, metadata)
        val functionsMap = tlData.groupFunctions()
        val syncFunctions = tlData.filterIsInstance<TlFunction>().filter { TlAddition.Sync in it.metadata.additions }

        val map = mutableMapOf<String, String>()

        fun String.nested(path: String, block: String.() -> Unit) {
            ("$this/$path").block()
        }

        fun String.file(name: String, block: StringBuilder.() -> Unit) {
            val nested = "$this/$name.kt"
            map[nested] = buildString(block)
        }

        val srcPath = "td"

        with(srcPath) {

            file("TdApi") {
                buildApi(tlScheme)
            }

        }

        /*

        with("coroutines/$srcPath") {
            functionsMap.forEach { (type, functions) ->
                nested(type.decapitalize()) {
                    file("Raw") {
                        buildRawFunctions(type, functions)
                    }
                    file("Parameterized") {
                        buildFunctions(type, functions, metadata)
                    }
                }
            }
            nested("sync") {
                file("Raw") {
                    buildRawSyncFunctions(syncFunctions)
                }
                file("Parameterized") {
                    buildSyncFunctions(syncFunctions, metadata)
                }
            }
        }

         */

        return map
    }

}