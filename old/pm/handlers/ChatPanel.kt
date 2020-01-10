package tookox.pm.handlers

import cn.hutool.core.util.NumberUtil
import cn.hutool.core.util.StrUtil
import tooko.main.Fn
import td.TdApi.*
import tookox.core.client.TdException
import tookox.core.*
import tookox.core.client.*
import tookox.core.utils.*
import tookox.pm.PmBot
import tookox.pm.PmData
import kotlin.properties.Delegates

class ChatPanel : TdBotHandler() {

    override val sudo: PmBot get() = super.sudo as PmBot

    val bot get() = sudo.bot
    val data get() = sudo.data
    
    override fun onLoad() {

        initFunction("enter", "exit", "chat")

        initData(DATA_1)

        initStartPayload("0")
        
    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (userId != bot.owner) rejectFunction()

        val L = userId.langForUserId
        
        if ("enter" == function) {
            
            var targetChat by Delegates.notNull<Long>()
            
            if (message.replyToMessageId != 0L) {
            
                targetChat = findChat(message)
            
                if (targetChat == -1L) {

                    sudo make L.INVALID_REPLY sendTo chatId
                    
                    return

                }
            
            } else if (params.isNotEmpty()) {
                
                val targetUser = findUser(message, param, params)
                
                if (targetUser == null) {

                    sudo make L.INVALID_CHAT_ID sendTo chatId

                    return

                }

                targetChat = targetUser.id.toLong()

            } else {

                sudo make L.REPLY_OR_ID sendTo chatId

                return

            }

            val chat = try {

                getChat(targetChat)

            } catch (e: TdException) {

                sudo make L.GET_CHAT_FAILED sendTo chatId

                return

            }

            if (chat.type !is ChatTypePrivate) {

                sudo make L.NO_PRIVATE_WARN sendTo chatId

                return

            }

            sudo.chat = targetChat

            sudo makeHtml L.CHAT_ENTERED.input(chat.title.toInlineMention(chat.id.toInt())) sendTo chatId

        } else if ("exit" == function) {

            if (sudo.chat != -1L) {

                sudo.chat = -1L

                sudo make L.CHAT_EXITED sendTo chatId

            } else {

                sudo make L.NO_CHAT_ENTERED sendTo chatId

            }

        } else if ("chat" == function) {

            val targetChat: Long

            if (message.replyToMessageId != 0L) {

                targetChat = findChat(message)

                if (targetChat == -1L) {

                    sudo make L.INVALID_REPLY sendTo chatId

                    return

                }

            } else if (params.size != 0) {

                val targetUser = findUser(message, param, params)

                if (targetUser == null) {

                    sudo make L.INVALID_CHAT_ID sendTo chatId

                    return

                }

                targetChat = targetUser.id.toLong()

            } else {

                sudo make L.REPLY_OR_ID sendTo chatId

                return

            }

            val session = data.getSession(targetChat)

            val targetUser = getUserOrNull(targetChat.toInt())

            if (targetUser == null) {

                sudo make L.CHAT_NO_RECORD sendTo chatId

                return

            }

            sudo delete message

            sudo makeHtml sessionStat(session) withMarkup sessionActions(session) replyTo message.replyToMessageId sendTo chatId

        }


    }

    suspend fun findUser(message: Message, param: String?, params: Array<String>): User? {
        
        if (NumberUtil.isInteger(param)) {
            
            return getUserOrNull(NumberUtil.parseInt(param))
            
        }
        
        for (entity in (message.content as MessageText).text.entities) {
          
            if (entity.type is TextEntityTypeMentionName) {
                
                val userId = (entity.type as TextEntityTypeMentionName).userId
                
                return getUserOrNull(userId)
                
            }
            
        }
        
        var userName = params[0]
        
        try {
        
            if (userName.startsWith("@")) userName = userName.substring(1)
        
            val chat = searchPublicChat(userName)
        
            if (chat.type is ChatTypePrivate) return getUser((chat.type as ChatTypePrivate).userId)
            
        } catch (ignored: TdException) {
        }
        
        return null
    }

    fun findChat(message: Message): Long {
        
        val actionMessage = message.replyToMessageId
        val actionMessageStr = actionMessage.toString() + ""
        
        if (data.received.containsKey(actionMessageStr)) {
        
            return data.getSessionByElement("received", data.received[actionMessageStr]!!).chatId
        
        } else if (data.sended.containsKey(actionMessageStr)) {
        
            return data.getSessionByElement("sended", data.sended[actionMessageStr]!!).chatId
        
        } else {
        
            var session = data.getSessionByElement("reports", actionMessage)
        
            if (session != null) return session.chatId
  
            session = data.getSessionByElement("extras", actionMessage)
           
            if (session != null) return session.chatId
        
        }
        
        return -1
    
    }

    override suspend fun onStartPayload(userId: Int, chatId: Long, message: Message, payload: String, params: Array<String>) {

        if (bot.owner != userId) return sudo.onLaunch(userId, chatId, message)
        
        val L = userId.langForUserId
        
        sudo delete message

        val sessionId = if (NumberUtil.isLong(params[0])) NumberUtil.parseLong(params[0]) else -1

        val targetUser = getUserOrNull(sessionId.toInt())

        if (targetUser == null) {
            
            sudo make L.CHAT_NO_RECORD sendTo chatId
            
            return
        
        }

        val session = data.getSession(sessionId)

        sudo makeHtml sessionStat(session) withMarkup sessionActions(session) sendTo chatId

    }


    suspend fun sessionStat(session: PmData.Session): String {
        
        val L = bot.owner.langForUserId
        
        var stat: String = L.CHAT_MANAGE_HELP
        
        stat += "\n\n" + L.USER_NAME.toString() + " : " + Fn.mention(getUser(session.chatId.toInt()))

        if (data.blocked.contains(session.chatId)) {
            stat += " " + L.CHAT_IS_BLOCKED
        }

        stat += "\n" + L.USER_ID.toString() + " : " + Fn.code(session.chatId)
        stat += "\n" + L.CHAT_MSG_RECEIVED
        stat += "\n" + L.CHAT_MSG_SENDED

        return StrUtil.format(stat, session.received.size, session.sended.size)

    }

    fun sessionActions(session: PmData.Session) = inlineButton {

        val L = bot.owner.langForUserId

        val sessionId = session.chatId.asByteArray

        dataLine(L.CHAT_MSG_DEL_RECEIVED, DATA_1, 0, sessionId)
        dataLine(L.CHAT_MSG_DEL_SENDED, DATA_1, 1, sessionId)
        dataLine(L.CHAT_MSG_DEL_ALL, DATA_1, 2, sessionId)
        dataLine(if (data.blocked.contains(session.chatId)) L.CHAT_UNBLOCK else L.CHAT_BLOCK, DATA_1, 3, sessionId)
        dataLine(L.FINISH_MANAGE, DATA_1, 4)

    }
    
}