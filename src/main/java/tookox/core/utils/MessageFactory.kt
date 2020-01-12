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

import cn.hutool.core.builder.Builder
import cn.hutool.core.util.ArrayUtil
import kotlinx.coroutines.CoroutineScope
import td.TdApi.*
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.raw.*
import tookox.core.syncRaw
import twitter4j.TwitterException
import java.util.*
import kotlin.properties.Delegates


val String.asText: FormattedText get() = FormattedText(this, arrayOf())
val String.asHtml: FormattedText get() = syncRaw(ParseTextEntities(this, TextParseModeHTML()))
val String.asMarkdown: FormattedText get() = syncRaw(ParseTextEntities(this, TextParseModeMarkdown()))
val String.asMarkdownV2: FormattedText get() = syncRaw(ParseTextEntities(this, TextParseModeMarkdown(2)))

infix fun TdAbsHandler.make(block: MessageFactory.() -> Unit): MessageFactory {

    return MessageFactory(this).apply(block)

}

infix fun TdAbsHandler.make(inputContent: InputMessageContent): MessageFactory {

    return make { input = inputContent }

}


infix fun TdAbsHandler.make(text: FormattedText): MessageFactory {

    return make { input = inputText(text) }

}

infix fun TdAbsHandler.make(text: String): MessageFactory {

    return make { inputText = text }

}

infix fun TdAbsHandler.makeHtml(text: String): MessageFactory {

    return make { inputHtml = text }

}

infix fun TdAbsHandler.makeMd(text: String): MessageFactory {

    return make { inputMarkdown = text }

}

infix fun TdAbsHandler.makeFile(text: String): MessageFactory {

    return make { inputFile = text }

}

infix fun TdAbsHandler.makeFileId(text: String): MessageFactory {

    return make { inputFileId = text }

}

infix fun TdAbsHandler.makePhoto(text: String): MessageFactory {

    return make { inputPhoto = text }

}

infix fun TdAbsHandler.makePhotoId(text: String): MessageFactory {

    return make { inputPhotoId = text }

}

fun TdAbsHandler.makeForward(chatId: Number, messageId: Long): MessageFactory {

    return make { input = inputForward(chatId, messageId) }

}

infix fun TdAbsHandler.makeForward(message: Message): MessageFactory {

    return make { input = inputForward(message) }

}


infix fun TdAbsHandler.make(ex: Throwable): MessageFactory {

    val text = if (ex is TwitterException) {

        Fn.parseTwitterException(Lang.DEFAULT, ex)

    } else if (ex is TdException && ex.code == -1) {

        Lang.DEFAULT.SERVER_CLOSING

    } else Fn.parseError(ex)

    return make { inputText = text }

}

fun inlineButton(block: InlineButtonBuilder.() -> Unit): ReplyMarkupInlineKeyboard {

    return InlineButtonBuilder().apply(block).build()

}

class InlineButtonBuilder : LinkedList<InlineButtonBuilder.Line>(), Builder<ReplyMarkupInlineKeyboard> {

    class Line : LinkedList<InlineKeyboardButton>() {

        fun urlButton(text: String, url: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeUrl(url)))

        }

        fun loginUrlButton(text: String, url: String, id: Int, forwardText: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeLoginUrl(url, id, forwardText)))

        }

        fun gameButton(text: String) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeCallbackGame()))

        }

        fun switchButton(text: String, query: String, inCurrentChat: Boolean = true) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeSwitchInline(query, inCurrentChat)))

        }

        fun dataButton(text: String, id: Int, subId: Int, vararg dataArray: ByteArray) {

            add(InlineKeyboardButton(text, InlineKeyboardButtonTypeCallback(Fn.mkData(id, subId, *dataArray))))

        }

    }

    fun newLine(block: (Line.() -> Unit)? = null): Line {

        return Line().apply {

            block?.invoke(this)

            add(this)

        }

    }

    fun urlLine(text: String, url: String) = newLine().urlButton(text, url)

    fun loginUrlLine(text: String, url: String, id: Int, forwardText: String) = newLine().loginUrlButton(text, url, id, forwardText)

    fun gameLine(text: String) = newLine().gameButton(text)

    fun switchLine(text: String, query: String, inCurrentChat: Boolean = true) = newLine().switchButton(text, query, inCurrentChat)

    fun dataLine(text: String, id: Int, subId: Int, vararg dataArray: ByteArray) = newLine().dataButton(text, id, subId, *dataArray)

    override fun build(): ReplyMarkupInlineKeyboard {

        return ReplyMarkupInlineKeyboard(map { it.toTypedArray() }.toTypedArray())

    }

    override fun isEmpty(): Boolean {

        if (super.isEmpty()) return true

        forEach { if (!it.isEmpty()) return false }

        return true

    }

}


