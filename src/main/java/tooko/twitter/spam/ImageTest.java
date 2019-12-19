package tooko.twitter.spam;

import cn.hutool.core.util.ArrayUtil;
import tooko.main.Fn;
import tooko.main.utils.nsfw.NSFW;
import tooko.td.TdApi;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.LinkedList;

public class ImageTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("img_test");
    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        Status status;

        try {

            status = account.mkApi().showStatus(Fn.parseStatusId(params[0]));

        } catch (TwitterException ex) {

            postErr(chatId, ex);

            return;

        }

        LinkedList<String> linkArray = new LinkedList<>();

        for (MediaEntity media : status.getMediaEntities()) {

            if (media.getMediaURLHttps().contains("jpg") && ArrayUtil.isEmpty(media.getVideoVariants())) {

                linkArray.add(media.getMediaURLHttps());

            }

        }

        if (!linkArray.isEmpty()) {

            postText(chatId, "no photo found in status");

            return;

        }

        float[][] results;

        try {

            results = NSFW.predictRaw(linkArray.toArray(new String[0]));

        } catch (IOException ex) {

            postErr(chatId, ex);

            return;

        }

        for (float[] result : results) {

            postText(chatId, "DRAWINGS: {}\nHENTAI: {}\nNEUTRALS: {}\nPORN: {}\nSEXY: {}", result[0], result[1], result[2], result[3], result[4]);

        }

    }

}
