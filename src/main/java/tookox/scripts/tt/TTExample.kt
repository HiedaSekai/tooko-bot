package tookox.scripts.tt

import chat.tamtam.botapi.model.Message
import tookox.core.createLog
import tookox.core.postUserText
import tookox.core.tt.TtBot
import tookox.core.tt.TtHandler

object TTExample : TtHandler() {

    val log = createLog("Ex")

    val botToken = ""

    @JvmStatic
    fun main(args: Array<String>) {

        val bot = object : TtBot(botToken) {

            override fun onLoad() {

                addHandler(this@TTExample)

            }

        }

        bot.start()

        TtBot.updateThread.start()

    }

    override fun onMessageCreated(message: Message, timestamp: Long) {

        log.debug("new message : $message")

        postUserText(message.sender.userId, "hello world!")

    }

}