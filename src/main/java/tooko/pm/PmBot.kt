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

package tooko.pm

import kotlinx.coroutines.coroutineScope
import org.apache.commons.collections4.bidimap.DualHashBidiMap
import td.TdApi
import tooko.INSTANCE
import tooko.core.*
import tooko.core.bots.BotImage
import tooko.core.client.TdBot
import tooko.core.client.TdException
import tooko.core.env.Lang
import tooko.core.funs.LICENCE
import tooko.core.raw.getUser
import tooko.core.utils.*
import tooko.pm.config.WelcomeConfig
import tooko.pm.handlers.ChatPanel
import tooko.pm.handlers.DeleteHandler
import tooko.pm.handlers.EditHandler
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

        INSTANCE make bot.owner.langFor.ERR_LOGGING_OUT sendTo bot.owner

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

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = userId.langFor

        sudo make L.NO_CHAT_ENTERED sendTo chatId

    }

    override suspend fun onNewMessage(userId: Int, chatId: Long, message: TdApi.Message) {

        if (data.blocked.contains(chatId)) finishEvent()

        super.onNewMessage(userId, chatId, message)

        val L = bot.owner.langFor

        if (userId != bot.owner) {

            data.initSessions()

            if (current != userId) {

                current = userId

                var report = L.USER_ID + " : " + userId.asCode + " [ " + L.CHAT.toStartPayload("enter", chatId.toString() + "") + " ]"

                report += "\n" + L.USER_NAME.toString() + " : " + getUser(userId).asInlineMention

                val reportMessage = sudo makeHtml report syncTo bot.owner

                data.sessions.arrayInsert(chatId, "reports", reportMessage.id)
            }

            val forwarded = try {

                sudo makeForward message syncTo bot.owner

            } catch (e: TdException) {

                destory(e)

                return

            }

            data.received[forwarded.id.toString() + ""] = message.id
            data.sessions.arrayInsert(chatId, "received", message.id)

            return

        }

        var targetChat = 0L
        var replyTo = 0L

        if (message.replyToMessageId != 0L) {

            val actionMessage = message.replyToMessageId
            val actionMessageStr = actionMessage.toString() + ""

            if (data.received.containsKey(actionMessageStr)) {

                replyTo = data.received[actionMessageStr]!!
                targetChat = data.getSessionByElement("received", replyTo).chatId

            } else if (data.sended.containsKey(actionMessageStr)) {

                replyTo = data.sended[actionMessageStr]!!
                targetChat = data.getSessionByElement("sended", replyTo).chatId

            } else {

                var session = data.getSessionByElement("reports", actionMessage)

                if (session != null) targetChat = session.chatId

                session = data.getSessionByElement("extras", actionMessage)

                if (session != null) targetChat = session.chatId

                if (targetChat != 0L) {

                    chat = targetChat

                    sudo makeHtml L.CHAT_ENTERED.input(getUser(session.chatId.toInt()).asInlineMention) to chatId send deleteDelay()

                }

            }

            if (targetChat == 0L) {

                sudo make L.CHAT_NO_RECORD sendTo chatId

                return

            }

        } else if (chat != -1L) {

            targetChat = chat

        } else {

            sudo make L.NO_CHAT_ENTERED to chatId send deleteDelay()

            delayDelete(message)

            return

        }

        var input = (if (message.forwardInfo == null) {

            message.content.asInput

        } else null) ?: inputForward(message)

        val sended = try {

            sudo make input replyTo replyTo syncTo targetChat

        } catch (e: TdException) {

            sudo make L.SEND_FAILED.input(e) replyTo message.id sendTo chatId

            chat = -1

            return

        }

        data.sended[message.id.toString() + ""] = sended.id

        data.sessions.arrayInsert(chat, "sended", sended.id)

        sudo make L.SEND_SUCCEED replyTo message.id to chatId send deleteDelay()

    }

}