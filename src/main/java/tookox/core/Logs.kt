package tookox.core

import cn.hutool.core.date.DateUtil
import cn.hutool.core.lang.Console
import cn.hutool.core.lang.Dict
import cn.hutool.core.util.StrUtil
import cn.hutool.log.Log
import cn.hutool.log.LogFactory
import cn.hutool.log.dialect.console.ConsoleLog
import cn.hutool.log.level.Level
import tooko.main.Env
import tookox.Launcher
import tookox.core.td.make

object TookoLog : LogFactory("Tooko Log") {

    override fun createLog(name: String?): Log {

        return if (name != null) createLog(name) else defaultLog

    }

    override fun createLog(clazz: Class<*>?): Log {

        return if (clazz != null) createLog(clazz.simpleName) else defaultLog

    }
}

val defaultLog = createLog("Tooko")

fun createLog(name: String): Log {

    return object : ConsoleLog(name) {

        override fun log(fqcn: String, level: Level, t: Throwable?, format: String, vararg arguments: Any) {

            val dict = Dict.create().set("date", DateUtil.now()).set("level", level.toString()).set("name", name).set("msg", StrUtil.format(format, *arguments))

            val logFormat = "[{level}] {name}: {msg}"

            val logMsg = StrUtil.format(logFormat, dict)

            if (level.ordinal >= Level.WARN.ordinal) {

                runCatching {

                    with(Launcher.INSTANCE) {

                        sudo make logMsg syncTo Env.LOG_CHANNEL

                    }

                }

                Console.error(t, logMsg)

            } else {

                Console.log(t, logMsg)

            }

        }

    }

}