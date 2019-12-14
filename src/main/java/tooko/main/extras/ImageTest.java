package tooko.main.extras;

import tooko.main.*;
import tooko.td.*;
import twitter4j.*;
import tooko.twitter.*;
import tooko.twitter.spam.*;

public class ImageTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("status_predict");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {
        
        try {
            
            Status status = account.mkApi().showStatus(Fn.parseStatusId(params[0]));

            ImageDetector.NSRC result = ImageDetector.predetectStatus(status)[0];

            send(Fn.sendText(chatId, Fn.plainText(result.toString())));

        } catch (Exception e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
