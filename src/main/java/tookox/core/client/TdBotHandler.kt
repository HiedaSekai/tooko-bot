package tookox.core.client

import tooko.td.TdApi
import tooko.td.client.TdException

open class TdBotHandler : TdHandler(), TdBotAbsHandler {

    override val client: TdBot get() = super.client as TdBot

    override fun onLoginFailed(ex: TdException) = Unit
    override fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit

    open fun onFunction() = Unit

    fun initFunction() {}

}