fun keyboadButton(block: KeyboadButtonBuilder.() -> Unit): ReplyMarkupShowKeyboard {

    return KeyboadButtonBuilder().apply(block).build()

}

class KeyboadButtonBuilder : LinkedList<KeyboadButtonBuilder.Line>(), Builder<ReplyMarkupShowKeyboard> {

    class Line : LinkedList<KeyboardButton>() {

        fun textButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeText()))

        }

        fun locationRequestButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeRequestLocation()))

        }

        fun phoneNumberRequestButton(text: String) {

            add(KeyboardButton(text, KeyboardButtonTypeRequestPhoneNumber()))

        }

    }

    fun newLine(block: (Line.() -> Unit)? = null): Line {

        return Line().apply {

            block?.invoke(this)

            add(this)

        }

    }

    fun textLine(text: String) = newLine().textButton(text)
    fun locationRequestLine(text: String) = newLine().locationRequestButton(text)
    fun phoneNumberRequestLine(text: String) = newLine().phoneNumberRequestButton(text)

    var resizeKeyboard = true

    var oneTime = true

    var isPersonal = true

    override fun build(): ReplyMarkupShowKeyboard {

        return ReplyMarkupShowKeyboard(
                map { it.toTypedArray() }.toTypedArray(),
                resizeKeyboard, oneTime, isPersonal
        )

    }

    override fun isEmpty(): Boolean {

        if (super.isEmpty()) return true

        forEach { if (!it.isEmpty()) return false }

        return true

    }

}

fun removeKeyboard(isPersional: Boolean = true): ReplyMarkupRemoveKeyboard {

    return ReplyMarkupRemoveKeyboard(isPersional)

}

fun forceReply(isPersional: Boolean = true): ReplyMarkupForceReply {

    return ReplyMarkupForceReply(isPersional)

}

fun inputText(textFormatted: FormattedText? = null, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return TextBuilder(textFormatted).applyIfNot(block == null, block).build()

}

fun inputPlainText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asText, block)
}

fun inputHtmlText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asHtml, block)

}

fun inputMarkdownText(text: String, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return inputText(text.asMarkdown, block)

}

class TextBuilder(var textFormatted: FormattedText? = null) : Builder<InputMessageText> {

    var text by WriteOnlyField<String> {

        textFormatted = it.asText

    }

    var textHtml by WriteOnlyField<String> {

        textFormatted = it.asHtml

    }

    var inputMarkdown by WriteOnlyField<String> {

        textFormatted = it.asMarkdown

    }

    var enableWebPagePreview = false

    var clearDraft = false

    override fun build(): InputMessageText {

        return InputMessageText(textFormatted!!, !enableWebPagePreview, clearDraft)

    }

}

fun inputForward(message: Message, block: (ForwardBuilder.() -> Unit)? = null) = inputForward(message.chatId,message.id,block)

fun inputForward(chatId: Number, messageId: Long, block: (ForwardBuilder.() -> Unit)? = null): InputMessageForwarded {

    val input = InputMessageForwarded(chatId.toLong(), messageId, false, false, false)

    return ForwardBuilder(input).applyIfNot(block == null, block).build()

}

class ForwardBuilder(val input: InputMessageForwarded) : Builder<InputMessageForwarded> {

    var chatId by input::fromChatId
    var messageId by input::messageId
    var inGameShare by input::inGameShare
    var removeCaption by input::removeCaption

    override fun build() = input
}

interface CaptionInterface {

    var caption: FormattedText?

}

const val WHEN_ONLINE = -1

