package tooko.twitter.spam;

import cn.hutool.core.util.NumberUtil;
import tooko.td.TdApi;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import twitter4j.Status;
import twitter4j.TwitterException;

public class ImageTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("img_test");
    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        Status status;

        try {

            status = account.mkApi().showStatus(NumberUtil.parseLong(param));

        } catch (TwitterException e) {

            client.log.warn(e);

            return;

        }

        StatusR.predictStatus(status);

    }

}
