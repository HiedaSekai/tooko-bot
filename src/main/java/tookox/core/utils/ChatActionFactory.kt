@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import tooko.td.TdApi.*
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