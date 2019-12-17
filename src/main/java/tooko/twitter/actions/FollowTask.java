package tooko.twitter.actions;

import tooko.Launcher;
import tooko.main.Fn;
import tooko.twitter.TwitterAccount;
import twitter4j.*;

import java.util.*;

public class FollowTask extends TimerTask {

    public static Timer timer;

    public static void start() {

        stop();

        timer = new Timer("Twitter Follow Task");

        timer.schedule(new TrackTask(), new Date(System.currentTimeMillis() + 60 * 1000L), 3 * 60 * 1000L);

    }

    public static void stop() {

        if (timer != null) {

            timer.cancel();

        }

        timer = null;

    }

    static void fetchInfo(TwitterAccount account, Twitter api) {

        try {

            ResponseList<Status> timeline = api.getHomeTimeline(new Paging().count(200));

            HashSet<Long> queue = new LinkedHashSet<>();

            for (Status status : timeline) {

                queue.add(status.getUser().getId());

                if (status.getInReplyToUserId() > 0) {

                    queue.add(status.getInReplyToStatusId());

                }

            }

            Iterator<Long> iter = queue.iterator();

            while (iter.hasNext()) {

                long accountId = iter.next();

                if (TrackTask.friends.arrayIsIn(account.accountId, "array", accountId) || TrackTask.followers.arrayIsIn(account.accountId, "array", accountId) || AutoData.DATA.arrayIsIn(account.accountId, "autoFollowedIDs", accountId)) {

                    iter.remove();

                }

            }

            LinkedList<User> users = Fn.fetchUsers(api, queue);

            for (User user : users) {


            }

        } catch (TwitterException e) {

            Launcher.log.warn(e);

        }

    }

    @Override
    public void run() {

        List<TwitterAccount> accounts = TwitterAccount.DATA.getAllByField("follow", true);

        for (TwitterAccount account : accounts) {

            fetchInfo(account, account.mkApi());

        }

    }

}
