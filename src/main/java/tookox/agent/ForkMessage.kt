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

package tookox.agent

import kotlinx.coroutines.delay
import tookox.core.*
import tookox.core.client.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.util.*

suspend fun TdAbsHandler.forkMessage(chatId: Number, message: String = "/ping"): LongArray {

    val origin = sudo make message syncTo chatId

    val originList = LinkedList<Long>()

    originList.add(origin.id)

    for (index in 0..6) {

        val messages = if (index < 6) {

            forwardMessages(chatId.toLong(), chatId.toLong(), originList.toLongArray(), null, false, false, false)

        } else {

            forwardMessages(chatId.toLong(), chatId.toLong(), originList.subList(0, 36).toLongArray(), null, false, false, false)

        }

        originList.addAll(messages.messages.map { it.id })

        defaultLog.debug("messages count: ${originList.size}")

        delay(1000L)

    }

    return originList.toLongArray()

}
