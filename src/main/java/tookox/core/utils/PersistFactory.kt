package tookox.core.utils

import cn.hutool.core.io.FileUtil
import cn.hutool.core.text.csv.CsvUtil
import cn.hutool.core.util.CharsetUtil
import tooko.main.Env
import tookox.core.client.TdAbsHandler
import tookox.core.shift
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