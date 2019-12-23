package tookox.core.client

/*

import tooko.main.Env
import tooko.td.Client
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdClient.LogCallback
import tooko.td.client.TdClient.ReturnBack
import tooko.td.client.TdException
import tooko.td.client.TdOptions
import tookox.core.createLog
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.locks.ReentrantLock
import kotlin.reflect.KClass

class TdClient(private val options: TdOptions) : TdAbsHandler {

    private val log = createLog("TD")
    private val td = Client()
    private val status: AtomicBoolean = AtomicBoolean(false)

    var handlers = LinkedList<TdAbsHandler>();

    private val requestId = AtomicLong(1)
    private val executionLock = ReentrantLock()
    private val callbacks = ConcurrentHashMap<Long, (isOk: Boolean, result: TdApi.Object, error: Error) -> Unit>()
    private val messages = ConcurrentHashMap<Long, (isOk: Boolean, result: TdApi.Object, error: Error) -> Unit>()

    val auth = AtomicBoolean(false)

    lateinit var me: TdApi.User

    fun addHandler(handler: TdAbsHandler) {

        handler.onInit(this)

        handlers.add(handler)

    }

    @Throws(IllegalStateException::class)
    fun <T : TdAbsHandler> findHandler(clazz: KClass<T>): T {

        for (handler in handlers) {

            @Suppress("UNCHECKED_CAST")
            if (clazz.isInstance(handler)) return handler as T

        }

        throw IllegalStateException("Hanlder ${clazz.java.name} not found !")

    }

    @Throws(TdException::class)
    fun <T : Object> execute(function: TdApi.Function?): T {
        check(!executionLock.isLocked) { "ClientActor is destroyed" }
        val responseAtomicReference = AtomicReference<Object>()
        val executedAtomicBoolean = AtomicBoolean(false)
        send(function, { isOk: Boolean, result: T, error: Error ->
            if (isOk) {
                responseAtomicReference.set(result)
            } else {
                responseAtomicReference.set(error)
            }
            executedAtomicBoolean.set(true)
        })

        while (!executedAtomicBoolean.get()) {
            if (Env.STOP.get()) {
                throw TdException(Error(-1, "Server Stopped"))
            }
        }
        val response = responseAtomicReference.get()
        if (response is Error) {
            throw TdException(response)
        }
        return response as T
    }

    fun send(function: TdApi.Function?, callback: (isOk: Boolean, result: TdApi.Object, error: Error) -> Unit) {
        check(!executionLock.isLocked) { "ClientActor is destroyed" }
        val requestId = requestId.getAndIncrement()
        callbacks[requestId] = callback
        send(requestId, function)
    }

    fun send(function: TdApi.Function?): Long {
        check(!executionLock.isLocked) { "Client is destroyed" }
        val requestId = requestId.getAndIncrement()

        callbacks[requestId] = { isOk,_,_  ->
            
            if (!isOk)

        }
        send(requestId, function)
        return requestId
    }

    fun send(requestId: Long, function: TdApi.Function?) {
        check(!executionLock.isLocked) { "Client is destroyed" }
        client.send(requestId, function)
    }

    fun send(function: SendMessage?, callback: (isOk: Boolean, result: Message, error: Error) -> Unit) {
        send(function as TdApi.Function?, object : ReturnBack<Message?> {
            fun onCallback(isOk: Boolean, result: Message, error: Error) {
                if (isOk) messages[result.id] = callback
            }
        })
    }


    companion object {

        val postAdd = LinkedList<TdClient>()
        val postDestroy = LinkedList<TdClient>()
        var mainTimer = Timer("Mian Timer")
        var clients = LinkedList<TdClient>()
        var publicPool = ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, LinkedBlockingQueue())
        var asyncPool = ThreadPoolExecutor(8, 8, 15, TimeUnit.SECONDS, LinkedBlockingQueue())

    }

}

 */