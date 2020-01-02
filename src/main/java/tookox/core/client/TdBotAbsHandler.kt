package tookox.core.client

import tooko.td.TdApi
import java.lang.IllegalStateException

interface TdBotAbsHandler : TdAbsHandler {

    override val sudo: TdBot

    fun initFunction(vararg functions: String) {

        functions.forEach {

            sudo.functions.put(it, this)?.apply {

                error("Function name alredy used.")

            }

        }

    }

    fun initData(dataId: Int) {

        sudo.callbacks.put(dataId, this)?.apply {

            error("Data id alredy used.")

        }

    }

    fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>)

}