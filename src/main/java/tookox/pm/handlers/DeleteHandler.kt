package tookox.pm.handlers

import cn.hutool.core.collection.CollectionUtil
import cn.hutool.core.util.NumberUtil
import cn.hutool.core.util.StrUtil
import tooko.main.Fn
import tooko.main.Lang
import tooko.td.core.LongLongArrayMap
import tookox.core.client.*
import tookox.core.utils.*
import tookox.pm.PmBot
import tookox.pm.PmData
import java.util.*

class DeleteHandler : TdBotHandler() {

    override val sudo: PmBot get() = super.sudo as PmBot

    val bot get() = sudo.bot
    val data get() = sudo.data

    override suspend fun onDeleteMessages(chatId: Long, messageIds: LongArray, isPermanent: Boolean, fromCache: Boolean) {

        if (!Fn.isPrivate(chatId) || !isPermanent || fromCache) return

        val L = Lang.get(bot.owner)

        if (chatId == bot.owner.toLong()) {

            val deleteReceivedMessage = LongLongArrayMap()
            val deleteSendedMessage = LongLongArrayMap()

            val deleteReports = HashSet<Long>()

            for (messageId in messageIds) {

                val messageIdStr = messageId.toString() + ""
                var session: PmData.Session

                if (data.received.containsKey(messageIdStr)) {
                    
                    val targetMessageId = data.received.get(messageIdStr)!!

                    session = data.getSessionByElement("received", targetMessageId)

                    deleteReceivedMessage[session.chatId]!!.add(targetMessageId)

                    data.sessions.arrayReomve(session.chatId, "received", data.received.remove(messageIdStr))

                } else if (data.sended.containsKey(messageIdStr)) {

                    val targetMessageId = data.sended.get(messageIdStr)!!

                    session = data.getSessionByElement("sended", targetMessageId)

                    deleteSendedMessage[session.chatId]!!.add(targetMessageId)

                    data.sessions.arrayReomve(session.chatId, "sended", data.sended.remove(messageIdStr
                    ))
                } else {

                    session = data.getSessionByElement("reports", messageId)

                    if (session == null) continue

                    if (session.chatId == sudo.current.toLong()) {

                        sudo.current = -1

                    }

                    session.reports.remove(messageId)

                    if (!CollectionUtil.isEmpty(session.sended)) {

                        deleteSendedMessage[session.chatId]!!.addAll(session.sended)

                        for (sended in session.sended) {

                            deleteReports.add(NumberUtil.parseLong(data.sended.removeValue(sended)))

                        }

                    }

                    if (!CollectionUtil.isEmpty(session.received)) {

                        deleteReceivedMessage[session.chatId]!!.addAll(session.received)

                        for (received in session.received) {

                            deleteReports.add(NumberUtil.parseLong(data.received.removeValue(received)))

                        }

                    }

                    if (session.extras != null) deleteReports.addAll(session.extras)
                    if (session.reports != null) deleteReports.addAll(session.reports)

                    data.sessions.deleteById(session.chatId)

                }

            }

            if (deleteReports.isNotEmpty()) {

                delete(bot.owner.toLong(),* deleteReports.toLongArray())

            }

            val marged = LongLongArrayMap()

            marged.putAll(deleteReceivedMessage)
            marged.putAll(deleteSendedMessage)

            if (marged.isEmpty()) return

            for ((key, value) in marged) {

                delete(key, * value.toLongArray())
            }

            var status = ""

            if (!deleteReceivedMessage.isEmpty()) {

                var targetUsersMention: String? = null

                for (userId in deleteReceivedMessage.keys) {

                    if (targetUsersMention == null) {

                        targetUsersMention = Fn.mention(getUser(userId.toInt()))

                    } else {

                        targetUsersMention += ", " + Fn.mention(getUser(userId.toInt()))

                    }

                }

                status += StrUtil.format(L.PM_DELETED_RECEIVED_FROM, targetUsersMention, deleteReceivedMessage.countElements())

                if (!deleteSendedMessage.isEmpty()) status += "\n"

            }

            if (!deleteSendedMessage.isEmpty()) {

                var targetUsersMention: String? = null

                for (userId in deleteSendedMessage.keys) {

                    if (targetUsersMention == null) {

                        targetUsersMention = Fn.mention(getUser(userId.toInt()))

                    } else {

                        targetUsersMention += ", " + Fn.mention(getUser(userId.toInt()))

                    }

                    status += StrUtil.format(L.PM_DELETED_SENDED_FROM, targetUsersMention, deleteSendedMessage.countElements())

                }

            }

            sudo make status.asHtml to bot.owner send deleteDelay()

        } else {

            data.initSessions()

            for (messageId in messageIds) {

                if (data.received.containsValue(messageId)) {

                    sudo make L.PM_DELETED_BY replyTo data.received.removeValue(messageId).toLong() sendTo bot.owner

                    data.sessions.arrayReomve(chatId, "received", messageId)

                } else if (data.sended.containsValue(messageId)) {

                    sudo make L.PM_DELETED_BY replyTo data.sended.removeValue(messageId).toLong() sendTo bot.owner

                    data.sessions.arrayReomve(chatId, "sended", messageId)

                }

            }

        }


    }

}