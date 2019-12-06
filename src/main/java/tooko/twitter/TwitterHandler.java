package tooko.twitter;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdFunction;
import tooko.td.client.TdHandler;
import tooko.td.core.KeyboardArray;
import tooko.td.core.KeyboardLine;
import tooko.twitter.archives.UserA;

import java.util.HashMap;
import java.util.List;

public abstract class TwitterHandler extends TdFunction {

    public static class FnCache {

        TwitterCallback callback;

        Message message;

    }

    public static class Receiver extends TdHandler {

        public HashMap<Integer, FnCache> caches = new HashMap<>();

        public static int PERSIST_ID = Fn.PerststId._5;

        @Override
        public void onLoad() {

            initPersist(PERSIST_ID);

        }

        @Override
        public boolean rejectPersistStore(int userId, int subId) {

            return true;

        }

        @Override
        public void onPersistMessage(User user, long chatId, Message message, int subId) {

            removePersist(user);
            
            Lang L = Lang.get(user);

            FnCache cache = caches.remove(user.id);

            delay(message);
            delay(cache.message);

            String screenName = Fn.getText(message);

            if (screenName == null || !screenName.startsWith("@")) {

                delay(Fn.sendText(chatId, Fn.plainText(L.TWI_ANF)));

                return;

            }

            UserA archive = UserA.get(screenName.substring(1));

            if (archive == null) {

                delay(Fn.sendText(chatId, Fn.plainText(L.TWI_ANF)));

                return;

            }

            TwitterAccount account = TwitterAccount.DATA.getById(archive.accountId);

            if (account == null || account.owner != user.id) {

                delay(Fn.sendText(chatId, Fn.plainText(L.TWI_ANF)));

                return;

            }

            delay(Fn.sendText(chatId, Fn.parseHtml(L.TWI_CHOSED, archive)));

            cache.callback.onCallback(user.id, account);

        }

    }

    public void requestTwitter(User user, TwitterCallback callback) {

        requestTwitter(user.id, callback);

    }

    public void requestTwitter(int userId, TwitterCallback callback) {

        List<TwitterAccount> accounts = TwitterAccount.getByOwner(userId);

        if (accounts.isEmpty()) {

            send(Fn.sendText(userId, Fn.plainText(Lang.get(userId).TWI_AUTH_REQUIRED)));

            return;

        } else if (accounts.size() == 1) {

            callback.onCallback(userId, accounts.get(0));

        } else {

            FnCache cache = new FnCache();

            cache.callback = callback;

            findHandler(Receiver.class).caches.put(userId, cache);

            KeyboardArray buttons = new KeyboardArray();

            KeyboardLine line = null;

            for (TwitterAccount account : accounts) {

                if (line == null) {

                    line = buttons.newLine();

                    line.text("@" + account.archive().screenName);

                } else {

                    line.text("@" + account.archive().screenName);

                    line = null;

                }


            }

            writePersist(userId, Receiver.PERSIST_ID);

            cache.message = sync(Fn.sendText(userId, buttons, Fn.plainText(Lang.get(userId).TWI_CH_ACCOUNT)));

        }

    }

    @Override
    public void onFunction(final User user, final long chatId, final Message message, final String function, final String param, final String[] params, final String[] originParams) {

        requestTwitter(user, new TwitterCallback() {

            @Override
            public void onCallback(int userId, TwitterAccount account) {

                onFunction(user, chatId, message, function, param, params, originParams, account);

            }

        });

    }

    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {
    }

}
