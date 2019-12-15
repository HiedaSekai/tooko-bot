package tooko.twitter.actions;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.td.client.TdClient;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import tooko.twitter.archives.UserA;
import tooko.twitter.spam.StatusR;
import tooko.twitter.spam.UserR;
import twitter4j.*;

import java.util.LinkedList;

public class FollowersScan extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("fo_scan");

    }

    @Override
    public void onFunction(TdApi.User user, final long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

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

                send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

                return;

            }

        }

        final LinkedList<Long> followers;

        try {

            followers = Fn.fetchFollowerIDs(api, target);

        } catch (TwitterException e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

            return;

        }

        TdClient.asyncPool.execute(new Runnable() {

            @Override
            public void run() {
                startPredict(chatId, L, api, followers);
            }

        });

    }

    void startPredict(long chatId, Lang L, Twitter api, LinkedList<Long> followers) {

        int count = 0;

        pridectUser:
        for (int index = 0; index < followers.size(); index++) {

            long followerId = followers.get(index);

            UserA archive = UserA.show(api, followerId);

            UserR ur = UserR.predictUser(archive);

            send(Fn.sendText(chatId, Fn.plainText("{} / {}", index + 1, followers.size())));

            if (ur.isSpam()) {

                count++;

                send(Fn.sendText(chatId, Fn.parseHtml(archive.simpleName() + " : " + ur.parseReason())));

                continue;

            }

            ResponseList<Status> timeline;

            try {

                timeline = api.getUserTimeline(followerId, new Paging().count(200));

            } catch (TwitterException e) {

                send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

                return;

            }

            for (Status status : timeline) {

                StatusR r = StatusR.predetectStatus(status);

                if (r.media == StatusR.NSRC.PORN || (r.text != null && r.text.isPorn())) {

                    count++;

                    send(Fn.sendText(chatId, Fn.parseHtml(archive.simpleName() + " : PORN STATUS https://twitter.com/{}/{}", archive.screenName, status.getId())));

                    continue pridectUser;

                }

            }

        }

        send(Fn.sendText(chatId, Fn.plainText("FINISHED : {} / {}", count, followers.size())));

    }

}
