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

package tookox.agent.fmt

import cn.hutool.core.io.FileUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import td.TdApi
import tookox.core.client.*
import java.util.concurrent.Executors

class TestNumberFormat : TdBotHandler() {

    override fun onLoad() {

        initFunction("fmt", "fmt_dc", "fmt_all")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (function == "fmt") {

            val dcId = param.substring(0, 1)
            val number = param.substring(1)

            FormatClient(dcId, number).start()

        } else if (function == "fmt_dc") {

            val pool = Executors.newFixedThreadPool(30)

            for (index in 0 until 10000) {

                var str = "$index"

                while (str.length < 4) {

                    str = "0$str"

                }

                pool.execute {

                    runBlocking {

                        val client = FormatClient(param, str)

                        client.start()

                        client.waitForClose()

                        launch(Dispatchers.IO) {

                            FileUtil.del("data/test/$param$str")

                        }

                    }

                }

            }

        } else {

            val pool = Executors.newFixedThreadPool(16)

            for (dcId in 1..3) {

                for (index in 0 until 10000) {

                    var str = "$index"

                    while (str.length < 4) {

                        str = "0$str"

                    }

                    pool.execute {

                        runBlocking {

                            val client = FormatClient(param, str)

                            client.start()

                            client.waitForClose()

                            launch(Dispatchers.IO) {

                                FileUtil.del("data/test/$param$str")

                            }

                        }

                    }

                }

            }

        }

    }

}