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

import td.TdApi.*
import tookox.agent.AgentData
import tookox.agent.AgentImage
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

        if (function.startsWith("new")) {

            writePersist(userId, PERSIST_ID, 0)

            sudo make {

                inputText = L.AGENT_CHT

                replyMarkup = keyboadButton {

                    textLine(L.AGENT_LOGIN)

                    if (Env.USE_TEST_DC) {

                        textLine(L.AGENT_LOGIN_NO_TEST_DC)

                    } else {

                        textLine(L.AGENT_LOGIN_TEST_DC)

                    }

                    // textLine(L.AGENT_IMPORT)

                }

            } sendTo chatId

        }

    }

    override fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>) = TODO()

    val cache = HashMap<Int, TdClient>()

    override suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int) {

        cache.remove(userId)?.stop()

    }

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int) {

        val L = userId.langFor

        if (subId == 0) {

            if (message.text == L.AGENT_IMPORT) {

                writePersist(userId, PERSIST_ID, 1)

                sudo make L.AGENT_INPUT_BINLOG sendTo chatId

            } else if (message.text == L.AGENT_LOGIN) {

                writePersist(userId, PERSIST_ID, if (Env.USE_TEST_DC) 3 else 2)

                sudo make L.AGENT_INPUT_PHONE sendTo chatId

            } else if (message.text == L.AGENT_LOGIN_NO_TEST_DC) {

                writePersist(userId, PERSIST_ID, 2)

                sudo make L.AGENT_INPUT_PHONE sendTo chatId

            } else if (message.text == L.AGENT_LOGIN_TEST_DC) {

                writePersist(userId, PERSIST_ID, 3)

                sudo make L.AGENT_INPUT_PHONE sendTo chatId

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

                    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

                        super.onAuthorizationState(authorizationState)

                        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

                            stop()

                            bot make L.AGENT_AUTH_INVALID sendTo chatId

                        }

                    }

                    override suspend fun onLogin() {

                        stop()

                        File(cacheDir, "td.binlog").copyTo(File(Env.getFile("data/agent/${me.id}"), "td.binlog"), true)

                        cacheDir.deleteRecursively()

                        bot make L.AGENT_AUTH_OK sendTo chatId

                        val agent = AgentData(me.id)

                        agent.owner = userId

                        AgentData.DATA.setById(userId, agent)

                        AgentImage.start(agent)

                    }

                }.start()

            }

        } else if (subId == 2 || subId == 3) {

            val cacheDir = Env.getFile("data/agent_create/$userId")

            cacheDir.deleteRecursively()

            sudo make L.AGENT_AUTHING sendTo chatId

            val bot = sudo

            object : TdClient(TdOptions()
                    .databaseDirectory(cacheDir.path)
                    .useTestDc(subId == 3)) {

                init {

                    cache[userId] = this

                }

                override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

                    super.onAuthorizationState(authorizationState)

                    if (authorizationState is AuthorizationStateWaitPhoneNumber) {

                        try {

                            setAuthenticationPhoneNumber(message.text)

                        } catch (ex: TdException) {

                            bot make L.AGENT_CODE_INVALID sendTo chatId

                            bot removePersist userId

                        }

                    } else if (authorizationState is AuthorizationStateWaitCode) {

                        bot.writePersist(userId, PERSIST_ID, 4)

                        bot make L.AGENT_INPUT_CODE sendTo chatId

                    } else if (authorizationState is AuthorizationStateWaitPassword) {

                        bot.writePersist(userId, PERSIST_ID, 5, true)

                        bot make L.AGENT_INPUT_PASSWORD sendTo chatId

                    }

                }

                override suspend fun onLogin() {

                    bot removePersist userId

                    val dir = if (subId == 2) {

                        "agent"

                    } else {

                        "agent_test_dc"

                    }

                    File(cacheDir, "td.binlog").copyTo(File(Env.getFile("data/$dir/${me.id}"), "td.binlog"), true)

                    cacheDir.deleteRecursively()

                    bot make L.AGENT_AUTH_OK sendTo chatId

                    val agent = AgentData(me.id)

                    agent.owner = userId

                    if (subId == 3) {

                        agent.testDc = true

                    }

                    AgentData.DATA.setById(userId, agent)

                    AgentImage.start(agent)

                }

            }.start()

        } else if (subId == 4) {

            if (!cache.containsKey(userId)) {

                sudo removePersist userId

                onSendCanceledMessage(userId)

                return

            }

            try {

                cache[userId]!!.checkAuthenticationCode(message.text)

            } catch (ex: TdException) {

                sudo make L.AGENT_CODE_INVALID sendTo chatId

            }

        } else if (subId == 5) {

            if (!cache.containsKey(userId)) {

                sudo removePersist userId

                onSendCanceledMessage(userId)

                return

            }

            try {

                cache[userId]!!.checkAuthenticationPassword(message.text)

            } catch (ex: TdException) {

                sudo make L.AGENT_INVALID_PASSWORD sendTo chatId

            }

        } else if (subId == 5) {

            if (!cache.containsKey(userId)) {

                sudo removePersist userId

                onSendCanceledMessage(userId)

                return

            }

            try {

                cache[userId]!!.deleteAccount(message.text)

            } catch (ex: TdException) {

                sudo make ex sendTo chatId

            }

            sudo removePersist userId

        }

    }

    override suspend fun onPersistFunction(userId: Int, chatId: Long, message: Message, subId: Int, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (function == "delete") {

            if (!cache.containsKey(userId)) {

                sudo removePersist userId

                onSendCanceledMessage(userId)

                return

            }

            writePersist(userId, PERSIST_ID, 5)

            sudo make userId.langFor.AGENT_INPUT_REASON sendTo chatId

        }

    }

}