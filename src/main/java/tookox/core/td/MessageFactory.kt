package tookox.core.td

import cn.hutool.core.builder.Builder
import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi.*
import tooko.td.client.TdException
import tookox.Launcher
import tookox.core.WriteOnlyField
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdCallback
import twitter4j.TwitterException
import java.util.*


val String.asText: FormattedText get() = FormattedText(this, null)
val String.asHtml: FormattedText
    get() = with(Launcher.INSTANCE) {

        return this@asHtml.asHtml

    }
val String.asMarkdown: FormattedText
    get() = with(Launcher.INSTANCE) {

        return this@asMarkdown.asMarkdown

    }

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

}

fun removeKeyboard(isPersional: Boolean = true): ReplyMarkupRemoveKeyboard {

    return ReplyMarkupRemoveKeyboard(isPersional)

}

fun forceReply(isPersional: Boolean = true): ReplyMarkupForceReply {

    return ReplyMarkupForceReply(isPersional)

}

fun inputText(textFormatted: FormattedText? = null, block: (TextBuilder.() -> Unit)? = null): InputMessageText {

    return TextBuilder(textFormatted).apply(block).build()

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

class MessageFactory(val context: TdAbsHandler) {

    lateinit var chatId: Number
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

    infix fun to(chatId: Number): MessageFactory {

        this.chatId = chatId

        return this

    }

    var inputText by WriteOnlyField<String> {

        input = plainText(it)

    }

    var inputHtml by WriteOnlyField<String> {

        input = htmlText(it)

    }

    var inputMarkdownby by WriteOnlyField<String> {

        input = markdownText(it)

    }

    var inputPhoto by WriteOnlyField<String> {

        input = photo(it) {

            _captionInterface = this

        }

    }

    var inputPhotoId: String
        set(value) {
            input = photoId(value)
        }
        get() = throw IllegalAccessError()

    private lateinit var _captionInterface: CaptionSetter

    var caption by WriteOnlyField<FormattedText> {

        _captionInterface.set(it)

    }

    var captionText by WriteOnlyField<String> {

        _captionInterface.set(it.asText)

    }

    var captionHtml by WriteOnlyField<String> {

        with(context) {

            _captionInterface.set(it.asHtml)

        }

    }

    var captionMarkdown by WriteOnlyField<String> {

        with(context) {

            _captionInterface.set(it.asMarkdown)

        }

    }

    fun plainText(text: String, block: TextBuilder.() -> Unit = {}): InputMessageText {

        return inputText(text.asText, block)
    }

    fun htmlText(text: String, block: TextBuilder.() -> Unit = {}): InputMessageText {

        return inputText(text.asHtml, block)

    }

    fun markdownText(text: String, block: TextBuilder.() -> Unit = {}): InputMessageText {

        return inputText(text.asMarkdown, block)

    }

    interface CaptionSetter {

        fun set(caption: FormattedText)

    }

    class PhotoBuilder(val photo: InputMessagePhoto) : CaptionSetter {

        var caption by WriteOnlyField(::set)

        override fun set(caption: FormattedText) {

            photo.caption = caption

        }

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

    private fun mkOptions(): SendMessageOptions {

        return SendMessageOptions(disableNotification, fromBackground, schedulingState)

    }

    infix fun postTo(chatId: Number): Message {

        return context.post(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input))

    }

    infix fun sendTo(chatId: Number): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1)

    }

    infix fun send(handler: ((Message) -> Unit)): TdCallback<Message> {

        return context.send(SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1, handler)

    }

}