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

@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Does nothing
 * For testing only
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdAbsHandler.testCallEmpty() = sync<Ok>(
    TestCallEmpty()
)

suspend fun TdAbsHandler.testCallEmptyOrNull() = syncOrNull<Ok>(
    TestCallEmpty()
)

fun TdAbsHandler.testCallEmpty(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TestCallEmpty(),block = block
)

/**
 * Returns the received string
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - String to return
 */
suspend fun TdAbsHandler.testCallString(
    x: String? = null
) = sync<TestString>(
    TestCallString(
        x
    )
)

suspend fun TdAbsHandler.testCallStringOrNull(
    x: String? = null
) = syncOrNull<TestString>(
    TestCallString(
        x
    )
)

fun TdAbsHandler.testCallString(
    x: String? = null,
    block: (suspend CoroutineScope.(TestString) -> Unit)
) = send(
    TestCallString(
        x
    ),block = block
)

/**
 * Returns the received bytes
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Bytes to return
 */
suspend fun TdAbsHandler.testCallBytes(
    x: ByteArray
) = sync<TestBytes>(
    TestCallBytes(
        x
    )
)

suspend fun TdAbsHandler.testCallBytesOrNull(
    x: ByteArray
) = syncOrNull<TestBytes>(
    TestCallBytes(
        x
    )
)

fun TdAbsHandler.testCallBytes(
    x: ByteArray,
    block: (suspend CoroutineScope.(TestBytes) -> Unit)
) = send(
    TestCallBytes(
        x
    ),block = block
)

/**
 * Returns the received vector of numbers
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of numbers to return
 */
suspend fun TdAbsHandler.testCallVectorInt(
    x: IntArray
) = sync<TestVectorInt>(
    TestCallVectorInt(
        x
    )
)

suspend fun TdAbsHandler.testCallVectorIntOrNull(
    x: IntArray
) = syncOrNull<TestVectorInt>(
    TestCallVectorInt(
        x
    )
)

fun TdAbsHandler.testCallVectorInt(
    x: IntArray,
    block: (suspend CoroutineScope.(TestVectorInt) -> Unit)
) = send(
    TestCallVectorInt(
        x
    ),block = block
)

/**
 * Returns the received vector of objects containing a number
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of objects to return
 */
suspend fun TdAbsHandler.testCallVectorIntObject(
    x: Array<TestInt>
) = sync<TestVectorIntObject>(
    TestCallVectorIntObject(
        x
    )
)

suspend fun TdAbsHandler.testCallVectorIntObjectOrNull(
    x: Array<TestInt>
) = syncOrNull<TestVectorIntObject>(
    TestCallVectorIntObject(
        x
    )
)

fun TdAbsHandler.testCallVectorIntObject(
    x: Array<TestInt>,
    block: (suspend CoroutineScope.(TestVectorIntObject) -> Unit)
) = send(
    TestCallVectorIntObject(
        x
    ),block = block
)

/**
 * Returns the received vector of strings
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of strings to return
 */
suspend fun TdAbsHandler.testCallVectorString(
    x: Array<String>
) = sync<TestVectorString>(
    TestCallVectorString(
        x
    )
)

suspend fun TdAbsHandler.testCallVectorStringOrNull(
    x: Array<String>
) = syncOrNull<TestVectorString>(
    TestCallVectorString(
        x
    )
)

fun TdAbsHandler.testCallVectorString(
    x: Array<String>,
    block: (suspend CoroutineScope.(TestVectorString) -> Unit)
) = send(
    TestCallVectorString(
        x
    ),block = block
)

/**
 * Returns the received vector of objects containing a string
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Vector of objects to return
 */
suspend fun TdAbsHandler.testCallVectorStringObject(
    x: Array<TestString>
) = sync<TestVectorStringObject>(
    TestCallVectorStringObject(
        x
    )
)

suspend fun TdAbsHandler.testCallVectorStringObjectOrNull(
    x: Array<TestString>
) = syncOrNull<TestVectorStringObject>(
    TestCallVectorStringObject(
        x
    )
)

