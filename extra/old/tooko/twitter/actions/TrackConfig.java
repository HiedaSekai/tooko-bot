package tooko.twitter.actions;

import tooko.main.*;
import tooko.td.*;
import tooko.td.TdApi.*;
import tooko.twitter.*;

public class TrackConfig extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("track");

    }

    @Override
    public void onFunction(final TdApi.User user, final long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {

        SendMessage usage = Fn.sendText(chatId, Fn.plainText("/track <enable/disable/delay>"));

        if (params.length == 0) {

            send(usage);

        } else if ("enable".equals(params[0])) {

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    TwitterAccount.DATA.updateField(account.accountId, "track", true);

                    send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).ENABLED)));

                }

            });

        } else if ("disable".equals(params[0])) {

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    TwitterAccount.DATA.updateField(account.accountId, "track", true);

                    send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).DISABLED)));

                }

            });

        } else if ("delay".equals(params[0])) {

            if (params.length == 1) {

                send(Fn.sendText(chatId, Fn.plainText("/track delay <time>")));

                return;

            }

            long delay;

            try {

                delay = Fn.parseTime(params[1]);

            } catch (IllegalArgumentException ex) {

                send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).INVALID_TIME)));

                return;

            }

            if (delay < 3 * 60 * 1000) {

                send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).DELAY_TOO_SHORT, "3m")));

                return;

            }

            final Long _delay = delay;

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    account.track_delay = _delay;

                    TwitterAccount.DATA.setById(account.accountId, account);

                    send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).SETTED)));

                }

            });


        } else {

            send(usage);

        }

    }

}
