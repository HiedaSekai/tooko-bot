package tooko.main.extras;

import tooko.main.*;
import tooko.td.TdApi.*;
import tooko.td.client.*;

public class PingFunction extends TdFunction {

    @Override
    public void onLoad() {

        initFunction("ping");

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (Fn.fromPrivate(message)) {

            if (param.length() == 0) {

                send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).FN_PING_RESULT)));

            } else {

                send(Fn.sendText(chatId, Fn.plainText(param)));

            }

        } else {

            send(Fn.deleteMessage(message));

        }

    }

}
