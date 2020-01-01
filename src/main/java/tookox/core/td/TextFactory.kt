package tookox.core.td

import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdCallback
import twitter4j.TwitterException

infix fun TdAbsHandler.make(block: MessageFactory.() -> Unit): MessageFactory {

    return MessageFactory(this).apply(block)

}

infix fun TdAbsHandler.make(text: TdApi.FormattedText): MessageFactory {

    return make { input = MessageFactory.TextBuilder(text).makeInput() }

}

infix fun TdAbsHandler.make(text: String): MessageFactory {

    return make { inputText = text }

}

infix fun TdAbsHandler.make(ex: Throwable): MessageFactory {

    val text = if (ex is TwitterException) {

        Fn.parseTwitterException(Lang.DEFAULT, ex)

    } else Fn.parseError(ex)

    return make { inputText = text }

}


class MessageFactory(val context: TdAbsHandler) {

    lateinit var chatId: Number
    lateinit var input: TdApi.InputMessageContent

    var replyToMessageId = 0L
    var disableNotification = false
    var fromBackground = false
    var replyMarkup: TdApi.ReplyMarkup? = null

    var schedulingState: TdApi.MessageSchedulingState? = null

    val WHEN_ONLINE = -1

    var sendAt: Int

        set(value) {

            schedulingState = if (sendAt > 0) TdApi.MessageSchedulingStateSendAtDate(value) else TdApi.MessageSchedulingStateSendWhenOnline()

        }

        get() = throw IllegalAccessError()

    infix fun sendAt(date: Number): MessageFactory {

        sendAt = date.toInt()

        return this

    }

    class TextBuilder(val text: TdApi.FormattedText) {

        var enableWebPagePreview = false

        var clearDraft = false

        internal fun makeInput(): TdApi.InputMessageText {

            return TdApi.InputMessageText(text, !enableWebPagePreview, clearDraft)

        }

    }

    infix fun to(chatId: Number): MessageFactory {

        this.chatId = chatId

        return this

    }

    var inputText: String
        set(value) {

            input = plainText(value)

        }
        get() = throw IllegalAccessError()

    var inputHtml: String
        set(value) {

            input = htmlText(value)

        }
        get() = throw IllegalAccessError()

    var inputMarkdown: String
        set(value) {

            input = markdownText(value)

        }
        get() = throw IllegalAccessError()

    fun plainText(text: String, block: TextBuilder.() -> Unit = {}): TdApi.InputMessageText {

        return TextBuilder(TdApi.FormattedText(text, arrayOfNulls<TdApi.TextEntity>(0))).apply(block).makeInput()

    }

    fun htmlText(text: String, block: TextBuilder.() -> Unit = {}): TdApi.InputMessageText {

        return TextBuilder(context.post(TdApi.ParseTextEntities(text, TdApi.TextParseModeHTML()))).apply(block).makeInput()

    }

    fun markdownText(text: String, block: TextBuilder.() -> Unit = {}): TdApi.InputMessageText {

        return TextBuilder(context.post(TdApi.ParseTextEntities(text, TdApi.TextParseModeMarkdown()))).apply(block).makeInput()

    }

    private fun mkOptions(): TdApi.SendMessageOptions {

        return TdApi.SendMessageOptions(disableNotification, fromBackground, schedulingState)

    }

    infix fun postTo(chatId: Number): TdApi.Message {

        return context.post(TdApi.SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input))

    }

    infix fun sendTo(chatId: Number): TdCallback<TdApi.Message> {

        return context.send(TdApi.SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1)

    }

    infix fun send(handler: ((TdApi.Message) -> Unit)): TdCallback<TdApi.Message> {

        return context.send(TdApi.SendMessage(chatId.toLong(), replyToMessageId, mkOptions(), replyMarkup, input), 1, handler)

    }

}