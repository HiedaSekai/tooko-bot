package tooko.main.extras;

import tooko.main.*;
import tooko.td.TdApi.*;
import tooko.td.client.*;

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
