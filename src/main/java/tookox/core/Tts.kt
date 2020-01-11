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