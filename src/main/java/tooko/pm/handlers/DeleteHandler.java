package tooko.pm.handlers;

import cn.hutool.core.collection.*;
import cn.hutool.core.util.*;
import tooko.main.*;
import tooko.main.bots.*;
import tooko.pm.*;
import tooko.td.client.*;
import tooko.td.core.*;

import java.util.*;

public class DeleteHandler extends TdHandler {

    public PmBot pm;

    public BotData bot;
    public PmData data;

    public Lang OL;

    @Override
    public void onLoad() {

        pm = (PmBot) client;

        bot = pm.bot;
        data = pm.data;

        OL = pm.OL;

    }

    @Override
    public void onDeleteMessages(long chatId, long[] messageIds, boolean isPermanent, boolean fromCache) {

        if (!Fn.isPrivate(chatId) || !isPermanent || fromCache) return;

        if (chatId == bot.owner) {

            LongLongArrayMap deleteReceivedMessage = new LongLongArrayMap();

            LongLongArrayMap deleteSendedMessage = new LongLongArrayMap();

            HashSet<Long> deleteReports = new HashSet<>();

            for (long messageId : messageIds) {

                String messageIdStr = messageId + "";

                PmData.Session session;

                if (data.received.containsKey(messageIdStr)) {

                    Long targetMessageId = data.received.get(messageIdStr);

                    session = data.getSessionByElement("received", targetMessageId);

                    deleteReceivedMessage.get(session.chatId).add(targetMessageId);

                    data.sessions.arrayReomve(session.chatId, "received", data.received.remove(messageIdStr));

                } else if (data.sended.containsKey(messageIdStr)) {

                    Long targetMessageId = data.sended.get(messageIdStr);

                    session = data.getSessionByElement("sended", targetMessageId);

                    deleteSendedMessage.get(session.chatId).add(targetMessageId);

                    data.sessions.arrayReomve(session.chatId, "sended", data.sended.remove(messageIdStr));

                } else {

                    session = data.getSessionByElement("reports", messageId);

                    if (session == null) continue;

                    if (session.chatId == pm.current) {

                        pm.current = -1;

                    }

                    session.reports.remove(messageId);

                    if (!CollectionUtil.isEmpty(session.sended)) {

                        deleteSendedMessage.get(session.chatId).addAll(session.sended);

                        for (long sended : session.sended) {

                            deleteReports.add(NumberUtil.parseLong(data.sended.removeValue(sended)));

                        }

                    }

                    if (!CollectionUtil.isEmpty(session.received)) {

                        deleteReceivedMessage.get(session.chatId).addAll(session.received);

                        for (long received : session.received) {

                            deleteReports.add(NumberUtil.parseLong(data.received.removeValue(received)));

                        }

                    }

                    if (session.extras != null) deleteReports.addAll(session.extras);

                    if (session.reports != null) deleteReports.addAll(session.reports);

                    data.sessions.deleteById(session.chatId);

                }

            }

            if (!deleteReports.isEmpty()) {

                I(Fn.deleteMessages(bot.owner, Fn.toArray(deleteReports)));

            }

            LongLongArrayMap marged = new LongLongArrayMap();

            marged.putAll(deleteReceivedMessage);
            marged.putAll(deleteSendedMessage);

            if (marged.isEmpty()) return;

            for (Map.Entry<Long, LinkedList<Long>> action : marged.entrySet()) {

                I(Fn.deleteMessages(action.getKey(), Fn.toArray(action.getValue())));

            }

            String status = "";

            if (!deleteReceivedMessage.isEmpty()) {

                String targetUsersMention = null;

                for (long userId : deleteReceivedMessage.keySet()) {

                    if (targetUsersMention == null) {

                        targetUsersMention = Fn.mention(user((int) (userId)));

                    } else {

                        targetUsersMention += ", " + Fn.mention(user((int) (userId)));

                    }

                }

                status += StrUtil.format(OL.PM_DELETED_RECEIVED_FROM, targetUsersMention, deleteReceivedMessage.countElements());

                if (!deleteSendedMessage.isEmpty()) status += "\n";

            }

            if (!deleteSendedMessage.isEmpty()) {

                String targetUsersMention = null;

                for (long userId : deleteSendedMessage.keySet()) {

                    if (targetUsersMention == null) {

                        targetUsersMention = Fn.mention(user((int) (userId)));

                    } else {

                        targetUsersMention += ", " + Fn.mention(user((int) (userId)));

                    }

                    status += StrUtil.format(OL.PM_DELETED_SENDED_FROM, targetUsersMention, deleteSendedMessage.countElements());

                }

            }

            delay(Fn.sendText(bot.owner, Fn.parseHtml(status)), 3000);

        } else {

            data.initSessions();

            for (long messageId : messageIds) {

                if (data.received.containsValue(messageId)) {

                    send(Fn.sendText(bot.owner, Long.parseLong(data.received.removeValue(messageId)), Fn.plainText(OL.PM_DELETED_BY)));

                    data.sessions.arrayReomve(chatId, "received", messageId);

                } else if (data.sended.containsValue(messageId)) {

                    send(Fn.sendText(bot.owner, Long.parseLong(data.sended.removeValue(messageId)), Fn.plainText(OL.PM_DELETED_BY)));

                    data.sessions.arrayReomve(chatId, "sended", messageId);

                }

            }

        }

    }

}
