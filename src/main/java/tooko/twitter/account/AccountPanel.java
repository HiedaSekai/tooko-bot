package tooko.twitter.account;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.core.InlineArray;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import tooko.twitter.archives.UserA;

public class AccountPanel extends TwitterHandler {

    public static int DATA_ID = Fn.DataId._6;

    @Override
    public void onLoad() {

        initData(DATA_ID);

        initFunction("account");

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        Lang L = Lang.get(user);

        main(L, chatId, message.id, account, true);

    }

    void main(Lang L, long chatId, long messageId, TwitterAccount account, boolean send) {

        byte[] accountId = Fn.numbyte(account.accountId);

        UserA archive = account.archive();

        String info = L.SPLIT + "\n\n";

        info += L.TWI_ACC_MNG + " : " + archive.parseInfo(L);
        
        info += "\n\n" + L.SPLIT_END;
        
        InlineArray actions = new InlineArray();

        // actions.dataLine(L.TWI_ACC_TRACK, DATA_ID, 1, accountId);

        actions.dataLine(L.TWI_ACC_EXPORT, DATA_ID, 2, accountId);

        if (send) {

            send(Fn.sendText(chatId, actions, Fn.parseHtml(info)));

        } else {

            send(Fn.editText(chatId, messageId, actions, Fn.parseHtml(info)));

        }

    }

    @Override
    public void onNewCallbackQuery(int userId, long chatId, long messageId, long queryId, int subId, byte[][] data) {

        Lang L = Lang.get(userId);

        if (subId == 0) {

            TwitterAccount account = TwitterAccount.DATA.getById(Fn.byte2long(data[0]));

            if (account == null || account.owner != userId) {

                send(Fn.answerAlert(queryId, 114514, L.TWI_ANF));

                send(Fn.deleteMessages(chatId, messageId));

            } else {

                send(Fn.answerConfirm(queryId));

                main(L, chatId, messageId, account, false);

            }

        } else if (subId == 2) {

            send(Fn.answerConfirm(queryId));

            TwitterAccount account = TwitterAccount.DATA.getById(Fn.byte2long(data[0]));

            UserA archive = account.archive();

            String info = L.SPLIT + "\n\n";

            info += L.TWI_ACC_MNG + " : " + archive.name + " " + Fn.b("@" + archive.screenName);

            info += "\n\n";

            info += Fn.b(L.TWI_API_KEY) + " : " + Fn.code(account.apiKey) + "\n\n";
            info += Fn.b(L.TWI_API_SECRET_KEY) + " : " + Fn.code(account.apiSecretKey) + "\n\n";
            info += Fn.b(L.TWI_ACCESS_TOKEN) + " : " + Fn.code(account.accessToken) + "\n\n";
            info += Fn.b(L.TWI_ACCESS_TOKEN_SECRET) + " : " + Fn.code(account.accessTokenSecret);

            InlineArray actions = new InlineArray();

            actions.dataLine(L.BACK, DATA_ID, 0, data);

            send(Fn.editText(chatId, messageId, actions, Fn.parseHtml(info)));

        }

    }

}
