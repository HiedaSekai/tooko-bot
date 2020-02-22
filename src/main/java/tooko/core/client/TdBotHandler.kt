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

package tooko.core.client

import td.TdApi
import java.util.*

open class TdBotHandler : TdHandler(), TdBotAbsHandler {

    override val sudo: TdBot get() = super.sudo as TdBot

    override suspend fun onUndefinedFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit
    override suspend fun onNewInlineCallbackQuery(userId: Int, inlineMessageId: String, queryId: Long, subId: Int, data: Array<ByteArray>) = Unit

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: TdApi.Message, subId: Int) = Unit
    override suspend fun onPersistRemove(userId: Int, subId: Int) = Unit
    override suspend fun onPersistTimeout(userId: Int, subId: Int) = Unit
    override suspend fun onPersistRemoveOrCancel(userId: Int, subId: Int) = Unit

    override fun onPersistStore(userId: Int, subId: Int, data: LinkedList<String>) = Unit
    override fun onPersistReStore(userId: Int, subId: Int, data: List<String>) = Unit
    override suspend fun onStartPayload(userId: Int, chatId: Long, message: TdApi.Message, payload: String, params: Array<String>) = Unit
    override suspend fun onUndefinedPayload(userId: Int, chatId: Long, message: TdApi.Message, payload: String, params: Array<String>) = Unit
}