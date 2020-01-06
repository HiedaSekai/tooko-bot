@file:Suppress("unused")

package tookox.core.utils

import cn.hutool.core.builder.Builder
import kotlinx.coroutines.CoroutineScope
import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi.*
import tooko.td.client.TdException
import tookox.core.WriteOnlyField
import tookox.core.applyIfNot
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdCallback
import tookox.core.getValue
import tookox.core.setValue
import twitter4j.TwitterException
import java.util.*


val String.asText: FormattedText get() = TdAbsHandler.syncRaw(GetTextEntities(this))
val String.asHtml: FormattedText get() = TdAbsHandler.syncRaw(ParseTextEntities(this, TextParseModeHTML()))
val String.asMarkdown: FormattedText get() = TdAbsHandler.syncRaw(ParseTextEntities(this, TextParseModeMarkdown()))

infix fun TdAbsHandler.make(block: MessageFactory.() -> Unit): MessageFactory {

    return MessageFactory(this).apply(block)

}

infix fun TdAbsHandler.make(text: FormattedText): MessageFactory {

    return make { input = inputText(text) }

}

infix fun TdAbsHandler.make(text: String): MessageFactory {

    return make { inputText = text }

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

        return InputMessageText(textFormatted, !enableWebPagePreview, clearDraft)

    }

}

interface CaptionInterface {

    var caption: FormattedText?

}

class MessageFactory(val context: TdAbsHandler) : CaptionInterface {

    lateinit var chatId: Number
    lateinit var messageId: Number
    lateinit var input: InputMessageContent

    var replyToMessageId = 0L
    var disableNotification = false
    var fromBackground = false
    var replyMarkup: ReplyMarkup? = null

    var schedulingState: MessageSchedulingState? = null

    val WHEN_ONLINE = -1

    var sendAt by WriteOnlyField<Int> {

        schedulingState = if (it > 0) MessageSchedulingStateSendAtDate(it) else MessageSchedulingStateSendWhenOnline()

    }

    infix fun sendAt(date: Number): MessageFactory {

        sendAt = date.toInt()

        return this

    }

    infix fun at(chatId: Number): MessageFactory {

        this.chatId = chatId

        return this

    }

    infix fun to(messageId: Number): MessageFactory {

        this.messageId = messageId

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

    private lateinit var _captionInterface: CaptionInterface

    override var caption
        get() = _captionInterface.caption
        set(value) = _captionInterface::caption.set(value)

    var CaptionInterface.captionText by WriteOnlyField<String> {

        caption = it.asText

    }

    var CaptionInterface.captionHtml by WriteOnlyField<String> {

        caption = with(context) { it.asHtml }

    }

    var CaptionInterface.captionMarkdown by WriteOnlyField<String> {

        caption = with(context) { it.asMarkdown }

    }

    inner class PhotoBuilder(val photo: InputMessagePhoto) : CaptionInterface {

        var thumbnail: InputThumbnail? by photo::thumbnail

        var addedStickerFileIds: IntArray by photo::addedStickerFileIds

        var width by photo::width

        var height by photo::height

        var ttl by photo::ttl

        override var caption: FormattedText? by photo::caption

    }

    fun photo(path: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto(InputFileLocal(path), null, null, 0, 0, null, 0).apply {

            block?.invoke(PhotoBuilder((this)))

        }

    }

    fun photoId(fileId: String, block: (PhotoBuilder.() -> Unit)? = null): InputMessagePhoto {

        return InputMessagePhoto(InputFileRemote(fileId), null, null, 0, 0, null, 0).apply {

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

    suspend infix fun syncTo(chatId: Number): Message {

        return context.sync(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input))

    }

    infix fun sendTo(chatId: Number): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1)

    }

    infix fun send(handler: (suspend CoroutineScope.(Message) -> Unit)): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1, handler)

    }

    suspend fun syncEditTo(chatId: Number, messageId: Long): Message = context.sync(EditMessageText(chatId.toLong(), messageId, replyMarkup, input))

    suspend infix fun syncEditAt(chatId: Number): Message = context.sync(EditMessageText(chatId.toLong(), messageId.toLong(), replyMarkup, input))

    suspend infix fun syncEditTo(messageId: Long): Message = context.sync(EditMessageText(chatId.toLong(), messageId, replyMarkup, input))

    suspend infix fun syncEditTo(message: Message): Message = context.sync(EditMessageText(message.chatId, message.id, replyMarkup, input))

    fun editTo(chatId: Number, messageId: Long): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId, replyMarkup, input), 1)

    }

    infix fun editAt(chatId: Number): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId.toLong(), replyMarkup, input), 1)

    }

    infix fun editTo(messageId: Long): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId, replyMarkup, input), 1)

    }

    infix fun editTo(message: Message): TdCallback<Message> {

        return context.send(EditMessageText(message.chatId, message.id, replyMarkup, input), 1)

    }

    infix fun edit(handler: (suspend CoroutineScope.(Message) -> Unit)): TdCallback<Message> {

        return context.send(EditMessageText(chatId.toLong(), messageId.toLong(), replyMarkup, input), 1, handler)

    }

}
