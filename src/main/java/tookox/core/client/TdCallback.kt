package tookox.core.client

import cn.hutool.core.thread.ThreadUtil
import tooko.main.Fn
import tooko.td.TdApi
import tooko.td.client.TdException
import tookox.core.defaultLog

class TdCallback<T : TdApi.Object>(stackIgnore: Int = 0, private var handler: ((T) -> Unit)?) {

    private val stackTrace: Array<StackTraceElement> = Fn.shift(ThreadUtil.getStackTrace(), 3 + stackIgnore)

    private var errorHandler: (TdException) -> Unit = {

        defaultLog.warn(it)

    }

    infix fun onError(handler: (TdException) -> Unit): TdCallback<T> {

        this.errorHandler = handler

        return this

    }

    @Suppress("UNCHECKED_CAST")
    fun postResult(result: TdApi.Object) = handler?.invoke(result as T)

    fun postError(error: TdException) = errorHandler.invoke(error.also { it.stackTrace = stackTrace })

}