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

package tooko.core.agent

import td.TdApi.*
import tooko.agent.AgentData
import tooko.agent.AgentImage
import tooko.core.PERSIST_3
import tooko.core.client.TdBotHandler
import tooko.core.client.TdClient
import tooko.core.client.TdException
import tooko.core.client.TdOptions
import tooko.core.env.Env
import tooko.core.langFor
import tooko.core.raw.checkAuthenticationCode
import tooko.core.raw.checkAuthenticationPassword
import tooko.core.raw.deleteAccount
import tooko.core.raw.setAuthenticationPhoneNumber
import tooko.core.text
import tooko.core.utils.keyboadButton
import tooko.core.utils.make
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

           if (message.text == L.AGENT_LOGIN) {

               writePersist(userId, PERSIST_ID, if (Env.USE_TEST_DC) 2 else 1)

               sudo make L.AGENT_INPUT_PHONE sendTo chatId

           } else if (message.text == L.AGENT_LOGIN_NO_TEST_DC) {

               writePersist(userId, PERSIST_ID, 1)

               sudo make L.AGENT_INPUT_PHONE sendTo chatId

           } else if (message.text == L.AGENT_LOGIN_TEST_DC) {

               writePersist(userId, PERSIST_ID, 2)

               sudo make L.AGENT_INPUT_PHONE sendTo chatId

           }

        } else if (subId == 1 || subId == 2) {

            val cacheDir = Env.getFile("data/agent_create/$userId")

            cacheDir.deleteRecursively()

            sudo make L.AGENT_AUTHING sendTo chatId

            val bot = sudo

            object : TdClient(TdOptions()
                    .databaseDirectory(cacheDir.path)
                    .useTestDc(subId == 2)) {

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

                        bot.writePersist(userId, PERSIST_ID, 3)

                        bot make L.AGENT_INPUT_CODE sendTo chatId

                    } else if (authorizationState is AuthorizationStateWaitPassword) {

                        bot.writePersist(userId, PERSIST_ID, 4, true)

                        bot make L.AGENT_INPUT_PASSWORD sendTo chatId

                    }

                }

                override suspend fun onLogin() {

                    bot removePersist userId

                    val binlog = if (subId != 2) {

                        "td.binlog"

                    } else {

                        "td_test.binlog"

                    }

                    File(cacheDir, binlog).copyTo(File(Env.getFile("data/agent/${me.id}"), binlog), true)

                    cacheDir.deleteRecursively()

                    bot make L.AGENT_AUTH_OK sendTo chatId

                    val agent = AgentData(me.id)

                    agent.owner = userId

                    if (subId == 2) {

                        agent.testDc = true

                    }

                    AgentData.DATA.setById(userId, agent)

                    AgentImage.start(agent)

                }

            }.start()

        } else if (subId == 3) {

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

        } else if (subId == 4) {

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