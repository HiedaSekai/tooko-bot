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