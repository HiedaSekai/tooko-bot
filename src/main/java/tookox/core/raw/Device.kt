@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Registers the currently used device for receiving push notifications
 * Returns a globally unique identifier of the push notification subscription
 *
 * @deviceToken - Device token
 * @otherUserIds - List of user identifiers of other users currently using the client
 */
suspend fun TdAbsHandler.registerDevice(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray
) = sync<PushReceiverId>(
    RegisterDevice(
        deviceToken,
        otherUserIds
    )
)

suspend fun TdAbsHandler.registerDeviceOrNull(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray
) = syncOrNull<PushReceiverId>(
    RegisterDevice(
        deviceToken,
        otherUserIds
    )
)

fun TdAbsHandler.registerDevice(
    deviceToken: DeviceToken? = null,
    otherUserIds: IntArray,
    block: (suspend CoroutineScope.(PushReceiverId) -> Unit)
) = send(
    RegisterDevice(
        deviceToken,
        otherUserIds
    ),block = block
)
