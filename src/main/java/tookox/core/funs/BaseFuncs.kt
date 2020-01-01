package tookox.core.funs

import cn.hutool.core.util.NumberUtil
import tooko.main.Lang
import tooko.td.TdApi
import tooko.td.TdApi.*
import tookox.core.*
import tookox.core.client.TdBotHandler
import tookox.core.td.make
import java.util.*

class BaseFuncs : TdBotHandler() {

    val functions = LinkedList<TdBotHandler>()

    override fun onLoad() = functions.forEach(client::addHandler)

    fun function(name: String, function: (userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) -> Unit) {

        functions.add(object : TdBotHandler() {

            override fun onLoad() = initFunction(name)

            override fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = function(userId, chatId, message, function, param, params, originParams)

        })

    }

    val ping = function("ping") {

        userId, chatId, _, _, _, _, _ ->

        sudo make Lang.get(userId).FN_PING_RESULT sendTo chatId

    }

    val getId = function("id") {

        userId, chatId, message, function, param, params, originParams ->

        val L = Lang.get(userId)

        if (params.isEmpty()) {

            if (message.fromPrivate) {

                sudo make "${"UID".blod} : ${userId.code}".asHtml sendTo chatId

            } else if (message.fromChannel) {

                sudo make "${"CID".blod} : ${chatId.code}".asHtml sendTo chatId

            } else {

                sudo make "${"CID".blod} : ${chatId.code}\n${"UID".blod} : ${userId.code}".asHtml sendTo chatId

            }

            return@function

        }

        if (NumberUtil.isInteger(param)) {

            send<User>(GetUser(param.toInt())) {

                make {

                    inputHtml = "${L.USER_NAME.blod} : " +
                            "${it.displayName.inlineMention(it.id)}\n" +
                            "${L.USER_ID.blod} : ${it.id.code}"

                } sendTo chatId


            } onError {

                sudo make L.USER_ID_NOT_FOUND.input(param) sendTo chatId

            }

        } else {

            for (entity in (message.content as MessageText).text.entities) {

                if (entity.type is TextEntityTypeMentionName) {

                    val targetUserId = (entity.type as TextEntityTypeMentionName).userId

                    send<User>(GetUser(targetUserId)) {

                        sudo make "${L.USER_NAME.blod} : ${it.displayName.inlineMention(it.id)}K\n${L.USER_ID.blod} : ${it.id.code}".asHtml sendTo chatId

                    } onError {

                        sudo make L.USER_ID_NOT_FOUND.input(targetUserId) sendTo chatId

                    }

                    return@function

                }
            }

            var userName = params[0]

            if (userName.startsWith("@")) userName = userName.substring(1)

            send<Chat>(SearchPublicChat(userName)) {

                if (it.type is ChatTypePrivate) {

                    val targetUser = post<User>(GetUser((it.type as ChatTypePrivate).userId))

                    sudo make "${L.USER_NAME.blod} : ${targetUser.displayName.inlineMention(targetUser.id)}\n${L.USER_ID.blod} : ${targetUser.id.code}".asHtml sendTo chatId

                } else {

                    sudo make "${"CID".blod} : ${it.id.code}".asHtml sendTo chatId

                }

            } onError {

                sudo make L.CHAT_NOT_FOUND sendTo chatId

            }

        }

    }

}