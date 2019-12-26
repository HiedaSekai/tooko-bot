package tookox.core.client

import tooko.td.TdApi
import tooko.td.client.TdException

interface TdBotAbsHandler : TdAbsHandler {

    override val client: TdBot

    fun onLoginFailed(ex: TdException)

    fun initFunction(functions: Array<String>) {

        functions.forEach {

            client.functions.put(it, this)

        }

    }


    fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)

}