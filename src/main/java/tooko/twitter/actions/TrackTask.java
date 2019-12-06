package tooko.twitter.actions;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import tooko.Launcher;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.core.Table;
import tooko.twitter.TwitterAccount;
import tooko.twitter.archives.UserA;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;


public class TrackTask extends TimerTask {

    public static Timer timer;

    public static void start() {

        stop();

        timer = new Timer("Twitter Track Task");

        timer.schedule(new TrackTask(), new Date(System.currentTimeMillis() + 60 * 1000L), 3 * 60 * 1000L);

    }

    public static void stop() {

        if (timer != null) {

            timer.cancel();

        }

        timer = null;

    }

    @Override
    public void run() {

        List<TwitterAccount> accounts = TwitterAccount.DATA.getAllByField("track", true);

        for (TwitterAccount account : accounts) {

            if (account.track_last == null || (account.track_delay == null || System.currentTimeMillis() - account.track_last > account.track_delay)) {

                TwitterAccount.DATA.updateField(account.accountId, "track_last", System.currentTimeMillis());

                Twitter api = account.mkApi();

                fetchInfo(account, api);

            }

        }

    }

    public static Table<Long, IdList> followers = new Table<>("fo", IdList.class);
    public static Table<Long, IdList> friends = new Table<>("fr", IdList.class);

    public static class IdList {

        @BsonId
        public long accountId;

        public LinkedList<Long> array;

        public IdList() {
        }

        public IdList(long accountId, LinkedList<Long> array) {
            this.accountId = accountId;
            this.array = array;
        }


    }

    static void fetchInfo(TwitterAccount account, Twitter api) {

        boolean followersChanged = true;
        boolean friendsChanged = true;

        UserA archive;

        try {

            archive = UserA.save(api.verifyCredentials());

            update:
            {

                Bson update;

                if ((archive.followers == null || archive.friends == null) || archive.followers != account.last_followers && archive.friends != account.last_friends) {

                    update = and(set("last_followers", archive.followers), set("last_friends", archive.friends));

                } else if (archive.followers != account.last_followers) {

                    update = set("last_followers", archive.followers);

                    friendsChanged = false;

                } else if (archive.friends != account.last_friends) {

                    update = set("last_friends", archive.friends);

                    followersChanged = false;

                } else {

                    followersChanged = friendsChanged = false;

                    break update;

                }

                TwitterAccount.DATA.collection.updateOne(eq("_id", account.accountId), update);

            }

        } catch (TwitterException e) {

            return;

        }

        IdList savedFollowers = followers.getById(account.accountId);
        IdList savedFriends = friends.getById(account.accountId);

        LinkedList<Long> followerIDs;
        LinkedList<Long> friendIDs;

        try {

            friendIDs = Fn.fetchFriendIDs(api);
            followerIDs = Fn.fetchFollowerIDs(api);

        } catch (TwitterException e) {

            Launcher.log.debug(e);

            return;

        }

        followers.setById(account.accountId, new IdList(account.accountId, followerIDs));
        friends.setById(account.accountId, new IdList(account.accountId, friendIDs));

        if (savedFollowers == null) return;

        LinkedList<Long> retain = new LinkedList<>();

        retain.addAll(followerIDs);

        retain.retainAll(savedFollowers.array);

        followerIDs.removeAll(retain);

        savedFollowers.array.removeAll(retain);

        if (followerIDs.isEmpty() && savedFollowers.array.isEmpty()) return;

        Lang L = Lang.get(account.owner);

        boolean simple = followerIDs.size() + savedFollowers.array.size() > 4;

        if (!followersChanged || archive.followers - account.last_followers != followerIDs.size() - savedFollowers.array.size()) {

            // 有用户账号异常

            simple = true;

        }

        String report = L.SPLIT + "\n\n";

        report += L.TWI_ACCOUNT + " : " + account.archive().simpleName() + "\n\n";

        report += L.SPLIT_END + "\n";

        if (!followerIDs.isEmpty()) {

            report += Fn.b(L.TWI_NEW_FOLLOWERS + " :") + "\n";

            for (Long followerId : followerIDs) {

                UserA follower = UserA.show(api, followerId);

                report += "\n";

                if (friendIDs.contains(followerId)) {

                    report += Fn.b("◎") + " " + Fn.b(L.TWI_FOLLOWING) + "  ";

                }

                if (follower == null) {

                    report += Fn.b(followerId) + " (Unknown)";

                    continue;

                }

                report += simple ? follower.simpleName() : follower.parseInfo(L) + "\n";

            }

            report += L.SPLIT_END + "\n";

        }

        if (!savedFollowers.array.isEmpty()) {

            report += Fn.b(L.TWI_LOST_FOLLOWERS + " :") + "\n";

            for (Long followerId : savedFollowers.array) {

                UserA follower = UserA.show(api, followerId);

                if (follower == null) {

                    report += "\n" + Fn.b(followerId) + " (Unknown)";

                    continue;

                }

                report += "\n";

                if (follower.status != null) {

                    report += Fn.b("◎") + " " + Fn.b(follower.status == 1 ? L.TWI_ACC_DELETED : L.TWI_ACC_SUSPENDED) + "  ";

                } else if (savedFriends.array != null && savedFriends.array.contains(followerId) && !friendIDs.contains(followerId)) {

                    report += Fn.b("◎") + " " + Fn.b(L.TWI_TWUF) + "  ";

                } else if (friendIDs.contains(followerId)) {

                    report += Fn.b("◎") + " " + Fn.b(L.TWI_OWUF) + "  ";

                }

                report += simple ? follower.simpleName() : follower.parseInfo(L) + "\n";

            }

            report += L.SPLIT_END + "\n";

        }

        Launcher.twitter.I(Fn.sendText(account.owner, Fn.parseHtml(report)));

    }

}
