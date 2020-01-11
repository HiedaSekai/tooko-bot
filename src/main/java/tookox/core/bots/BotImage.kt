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

package tookox.core.bots

import cn.hutool.core.util.RuntimeUtil
import tookox.core.client.*
import tookox.core.env.*
import tookox.pm.PmBot
import tookox.pm.PmData

class BotImage(var data: BotData) {

    var status = 0
    var error: TdException? = null
    lateinit var bot: TdBot

    companion object {

        var images = HashMap<Int, BotImage>()

        const val STATUS_RUNNING = 0
        const val STATUS_ERROR = 1

        suspend fun start(data: BotData) : Boolean {

            if (images.containsKey(data.botId)) error("重复启动.")

            val image = BotImage(data)

            images[data.botId] = image

            if (data.type == 1) {

                image.bot = PmBot(image)

                return image.bot.start(true)

            } else {

                error("没有这种BOT.")

            }

        }

        suspend fun delete(data: BotData) {

            if (images.containsKey(data.botId)) {

                images[data.botId]!!.bot.stop()

            }

            BotData.DATA.deleteById(data.botId)

            if (data.type == 1) {

                PmData.DATA.deleteById(data.botId)

                Env.DB.getCollection("pm_" + data.botId).drop()

            }

            RuntimeUtil.exec("rm -rf \"" + Env.getPath("data/" + data.botId) + "\"")

        }
    }

}