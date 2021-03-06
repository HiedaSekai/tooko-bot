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

import cn.hutool.core.util.StrUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import td.TdApi.*
import tooko.INSTANCE
import tooko.core.*
import tooko.core.client.*
import tooko.core.env.*
import tooko.core.raw.*
import tooko.core.utils.*

class AgentClient(val data: AgentData) : TdClient(
        initDataDir("data/agent/${data.userId}")
                .useTestDc(data.testDc != null)) {

    override val sudo = this

    val inDiffWorld = Env.USE_TEST_DC == (data.testDc != null)

    override suspend fun onLogin() {

        if (!inDiffWorld) searchPublicChat(INSTANCE.me.username)

    }

    fun transferForward(): suspend CoroutineScope.(Message) -> Unit {

        return {

            sudo make "/_agent_forward ${data.owner}" replyTo it.id sendTo INSTANCE.botUserId

        }

    }

    infix fun transferForward(message: Message) {

        sudo makeForward message to INSTANCE.botUserId send transferForward()

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope event@{

        super.onNewMessage(userId, chatId, message)

        if (userId == sudo.me.id) {

            return@event

        } else if (userId == 777000) {

            INSTANCE make "${me.asInlineMention}: \n\n${message.text}" sendTo data.owner

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

            defaultLog.debug(msg)

        }

    }

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            INSTANCE make "认证失败" sendTo data.owner

            stop()

            AgentData.DATA.deleteById(data.userId)

        }

    }

}