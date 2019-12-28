package tookox.core.client

import tooko.td.TdApi

open class TdBotHandler : TdHandler(), TdBotAbsHandler {

    override val client: TdBot get() = super.client as TdBot

    override fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit

}