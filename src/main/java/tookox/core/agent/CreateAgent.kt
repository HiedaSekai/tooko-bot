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
import td.TdApi.*
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.io.File
import java.util.*

class CreateAgent : TdBotHandler() {

    val PERSIST_ID = PERSIST_3

    override fun onLoad() {

        initFunction("new_agent")

        initPersist(PERSIST_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = userId.langFor

        writePersist(userId, PERSIST_ID, 0)

        sudo make {

            inputText = L.AGENT_CHT

            replyMarkup = keyboadButton {

                textLine(L.AGENT_LOGIN)

                textLine(L.AGENT_IMPORT)

            }

        } sendTo chatId

    }

    override fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>) = TODO()

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int) {

        sudo removePersist userId

        val L = userId.langFor

        if (subId == 0) {

            if (message.text == L.AGENT_IMPORT) {

                writePersist(userId, PERSIST_ID, 1)

                sudo make L.AGENT_INPUT_BINLOG sendTo chatId

            }

        } else if (subId == 1) {

            with(message.content) {

                if (this !is MessageDocument ||
                        document.fileName != "td.binlog") {

                    onSendCanceledMessage(userId)

                    return

                }

                val file = document.download()

                val agentDir = Env.getFile("data/agent/$userId")

                agentDir.deleteRecursively()

                file.copyTo(File(agentDir, "td.binlog"))

                val superSudo = sudo

                val client = AgentClient(agentDir)

                client.addHandler(object : TdHandler() {

                    override suspend fun onNewMessage(userId: Int, chatId: Long, message: Message) = coroutineScope {

                        defaultLog.debug("${getUserOrNull(userId)?.displayName} : ${message.text}")

                        if (userId == sudo.me.id) return@coroutineScope

                        if (message.fromPrivate) {

                            sudo make "IS" to chatId send deleteDelay()

                            deleteDelay()(message)

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

                            superSudo make msg sendTo chatId

                        }

                    }

                    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

                        if (authorizationState is AuthorizationStateReady) {

                            superSudo make L.AGENT_AUTH_OK sendTo chatId

                            val bot = searchPublicChat(superSudo.me.username)

                            sudo make "Hello" syncTo bot.id

                            superSudo makeHtml getMe().asInlineMention syncTo chatId

                        } else {

                            superSudo make authorizationState.javaClass.simpleName sendTo chatId

                        }

                    }

                })

                sudo make L.AGENT_AUTHING sendTo chatId

                client.start()

            }

        }

    }

}