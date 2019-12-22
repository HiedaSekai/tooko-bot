package tooko.twitter.follow;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterCallback;
import tooko.twitter.TwitterHandler;

public class FollowConfig extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("follow");

    }

    @Override
    public void onFunction(final TdApi.User user, final long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {

        TdApi.SendMessage usage = Fn.sendText(chatId, Fn.plainText("/follow <enable/disable>"));

        if (params.length == 0) {

            send(usage);

        } else if ("enable".equals(params[0])) {

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    TwitterAccount.DATA.updateField(account.accountId, "follow", true);

                    send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).ENABLED)));

                }

            });

        } else if ("disable".equals(params[0])) {

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    TwitterAccount.DATA.updateField(account.accountId, "follow", null);

                    send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).DISABLED)));

                }

            });

        } else {

            send(usage);

        }

    }

}