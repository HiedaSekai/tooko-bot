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

package tookox

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import td.TdApi
import tookox.agent.AgentData
import tookox.agent.AgentImage
import tookox.agent.BotFlood
import tookox.agent.fmt.TestNumberFormat
import tookox.agent.stickers.SyncStickers
import tookox.core.*
import tookox.core.agent.*
import tookox.core.bots.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.funs.*
import tookox.core.utils.*
import java.util.*
import kotlin.system.exitProcess

class TookoBot(botToken: String) : TdBot(botToken) {

    override fun onLoad() {

        addHandler(BaseFuncs())

        addHandler(StickerExport())

        // Bots

        addHandler(CreateBot())

        addHandler(BotPanel())

        // Agent

        addHandler(CreateAgent())

        addHandler(AgentTransfer())

        addHandler(BotFlood())

        addHandler(SyncStickers())

        addHandler(TestNumberFormat())

        // Licence

        addHandler(LICENCE())

    }

    override suspend fun onLogin() = coroutineScope<Unit> {

        /*

        twitter = TwitterBot().apply { start() }

         */

        val deferreds = LinkedList<Deferred<*>>()

        BotData.DATA.all.forEach { bot ->

            run<Unit> {

                deferreds.add(async<Unit> {

                    BotImage.start(bot).waitForAuth()

                })

            }

        }

        AgentData.DATA.all.forEach { agent ->

            run<Unit> {

                deferreds.add(async<Unit> {

                    AgentImage.start(agent).waitForAuth()

                })

            }

        }

        deferreds.awaitAll()

    }

    override fun onDestroy() {

        super.onDestroy()

        Launcher.cachedTables.forEach { it.saveAll() }

    }

    override suspend fun onLaunch(userId: Int, chatId: Long, message: TdApi.Message) {

        val L = userId.langFor

        sudo makeHtml (if (Env.isAdmin(userId)) L.HELP else "${L.HELP}\n\n${L.PUBLIC_WARN}") sendTo chatId

    }

    override suspend fun onAuthorizationFailed(ex: TdException) {

        super.onAuthorizationFailed(ex)

        defaultLog.error(ex, "本体认证失败.")

        INSTANCE.stop()

        INSTANCE.waitForClose()

        exitProcess(100)

    }

}