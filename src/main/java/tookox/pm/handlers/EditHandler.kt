package tookox.pm.handlers

/*
import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException
import tookox.core.*
import tookox.core.client.*
import tookox.core.utils.*
import tookox.pm.PmBot
import tookox.pm.PmData

class EditHandler : TdBotHandler() {

    override val sudo: PmBot get() = super.sudo as PmBot

    val bot get() = sudo.bot
    val data get() = sudo.data

    override suspend fun onMessageContent(chatId: Long, messageId: Long, newContent: TdApi.MessageContent) {

        val L = Lang.get(bot.owner)

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

                    edit = EditMessageMedia(targetChat, targetMessage, null, Fn.convertToInput(newContent))

                } else if (newContent is MessageVoiceNote) {

                    edit = EditMessageCaption(targetChat, targetMessage, null, (newContent as MessageVoiceNote).caption)

                } else if (newContent is MessageLocation) {

                    edit = EditMessageLiveLocation(targetChat, targetMessage, null, (newContent as MessageLocation).location)

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

 */