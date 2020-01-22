/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tooko.core.client

import cn.hutool.core.thread.ThreadUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import tooko.core.*

class TdCallback<T>(stackIgnore: Int = 0, private var handler: (suspend CoroutineScope.(T) -> Unit)?) {

    private val stackTrace: Array<StackTraceElement> = ThreadUtil.getStackTrace().shift(3 + stackIgnore)

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