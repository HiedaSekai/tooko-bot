package tookox.core.agent

import tooko.td.TdApi
import tookox.core.client.*

class CreateAgent : TdBotHandler() {

    override fun onLoad() {

        initFunction("new_agent")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {


    }

}