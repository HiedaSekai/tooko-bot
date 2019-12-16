package tooko.twitter.account;

import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.ReplyMarkupRemoveKeyboard;
import tooko.td.TdApi.User;
import tooko.td.client.TdHandler;
import tooko.td.core.KeyboardArray;
import tooko.twitter.ApiToken;
import tooko.twitter.TwitterAccount;
import tooko.twitter.archives.UserA;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TwitterLogin extends TdHandler {

    private LinkedHashMap<String, ApiToken> apis;

    {

        apis = new LinkedHashMap<>();

        for (ApiToken api : Env.TWITTER_API_TOKENS) {

            apis.put(api.name, api);

        }

    }

    public int PERSIST_ID = Fn.PerststId._4;

    @Override
    public void onLoad() {

        initFunction("login");

        initPersist(PERSIST_ID);

    }

    @Override
    public boolean rejectPersistStore(int userId, int subId) {

        return true;

    }

    private HashMap<Integer, LoginCache> cache = new HashMap<>();

    private static class LoginCache {

        int type = 0;

        String apiKey;
        String apiSecretKey;
        String accessToken;
        String accessTokenSecret;

        Twitter api;

        RequestToken requestToken;


    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        Lang L = Lang.get(user);

        if (!Env.TWITTER_PUBLIC && !Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.plainText(L.ERR_PERSIONAL)));

            return;

        } else if (!Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.plainText(L.PUBLIC_WARN)));

        }

        KeyboardArray buttons = new KeyboardArray();

        for (ApiToken api : apis.values()) {

            buttons.textLine(api.name);

        }

        buttons.textLine(L.TWI_CUS_API);

        cache.put(user.id, new LoginCache());

        writePersist(user, PERSIST_ID);

        send(Fn.sendText(chatId, buttons, Fn.plainText(L.TWI_CH_API)));

    }

    @Override
    public void onPersistFinished(int userId, int subId) {

        cache.remove(userId);

    }

    @Override
    public void onPersistMessage(User user, long chatId, Message message, int subId) {

        Lang L = Lang.get(user);

        LoginCache login = cache.get(user.id);

        if (login.type == 0) {

            String method = Fn.getText(message);

            if (apis.containsKey(method)) {

                ApiToken api = apis.get(method);

                login.apiKey = api.apiKey;
                login.apiSecretKey = api.apiSecretKey;

                login.api = api.mkApi();

                login.type = 3;

                send(Fn.sendText(chatId, Fn.horizontal(L.TWI_OAUTH, L.TWI_ACCESS_TOKEN), Fn.plainText(L.TWI_CH_LT)));

            } else if (L.TWI_CUS_API.equals(method)) {

                login.type = 1;

                send(Fn.sendText(chatId, new ReplyMarkupRemoveKeyboard(), Fn.plainText(L.TWI_INPUT_API_KEY)));

            } else {

                onPersistCancel(user, chatId, message, subId);

            }

        } else if (login.type == 1) {

            login.apiKey = Fn.getText(message);

            login.type = 2;

            send(Fn.sendText(chatId, Fn.plainText(L.TWI_INPUT_API_SECRET_KEY)));

        } else if (login.type == 2) {

            login.apiSecretKey = Fn.getText(message);

            login.api = Fn.mkApi(login.apiKey, login.apiSecretKey);

            login.type = 3;

            send(Fn.sendText(chatId, Fn.horizontal(L.TWI_OAUTH, L.TWI_ACCESS_TOKEN), Fn.plainText(L.TWI_CH_LT)));

        } else if (login.type == 3) {

            String method = Fn.getText(message);

            if (L.TWI_OAUTH.equals(method)) {

                RequestToken requestToken;

                try {

                    requestToken = login.api.getOAuthRequestToken();

                } catch (TwitterException e) {

                    send(Fn.sendText(chatId, new ReplyMarkupRemoveKeyboard(), Fn.plainText(Fn.parseTwitterException(L, e))));

                    removePersist(user);

                    return;

                }

                login.requestToken = requestToken;

                login.type = 4;

                send(Fn.sendText(chatId, new ReplyMarkupRemoveKeyboard(), Fn.plainText(L.TWI_AUTH_INPUT_CODE, requestToken.getAuthenticationURL())));

            } else if (L.TWI_ACCESS_TOKEN.equals(method)) {

                login.type = 5;

                send(Fn.sendText(chatId, Fn.plainText(L.TWI_INPUT_ACCESS_TOKEN)));

            } else {

                onPersistCancel(user, chatId, message, subId);

            }

        } else if (login.type == 4) {

            removePersist(user);

            String codeText = Fn.getText(message);

            try {

                AccessToken accessToken = login.api.getOAuthAccessToken(login.requestToken, codeText);

                login.accessToken = accessToken.getToken();

                login.accessTokenSecret = accessToken.getTokenSecret();

                finishAuth(L, user, chatId, login);

            } catch (TwitterException e) {

                send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

            }

        } else if (login.type == 5) {

            login.accessToken = Fn.getText(message);

            login.type = 6;

            send(Fn.sendText(chatId, Fn.plainText(L.TWI_INPUT_ACCESS_TOKEN_SECRET)));

        } else if (login.type == 6) {

            removePersist(user);

            login.accessTokenSecret = Fn.getText(message);

            finishAuth(L, user, chatId, login);

        }

    }

    void finishAuth(Lang L, User user, long chatId, LoginCache login) {

        TwitterAccount account = new TwitterAccount();

        account.owner = user.id;

        account.apiKey = login.apiKey;
        account.apiSecretKey = login.apiSecretKey;
        account.accessToken = login.accessToken;
        account.accessTokenSecret = login.accessTokenSecret;

        Twitter api = account.mkApi();

        UserA archive;

        try {

            archive = UserA.save(api.verifyCredentials());

            account.accountId = archive.accountId;

        } catch (TwitterException e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

            return;

        }

        TwitterAccount existAccount = TwitterAccount.DATA.getById(account.accountId);

        if (existAccount != null && existAccount.owner != user.id) {

            send(Fn.sendText(existAccount.owner, Fn.parseHtml(L.TWI_AUTHED_BY, archive, Fn.mention(user))));

        }

        TwitterAccount.DATA.setById(account.accountId, account);

        send(Fn.sendText(chatId, Fn.parseHtml(L.TWI_WELCOME, archive)));

    }

}
