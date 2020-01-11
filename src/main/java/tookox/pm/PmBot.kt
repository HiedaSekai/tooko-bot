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

package tookox.pm

import kotlinx.coroutines.coroutineScope
import org.apache.commons.collections4.bidimap.DualHashBidiMap
import td.TdApi
import tookox.Launcher
import tookox.core.*
import tookox.core.bots.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.funs.*
import tookox.core.raw.*
import tookox.core.utils.*
import tookox.pm.config.WelcomeConfig
import tookox.pm.handlers.ChatPanel
import tookox.pm.handlers.DeleteHandler
import tookox.pm.handlers.EditHandler
import java.util.*

class PmBot(val image: BotImage) : TdBot(image.data.botToken) {

    var bot = image.data
    var data: PmData

    var current = -1
    var chat = -1L

    init {

        var _data = PmData.DATA.getById(bot.botId)

        if (_data == null) {

            _data = PmData()

            _data.id = bot.botId

            val main = PmData.Payload()

            main.notice = false

            _data.welcome = main

            PmData.DATA.idIndex[_data.id] = _data

        }

        data = _data

        if (data.sended == null) data.sended = DualHashBidiMap()
        if (data.received == null) data.received = DualHashBidiMap()
        if (data.blocked == null) data.blocked = HashSet()

    }

    override fun onLoad() {

        addHandler(EditHandler())

        addHandler(DeleteHandler())

        addHandler(ChatPanel())

        addHandler(WelcomeConfig())

        addHandler(LICENCE())

    }

    override suspend fun onAuthorizationFailed(ex: TdException) {

        destory(ex)

    }

    private suspend fun destory(ex: TdException) {

        image.error = ex
        image.status = BotImage.STATUS_ERROR

        Launcher.INSTANCE make bot.owner.langFor.ERR_LOGGING_OUT sendTo bot.owner

        stop()

    }


    override fun onDataRestore(data: Map<String, List<String>>) {

        chat = data["chat"]?.get(0)?.toLong() ?: chat

    }

    override fun onDataSave(data: HashMap<String, List<String>>) {

        if (chat != -1L) {

            data["chat"] = listOf("$chat")

        }

    }

    override suspend fun onLaunch(userId: Int, chatId: Long, message: TdApi.Message) = coroutineScope launch@{

        val L = Lang.get(userId)

        if (userId == bot.owner) {

            // TODO: deleteStartMessages(chatId)

            sudo makeHtml L.PM_OK sendTo chatId

            return@launch

        }

        if (data.welcome.notice) {

            sudo make L.PM_ON_START.input(getUser(userId).displayName.toInlineMention(userId), "/start").asHtml sendTo bot.owner

        }

        if (data.welcome.messages == null || data.welcome.messages.isEmpty()) {

            sudo make L.PM_WELCOME syncTo chatId

        } else {

            data.welcome.messages.forEach {

                sudo make it syncTo chatId

            }

        }
    }

}