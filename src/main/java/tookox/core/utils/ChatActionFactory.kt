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

@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdCallback

val Typing = ChatActionTyping()
val UploadingVideo = ChatActionUploadingVideo()
val RecordingVoiceNote = ChatActionRecordingVoiceNote()
val UploadingVoiceNote = ChatActionUploadingVoiceNote()
val UploadingPhoto = ChatActionUploadingPhoto()
val UploadingDocument = ChatActionUploadingDocument()
val ChoosingLocation = ChatActionChoosingLocation()
val ChoosingContact = ChatActionChoosingContact()
val StartPlayingGame = ChatActionStartPlayingGame()
val RecordingVideoNote = ChatActionRecordingVideoNote()
val UploadingVideoNote = ChatActionUploadingVideoNote()
val CancelChatAction = ChatActionCancel()

infix fun TdAbsHandler.make(action: ChatAction): ChatActionFactory {

    return ChatActionFactory(this, action)

}

class ChatActionFactory(val context: TdAbsHandler, val action: ChatAction) {

    lateinit var chatId: Number

    infix fun to(chatId: Number): ChatActionFactory {

        this.chatId = chatId

        return this

    }

    suspend infix fun syncTo(chatId: Number) {

        context.syncUnit(SendChatAction(chatId.toLong(), action))

    }

    infix fun sendTo(chatId: Number): TdCallback<Ok> {

        return context.send(SendChatAction(chatId.toLong(), action), 1)

    }

    infix fun send(block: suspend CoroutineScope.(Ok) -> Unit): TdCallback<Ok> {

        return context.send(SendChatAction(chatId.toLong(), action), 1, block)

    }

}