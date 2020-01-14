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

package tookox.agent.stickers

import cn.hutool.core.io.FileUtil
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import net.coobird.thumbnailator.Thumbnails
import td.TdApi.*
import tookox.agent.AgentImage
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.util.*
import kotlin.properties.Delegates
import java.io.File as JFile

class SyncStickers : TdBotHandler() {

    override fun onLoad() {

        initFunction("sync_stickers")

    }

    var stickersBotId by Delegates.notNull<Long>()

    suspend infix fun TdAbsHandler.cmd(str: String) {

        sudo make str syncTo stickersBotId

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = coroutineScope event@{

        if (Env.USE_TEST_DC) TODO()

        if (params.size < 2) TODO()

        val agent = AgentImage.agents[params[0].toInt()]

        if (agent == null) {

            sudo make "AGENT_NOT_FOUND" sendTo chatId

            return@event

        }

        val setName = params[1]

        val stickerSet = searchStickerSet(setName)

        val deferreds = LinkedList<Deferred<*>>()

        for (index in stickerSet.stickers.indices) {

            val sticker: Sticker = stickerSet.stickers[index]

            val stickerFile = sticker.sticker

            if (stickerFile.local.isDownloadingCompleted) continue

            async {

                sticker.sticker = sync(DownloadFile(stickerFile.id, 1, 0, 0, true))

            }.also {

                deferreds.add(it)

            }

        }

        deferreds.awaitAll()

        with(agent) {

            stickersBotId = searchPublicChat("Stickers").id

            val anim = if (stickerSet.isAnimated) {

                sudo cmd "/newanimated"

                true

            } else if (stickerSet.isMasks) {

                sudo cmd "/newmasks"

                false

            } else {

                sudo cmd "/newpack"

                false

            }

            sudo cmd stickerSet.title

            val png = stickerSet.stickers[0].cachePng()

            sudo makeFile png.path syncTo stickersBotId

            sudo cmd stickerSet.stickers[0].emoji

            sudo cmd "/finish"

            if (stickerSet.thumbnail != null) {

                var icon = stickerSet.thumbnail!!.photo

                if (!icon.local.isDownloadingCompleted) {

                    icon = sync(DownloadFile(icon.id, 1, 0, 0, true))

                }

                val iconFile = icon.cacheIcon()

                sudo makeFile iconFile.path syncTo stickersBotId

            } else {

                sudo cmd "/skip"

            }

            sudo cmd stickerSet.name

        }

    }

    fun Sticker.cachePng(): JFile {

        val webp = JFile(sticker.local.path!!)

        defaultLog.debug("${webp.path}")

        val cache = Env.getFile("cache/sticker_png/${webp.nameWithoutExtension}.png")

        if (!cache.isFile) {

            FileUtil.touch(cache)

            Thumbnails
                    .of(webp)
                    .scale(1.0)
                    .outputFormat("png")
                    .outputQuality(1.0F)
                    .toFile(cache)

            var outputQuality = 1.0f

            while (cache.length() > 512 * 1024L) {

                outputQuality -= 0.05f

                Thumbnails
                        .of(webp)
                        .scale(1.0)
                        .outputFormat("png")
                        .outputQuality(outputQuality)
                        .toFile(cache)

            }

        }

        return cache

    }

    fun File.cacheIcon(): JFile {

        val webp = JFile(local.path!!)

        val cache = Env.getFile("cache/sticker_png/${webp.nameWithoutExtension}.png")

        if (!cache.isFile) {

            FileUtil.touch(cache)

            Thumbnails
                    .of(webp)
                    .scale(100.0, 100.0)
                    .outputFormat("png")
                    .outputQuality(1.0F)
                    .toFile(cache)

        }

        return cache

    }

}