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

package tookox.core.agent

import cn.hutool.core.util.NumberUtil
import kotlinx.coroutines.coroutineScope
import td.TdApi
import tookox.core.*
import tookox.core.client.*
import tookox.core.utils.*

class AgentTransfer : TdBotHandler() {

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = coroutineScope event@{

        if (!message.fromPrivate) return@event

        if (userId !in AgentImage.agents) return@event

        run fn@{

            if (function == "_agent_init") {

                sudo delete message

            } else if (function == "_agent_forward") {

                if (NumberUtil.isLong(param)) {

                    sudo delete message

                    return@fn

                }

                makeForward(chatId, message.replyToMessageId) sendTo param.toLong()

                delete(chatId, message.id, message.replyToMessageId)

            } else {

                return@event

            }

        }

        finishEvent()

    }

}