package tookox.core.funs

import td.TdApi
import tookox.core.env.Lang
import tookox.core.client.*
import tookox.core.utils.*

class LICENCE : TdBotHandler() {

    override fun onLoad() = initFunction("license")

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        sudo makeHtml Lang.get(userId).LICENSE sendTo chatId

    }

}