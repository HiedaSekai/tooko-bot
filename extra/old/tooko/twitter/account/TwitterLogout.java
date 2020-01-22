package tooko.twitter.account;

import tooko.main.*;
import tooko.td.TdApi.*;
import tooko.twitter.*;
import tooko.twitter.actions.*;

public class TwitterLogout extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("logout");

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        TwitterAccount.DATA.deleteById(account.accountId);

        TrackTask.followers.deleteById(account.accountId);
        TrackTask.friends.deleteById(account.accountId);

        send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).TWI_LOGOUT)));

    }

}
