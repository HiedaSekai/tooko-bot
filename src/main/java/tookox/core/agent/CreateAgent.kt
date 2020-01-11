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

                    override suspend fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) {

                        if (authorizationState is TdApi.AuthorizationStateReady) {

                            superSudo make L.AGENT_AUTH_OK sendTo chatId

                            // createPrivateChat(botUserId,getUser(superSudo.botUserId))

                            val botUserId = superSudo.botUserId

                            getUser(botUserId)

                            createPrivateChat(botUserId, false)

                            sudo make "Hello" syncTo botUserId

                            superSudo makeHtml getMe().asInlineMention syncTo chatId

                            sudo.stop()

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