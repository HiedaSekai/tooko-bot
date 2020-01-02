package tookox.core.funs

import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.client.TdBotHandler
import tookox.core.td.make

class LICENCE : TdBotHandler() {

    override fun onLoad() = initFunction("license")

    override fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        sudo make Lang.get(userId).LICENSE sendTo chatId

    }

}