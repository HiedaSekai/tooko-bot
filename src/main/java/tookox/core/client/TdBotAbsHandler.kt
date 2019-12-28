package tookox.core.client

import tooko.td.TdApi

interface TdBotAbsHandler : TdAbsHandler {

    override val client: TdBot

    fun initFunction(vararg functions: String) {

        functions.forEach {

            client.functions.put(it, this)

        }

    }


    fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)

}