class MessageFactory(val context: TdAbsHandler) : CaptionInterface {

    lateinit var chatId: Number
    var messageId by Delegates.notNull<Long>()
    lateinit var input: InputMessageContent

    var replyToMessageId = 0L

    infix fun replyTo(replyToMessageId: Long): MessageFactory {

        this.replyToMessageId = replyToMessageId

        return this

    }

    var disableNotification = false

    infix fun disNtf(disableNotification: Boolean): MessageFactory {

        this.disableNotification = disableNotification

        return this

    }

    var fromBackground = false

    infix fun fromBack(fromBackground: Boolean): MessageFactory {

        this.fromBackground = fromBackground

        return this

    }

    var replyMarkup: ReplyMarkup? = null

    infix fun withMarkup(replyMarkup: ReplyMarkup): MessageFactory {

        this.replyMarkup = replyMarkup

        return this

    }

    var schedulingState: MessageSchedulingState? = null

    var sendAt by WriteOnlyField<Int> {

        schedulingState = if (it > 0) MessageSchedulingStateSendAtDate(it) else MessageSchedulingStateSendWhenOnline()

    }

    infix fun sendAt(date: Number): MessageFactory {

        sendAt = date.toInt()

        return this

    }

    infix fun at(messageId: Long): MessageFactory {

        this.messageId = messageId

        return this

    }


    infix fun to(chatId: Number): MessageFactory {

        this.chatId = chatId

        return this

    }


    var inputText by WriteOnlyField<String> {

        input = inputPlainText(it)

    }

    var inputHtml by WriteOnlyField<String> {

        input = inputHtmlText(it)

    }

    var inputMarkdown by WriteOnlyField<String> {

        input = inputMarkdownText(it)

    }

    var inputPhoto by WriteOnlyField<String> {

        input = photo(it) { _captionInterface = this }

    }

    var inputPhotoId by WriteOnlyField<String> {

        input = photoId(it) { _captionInterface = this }

    }

    var inputFile by WriteOnlyField<String> {

        input = file(it) { _captionInterface = this }

    }

    var inputFileId by WriteOnlyField<String> {

        input = fileId(it) { _captionInterface = this }

    }

    var inputForward by WriteOnlyField<Message> {

        input = inputForward(it)

    }

    private lateinit var _captionInterface: CaptionInterface

    override var caption
        get() = _captionInterface.caption
        set(value) = _captionInterface::caption.set(value)

    var CaptionInterface.captionText by WriteOnlyField<String> {

        caption = it.asText

    }

    var CaptionInterface.captionHtml by WriteOnlyField<String> {

        caption = it.asHtml

    }

    var CaptionInterface.captionMarkdown by WriteOnlyField<String> {

        caption = it.asMarkdown

    }


    infix fun caption(caption: FormattedText): MessageFactory {

        this.caption = caption

        return this

    }

    infix fun captionText(caption: String): MessageFactory {

        this.captionText = caption

        return this

    }


    infix fun captionHtml(caption: String): MessageFactory {

        this.captionHtml = caption

        return this

    }

    infix fun captionMd(caption: String): MessageFactory {

        this.captionMarkdown = caption

        return this

    }

    inner class PhotoBuilder(val photo: InputMessagePhoto) : CaptionInterface {

        var thumbnail by photo::thumbnail

        var addedStickerFileIds by photo::addedStickerFileIds

        var width by photo::width

        var height by photo::height

        var ttl by photo::ttl

        override var caption: FormattedText? by photo::caption

    }

    fun photo(path: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto().apply {

            photo = InputFileLocal(path)

            block?.invoke(PhotoBuilder((this)))

        }

    }

    fun photoId(fileId: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto().apply {

            photo = InputFileRemote(fileId)

            block?.invoke(PhotoBuilder((this)))

        }

    }

    inner class FileBuilder(val file: InputMessageDocument) : CaptionInterface {

        var document: InputFile? by file::document

        var thumbnail: InputThumbnail? by file::thumbnail

        override var caption: FormattedText? by file::caption

    }

    fun file(file: String, block: (FileBuilder.() -> Unit)? = null): InputMessageDocument {

        return InputMessageDocument(InputFileLocal(file), null, null).applyIfNot(block == null) {

            block?.invoke(FileBuilder(this))

        }

    }

