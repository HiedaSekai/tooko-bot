package tookox.core.client

import cn.hutool.core.thread.ThreadUtil
import cn.hutool.core.util.RuntimeUtil
import cn.hutool.core.util.StrUtil
import tooko.main.Env
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException
import tookox.core.fromPrivate
import java.io.File
import java.util.*

open class TdBot(val botToken: String) : TdClient(initDataDir(botToken)), TdBotAbsHandler {

    override val client: TdBot get() = this

    var persists = HashMap<Int, TdBotAbsHandler>()
    var payloads = HashMap<String, TdBotAbsHandler>()
    var functions = HashMap<String, TdBotAbsHandler>()
    var callbacks = HashMap<Int, TdBotAbsHandler>()

    override fun onAuthorizationState(authorizationState: TdApi.AuthorizationState) {

        super.onAuthorizationState(authorizationState)

        if (authorizationState is TdApi.AuthorizationStateWaitPhoneNumber) {

            send(TdApi.CheckAuthenticationBotToken(botToken)) {

                isOk, _: TdApi.Object?, error ->

                if (!isOk) {

                    onLoginFailed(TdException(error!!))

                }

            }

        }

    }

    override fun onLoginFailed(ex: TdException) = Unit

    override fun onNewMessage(userId: Int, chatId: Long, message: Message) {

        while (!authed) ThreadUtil.sleep(233)

        if (userId == me.id) return

        if (message.fromPrivate) {

            synchronized(persists) {

                // TODO: handle persist message

            }

            if (message.content !is MessageText) return

            val content = (message.content as MessageText).text

            if (content.entities.size == 0 || content.entities[0].type !is TextEntityTypeBotCommand || content.entities[0].offset != 0) {

                return

            }

            val command = content.entities[0]

            var function = content.text.substring(1, command.length)

            if (function.endsWith("@" + me.username)) {

                function = function.substring(0, function.length - me.username.length - 1)

            }

            val param = if (content.text.length > command.length) content.text.substring(command.length + 1) else ""

            val params: Array<String>

            val originParams: Array<String>

            if (param.isBlank()) {

                originParams = arrayOf()
                params = originParams

            } else {

                originParams = param.split(" ".toRegex()).toTypedArray()
                params = param.replace("  ", " ").split(" ".toRegex()).toTypedArray()

            }

            if ("start" == function) {

                if (params.isEmpty()) {

                    onLaunch(userId, chatId, message)

                } else {

                    // TODO: handle start payload

                }

                return

            }

            if (!functions.containsKey(function)) {

                handlers.filterIsInstance<TdBotAbsHandler>().forEach {

                    it.onUndefinedFunction(userId, chatId, message, function, param, params, originParams)

                }

            } else {

                functions.get(function)!!.onFunction(userId, chatId, message, function, param, params, originParams)

            }

        }

    }

    open fun onLaunch(userId: Int, chatId: Long, message: Message) = Unit
    override fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit
    override fun onUndefinedFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = Unit

    companion object {

        private fun initDataDir(botToken: String): TdOptions {

            val dataDir = Env.getFile("data/" + StrUtil.subBefore(botToken, ":", false))

            dataDir.mkdirs()

            mkLink(dataDir, "stickers")
            mkLink(dataDir, "profile_photos")
            mkLink(dataDir, "thumbnails")
            mkLink(dataDir, "wallpapers")


            return TdOptions().databaseDirectory(dataDir.path)

        }

        private fun mkLink(dataDir: File, target: String) {

            val sourceDir = File(dataDir, target)

            val targetDir = Env.getFile("cache/files/$target")

            if (!sourceDir.isDirectory) {

                targetDir.mkdirs()

                RuntimeUtil.execForStr("ln -s " + targetDir.path + " " + sourceDir.path)

            }

        }

    }

}