package tookox.core.funs

import cn.hutool.core.util.NumberUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import tooko.main.Lang
import td.TdApi.*
import tookox.core.*
import tookox.core.client.*
import tookox.core.utils.*
import java.util.*

class BaseFuncs : TdBotHandler() {

    val functions = LinkedList<TdBotHandler>()

    override fun onLoad() = functions.forEach(sudo::addHandler)

    fun function(name: String, function: suspend CoroutineScope.(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) -> Unit) {

        functions.add(object : TdBotHandler() {

            override fun onLoad() = initFunction(name)

            override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) = coroutineScope {

                function(userId, chatId, message, function, param, params, originParams)

            }

        })

    }

    val ping = function("ping") {

        userId, chatId, _, _, _, _, _ ->

        sudo make Lang.get(userId).FN_PING_RESULT sendTo chatId

    }

    val getId = function("id") {

        userId, chatId, message, _, param, params, _ ->

        val L = Lang.get(userId)

        if (params.isEmpty()) {

            if (message.fromPrivate) {

                sudo makeHtml "${"UID".asBlod} : ${userId.asCode}" sendTo chatId

            } else if (message.fromChannel) {

                sudo makeHtml "${"CID".asBlod} : ${chatId.asCode}" sendTo chatId

            } else {

                sudo makeHtml "${"CID".asBlod} : ${chatId.asCode}\n${"UID".asBlod} : ${userId.asCode}" sendTo chatId

            }

            return@function

        }

        if (NumberUtil.isInteger(param)) {

            send<User>(GetUser(param.toInt())) {

                sudo makeHtml "${L.USER_NAME.asBlod} : " +
                        "${it.displayName.toInlineMention(it.id)}\n" +
                        "${L.USER_ID.asBlod} : ${it.id.asCode}" sendTo chatId


            } onError {

                sudo make L.USER_ID_NOT_FOUND.input(param) sendTo chatId

            }

        } else {

            for (entity in (message.content as MessageText).text.entities) {

                if (entity.type is TextEntityTypeMentionName) {

                    val targetUserId = (entity.type as TextEntityTypeMentionName).userId

                    send<User>(GetUser(targetUserId)) {

                        sudo makeHtml "${L.USER_NAME.asBlod} : " +
                                "${it.displayName.toInlineMention(it.id)}\n" +
                                "${L.USER_ID.asBlod} : ${it.id.asCode}" sendTo chatId

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

                    val targetUser = sync<User>(GetUser((it.type as ChatTypePrivate).userId))

                    sudo makeHtml "${L.USER_NAME.asBlod} : ${targetUser.displayName.toInlineMention(targetUser.id)}\n${L.USER_ID.asBlod} : ${targetUser.id.asCode}" sendTo chatId

                } else {

                    sudo makeHtml "${"CID".asBlod} : ${it.id.asCode}" sendTo chatId

                }

            } onError {

                sudo make L.CHAT_NOT_FOUND sendTo chatId

            }

        }

    }

}