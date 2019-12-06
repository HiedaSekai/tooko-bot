package tooko.twitter.actions;

import java.util.*;
import tooko.twitter.*;
import twitter4j.*;
import com.mongodb.client.*;
import tooko.twitter.actions.TrackTask.*;
import cn.hutool.core.collection.*;
import tooko.main.*;
import tooko.twitter.archives.*;

public class UserFetchTask extends TimerTask {

    public static Timer timer;

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

    static MongoCursor<TrackTask.IdList> foIter;
    static MongoCursor<TrackTask.IdList> frIter;

    static void reset() {

        foIter = TrackTask.followers.collection.find().cursor();
        frIter = TrackTask.followers.collection.find().cursor();

    }

    static HashSet<Long> cache = new HashSet<>();

    static boolean hasNext() {

        return !cache.isEmpty() || foIter.hasNext();

    }

    static List<Long> nextArray() {

        if (cache.isEmpty()) {

            for (int index = 0;index < 5;index ++) {

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

            array = new LinkedList<>();

            array.addAll(cache);

            cache.clear();

        } else {

            List<Long> arrayList = CollectionUtil.sub(cache, 0, 100);

            array = new LinkedList<>();

            array.addAll(arrayList);

            cache.removeAll(arrayList);

        }

        return array;


    }

    static void fetchInfo(TwitterAccount account, Twitter api) {

        List<Long> array = nextArray();

        try {

            ResponseList<User> users = api.lookupUsers(Fn.toArray(array));

            for (User user : users) { 

                UserA.save(user);

                array.remove(user.getId());

            }

            for (long anf : array) UserA.show(api, anf);

        } catch (TwitterException e) {}

    }

}
