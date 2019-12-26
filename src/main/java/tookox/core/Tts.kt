package tookox.core

import chat.tamtam.botapi.model.NewMessageBody
import tookox.core.tt.TtHandler

val TtHandler.api get() = client.api

fun TtHandler.postUserText(userId: Long, text: String, enableLinkPreView: Boolean = false) {

    api.sendMessage(NewMessageBody(text, null, null))
            .userId(userId)
            .disableLinkPreview(!enableLinkPreView)
            .execute()

}

fun TtHandler.postChatText(chatId: Long, text: String, enableLinkPreView: Boolean = false) {

    api.sendMessage(NewMessageBody(text, null, null))
            .chatId(chatId)
            .disableLinkPreview(!enableLinkPreView)
            .execute()

}