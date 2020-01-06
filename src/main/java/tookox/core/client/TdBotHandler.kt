package tookox.core.client

import tooko.td.TdApi

open class TdBotHandler : TdHandler(), TdBotAbsHandler {

    override val sudo: TdBot get() = super.sudo as TdBot

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit
    override suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit

}