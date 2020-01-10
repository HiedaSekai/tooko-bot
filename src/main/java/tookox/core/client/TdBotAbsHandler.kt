package tookox.core.client

import td.TdApi.Message
import tookox.core.env.Lang
import tookox.core.*
import tookox.core.utils.*
import java.util.*

interface TdBotAbsHandler : TdAbsHandler {

    override val sudo: TdBot

    val Message.fromPrivateOrdelete: Boolean
        get() {

            return if (fromPrivate) true else {

                sudo delete this

                false

            }

        }

    fun initFunction(vararg functions: String) {

        functions.forEach { function ->

            sudo.functions.put(function, this)?.apply {

                error("function name alredy used.")

            }

        }

    }

    fun initData(dataId: Int) {

        sudo.callbacks.put(dataId, this)?.apply {

            error("data id alredy used.")

        }

    }

    fun initPersist(persistId: Int) {

        sudo.persistHandlers.put(persistId, this)?.apply {

            error("perisst id alredy used.")

        }

    }

    fun initStartPayload(payload: String) {

        sudo.payloads.put(payload, this)?.apply {

            error("payload prefix alredy used.")

        }

    }

    fun writePersist(userId: Int, peristId: Int, subId: Int = 0, allowFunction: Boolean = false, allowCancel: Boolean = true) {

        val persist = TdPerstst(userId, peristId, subId, allowCancel, allowCancel)

        sudo.persists[userId] = persist

    }

    suspend infix fun removePersist(userId: Int) {

        sudo.persists.remove(userId)?.apply {

            sudo.persistHandlers[persistId]?.also {

                it.onPersistRemove(userId, subId)
                it.onPersistRemoveOrCancel(userId, subId)

            }

        }

    }

    suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>)
    suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>)

    suspend fun onStartPayload(userId: Int, chatId: Long, message: Message, payload: String, params: Array<String>)

    suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>)
    suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>)

    suspend fun onPersistMessage(userId: Int, chatId: Long, message: Message, subId: Int)

    suspend fun onPersistFunction(userId: Int, chatId: Long, message: Message, subId: Int, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        onPersistMessage(userId, chatId, message, subId)

    }

    suspend fun onPersistRemove(userId: Int, subId: Int)
    suspend fun onPersistTimeout(userId: Int, subId: Int)

    suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int)

    suspend fun onPersistCancel(userId: Int, chatId: Long, message: Message, subId: Int) {
    }

    suspend fun onSendCanceledMessage(userId: Int) {

        sudo make Lang.get(userId).CANCELED sendTo userId

    }

    suspend fun onSendTimeoutedMessage(userId: Int) {

        sudo make Lang.get(userId).TIMEOUTED sendTo userId

    }

    fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>)
    fun onPersistReStore(userId: Int, subId: Int, data: List<String>)

    class Reject : RuntimeException("Reject Function")

    fun rejectFunction(): Unit = throw Reject()

}