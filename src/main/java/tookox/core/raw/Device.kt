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
