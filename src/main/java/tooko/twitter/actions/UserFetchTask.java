package tooko.twitter.actions;

import cn.hutool.core.collection.CollectionUtil;
import com.mongodb.client.MongoCursor;
import tooko.main.Fn;
import tooko.twitter.TwitterAccount;
import tooko.twitter.archives.UserA;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.util.*;

public class UserFetchTask extends TimerTask {

    public static Timer timer;
    static MongoCursor<TrackTask.IdList> foIter;
    static MongoCursor<TrackTask.IdList> frIter;
    static HashSet<Long> cache = new HashSet<>();

    public static void start() {

        stop();

        timer = new Timer("Twitter User Fetch Task");

        timer.scheduleAtFixedRate(new UserFetchTask(), new Date(System.currentTimeMillis() + 60 * 1000L), 5 * 60 * 1000L);

    }

    public static void stop() {

        if (timer != null) {

            timer.cancel();

        }

        timer = null;

    }

    static List<Long> nextArray() {

        if (cache.isEmpty()) {

            for (int index = 0; index < 5; index++) {

                if (foIter.hasNext()) {

                    cache.addAll(foIter.next().array);

                }

                if (frIter.hasNext()) {

                    cache.addAll(frIter.next().array);

                }

            }

        }

        List<Long> array;

        if (cache.size() <= 100) {

            array = new LinkedList<>(cache);

            cache.clear();

        } else {

            List<Long> arrayList = CollectionUtil.sub(cache, 0, 100);

            array = new LinkedList<>(arrayList);

            cache.removeAll(arrayList);

        }

        return array;


    }

    static void reset() {

        foIter = TrackTask.followers.collection.find().cursor();
        frIter = TrackTask.followers.collection.find().cursor();

    }

    static boolean hasNext() {

        return !cache.isEmpty() || foIter.hasNext();

    }

    static void fetchInfo(TwitterAccount account, Twitter api) {

        List<Long> array = nextArray();

        try {

            ResponseList<User> users = api.lookupUsers(Fn.toArray(array));

            for (User user : users) {

                UserA.save(user);

                array.remove(user.getId());

            }

            for (long anf : array)
                UserA.show(api, anf);

        } catch (TwitterException ignored) {
        }

    }

    @Override
    public void run() {

        List<TwitterAccount> accounts = TwitterAccount.DATA.getAll();

        if (accounts.isEmpty()) return;

        reset();

        while (true) for (TwitterAccount account : accounts) {

            if (account.track_last == null || (account.track_delay == null || System.currentTimeMillis() - account.track_last > account.track_delay)) {

                TwitterAccount.DATA.updateField(account.accountId, "track_last", System.currentTimeMillis());

                Twitter api = account.mkApi();

                fetchInfo(account, api);

                if (!hasNext()) return;

            }

        }

    }

}
