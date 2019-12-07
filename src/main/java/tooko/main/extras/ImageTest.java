package tooko.main.extras;

import tooko.main.*;
import tooko.main.utils.*;
import tooko.td.*;
import tooko.td.client.*;

public class ImageTest extends TdHandler {

    @Override
    public void onLoad() {

        initFunction("img_test");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {

        try {
            
            String result = NSFWClient.predict(params[1]);

            send(Fn.sendText(chatId, Fn.plainText(result)));

        } catch (Exception e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
