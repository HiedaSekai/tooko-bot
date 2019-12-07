package tooko.twitter.nsfw;

import tooko.twitter.*;
import tooko.td.*;
import twitter4j.*;
import tooko.main.*;
import tooko.twitter.archives.*;

public class NSFWTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("user_predict");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        try {
            
            NSFWDetector.NSRC result = NSFWDetector.predetectStatus(account.mkApi().getUserTimeline(Fn.parseScreenName(param), new Paging().count(200)));

            send(Fn.sendText(chatId, Fn.plainText(result.toString())));

        } catch (Exception e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
