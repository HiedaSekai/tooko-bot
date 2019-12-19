package tooko.twitter.spam;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.main.utils.nsfw.NSFW;
import tooko.main.utils.nsfw.TCRC;
import tooko.main.utils.nsfw.TextCensor;
import tooko.td.TdApi;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import tooko.twitter.archives.UserA;
import twitter4j.*;

import java.io.IOException;
import java.util.LinkedList;

public class UserTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("user_test");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        final Twitter api = account.mkApi();

        final Lang L = Lang.get(user);

        long target;

        if (params.length == 0) {

            target = account.accountId;

        } else {

            String targetSN = Fn.parseScreenName(params[0]);

            try {

                target = api.showUser(targetSN).getId();

            } catch (TwitterException e) {

                postErr(chatId, e);

                return;

            }

        }

        TdApi.Message stat = postText(chatId, "PREDICTING...");

        UserA archive = UserA.show(api, target);

        UserR ur = UserR.predictUser(archive);

        if (ur.isSpam()) {

            editText(stat, "ALREADY A SPAMMER : {}", ur.parseReason());

            return;

        }

        editText(stat, "PRDICTING - FETCHING");

        ResponseList<Status> timeline;

        try {

            timeline = api.getUserTimeline(target, new Paging().count(200));

        } catch (TwitterException e) {

            editText(stat, Fn.parseTwitterException(Lang.get(user.id), e));

            return;

        }

        int count = 0;

        for (int index = 0; index < timeline.size(); index++) {

            Status status = timeline.get(index);

            LinkedList<String> linkArray = new LinkedList<>();

            for (MediaEntity media : status.getMediaEntities()) {

                // if (media.getMediaURLHttps().contains("jpg") && ArrayUtil.isEmpty(media.getVideoVariants())) {

                linkArray.add(media.getMediaURLHttps());

                // }

            }

            log.debug("{} / {} : {} photo", index + 1, timeline.size(), status.getMediaEntities().length);

            if (!linkArray.isEmpty()) {

                continue;


            }

            StatusR rc = StatusR.DATA.getById(status.getId());

//if (rc != null && rc.media == null && rc.text == null) continue;

            editText(stat, "PRDICTING - {} / {}", index + 1, timeline.size());

            boolean spam = false;

            try {

                float[][] results = NSFW.predictRaw(linkArray.toArray(new String[linkArray.size()]));

                for (int i = 0; i < results.length; i++) {

                    float[] result = results[i];

                    log.debug(ArrayUtil.join(result, " "));

                    if (result[3] > 0.8f || result[4] > 0.8f) {

                        spam = true;

                        postText(chatId, "STATUS https://twitter.com/{}/status/{} IMAGE :{}\n\nDRAWINGS: {}\nHENTAI: {}\nNEUTRALS: {}\nPORN: {}\nSEXY: {}", status.getUser().getScreenName(), status.getId(), i + 1, result[0], result[1], result[2], result[3], result[4]);

                    }

                }

            } catch (IOException e) {

                postErr(chatId, e);

            }

            String text = StatusR.statusText(status);

            if (StrUtil.isNotBlank(text)) {

                String raw = TextCensor.getInstance().predictRaw(text);

                TCRC tcrc = TextCensor.getInstance().parseRaw(raw);

                if (tcrc == TCRC.PORN) spam = true;

                if (tcrc != null) {

                    postText(chatId, "STATUS https://twitter.com/{}/status/{} TEXT :{}\n\n{}\n\n{}", status.getUser().getScreenName(), status.getId(), tcrc, text, raw);

                }

            }

            if (spam) count++;

        }

        editText(stat, "FINISH; {} / {} : {}", count, timeline.size(), (double) count / timeline.size());

    }


}
