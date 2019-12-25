package tookox.core.tt

import chat.tamtam.botapi.model.*

open class TtHandler {

    open lateinit var client: TtBot

    open fun onLoad() {}

    open fun onDestroy() {}

    open fun onMessageCreated(message: Message, timestamp: Long) {}

    open fun onMessageCallback(callback: Callback, message: Message?, userLocale: String?, timestamp: Long) {}

    open fun onMessageEdited(message: Message, timestamp: Long) {}

    open fun onMessageRemoved(messageId: String, userId: Long, chatId: Long, timestamp: Long) {}

    open fun onBotAddedToChat(chatId: Long, user: User, timestamp: Long) {}

    open fun onBotRemovedFromChat(chatId: Long, user: User, timestamp: Long) {}

    open fun onUserAddedToChatUpdate(chatId: Long, user: User, inviterId: Long?, timestamp: Long) {}

    open fun onUserRemovedFromChat(chatId: Long, user: User, adminId: Long?, timestamp: Long) {}

    open fun onBotStarted(chatId: Long, user: User, payload: String?, userLocale: String?, timestamp: Long) {}

    open fun onChatTitleChanged(chatId: Long, user: User, title: String, timestamp: Long) {}

    open fun onConstructionRequest(user: UserWithPhoto, userLocale: String?, sessionId: String, data: String?, input: ConstructorInput, timestamp: Long) {}

    open fun onConstructed(sessionId: String, message: ConstructedMessage, timestamp: Long) {}

    open fun onMessageChatCreated(chat: Chat, messageId: String, startPayload: String, timestamp: Long) {}

}