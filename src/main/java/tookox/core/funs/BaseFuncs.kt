package tookox.core.funs

import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.blod
import tookox.core.client.TdBotHandler
import tookox.core.fromChannel
import tookox.core.fromPrivate
import tookox.core.td.make
import java.util.*

class BaseFuncs : TdBotHandler() {

    val functions = LinkedList<TdBotHandler>()

    override fun onLoad() = functions.forEach(client::addHandler)

    fun function(name: String, function: (userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) -> Unit) {

        functions.add(object : TdBotHandler() {

            override fun onLoad() = initFunction(name)

            override fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = function(userId, chatId, message, function, param, params, originParams)

        })

    }

    val ping = function("ping") {

        userId, chatId, _, _, _, _, _ ->

        sudo make Lang.get(userId).FN_PING_RESULT sendTo chatId

    }

    val getId = function("id") {

        userId, chatId, message, function, param, params, originParams ->

        val L = Lang.get(userId)

        if (params.isEmpty()) {

            if (message.fromPrivate) {

                sudo make "${"UID".blod} : $userId" sendTo chatId

            } else if (message.fromChannel) {


            }

        }

    }

}

private infix fun Unit.el(function: () -> Unit) {

}
