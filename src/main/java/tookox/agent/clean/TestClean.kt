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

package tookox.agent.clean

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import td.TdApi
import tookox.core.client.*
import java.util.*
import java.util.concurrent.Executors

class TestClean : TdBotHandler() {

    override fun onLoad() {

        initFunction("clean", "clean_dc", "cleana_all")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (function == "clean") {

            val dcId = param.substring(0, 1).toInt()
            val number = param.substring(1).toInt()

            CleanClient(dcId, number).start()

        } else if (function == "clean_dc") {

            val dcId = param.toInt()

            val pool = Executors.newFixedThreadPool(5)

            val deferredes = LinkedList<Deferred<Unit>>()

            for (index in 0 until 10000) {

                pool.execute {

                    runBlocking {

                        val client = CleanClient(dcId, index)

                        client.start()

                        while (!client.closed) delay(100L)

                    }

                }

            }

        }

    }

}