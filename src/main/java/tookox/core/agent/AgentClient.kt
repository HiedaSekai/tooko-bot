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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import td.TdApi.*
import tookox.Launcher
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.utils.*

class AgentClient(val data: AgentData) : TdClient(TdOptions()
        .databaseDirectory(Env.getPath("data/agent/${data.userId}"))) {

    override val sudo = this

    fun transferForward(): suspend CoroutineScope.(Message) -> Unit {

        return {

            sudo make "/_agent_forward ${data.owner}" replyTo it.id sendTo Launcher.INSTANCE.botUserId

        }

    }

    infix fun transferForward(message: Message) {

        sudo makeForward message to Launcher.INSTANCE.botUserId send transferForward()

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope event@{

        super.onNewMessage(userId, chatId, message)

        if (userId == sudo.me.id) {

            return@event

        } else if (userId == 777000) {

            sudo make {

                input = inputForward(message) {

                    sendCopy = true

                }

            } to Launcher.INSTANCE.botUserId send transferForward()

            return@event

        }

        if (message.replyMarkup is ReplyMarkupInlineKeyboard) {

            var msg = "chat ${message.chatId} message ${message.id} : ${message.text}"

            val keyboard = (message.replyMarkup as ReplyMarkupInlineKeyboard)

            keyboard.rows.forEachIndexed { index, buttonArray ->

                msg += "\n\nrow ${index + 1}: "

                buttonArray.forEachIndexed { buttonIndex, button ->

                    msg += "\n\nbutton ${buttonIndex + 1}: ${button.text}"

                    with(button.type) {

                        when (this) {

                            is InlineKeyboardButtonTypeCallback -> {

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

            Launcher.INSTANCE make msg sendTo data.owner

        }

    }

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            Launcher.INSTANCE make "认证失败" sendTo data.owner

            stop()

            AgentData.DATA.deleteById(data.userId)

        }

    }

}