package tookox.vote

import cn.hutool.http.HttpUtil
import tookox.Launcher
import tookox.core.*
import tookox.core.utils.*
import java.util.*

object VoteUpdateTask : TimerTask() {

    override fun run() {

        val html = HttpUtil.get("https://www.cec.gov.tw/pc/zh_TW/P1/n00000000000000000.html")

        val pfp = html.substringAfter("宋楚瑜")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        val pfpPer = html.substringAfter("宋楚瑜")
                .substringAfter("tdAlignRight\"")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        val tnp = html.substringAfter("韓國瑜")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        val tnpPer = html.substringAfter("韓國瑜")
                .substringAfter("tdAlignRight\"")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        val dpp = html.substringAfter("蔡英文")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        val dppPer = html.substringAfter("蔡英文")
                .substringAfter("tdAlignRight\"")
                .substringAfter("tdAlignRight\"")
                .substringBefore("\"")

        Launcher.INSTANCE make {

            inputText = """
                
                宋楚瑜 (親民黨): 得票 {} 占比 {}%
                韓國瑜 (中國國民黨): 得票 {} 占比 {}%
                蔡英文 (民主進步黨): 得票 {} 占比 {}%
                
            """.trimIndent().input(pfp,pfpPer,tnp,tnpPer,dpp,dppPer)

        } sendTo -1001367035152L

    }
}