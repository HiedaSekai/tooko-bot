package tooko.pm.handlers;

import cn.hutool.core.collection.*;
import cn.hutool.core.util.*;
import tooko.main.*;
import tooko.pm.*;
import tooko.td.TdApi.*;
import tooko.td.client.*;
import tooko.td.core.*;

import java.math.*;
import java.util.*;

public class ChatPanel extends TdHandler {

    public PmBot bot;
    public PmData data;

    public int DATA_ID = Fn.DataId._5;

    @Override
    public void onLoad() {

        bot = (PmBot) client;

        data = bot.data;

        initFunction("enter", "exit", "chat");

        initData(DATA_ID);

        initPayload("0");

    }

    @Override
    public void onPayload(User user, long chatId, Message message, String payload, String[] params) {

        send(Fn.deleteMessage(message));

        long sessionId = NumberUtil.isLong(params[0]) ? NumberUtil.parseLong(params[0]) : -1;

        User targetUser = user((int) sessionId);

        if (targetUser == null) {

            Fn.sendText(chatId, Fn.plainText(bot.OL.CHAT_NO_RECORD));

            return;

        }

        PmData.Session session = data.getSession(sessionId);

        send(Fn.sendText(chatId, message.replyToMessageId, sessionActions(session), Fn.parseHtml(sessionStat(session))));

    }


    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (user.id != bot.bot.owner) {

            send(Fn.deleteMessage(message));

            return;

        }

