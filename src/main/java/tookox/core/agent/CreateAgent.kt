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

import td.TdApi
import td.TdApi.Message
import td.TdApi.MessageDocument
import tookox.Launcher
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
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

        if (function.startsWith("new")) {

            writePersist(userId, PERSIST_ID, 0)

            sudo make {

                inputText = L.AGENT_CHT

                replyMarkup = keyboadButton {

                    textLine(L.AGENT_LOGIN)

                    textLine(L.AGENT_IMPORT)

                }

            } sendTo chatId

        }

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

                val cacheDir = Env.getFile("data/agent_create/$userId")

                cacheDir.deleteRecursively()

                file.copyTo(File(cacheDir, "td.binlog"))

                sudo make L.AGENT_AUTHING sendTo chatId

                val bot = sudo

                object : TdClient(TdOptions()
                        .databaseDirectory(cacheDir.path)) {

                    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) {

                        super.onAuthorizationState(authorizationState)

                        if (authorizationState is TdApi.AuthorizationStateWaitPhoneNumber) {

                            stop()

                            bot make L.AGENT_AUTH_INVALID sendTo chatId

                        }

                    }

                    override suspend fun onLogin() {

                        sudo make "/_agent_init" syncTo Launcher.INSTANCE.botUserId

                        stop()

                        File(cacheDir, "td.binlog").copyTo(File(Env.getFile("data/agent/${me.id}"), "td.binlog"), true)

                        cacheDir.deleteRecursively()

                        bot make L.AGENT_AUTH_OK sendTo chatId

                        val agent = AgentData(me.id)

                        agent.owner = if (Env.isAdmin(userId)) -1 else userId

                        AgentData.DATA.setById(userId, agent)

                        AgentImage.start(agent)

                    }

                }.start()

            }

        }

    }

}