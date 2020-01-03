package tooko.pm;

import cn.hutool.core.io.*;
import org.apache.commons.collections4.bidimap.*;
import tooko.*;
import tooko.main.*;
import tooko.main.bots.*;
import tooko.main.extras.*;
import tooko.pm.PmData.*;
import tooko.pm.config.*;
import tooko.pm.handlers.*;
import tooko.td.TdApi.*;
import tooko.td.client.*;

import java.io.File;
import java.util.*;

public class PmBot extends TdBot {

    public BotImage image;

    public BotData bot;
    public PmData data;

    public Lang OL;
    public int current = -1;
    public long chat = -1;

    public PmBot(BotImage image) {

        super(image.data.botToken);

        this.image = image;

        this.bot = image.data;

        this.OL = Lang.get(bot.owner);

        data = PmData.DATA.getById(bot.botId);

        if (data == null) {

            data = new PmData();

            data.id = bot.botId;

            Payload main = new Payload();

            main.notice = false;

            data.welcome = main;

            PmData.DATA.idIndex.put(data.id, data);

        }

        if (data.sended == null) data.sended = new DualHashBidiMap<>();
        if (data.received == null) data.received = new DualHashBidiMap<>();

        if (data.blocked == null) data.blocked = new HashSet<>();

    }

    @Override
    public void onLoad() {

        // addHandler(new PingFunction());

        addHandler(new WelcomeConfig());

        addHandler(new EditHandler());

        addHandler(new DeleteHandler());

        addHandler(new ChatPanel());

        addHandler(new LICENCE());

        super.onLoad();

        File chatId = new File(dataDir, "chat.bin");

        if (chatId.isFile()) {

            try {

                chat = Fn.byte2long(FileUtil.readBytes(chatId));

            } catch (Exception ignored) {
            }

            FileUtil.del(chatId);

        }

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        if (chat != -1) {

            FileUtil.writeBytes(Fn.num2byte(chat), new File(dataDir, "chat.bin"));

        }

    }


    @Override
    public void onAuthorizationFailed(TdException ex) {

        destory(ex);

    }

    @Override
    public void onLoggingOut() {

        destory(new TdException(OL.ERR_LOGGING_OUT));

    }

    @Override
    public void onLaunch(User user, long chatId, Message message) {

        Lang L = Lang.get(user);

        if (user.id == bot.owner) {

            deleteStartMessages(chatId);

            send(Fn.sendText(chatId, Fn.parseHtml(L.PM_OK)), asStartMessage(message));

            return;

        }

        if (data.welcome.notice) {

            try {

                execute(Fn.sendText(bot.owner, Fn.parseHtml(L.PM_ON_START, Fn.a(Fn.displayName(user), "tg://user?id=" + user.id), "/start")));

            } catch (TdException e) {

                destory(e);

            }

        }

        deleteStartMessages(chatId);

        insertStartMessages(chatId, message);

        if (data.welcome.messages == null || data.welcome.messages.length == 0) {

            send(Fn.sendText(chatId, Fn.parseHtml(L.PM_WELCOME)), asStartMessage());

        } else {

            for (InputMessageContent input : data.welcome.messages) {

                sync(Fn.sendMessage(chatId, input), asStartMessage());

            }

        }

    }

    @Override
    public void onUnDefPayload(User user, long chatId, Message message, String payload, String[] params) {

        Lang L = Lang.get(user);

        if (user.id == bot.owner) {

            if ("-init".equals(payload)) {

                onLaunch(user, chatId, message);

            }

            return;

        }

        if (!this.data.payloads.containsKey(payload)) {

            Fn.rejectFunction();

            return;

        }

        PmData.Payload data = this.data.payloads.get(payload);

        if (data.notice) {

            try {

                execute(Fn.sendText(bot.owner, Fn.parseHtml(L.PM_ON_START, Fn.a(Fn.displayName(user), "tg://user?id=" + user.id), data)));

            } catch (TdException e) {

                destory(e);

            }

        }

        deleteStartPayloads(chatId, payload);

        for (InputMessageContent input : data.messages) {

            sync(Fn.sendMessage(chatId, input), asStartPayload(payload));

        }

    }

