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

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import td.TdApi.*
import tookox.agent.AgentImage
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.util.*
import kotlin.properties.Delegates

class SyncStickers : TdBotHandler() {

    override fun onLoad() {

        initFunction("sync_stickers")

    }

    var stickersBotId by Delegates.notNull<Long>()

    override suspend fun onLogin() {

        stickersBotId = searchPublicChat("Stickers").id

    }

    suspend fun command(msg: String) {

        sudo make msg syncTo stickersBotId

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

        val anim = if (stickerSet.isAnimated) {

            command("/newanimated")

            true

        } else if (stickerSet.isMasks) {

            command("/newmasks")

            false

        } else {

            command("/newpack")

            false

        }

        command(stickerSet.title)

        sudo makeFile stickerSet.stickers[0].sticker.local.path!! syncTo stickersBotId

    }

}