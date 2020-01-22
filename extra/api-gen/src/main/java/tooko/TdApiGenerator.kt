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

package tooko

import cn.hutool.http.HttpUtil
import tooko.builder.*
import tooko.tl.*
import tooko.tl.parser.parseTlData
import tooko.tl.parser.readTlScheme
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
            val nested = "$this/$name"
            map[nested] = buildString(block)
        }

        with("td") {

            file("TdApi.java") {

                buildApi(tlScheme)

            }

        }

        with("/core/raw") {

            functionsMap.forEach { (type, functions) ->

                file("$type.kt") {

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

            file("Static.kt") {

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