    @Override
    public void onUnDefFn(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (Fn.fromPrivate(message)) {

            Fn.rejectFunction();

        }

    }

    @Override
    public void onNewMessage(int userId, long chatId, Message message) {

        if (data.blocked.contains(chatId)) Fn.finishEvent();

        super.onNewMessage(userId, chatId, message);

        if (userId != bot.owner) {

            data.initSessions();

            if (current != userId) {

                current = userId;

                String report = OL.USER_ID + " : " + Fn.code(userId) + " [ " + Fn.a(OL.CHAT, mkPayload("0", chatId + "")) + " ]";

                report += "\n" + OL.USER_NAME + " : " + Fn.mention(user(userId));

                Message reportMessage = syncE(Fn.sendText(bot.owner, Fn.parseHtml(report)));

                data.sessions.arrayInsert(chatId, "reports", reportMessage.id);

            }

            Message forwarded;

            try {

                forwarded = sync(Fn.sendMessage(bot.owner, Fn.forward(message)));

            } catch (TdException e) {

                destory(e);

                return;

            }

            data.received.put(forwarded.id + "", message.id);

            data.sessions.arrayInsert(chatId, "received", message.id);

            return;

        }

        long targetChat = 0, replyTo = 0;

        if (message.replyToMessageId != 0) {

            long actionMessage = message.replyToMessageId;

            String actionMessageStr = actionMessage + "";

            if (data.received.containsKey(actionMessageStr)) {

                replyTo = data.received.get(actionMessageStr);

                targetChat = data.getSessionByElement("received", replyTo).chatId;

            } else if (data.sended.containsKey(actionMessageStr)) {

                replyTo = data.sended.get(actionMessageStr);

                targetChat = data.getSessionByElement("sended", replyTo).chatId;

            } else {

                PmData.Session session = data.getSessionByElement("reports", actionMessage);

                if (session != null) {

                    targetChat = session.chatId;

                }

                session = data.getSessionByElement("extras", actionMessage);

                if (session != null) {

                    targetChat = session.chatId;

                }

                if (targetChat != 0) {

                    chat = targetChat;

                    delay(Fn.sendText(bot.owner, Fn.parseHtml(OL.CHAT_ENTERED, Fn.mention(user((int) chatId)))));

                }

            }

            if (targetChat == 0) {

                send(Fn.sendText(chatId, Fn.plainText(OL.CHAT_NO_RECORD)));

                return;

            }

        } else if (chat != -1) {

            targetChat = chat;

        } else {

            delay(Fn.sendText(chatId, Fn.plainText(OL.NO_ENTERED_CHAT)), 3000);

            delay(message, 3000);

            return;

        }

        InputMessageContent input = null;

        if (message.forwardInfo == null) {

            input = Fn.convertToInput(message.content);

        }

        if (input == null) input = Fn.forward(message);

        Message sended;

        try {

            sended = sync(Fn.sendMessage(targetChat, replyTo, input));

        } catch (TdException e) {

            send(Fn.sendText(chatId, message.id, Fn.plainText(OL.SEND_FAILED, e)));

            chat = -1;

            return;

        }

        data.sended.put(message.id + "", sended.id);

        data.sessions.arrayInsert(chat, "sended", sended.id);

        delay(Fn.sendText(chatId, message.id, Fn.plainText(OL.SEND_SUCCEED)));

    }

    public void destory(TdException ex) {

        // 由于某些原因停止机器人 比如 : BotToken 无效 / 无法发送消息给主人 等

        image.error = ex;
        image.status = BotImage.STATUS_ERROR;

        Launcher.INSTANCE.E(Fn.sendText(bot.owner, Fn.plainText(OL.BOT_ERR, ex)));

        destroy();

    }

}
