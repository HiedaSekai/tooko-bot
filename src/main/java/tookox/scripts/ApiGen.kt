package tookox.scripts

import td.TdApi
import java.io.File

object ApiGen {

    @JvmStatic
    fun main(args: Array<String>) {

        val src = File("src/main/java/td/TdApi.java").readText()

        TdApi::class.java.classes.forEach {



        }

    }

}