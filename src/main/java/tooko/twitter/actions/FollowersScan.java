package tooko.twitter.actions;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import tooko.twitter.spam.StatusR;
import twitter4j.*;

import java.util.LinkedList;

public class FollowersScan extends TwitterHandler {

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        Twitter api = account.mkApi();

        Lang L = Lang.get(user);

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

        LinkedList<Long> followers;

        try {

            followers = Fn.fetchFollowerIDs(api, target);

        } catch (TwitterException e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

            return;

        }

        for (long followerId : followers) {

            ResponseList<Status> timeline;

            try {

                timeline = api.getUserTimeline(followerId, new Paging().count(200));

            } catch (TwitterException e) {

                send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

                return;

            }

            for (Status status : timeline) {

                StatusR.NSRC rc = StatusR.predetectStatus(status);


            }

        }

    }

}
