package tookox.core.client

import tooko.td.TdApi

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

    suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>)
    suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>)

}