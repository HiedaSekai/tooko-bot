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
 * Shares the phone number of the current user with a mutual contact
 * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
 *
 * @userId - Identifier of the user with whom to share the phone number
 *           The user must be a mutual contact
 */
suspend fun TdAbsHandler.sharePhoneNumber(
    userId: Int
) = sync<Ok>(
    SharePhoneNumber(
        userId
    )
)

suspend fun TdAbsHandler.sharePhoneNumberOrNull(
    userId: Int
) = syncOrNull<Ok>(
    SharePhoneNumber(
        userId
    )
)

fun TdAbsHandler.sharePhoneNumber(
    userId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SharePhoneNumber(
        userId
    ),block = block
)
