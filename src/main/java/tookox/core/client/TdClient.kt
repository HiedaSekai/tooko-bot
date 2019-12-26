package tookox.core.client

import cn.hutool.core.thread.ThreadUtil
import cn.hutool.core.util.ArrayUtil
import tooko.main.Env
import tooko.main.Fn
import tooko.td.Client
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException
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

open class TdClient(private val options: TdOptions) : TdAbsHandler {

    override var client = this
        set

    override fun onLoad(client: TdClient) = Unit

    private val td = Client()

    private val status: AtomicBoolean = AtomicBoolean(false)

    var handlers = LinkedList<TdAbsHandler>()

    private val requestId = AtomicLong(1)
    private val executionLock = ReentrantLock()

    private val callbacks = ConcurrentHashMap<Long, (isOk: Boolean, result: Object?, error: Error?) -> Unit>()
    private val messages = ConcurrentHashMap<Long, (isOk: Boolean, result: Message?, error: Error?) -> Unit>()

    val auth = AtomicBoolean(false)

    lateinit var me: User

    fun addHandler(handler: TdAbsHandler) {

        handler.onLoad(this)

        handlers.add(handler)

    }

    fun clearHandlers() {

        handlers.clear()

    }

    fun start() {

        check(!status.get()) { "已经启动." }

        clearHandlers()

        addHandler(this)

        if (!eventTask.isInitialized()) {

            eventTask.value

        }

        postAdd.add(this)

    }

    fun stop() {

        check(status.get()) { "未启动." }

        handlers.forEach { it.onDestroy() }

        postDestroy.add(this)

    }

    fun destroy() {

        if (status.get()) stop()

        check(!executionLock.isLocked) { "Client is destroyed" }

        executionLock.lock()

        td.destroyClient()

    }

    fun <T : TdAbsHandler> findHandler(clazz: KClass<T>): T {

        for (handler in handlers) {

            @Suppress("UNCHECKED_CAST")
            if (clazz.isInstance(handler)) return handler as T

        }

        error("Hanlder ${clazz.java.name} not found !")

    }

    override fun onAuthorizationState(authorizationState: AuthorizationState) {

        if (authorizationState is AuthorizationStateWaitTdlibParameters) {

            send(SetTdlibParameters(options.build()))

        } else if (authorizationState is AuthorizationStateWaitEncryptionKey) {

            send(CheckDatabaseEncryptionKey())

        } else if (authorizationState is AuthorizationStateReady) {

            try {

                me = execute(GetMe())

            } catch (e: TdException) {

                try {

                    me = execute(GetMe())

                } catch (ex: TdException) {

                    log.error(e)

                    Fn.finishEvent()

                }

            }

            log.debug("认证完成 : {}", Fn.displayName(me))

            auth.set(true)

            for (handler in handlers) handler.onLogin()

        } else if (authorizationState is AuthorizationStateLoggingOut) {

            for (handler in handlers) handler.onLogout()

        }

    }

    override fun onMessageSendSucceeded(message: Message, oldMessageId: Long) {

        try {

            val callback = messages.remove(oldMessageId)

            if (callback == null) return

            callback.invoke(true, message, null)

        } catch (e: Exception) {

            log.error(e, "TdError - Sync")

        }
    }

    override fun onMessageSendFailed(message: Message, oldMessageId: Long, errorCode: Int, errorMessage: String) {

        val callback = messages.remove(oldMessageId)

        if (callback == null) return

        try {

            callback.invoke(false, null, TdApi.Error(errorCode, errorMessage))

        } catch (e: Exception) {

            log.error(e, "TdError - Sync")

        }

    }

