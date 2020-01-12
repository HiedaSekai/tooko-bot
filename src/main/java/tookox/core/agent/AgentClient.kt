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

import cn.hutool.core.util.StrUtil
import kotlinx.coroutines.coroutineScope
import td.TdApi
import tookox.core.*
import tookox.core.client.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.io.File

class AgentClient(val bot: TdBot, val ownerChat: Long, dir: File) : TdClient(TdOptions().databaseDirectory(dir.path)) {

    val L = ownerChat.langFor

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: TdApi.Message) = coroutineScope {

        super.onNewMessage(userId, chatId, message)

        defaultLog.debug("${getUserOrNull(userId)?.displayName} : ${message.text}")

        if (userId == sudo.me.id) return@coroutineScope

        if (message.fromPrivate) {

            sudo make "IS" to chatId send deleteDelay()

            deleteDelay()(message)

        }

        if (message.replyMarkup is TdApi.ReplyMarkupInlineKeyboard) {

            var msg = "chat ${message.chatId} message ${message.id} : ${message.text}"

            val keyboard = (message.replyMarkup as TdApi.ReplyMarkupInlineKeyboard)

            keyboard.rows.forEachIndexed { index, buttonArray ->

                msg += "\n\nrow ${index + 1}: "

                buttonArray.forEachIndexed { buttonIndex, button ->

                    msg += "\n\nbutton ${buttonIndex + 1}: ${button.text}"

                    with(button.type) {

                        when (this) {

                            is TdApi.InlineKeyboardButtonTypeCallback -> {

                                msg += "\n  回调数据 ${data.joinToString(" ")}"
                                msg += "\n  转字符串 ${StrUtil.utf8Str(data)}"

                            }

                            else -> {

                                msg += "\n  Type : " + javaClass.simpleName.substringAfter("Type")

                            }

                        }

                    }

                }

            }

            defaultLog.debug(msg)

            bot make msg sendTo ownerChat

        }

    }

    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) {

        if (authorizationState is TdApi.AuthorizationStateReady) {

            bot make L.AGENT_AUTH_OK sendTo ownerChat

            val chat = searchPublicChat(bot.me.username)

            sudo make "Hello" syncTo chat.id

            bot makeHtml getMe().asInlineMention syncTo ownerChat

        } else {

            bot make authorizationState.javaClass.simpleName sendTo ownerChat

        }

    }

}