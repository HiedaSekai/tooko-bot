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

package tooko.pm.config

import cn.hutool.core.map.MapUtil
import cn.hutool.core.util.StrUtil
import td.TdApi.InputMessageContent
import td.TdApi.Message
import tooko.core.*
import tooko.core.client.*
import tooko.core.env.*
import tooko.core.utils.*
import tooko.pm.PmBot
import tooko.pm.PmData
import java.util.*
import kotlin.collections.HashMap

class WelcomeConfig : TdBotHandler() {

    override val sudo get() = super.sudo as PmBot

    private val cache = HashMap<Int, EditCache>()

    private val PERSISTD_ID = PERSIST_2

    override fun onLoad() {

        initFunction("msg")

        initPersist(PERSISTD_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = userId.langFor

        if (params.isEmpty()) {

            stat(L, chatId)

            return

        }

        val subParams = params.shift()

        if ("show" == params[0]) {

            show(L, chatId, subParams)

        } else if ("notice" == params[0]) {

            notice(L, chatId, subParams)

        } else if ("set" == params[0]) {

            set(L, userId, chatId, subParams)

        } else if ("del" == params[0]) {

            del(L, chatId, subParams)

        }
    }

    fun set(L: Lang, userId: Int, chatId: Long, params: Array<String>) {

        val edit = EditCache()

        if (params.isNotEmpty()) {

            val payload = params[0]

            if (!payload.matches("[a-zA-Z0-9_-]*".toRegex()) || payload.length !in 1..64) {

                sudo make L.PM_PAYLOAD_INVALID sendTo chatId

                return

            }

            edit.payload = payload

        }

        cache[userId] = edit

        writePersist(userId, PERSISTD_ID, allowFunction = true)

        sudo make L.PM_WELCOME_INPUT sendTo chatId

    }

    override fun onPersistReStore(userId: Int, subId: Int, data: List<String>) {

        TODO()

    }

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int) {

        val L = userId.langFor

        var input: InputMessageContent? = null

        if (message.forwardInfo == null) {

            input = message.content.asInput

            // make a copy

        }

        if (input == null) {

            sudo make L.PM_WELCOME_FD_WARN sendTo chatId

            input = inputForward(message)

        }

        cache[userId]!!.messages.add(input)

        sudo make L.PM_WELCOME_ADDED sendTo chatId

    }

    override suspend fun onPersistFunction(userId: Int, chatId: Long, message: Message, subId: Int, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if ("submit" == function) {

            val L = userId.langFor

            sudo removePersist userId

            val edit = cache.remove(userId)!!

            val payload = if (edit.payload == null) {

                sudo.data.welcome

            } else {

                if (sudo.data.payloads == null) sudo.data.payloads = HashMap()

                sudo.data.payloads[edit.payload] ?: PmData.Payload().apply {

                    notice = false

                    sudo.data.payloads[edit.payload] = this

                }

            }

            payload.messages = edit.messages.toArray(arrayOf<InputMessageContent>())

            sudo make L.PM_WELCOME_FINISH sendTo chatId

        } else {

            super.onPersistFunction(userId, chatId, message, subId, function, param, params, originParams)

        }

    }

    fun del(L: Lang, chatId: Long, params: Array<String>) {

        if (params.isEmpty()) {

            sudo.data.welcome.messages = null

            sudo make L.DELETED sendTo chatId

            return
        }

        if (sudo.data.payloads == null || !sudo.data.payloads.containsKey(params[0])) {

            sudo make L.PM_PAYLOAD_UNDEFINED sendTo chatId

            return

        }

        sudo.data.payloads.remove(params[0])

        sudo make L.DELETED sendTo chatId

    }

    fun notice(L: Lang, chatId: Long, params: Array<String>) {

        val usage = "/msg notice <enable/disable> [payload]"

        if (params.isEmpty()) {

            sudo make usage sendTo chatId

            return

        }

        val target: Boolean = when (params[0]) {

            "enable" -> true
            "disable" -> false

            else -> {

                sudo make usage sendTo chatId

                return

            }

        }

        val payload = if (params.size != 1) {

            if (sudo.data.payloads == null || !sudo.data.payloads.containsKey(params[1])) {

                sudo make L.PM_PAYLOAD_UNDEFINED sendTo chatId

                return

            }

            sudo.data.payloads[params[1]]!!

        } else {

            sudo.data.welcome

        }

        payload.notice = target

        sudo make (if (target) L.ENABLED else L.DISABLED) sendTo chatId

    }

    suspend fun show(L: Lang, chatId: Long, params: Array<String>) {

        val payload = if (params.isNotEmpty()) {

            if (sudo.data.payloads == null || !sudo.data.payloads.containsKey(params[0])) {

                sudo make L.PM_PAYLOAD_UNDEFINED sendTo chatId

                return

            }

            sudo.data.payloads[params[1]]!!

        } else {

            sudo.data.welcome

        }

        if (payload.messages == null) {

            sudo make L.PM_WELCOME sendTo chatId

        } else {

            payload.messages.forEach { sudo make it syncTo chatId }

        }

    }

    fun stat(L: Lang, chatId: Long) {

        val welcomeMessagesCount = if (sudo.data.welcome.messages == null) 0 else sudo.data.welcome.messages.size

        var stat = if (welcomeMessagesCount == 0) {

            L.PM_NO_WELCOME_MESSAGE

        } else {

            StrUtil.format(L.PM_WELCOME_MESSAGE, welcomeMessagesCount)

        }

        stat += "\n" + L.PM_WELCOME_NOTICE.toString() + " : "

        stat += (if (sudo.data.welcome.notice) L.ENABLED else L.DISABLED)

        stat += "\n\n"

        if (MapUtil.isEmpty(sudo.data.payloads)) {

            stat += L.PM_PAYLOAD.toString() + " : " + L.UNDEFINE

        } else {

            stat += L.PM_PAYLOADS.toString() + " : \n\n"

            for ((key, value) in sudo.data.payloads) {

                stat += "$key :\n"

                val messagesCount = value.messages.size

                stat += if (messagesCount == 0) {

                    "  - " + L.PM_NO_WELCOME_MESSAGE

                } else {

                    "  - " + StrUtil.format(L.PM_WELCOME_MESSAGE, messagesCount)

                }

                stat += "\n  - " + L.PM_WELCOME_NOTICE.toString() + " : "

                stat += (if (sudo.data.welcome.notice) L.ENABLED else L.DISABLED)

            }

        }

        sudo make stat sendTo chatId

    }

    private class EditCache(var payload: String? = null) {

        val messages: LinkedList<InputMessageContent> = LinkedList()

    }

}