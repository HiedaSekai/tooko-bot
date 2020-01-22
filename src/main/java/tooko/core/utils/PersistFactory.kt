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

package tooko.core.utils

import cn.hutool.core.io.FileUtil
import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.core.util.CharsetUtil
import tooko.core.env.Env
import tooko.core.client.TdAbsHandler
import tooko.core.shift
import java.util.*

fun TdAbsHandler.readDataFrom(name: String): List<List<String>>? {

    val csvFile = Env.getFile("data/${sudo.me.id}/.persist/$name.csv")

    val data = LinkedList<List<String>>()

    if (csvFile.isFile) {

        CsvUtil.getReader().read(csvFile).forEach {

            data.add(it.rawList)

        }

    }

    csvFile.delete()

    return data.takeIf { it.isNotEmpty() }

}


fun TdAbsHandler.writeDataTo(name: String, data: List<List<String>>) {

    val csvFile = Env.getFile("data/${sudo.me.id}/.persist/$name.csv")

    if (csvFile.isFile) csvFile.delete()

    FileUtil.touch(csvFile)

    with(CsvUtil.getWriter(csvFile, CharsetUtil.CHARSET_UTF_8)) {

        write(* data.map { it.toTypedArray() }.toTypedArray())

        close()

    }

}

fun TdAbsHandler.readDataMapFrom(name: String): HashMap<String, List<String>>? {

    val dataList = readDataFrom(name)

    val data = HashMap<String, List<String>>()

    dataList?.forEach {

        data[it[0]] = it.shift().toList()

    }

    return data

}

fun TdAbsHandler.writeDataMapTo(name: String, data: Map<String, List<String>>) {

    writeDataTo(name, data.map {

        LinkedList<String>(listOf(it.key)).apply { addAll(it.value) }

    })

}