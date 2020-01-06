@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.CoroutineScope
import tooko.td.TdApi.*
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdCallback

class EditButtonFactory(val context: TdAbsHandler) {

    lateinit var chatId: Number
    lateinit var messageId: Number

    var replyMarkupInlineKeyboard: ReplyMarkupInlineKeyboard? = null

    infix fun at(chatId: Number): EditButtonFactory {

        this.chatId = chatId

        return this

    }

    infix fun to(messageId: Number): EditButtonFactory {

        this.messageId = messageId

        return this

    }

    suspend fun syncEditTo(chatId: Number, messageId: Long): Message = context.sync(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard))

    suspend infix fun syncEditAt(chatId: Number): Message = context.sync(EditMessageReplyMarkup(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard))

    suspend infix fun syncEditTo(messageId: Long): Message = context.sync(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard))

    suspend infix fun syncEditTo(message: Message): Message = context.sync(EditMessageReplyMarkup(message.chatId, message.id, replyMarkupInlineKeyboard))

    fun editTo(chatId: Number, messageId: Long): TdCallback<Message> {

        return context.send(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard), 1)

    }

    infix fun editAt(chatId: Number): TdCallback<Message> {

        return context.send(EditMessageReplyMarkup(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard), 1)

    }

    infix fun editTo(messageId: Long): TdCallback<Message> {

        return context.send(EditMessageReplyMarkup(chatId.toLong(), messageId, replyMarkupInlineKeyboard), 1)

    }

    infix fun editTo(message: Message): TdCallback<Message> {

        return context.send(EditMessageReplyMarkup(message.chatId, message.id, replyMarkupInlineKeyboard), 1)

    }

    infix fun edit(handler: (suspend CoroutineScope.(Message) -> Unit)): TdCallback<Message> {

        return context.send(EditMessageReplyMarkup(chatId.toLong(), messageId.toLong(), replyMarkupInlineKeyboard), 1, handler)

    }

}

infix fun TdAbsHandler.makeInlineButton(block: (InlineButtonBuilder.() -> Unit)?): EditButtonFactory {

    return EditButtonFactory(this).apply {

        val builder = InlineButtonBuilder()

        block?.invoke(builder)

        if (!builder.isEmpty()) {

            replyMarkupInlineKeyboard = builder.build()

        }

    }

}