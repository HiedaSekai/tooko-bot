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

package tooko.pm.handlers

import td.TdApi
import td.TdApi.*
import tooko.core.*
import tooko.core.client.*
import tooko.core.raw.*
import tooko.core.utils.*
import tooko.pm.PmBot
import tooko.pm.PmData

class EditHandler : TdBotHandler() {

    override val sudo: PmBot get() = super.sudo as PmBot

    val bot get() = sudo.bot
    val data get() = sudo.data

    override suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: TdApi.MessageContent) {

        val L = bot.owner.langFor

        if (chatId == bot.owner.toLong()) {

            val messageIdStr = messageId.toString() + ""

            if (data.sended.containsKey(messageIdStr)) {

                val targetMessage = data.sended.get(messageIdStr)!!
                val session: PmData.Session = data.getSessionByElement("sended", targetMessage)
                val targetChat: Long = session.chatId

                val oldMessage = getMessageOrNull(targetChat, targetMessage)

                if (oldMessage == null) {

                    data.sended.remove(messageIdStr)
                    session.sended.remove(targetMessage)

                    return

                }

                var edit: TdApi.Function? = null

                if (newContent is MessageText) {

                    edit = sudo make newContent.text to targetChat mkEditAt targetMessage

                } else if (newContent is MessageAnimation || newContent is MessageAudio || newContent is MessageDocument || newContent is MessagePhoto || newContent is MessageVideo) {

                    edit = EditMessageMedia(targetChat, targetMessage, null, newContent.asInput)

                } else if (newContent is MessageVoiceNote) {

                    edit = EditMessageCaption(targetChat, targetMessage, null, newContent.caption)

                } else if (newContent is MessageLocation) {

                    edit = EditMessageLiveLocation(targetChat, targetMessage, null, newContent.location)

                }

                if (edit == null) {

                    sudo make L.EDIT_TYPE_INVALID sendTo chatId

                    return

                }

                try {

                    syncUnit(edit)

                    sudo make L.EDIT_SUCCEED to chatId send deleteDelay()

                } catch (e: TdException) {

                    sudo make L.EDIT_FAILED.input(e.message) sendTo chatId

                }

            }

        } else {

            if (data.received.containsValue(messageId)) {

                val receivedMessageId = data.received.getKey(messageId).toLong()

                val reportMessage = sudo make {

                    inputText = L.EDITED_BY

                    replyToMessageId = receivedMessageId

                } syncTo bot.owner

                val newMessage =sudo make {

                    input = inputForward(chatId, messageId)

                } syncTo bot.owner

                data.sessions.arrayInsert(chatId, "extras", reportMessage.id)
                data.sessions.arrayInsert(chatId, "extras", newMessage.id)

            }

        }

    }

}