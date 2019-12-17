package tooko.main.bots;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.InlineKeyboardButton;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdHandler;
import tooko.td.core.InlineArray;

import java.math.BigInteger;
import java.util.List;

public class BotPanel extends TdHandler {

    public int DATA_ID = Fn.DataId._4;

    @Override
    public void onLoad() {

        initFunction("bots");

        initData(DATA_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (!Fn.fromPrivate(message)) {

            send(Fn.deleteMessage(message));

            return;

        }

        Lang L = Lang.get(user);

        showList(L, user.id, chatId, message.id, true);

    }

    void showList(Lang L, int userId, long chatId, long messageId, boolean send) {

        List<BotData> bots = BotData.DATA.getAllByField("owner", userId);

        if (bots.isEmpty()) {

            if (send) {

                send(Fn.sendText(chatId, Fn.plainText(L.BOT_NONE)));

            } else {

                send(Fn.editText(chatId, messageId, Fn.plainText(L.BOT_NONE)));

            }

            return;

        }

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[bots.size()][];

        for (int index = 0; index < buttons.length; index++) {


            BotData botData = bots.get(index);

            buttons[index] = Fn.inlineDataLine("@" + botData.userName, DATA_ID, 1, BigInteger.valueOf(botData.botId).toByteArray());

        }

        if (send) {

            send(Fn.sendText(chatId, 0, buttons, Fn.plainText(L.BOT_CH)));

        } else {

            send(Fn.editText(chatId, messageId, buttons, Fn.plainText(L.BOT_CH)));

        }

    }

    void showPanel(Lang L, BotData botData, long chatId, long messageId, byte[][] data) {

        String stat = L.SPLIT;

        stat += "\n\n" + L.BOT_MANAGE + " : " + Fn.a("@" + botData.userName, "https://t.me/" + botData.userName);

        stat += "\n" + L.BOT_ID + " : " + Fn.code(botData.botId);

        stat += "\n" + L.BOT_STATUS + " : ";

        if (!BotImage.images.containsKey(botData.botId)) {

            stat += L.BOT_STATUS_UNSTARTED;

        } else {

            BotImage image = BotImage.images.get(botData.botId);

            if (image.status == BotImage.STATUS_RUNNING) {

                stat += L.BOT_STATUS_RUNNING;

            } else {

                stat += L.BOT_STATUS_ERROR;

            }

        }

        InlineArray buttons = new InlineArray();

        buttons.dataLine(L.REFRESH, DATA_ID, 2, data[0]).inlineData(L.BOT_DELETE, DATA_ID, 4, data[0]);

        buttons.dataLine("🔙", DATA_ID, 0);

        I((Fn.editText(chatId, messageId, buttons, Fn.parseHtml(stat))));

    }

    @Override
    public void onNewCallbackQuery(int userId, long chatId, long messageId, long queryId, int subId, byte[][] data) {

        Lang L = Lang.get(userId);

        if (subId == 0) {

            send(Fn.answerConfirm(queryId));

            showList(L, userId, chatId, messageId, false);

            return;

        }

        int botId = new BigInteger(data[0]).intValue();

        BotData botData = BotData.DATA.getById(botId);

        if (botData == null || botData.owner != userId) {

            send(Fn.answerAlert(queryId, L.BOT_INVALID));

            send(Fn.deleteMessages(chatId, messageId));

            return;

        }

        if (subId == 1) {

            showPanel(L, botData, chatId, messageId, data);

        } else if (subId == 2) {

            showPanel(L, botData, chatId, messageId, data);

            send(Fn.answerText(queryId, L.REFRESHED));

        } else if (subId == 3) {

            send(Fn.answerAlert(queryId, L.RESTARTING));

            BotImage.images.remove(botId);

            BotImage.start(botData);

            showPanel(L, botData, chatId, messageId, data);

        } else if (subId == 4) {

            InlineArray confirm = new InlineArray();

            confirm.dataLine(L.BOT_DELETE_CONFIRM, DATA_ID, 5, data);

            confirm.dataLine("🔙", DATA_ID, 1, data);

            send(Fn.editText(chatId, messageId, confirm, Fn.plainText(L.BOT_DELETE_WARN)));

        } else if (subId == 5) {

            send(Fn.answerText(queryId, L.DELETED));

            BotImage.delete(botData);

            showList(L, userId, chatId, messageId, false);

        }

    }


}
