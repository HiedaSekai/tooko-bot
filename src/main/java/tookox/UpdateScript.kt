package tookox

import cn.hutool.core.io.FileUtil
import tooko.main.Env
import tooko.main.Fn

object UpdateScript {

    var VERSION = 0
    var FROM = VERSION

    fun checkUpdate() {
        val versionFile = Env.getFile("data/.version")
        if (!FileUtil.isFile(versionFile)) {
            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile)
            return
        }
        FROM = try {
            Fn.byte2int(FileUtil.readBytes(versionFile))
        } catch (ex: NumberFormatException) {
            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile)
            return
        }
        FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile)
    }

    fun beforeLaunch() {}

    fun afterLaunch() { // ...
    }
}