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

package tooko.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tooko.core.client.*

/**
 * Sets the current network type
 * Can be called before authorization
 * Calling this method forces all network connections to reopen, mitigating the delay in switching between different networks, so it should be called whenever the network is changed, even if the network type remains the same
 * Network type is used to check whether the library can use the network at all and also for collecting detailed network data usage statistics
 *
 * @type - The new network type
 *         By default, networkTypeOther
 */
suspend fun TdAbsHandler.setNetworkType(
    type: NetworkType? = null
) = sync<Ok>(
    SetNetworkType(
        type
    )
)

suspend fun TdAbsHandler.setNetworkTypeOrNull(
    type: NetworkType? = null
) = syncOrNull<Ok>(
    SetNetworkType(
        type
    )
)

fun TdAbsHandler.setNetworkType(
    type: NetworkType? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetNetworkType(
        type
    ),block = block
)

/**
 * Returns network data usage statistics
 * Can be called before authorization
 *
 * @onlyCurrent - If true, returns only data for the current library launch
 */
suspend fun TdAbsHandler.getNetworkStatistics(
    onlyCurrent: Boolean
) = sync<NetworkStatistics>(
    GetNetworkStatistics(
        onlyCurrent
    )
)

suspend fun TdAbsHandler.getNetworkStatisticsOrNull(
    onlyCurrent: Boolean
) = syncOrNull<NetworkStatistics>(
    GetNetworkStatistics(
        onlyCurrent
    )
)

fun TdAbsHandler.getNetworkStatistics(
    onlyCurrent: Boolean,
    block: (suspend CoroutineScope.(NetworkStatistics) -> Unit)
) = send(
    GetNetworkStatistics(
        onlyCurrent
    ),block = block
)

/**
 * Adds the specified data to data usage statistics
 * Can be called before authorization
 *
 * @entry - The network statistics entry with the data to be added to statistics
 */
suspend fun TdAbsHandler.addNetworkStatistics(
    entry: NetworkStatisticsEntry? = null
) = sync<Ok>(
    AddNetworkStatistics(
        entry
    )
)

suspend fun TdAbsHandler.addNetworkStatisticsOrNull(
    entry: NetworkStatisticsEntry? = null
) = syncOrNull<Ok>(
    AddNetworkStatistics(
        entry
    )
)

fun TdAbsHandler.addNetworkStatistics(
    entry: NetworkStatisticsEntry? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AddNetworkStatistics(
        entry
    ),block = block
)

/**
 * Resets all network data usage statistics to zero
 * Can be called before authorization
 */
suspend fun TdAbsHandler.resetNetworkStatistics() = sync<Ok>(
    ResetNetworkStatistics()
)

suspend fun TdAbsHandler.resetNetworkStatisticsOrNull() = syncOrNull<Ok>(
    ResetNetworkStatistics()
)

fun TdAbsHandler.resetNetworkStatistics(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    ResetNetworkStatistics(),block = block
)
