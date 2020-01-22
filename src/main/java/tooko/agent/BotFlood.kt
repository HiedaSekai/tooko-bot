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

package tooko.agent

import kotlinx.coroutines.delay
import td.TdApi
import tooko.INSTANCE
import tooko.core.*
import tooko.core.client.*
import tooko.core.raw.*

class BotFlood : TdBotHandler() {

    override fun onLoad() {

        initFunction("start_flood")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        AgentImage.agents.values.forEach {

            with(it) {

                val from = INSTANCE.me.id.toLong()

                val target = searchPublicChat(params[0]).id

                val originMessages = forkMessage(from, originParams.shift(2).joinToString(" "))

                repeat(params[1].toInt()) {

                    forwardMessages(target, from, originMessages, null, false, false, false)

                    delay(100L)

                }

                deleteMessages(sudo.me.id.toLong(), originMessages, true)

            }

        }

    }

}