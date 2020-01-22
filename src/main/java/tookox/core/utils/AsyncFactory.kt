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

@file:Suppress("unused")

package tookox.core.utils

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class AsyncFactory<T> : LinkedList<Deferred<T>>() {

    fun execute(context: CoroutineContext = EmptyCoroutineContext,
                block: suspend CoroutineScope.() -> T
    ) {
        add(GlobalScope.async(context, block = block))
    }

    fun executeIo(block: suspend CoroutineScope.() -> T) = execute(Dispatchers.IO, block)
    fun executeUnconfined(block: suspend CoroutineScope.() -> T) = execute(Dispatchers.Unconfined, block)

}

fun <T> mkAsync() = AsyncFactory<T>()