package tookox

import cn.hutool.http.HttpUtil
import tookox.builder.*
import tookox.tl.*
import tookox.tl.parser.parseTlData
import tookox.tl.parser.readTlScheme
import java.io.File
import kotlin.collections.set

object TdApiGenerator {

    @JvmStatic
    fun main(args: Array<String>) {

        val scheme = "https://raw.githubusercontent.com/tdlib/td/master/td/generate/scheme/td_api.tl"

        val file = File("td_api.tl")

        if (!file.isFile) {

            HttpUtil.downloadFile(scheme,file)

        }

        val api = generateApi(file.readBytes())

        api.forEach { (path, src) ->

            with(File("src/main/java/$path")) {

                println("write $path")

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

        with("td") {

            file("TdApi") {

                buildApi(tlScheme)

            }

        }

        with("tookox/core/raw") {

            functionsMap.forEach { (type, functions) ->

                file(type) {

                    buildHeader()

                    functions.forEach {

                        append("\n")

                        buildFunction(it, metadata[it])

                        append("\n")

                        buildNullaableFunction(it, metadata[it])

                        append("\n")

                        buildCallbackFunction(it, metadata[it])

                    }

                }


            }

            file("Static") {

                buildHeader()

                syncFunctions.forEach {

                    append("\n")

                    buildSyncFunction(it,metadata[it])

                    append("\n")

                    buildRawSyncFunction(it)

                }

            }

        }

        return map
    }

}