        if ("enter".equals(function)) {

            long targetChat;

            if (message.replyToMessageId != 0) {

                targetChat = findChat(message);

                if (targetChat == -1) {

                    send(Fn.sendText(chatId, Fn.plainText(bot.OL.INVALID_REPLY)));

                    return;

                }

            } else if (params.length != 0) {

                User targetUser = findUser(message, param, params);

                if (targetUser == null) {

                    send(Fn.sendText(chatId, Fn.plainText(bot.OL.INVALID_CHAT_ID)));

                    return;

                }

                targetChat = targetUser.id;

            } else {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.REPLY_OR_ID)));

                return;

            }

            Chat chat;

            try {

                chat = execute(new GetChat(targetChat));

            } catch (TdException e) {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.GET_CHAT_FAILED, e)));

                return;

            }

            if (!(chat.type instanceof ChatTypePrivate)) {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.NO_PRIVATE_WARN)));

                return;

            }

            bot.chat = targetChat;

            send(Fn.sendText(chatId, Fn.parseHtml(bot.OL.CHAT_ENTERED, Fn.mention(chat.title, (int) chat.id))));

        } else if ("exit".equals(function)) {

            if (bot.chat != -1) {

                bot.chat = -1;

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.CHAT_EXITED)));

            } else {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.NO_CHAT_ENTERED)));

            }

        } else if ("chat".equals(function)) {

            long targetChat;

            if (message.replyToMessageId != 0) {

                targetChat = findChat(message);

                if (targetChat == -1) {

                    send(Fn.sendText(chatId, Fn.plainText(bot.OL.INVALID_REPLY)));

                    return;

                }

            } else if (params.length != 0) {

                User targetUser = findUser(message, param, params);

                if (targetUser == null) {

                    send(Fn.sendText(chatId, Fn.plainText(bot.OL.INVALID_CHAT_ID)));

                    return;

                }

                targetChat = targetUser.id;

            } else {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.REPLY_OR_ID)));

                return;

            }

            PmData.Session session = data.getSession(targetChat);

            User targetUser = user((int) targetChat);

            if (targetUser == null) {

                send(Fn.sendText(chatId, Fn.plainText(bot.OL.CHAT_NO_RECORD)));

                return;

            }

            send(Fn.deleteMessage(message));

            send(Fn.sendText(chatId, message.replyToMessageId, sessionActions(session), Fn.parseHtml(sessionStat(session))));

        }

    }

    String sessionStat(PmData.Session session) {

        String stat = bot.OL.CHAT_MANAGE_HELP;

        stat += "\n\n" + bot.OL.USER_NAME + " : " + Fn.mention(user((int) session.chatId));

        if (data.blocked.contains(session.chatId)) {

            stat += " " + bot.OL.CHAT_IS_BLOCKED;

        }

        stat += "\n" + bot.OL.USER_ID + " : " + Fn.code(session.chatId);

        stat += "\n" + bot.OL.CHAT_MSG_RECEIVED;
        stat += "\n" + bot.OL.CHAT_MSG_SENDED;

        return StrUtil.format(stat, session.received.size(), session.sended.size());

    }

    InlineArray sessionActions(PmData.Session session) {

        byte[] sessionId = BigInteger.valueOf(session.chatId).toByteArray();

        InlineArray buttons = new InlineArray();

        buttons.dataLine(bot.OL.CHAT_MSG_DEL_RECEIVED, DATA_ID, 0, sessionId);
        buttons.dataLine(bot.OL.CHAT_MSG_DEL_SENDED, DATA_ID, 1, sessionId);
        buttons.dataLine(bot.OL.CHAT_MSG_DEL_ALL, DATA_ID, 2, sessionId);

        buttons.dataLine(data.blocked.contains(session.chatId) ? bot.OL.CHAT_UNBLOCK : bot.OL.CHAT_BLOCK, DATA_ID, 3, sessionId);

        buttons.dataLine(bot.OL.FINISH_MANAGE, DATA_ID, 4);

        return buttons;

    }

    @Override
    public void onNewCallbackQuery(int userId, long chatId, long messageId, long queryId, int subId, byte[][] data) {

        if (subId == 4) {

            send(Fn.deleteMessages(chatId, messageId));

            return;

        }

        long targetChat = new BigInteger(data[0]).longValue();

        PmData.Session session = this.data.getSession(targetChat);

        if (subId < 3) {

            if (targetChat == bot.current) bot.current = -1;

            HashSet<Long> sended = new HashSet<>();
            HashSet<Long> received = new HashSet<>();
            HashSet<Long> reports = new HashSet<>();

            if (subId > 0) {

                if (CollectionUtil.isEmpty(session.sended)) {

                    if (subId == 1) {

                        send(Fn.answerAlert(queryId, bot.OL.CHAT_EMPTY));

                        return;

                    }

                } else {

                    sended.addAll(session.sended);

                    for (long send : session.sended) {

                        reports.add(NumberUtil.parseLong(this.data.sended.removeValue(send)));

                    }

                    session.sended.clear();

                    if (subId != 2) {

                        this.data.sessions.setById(session.chatId, session);

                    }

                }

            }

            if (subId != 1) {

                if (CollectionUtil.isEmpty(session.received)) {

                    if (subId == 0) {

                        send(Fn.answerAlert(queryId, bot.OL.CHAT_EMPTY));

                        return;

                    }

                } else {

                    received.addAll(session.received);

                    for (long receive : session.received) {

                        reports.add(NumberUtil.parseLong(this.data.received.removeValue(receive)));

                    }

                    session.received.clear();

                    if (subId != 2) {

                        this.data.sessions.setById(session.chatId, session);

                    }

                }

            }

            HashSet<Long> marged = new HashSet<>();

            marged.addAll(received);
            marged.addAll(sended);

            if (marged.isEmpty()) {

                send(Fn.answerAlert(queryId, bot.OL.CHAT_EMPTY));

                return;

            }

            send(Fn.deleteMessages(session.chatId, Fn.toArray(marged)));

            String status = "";

            if (!received.isEmpty()) {

                status += StrUtil.format(bot.OL.PM_DELETED_RECEIVED, received.size());

                if (!sended.isEmpty()) status += "\n";

            }

            if (!sended.isEmpty()) {

                status += StrUtil.format(bot.OL.PM_DELETED_SENDED, sended.size());

            }

            send(Fn.answerAlert(queryId, status));

            if (subId == 2) {

                if (session.extras != null) reports.addAll(session.extras);

                if (session.reports != null) reports.addAll(session.reports);

                I(Fn.deleteMessages(bot.bot.owner, Fn.toArray(reports)));

                this.data.sessions.deleteById(session.chatId);

            } else {

                send(Fn.deleteMessages(bot.bot.owner, Fn.toArray(reports)));

            }

            I(Fn.editText(chatId, messageId, sessionActions(session), Fn.parseHtml(sessionStat(session))));

        } else if (subId == 3) {

            boolean target;

            if (this.data.blocked.contains(targetChat)) {

                this.data.blocked.remove(targetChat);

                target = false;

            } else {

                this.data.blocked.add(targetChat);

                target = true;

            }

            I(Fn.editText(chatId, messageId, sessionActions(session), Fn.parseHtml(sessionStat(session))));

            send(Fn.answerText(queryId, target ? bot.OL.CHAT_BLOCKED : bot.OL.CHAT_UNBLOCKED));

        }

    }

    User findUser(Message message, String param, String[] params) {

        if (NumberUtil.isInteger(param)) {

            return user(NumberUtil.parseInt(param));

        }

        for (TextEntity entity : ((MessageText) message.content).text.entities) {

            if (entity.type instanceof TextEntityTypeMentionName) {

                int userId = ((TextEntityTypeMentionName) entity.type).userId;

                return user(userId);

            }

        }

        String userName = params[0];

        try {

            if (userName.startsWith("@")) userName = userName.substring(1);

            Chat chat = execute(new SearchPublicChat(userName));

            if (chat.type instanceof ChatTypePrivate) {

                return user(((ChatTypePrivate) chat.type).userId);

            }

        } catch (TdException ignored) {
        }

        return null;

    }

    long findChat(Message message) {

        long actionMessage = message.replyToMessageId;

        String actionMessageStr = actionMessage + "";

        if (data.received.containsKey(actionMessageStr)) {

            return data.getSessionByElement("received", data.received.get(actionMessageStr)).chatId;

        } else if (data.sended.containsKey(actionMessageStr)) {

            return data.getSessionByElement("sended", data.sended.get(actionMessageStr)).chatId;

        } else {

            PmData.Session session = data.getSessionByElement("reports", actionMessage);

            if (session != null) {

                return session.chatId;

            }

            session = data.getSessionByElement("extras", actionMessage);

            if (session != null) {

                return session.chatId;

            }

        }

        return -1;

    }

}
