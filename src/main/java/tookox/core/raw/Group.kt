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
    basicGroupId: Int = 0
) = sync<BasicGroup>(
    GetBasicGroup(
        basicGroupId
    )
)

/**
 * Returns information about a basic group by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdAbsHandler.getBasicGroupOrNull(
    basicGroupId: Int = 0
) = syncOrNull<BasicGroup>(
    GetBasicGroup(
        basicGroupId
    )
)

/**
 * Returns information about a basic group by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @basicGroupId - Basic group identifier
 */
fun TdAbsHandler.getBasicGroup(
    basicGroupId: Int = 0,
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
    basicGroupId: Int = 0
) = sync<BasicGroupFullInfo>(
    GetBasicGroupFullInfo(
        basicGroupId
    )
)

/**
 * Returns full information about a basic group by its identifier
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdAbsHandler.getBasicGroupFullInfoOrNull(
    basicGroupId: Int = 0
) = syncOrNull<BasicGroupFullInfo>(
    GetBasicGroupFullInfo(
        basicGroupId
    )
)

/**
 * Returns full information about a basic group by its identifier
 *
 * @basicGroupId - Basic group identifier
 */
fun TdAbsHandler.getBasicGroupFullInfo(
    basicGroupId: Int = 0,
    block: (suspend CoroutineScope.(BasicGroupFullInfo) -> Unit)
) = send(
    GetBasicGroupFullInfo(
        basicGroupId
    ),block = block
)