    fun fileId(fileId: String, block: (FileBuilder.() -> Unit)? = null): InputMessageDocument {

        return InputMessageDocument(InputFileRemote(fileId), null, null).applyIfNot(block == null) {

            block?.invoke(FileBuilder(this))

        }

    }

    private fun mkOptions(): SendMessageOptions {

        return SendMessageOptions(disableNotification, fromBackground, schedulingState)

    }

    infix fun mkSend(chatId: Number) = SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input)
    fun mkSend() = SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input)
    fun mkEdit(chatId: Number, messageId: Long) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    infix fun mkEditTo(chatId: Number) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    infix fun mkEditAt(messageId: Long) = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)
    fun mkEdit() = EditMessageText(chatId.toLong(), messageId, replyMarkup, input)

    suspend infix fun syncTo(chatId: Number): Message {

        return context.sync(mkSend(chatId))

    }

    infix fun sendTo(chatId: Number): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1)

    }

    infix fun send(handler: (suspend CoroutineScope.(Message) -> Unit)): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1, handler)

    }

    suspend fun syncEditTo(chatId: Number, messageId: Long): Message = context.sync(EditMessageText(chatId.toLong(), messageId, replyMarkup, input))

    suspend infix fun syncEditTo(chatId: Number): Message = context.sync(EditMessageText(chatId.toLong(), messageId.toLong(), replyMarkup, input))

    suspend infix fun syncEditTo(messageId: Long): Message = context.sync(EditMessageText(chatId.toLong(), messageId, replyMarkup, input))

    suspend infix fun syncEditTo(message: Message): Message = context.sync(EditMessageText(message.chatId, message.id, replyMarkup, input))

    fun editTo(chatId: Number, messageId: Long): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId, replyMarkup, input), 1)

    }

    infix fun editTo(chatId: Number): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId.toLong(), replyMarkup, input), 1)

    }

    infix fun editAt(messageId: Long): TdCallback<Message> {

        return context.send(mkEditAt(messageId), 1)

    }

    infix fun editTo(message: Message): TdCallback<Message> {

        return context.send(mkEdit(message.chatId, message.id), 1)

    }

    infix fun edit(handler: (suspend CoroutineScope.(Message) -> Unit)): TdCallback<Message> {

        return context.send(mkEdit(), 1, handler)

    }

    fun sendOrEditTo(send: Boolean, chatId: Long, messageId: Long) {

        if (send) sendTo(chatId) else {

            context.getMessage(chatId, messageId) {

                // TDLib Issue #859

                editTo(chatId, messageId)

            }

        }

    }

}


operator fun FormattedText.plus(text: FormattedText): FormattedText {

    val result = FormattedText(this.text + text.text,entities.takeIf { it.isNotEmpty() } ?: arrayOf())

    if (text.entities.isNotEmpty()) {

        text.entities.forEach {

            it.offset += this.text.length

        }

        ArrayUtil.append(result.entities, text, entities)

    }

    return result

}

val FormattedText.asHtml: String
    get() {

        var htmlText = ""

        var index = 0

        entities.forEach {

            if (it.offset > index) {

                htmlText += text.substring(index, it.offset)

            }

            index = it.offset + it.length

            val entityText = text.substring(it.offset, index)

            htmlText += when (it.type) {

                is TextEntityTypeBold -> entityText.asBlod
                is TextEntityTypeItalic -> entityText.asItalic
                is TextEntityTypeCode -> entityText.asCode
                is TextEntityTypePre -> entityText.asCode
                is TextEntityTypePreCode -> entityText.asCode
                is TextEntityTypeUnderline -> entityText.asUnderline
                is TextEntityTypeStrikethrough -> entityText.asDelete

                is TextEntityTypeTextUrl -> entityText.toLink((it.type as TextEntityTypeTextUrl).url)
                is TextEntityTypeMentionName -> entityText.toInlineMention((it.type as TextEntityTypeMentionName).userId)

                else -> entityText

            }

        }

        if (text.length > index) {

            htmlText += text.substring(index, text.length)

        }

        return htmlText

    }