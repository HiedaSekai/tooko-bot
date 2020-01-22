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
 * Returns information about a basic group by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdAbsHandler.getBasicGroup(
    basicGroupId: Int
) = sync<BasicGroup>(
    GetBasicGroup(
        basicGroupId
    )
)

suspend fun TdAbsHandler.getBasicGroupOrNull(
    basicGroupId: Int
) = syncOrNull<BasicGroup>(
    GetBasicGroup(
        basicGroupId
    )
)

fun TdAbsHandler.getBasicGroup(
    basicGroupId: Int,
    block: (suspend CoroutineScope.(BasicGroup) -> Unit)
) = send(
    GetBasicGroup(
        basicGroupId
    ),block = block
)

/**
 * Returns full information about a basic group by its identifier
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdAbsHandler.getBasicGroupFullInfo(
    basicGroupId: Int
) = sync<BasicGroupFullInfo>(
    GetBasicGroupFullInfo(
        basicGroupId
    )
)

suspend fun TdAbsHandler.getBasicGroupFullInfoOrNull(
    basicGroupId: Int
) = syncOrNull<BasicGroupFullInfo>(
    GetBasicGroupFullInfo(
        basicGroupId
    )
)

fun TdAbsHandler.getBasicGroupFullInfo(
    basicGroupId: Int,
    block: (suspend CoroutineScope.(BasicGroupFullInfo) -> Unit)
) = send(
    GetBasicGroupFullInfo(
        basicGroupId
    ),block = block
)
