package tookox.core.client

import cn.hutool.core.thread.ThreadUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import tooko.main.Fn
import tooko.td.client.TdException
import tookox.core.defaultLog

class TdCallback<T>(stackIgnore: Int = 0, private var handler: (suspend CoroutineScope.(T) -> Unit)?) {

    private val stackTrace: Array<StackTraceElement> = Fn.shift(ThreadUtil.getStackTrace(), 3 + stackIgnore)

    private var errorHandler: (suspend CoroutineScope.(TdException) -> Unit)? = {

        defaultLog.warn(it)

    }

    infix fun onFinish(handler: (suspend CoroutineScope.(T) -> Unit)?): TdCallback<T> {

        this.handler = handler

        return this

    }

    infix fun onError(handler: (suspend CoroutineScope.(TdException) -> Unit)?): TdCallback<T> {

        this.errorHandler = handler

        return this

    }

    @Suppress("UNCHECKED_CAST")
    suspend fun postResult(result: Any) = coroutineScope {

        handler?.invoke(this, result as T)

    }

    suspend fun postError(error: TdException) = coroutineScope {

        errorHandler?.invoke(this, error.also { it.stackTrace = stackTrace })

    }

}