    fun <T : Object> execute(function: TdApi.Function): T {

        check(!executionLock.isLocked) { "ClientActor is destroyed" }

        val responseAtomicReference = AtomicReference<Object>()

        val executedAtomicBoolean = AtomicBoolean(false)

        send(function) { isOk: Boolean, result: Object?, error: Error? ->

            if (isOk) {

                responseAtomicReference.set(result)

            } else {

                responseAtomicReference.set(error)

            }

            executedAtomicBoolean.set(true)

        }

        while (!executedAtomicBoolean.get()) {

            if (Env.STOP.get()) {

                throw TdException(Error(-1, "Server Stopped"))

            }

        }

        val response = responseAtomicReference.get()

        if (response is Error) {

            throw TdException(response)

        }

        @Suppress("UNCHECKED_CAST")
        return response as T

    }

    fun <T : Object> send(function: TdApi.Function, callback: (isOk: Boolean, result: T?, error: Error?) -> Unit) {

        check(!executionLock.isLocked) { "ClientActor is destroyed" }

        val requestId = requestId.getAndIncrement()

        if (function is SendMessage) {

            callbacks[requestId] = { isOk: Boolean, result: Object?, error: Error? ->

                if (isOk) {

                    @Suppress("UNCHECKED_CAST")
                    messages[(result as Message).id] = (callback as ((isOk: Boolean, result: Message?, error: Error?) -> Unit))

                }

            }

        } else {

            callbacks[requestId] = { isOk: Boolean, result: Object?, error: Error? ->

                @Suppress("UNCHECKED_CAST")
                callback.invoke(isOk, result as T?, error)

            }

        }

        send(requestId, function)

    }

    fun send(function: TdApi.Function): Long {

        check(!executionLock.isLocked) { "Client is destroyed" }

        val requestId = requestId.getAndIncrement()

        var stackTrace = ThreadUtil.getStackTrace()

        stackTrace = ArrayUtil.sub<StackTraceElement>(stackTrace, 3, stackTrace.size)

        callbacks[requestId] = { isOk, _, error ->

            if (!isOk) {

                val exception = TdException(error!!)

                exception.stackTrace = stackTrace

                log.warn(exception)

            }

        }

        send(requestId, function)

        return requestId
    }

    fun send(requestId: Long, function: TdApi.Function) {

        check(!executionLock.isLocked) { "Client is destroyed" }

        td.send(requestId, function)

    }


    companion object {

        val log = createLog("TD")

        val postAdd = LinkedList<TdClient>()
        val postDestroy = LinkedList<TdClient>()
        var mainTimer = Timer("Mian Timer")
        var clients = LinkedList<TdClient>()
        var publicPool = ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, LinkedBlockingQueue())
        var asyncPool = ThreadPoolExecutor(8, 8, 15, TimeUnit.SECONDS, LinkedBlockingQueue())

        val eventTask = lazy {

            Thread {

                while (true) {

                    synchronized(postAdd) {

                        val iter = postAdd.iterator()

                        while (iter.hasNext()) {

                            val toAdd = iter.next()

                            clients.add(toAdd)

                            iter.remove()

                        }

                    }

                    synchronized(postDestroy) {

                        val iter = postDestroy.iterator()

                        while (iter.hasNext()) {

                            val toDestroy = iter.next()

                            clients.remove(toDestroy)

                            toDestroy.status.set(true)

                            iter.remove()

                        }

                    }

                    if (clients.isEmpty()) {

                        ThreadUtil.safeSleep(1000)

                        continue

                    }

                    for (client in clients) {

                        val responseList = client.td.receive(0.0, 4)

                        responseList.forEach { event: Client.Event ->

                            if (event.requestId != 0L) {

                                if (!client.callbacks.containsKey(event.requestId)) {

                                    if (event.event is Error) {

                                        val err = event.event as Error

                                        log.warn(err.code.toString() + " " + err.message)

                                    }

                                    return@forEach
                                }

                                try {

                                    val callback = client.callbacks.remove(event.requestId)!!

                                    val isOk = !(event.event is Error)

                                    callback.invoke(isOk, if (isOk) event.event else null, if (isOk) null else event.event as Error)

                                } catch (e: Exception) {

                                    log.error(e, "TdError - Sync")

                                }

                                return@forEach

                            }

                            client.handlers.forEach {

                                it.onEvent(event.event)

                            }

                        }

                    }

                }

            }.apply {

                start()

            }

        }

    }

}