fun TdAbsHandler.testCallVectorStringObject(
    x: Array<TestString>,
    block: (suspend CoroutineScope.(TestVectorStringObject) -> Unit)
) = send(
    TestCallVectorStringObject(
        x
    ),block = block
)

/**
 * Returns the squared received number
 * For testing only
 * This is an offline method
 * Can be called before authorization
 *
 * @x - Number to square
 */
suspend fun TdAbsHandler.testSquareInt(
    x: Int
) = sync<TestInt>(
    TestSquareInt(
        x
    )
)

suspend fun TdAbsHandler.testSquareIntOrNull(
    x: Int
) = syncOrNull<TestInt>(
    TestSquareInt(
        x
    )
)

fun TdAbsHandler.testSquareInt(
    x: Int,
    block: (suspend CoroutineScope.(TestInt) -> Unit)
) = send(
    TestSquareInt(
        x
    ),block = block
)

/**
 * Sends a simple network request to the Telegram servers
 * For testing only
 * Can be called before authorization
 */
suspend fun TdAbsHandler.testNetwork() = sync<Ok>(
    TestNetwork()
)

suspend fun TdAbsHandler.testNetworkOrNull() = syncOrNull<Ok>(
    TestNetwork()
)

fun TdAbsHandler.testNetwork(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TestNetwork(),block = block
)

/**
 * Sends a simple network request to the Telegram servers via proxy
 * For testing only
 * Can be called before authorization
 *
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @type - Proxy type
 * @dcId - Identifier of a datacenter, with which to test connection
 * @timeout - The maximum overall timeout for the request
 */
suspend fun TdAbsHandler.testProxy(
    server: String? = null,
    port: Int,
    type: ProxyType? = null,
    dcId: Int,
    timeout: Double
) = sync<Ok>(
    TestProxy(
        server,
        port,
        type,
        dcId,
        timeout
    )
)

suspend fun TdAbsHandler.testProxyOrNull(
    server: String? = null,
    port: Int,
    type: ProxyType? = null,
    dcId: Int,
    timeout: Double
) = syncOrNull<Ok>(
    TestProxy(
        server,
        port,
        type,
        dcId,
        timeout
    )
)

fun TdAbsHandler.testProxy(
    server: String? = null,
    port: Int,
    type: ProxyType? = null,
    dcId: Int,
    timeout: Double,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TestProxy(
        server,
        port,
        type,
        dcId,
        timeout
    ),block = block
)

/**
 * Forces an updates.getDifference call to the Telegram servers
 * For testing only
 */
suspend fun TdAbsHandler.testGetDifference() = sync<Ok>(
    TestGetDifference()
)

suspend fun TdAbsHandler.testGetDifferenceOrNull() = syncOrNull<Ok>(
    TestGetDifference()
)

fun TdAbsHandler.testGetDifference(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    TestGetDifference(),block = block
)

/**
 * Does nothing and ensures that the Update object is used
 * For testing only
 * This is an offline method
 * Can be called before authorization
 */
suspend fun TdAbsHandler.testUseUpdate() = sync<Update>(
    TestUseUpdate()
)

suspend fun TdAbsHandler.testUseUpdateOrNull() = syncOrNull<Update>(
    TestUseUpdate()
)

fun TdAbsHandler.testUseUpdate(
    block: (suspend CoroutineScope.(Update) -> Unit)
) = send(
    TestUseUpdate(),block = block
)

/**
 * Returns the specified error and ensures that the Error object is used
 * For testing only
 * This is an offline method
 * Can be called before authorization
 * Can be called synchronously
 *
 * @error - The error to be returned
 */
suspend fun TdAbsHandler.testReturnError(
    error: Error? = null
) = sync<Error>(
    TestReturnError(
        error
    )
)

suspend fun TdAbsHandler.testReturnErrorOrNull(
    error: Error? = null
) = syncOrNull<Error>(
    TestReturnError(
        error
    )
)

fun TdAbsHandler.testReturnError(
    error: Error? = null,
    block: (suspend CoroutineScope.(Error) -> Unit)
) = send(
    TestReturnError(
        error
    ),block = block
)
