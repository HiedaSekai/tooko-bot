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

package tooko.twitter.login

import td.TdApi
import tooko.core.PERSIST_4
import tooko.core.client.TdBotHandler

class TokenLogin : TdBotHandler() {

    val PERSIST_ID = PERSIST_4

    override fun onLoad() {

        initFunction("login")

        initPersist(PERSIST_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {


    }

    companion object {

        suspend fun suspendLogin() {


        }

    }

}