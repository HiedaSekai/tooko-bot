package tookox.core

import cn.hutool.core.date.DateUtil
import cn.hutool.core.lang.Console
import cn.hutool.core.lang.Dict
import cn.hutool.core.util.StrUtil
import cn.hutool.log.Log
import cn.hutool.log.dialect.console.ConsoleLog
import cn.hutool.log.level.Level

fun createLog(name: String): Log {

    return object : ConsoleLog(name) {

        override fun log(fqcn: String, level: Level, t: Throwable, format: String, vararg arguments: Any) {

            val dict = Dict.create().set("date", DateUtil.now()).set("level", level.toString()).set("name", name).set("msg", StrUtil.format(format, *arguments))

            val logFormat = "[{level}] {name}: {msg}"

            val logMsg = StrUtil.format(logFormat, dict)

            if (level.ordinal >= Level.WARN.ordinal) {

                Console.error(t, logMsg)

            } else {


                Console.log(t, logMsg)

            }

        }

    }

}