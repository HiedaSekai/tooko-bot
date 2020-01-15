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

package tookox.test

import td.TdApi
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.utils.*

class BigFileTest : TdBotHandler() {

    override fun onLoad() {

        initFunction("big_file_test")

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val file = ByteArray(100 * 1024 * 1024)

        for (index in file.indices) {

            file[index] = 0

        }

        val cache = Env.getFile("cache/test${param}mb.bin")

        cache.writeBytes(file)

        for (index in 1 until 16) {

            cache.appendBytes(file)

        }

        sudo makeFile cache.path sendTo chatId

    }

}