@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

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
