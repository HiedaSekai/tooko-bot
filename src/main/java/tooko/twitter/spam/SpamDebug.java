package tooko.twitter.spam;

import tooko.td.TdApi;
import tooko.td.client.TdHandler;

public class SpamDebug extends TdHandler {

    @Override
    public void onLoad() {
        initFunction("spam_clear");
    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {


    }
}
