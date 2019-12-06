package tooko.main.extras;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.FormattedText;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdException;
import tooko.td.client.TdHandler;
import tooko.td.core.InlineArray;

public class SwitchLang extends TdHandler {

    public static int DATA_ID = Fn.DataId._1;

    @Override
    public void onLoad() {

        initFunction("lang");

        initData(DATA_ID);

    }

    public static InlineArray langs = new InlineArray();

    static {

        langs.dataLine(Lang.DEF.LANG_NAME, DATA_ID, 0);

        langs.dataLine(Lang.ENG.LANG_NAME, DATA_ID, 1);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        send(Fn.sendText(chatId, SwitchLang.langs, Fn.plainText(Lang.get(user).CHOOSE_LANG)));
    }

    @Override
    public void onNewCallbackQuery(int userId, long chatId, long messageId, long queryId, int subId, byte[][] data) {

        send(Fn.answerConfirm(queryId));

        Lang.DATA.setById(userId, new Lang.DB(userId, subId));

        Lang L = Lang.get(userId);

        FormattedText msg = Fn.parseHtml(L.LANG_SELECTED);

        try {

            execute(Fn.editText(chatId, messageId, msg));

            send(Fn.sendText(chatId, Fn.plainText(L.LAUNCH)));

        } catch (TdException ex) {

            if (ex.getCode() == -1) {

                send(Fn.deleteMessages(chatId, messageId));

                send(Fn.answerServerClosed(queryId, userId));

            } else {

                send(Fn.sendText(chatId, msg));

                send(Fn.deleteMessages(chatId, messageId));

            }

        }

    }

}
