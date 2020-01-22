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

package tooko.lottie

import cn.hutool.core.img.ImgUtil
import cn.hutool.core.io.IoUtil
import cn.hutool.core.io.LineHandler
import cn.hutool.core.lang.UUID
import cn.hutool.core.util.CharsetUtil
import cn.hutool.core.util.RuntimeUtil
import cn.hutool.core.util.URLUtil
import cn.hutool.json.JSONObject
import com.baidu.aip.util.Base64Util
import kotlinx.coroutines.*
import tooko.core.env.Env
import tooko.core.env.Img
import tooko.core.utils.mkAsync
import tooko.core.utils.mkTimeCount
import java.awt.Color
import java.io.ByteArrayInputStream
import java.io.File
import java.util.*

object LottieRenderer {

    private val driver by lazy {

        mkDriver().apply {

            val html = """
<html>
<head>
  <meta charset="UTF-8">
  <script>${Env.getFile("extra/lottie/lottie.min.js").readText()}</script>
</head>
<body>
</body>
</html>
"""
            get("data:text/html;charset=utf-8,${URLUtil.encode(html)}")

        }

    }

    fun initDriver() = driver
    fun initDriverAsync() = GlobalScope.async { initDriver() }

    fun closeDriver() = driver.close()

    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {

        var time = mkTimeCount()

        driver

        time.printTime("init: ")

        val deferreds = LinkedList<Deferred<Unit>>()

        (1..1).forEach {

            deferreds.add(GlobalScope.async {

                renderLottie(File("D:\\sticker.json").readText(), File("D:/sticker_$it.mp4"), null)

            })

        }

        deferreds.awaitAll()

        time.printTime("all: ")

    }

    suspend fun renderLottie(jsonStr: String, outputMp4: File?, outputGif: File?) {

        val time = mkTimeCount()

        val json = JSONObject(jsonStr)

        val fps = json.getInt("fr")
        val width = json.getInt("w")
        val height = json.getInt("h")

        @Suppress("UNCHECKED_CAST")
        val strs = (driver.execute("""
            let animationData = $jsonStr
            let sticker = document.createElement("div")
            document.body.appendChild(sticker)
            let animation = lottie.loadAnimation({
            	container: sticker,
            	renderer: "canvas",
            	loop: false,
            	autoplay: false,
            	rendererSettings: {},
            	animationData
            })
            let numFrames = animation.getDuration(true)
            let images = []
            for (let frame = 0; frame < numFrames; ++frame) {
                animation.goToAndStop(frame + 1, true)
                images[frame] = animation.container.toDataURL("image/jpg")
            }
            document.body.removeChild(sticker)
            return images
        """.trimIndent()) as List<String>)

        time.printTime("load: ")

        val bytes = strs.map {

            Base64Util.decode(it.substringAfter(","))

        }

        time.printTime("decode: ")

        val frames = bytes.map {

            val img = ImgUtil.read(ByteArrayInputStream(it))

            val image = Img(img.width, img.height, Color.WHITE)

            image.drawImage(0, 0, img, img.width, img.height)

            image.getBytes("png")

        }

        time.printTime("render: ")

        val async = mkAsync<Unit>()

        if (outputMp4 != null) {

            @Suppress("BlockingMethodInNonBlockingContext")
            async.execute {

                var scale = "scale=${width}:-2"

                if (width % 2 != 0) {

                    scale = if (height % 2 == 0) {

                        "scale=-2:${height}"

                    } else {

                        "scale=${width + 1}:-2"

                    }

                }

                val ffArgs = listOf(
                       // "-v error",
                        "-stats",
                        "-hide_banner",
                        "-y",
                        "-f image2pipe",
                        "-c:v png",
                        "-r $fps",
                        "-i -",
                        "-vf $scale",
                        "-c:v libx264",
                        "-profile:v main",
                        "-preset medium",
                        "-crf 20",
                        "-movflags faststart",
                        "-pix_fmt yuv420p",
                        "-an", outputMp4.path).joinToString(" ")

                val ffProc = RuntimeUtil.exec("ffmpeg $ffArgs")

                with(ffProc.outputStream) {

                    runCatching {

                        frames.forEach { write(it) }

                    }

                    flush()
                    close()

                }

                time.printTime("write: ")

                IoUtil.readLines(IoUtil.getReader(ffProc.inputStream, CharsetUtil.CHARSET_UTF_8), LineHandler {

                    println(it)

                })

                //ffProc.waitFor()


            }

        }

        if (outputGif != null) {

            @Suppress("BlockingMethodInNonBlockingContext")
            async.execute {

                val cacheDir = Env.getFile("cache/gifski/${UUID.fastUUID().toString(true)}")

                cacheDir.mkdirs()

                frames.forEachIndexed { index, bytes ->

                    File(cacheDir, "$index.png").writeBytes(bytes)

                }

                val skiArgs = listOf(
                        "-o", outputGif.path,
                        "--fps", fps,
                        "--fast",
                        "--quality 1",
                        "--quiet",
                        "$cacheDir/*.png"
                )

                val skiProc = RuntimeUtil.exec("gifski $skiArgs")

                IoUtil.readLines(IoUtil.getReader(skiProc.inputStream, CharsetUtil.CHARSET_UTF_8), LineHandler {

                    println(it)

                })

                // skiProc.waitFor()

            }

        }

        async.awaitAll()

    }


}