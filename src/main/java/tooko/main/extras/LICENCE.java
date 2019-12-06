package tooko.main.extras;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdHandler;

public class LICENCE extends TdHandler {

    @Override
    public void onLoad() {

        initFunction("license");

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        send(Fn.sendText(chatId, Fn.parseHtml(Lang.get(user).LICENSE)));

    }

}
