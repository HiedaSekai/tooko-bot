package tooko.twitter.actions;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.main.utils.nsfw.NSRC;
import tooko.main.utils.nsfw.TCRC;
import tooko.td.TdApi;
import tooko.td.client.TdClient;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterHandler;
import tooko.twitter.archives.UserA;
import tooko.twitter.spam.StatusR;
import tooko.twitter.spam.UserR;
import twitter4j.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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

        TdApi.Message stat = sync(Fn.sendText(chatId, Fn.plainText("PREDICTING ...")));

        for (int index = 0; index < followers.size(); index++) {

            long followerId = followers.get(index);

            UserA archive = UserA.show(api, followerId);

            // if (archive.isProtected && !TrackTask.friends.arrayIsIn(accountId,"array",followerId)) continue;

            UserR ur = UserR.predictUser(archive);

            send(Fn.editText(stat, Fn.plainText("PRDICTING ... {} / {} - FETCHING", index + 1, followers.size())));

            if (ur.isSpam()) {

                count++;

                // send(Fn.sendText(chatId, Fn.parseHtml(archive.simpleName() + " : " + ur.parseReason())));

                continue;

            }

            if (ur.predictAt != null && System.currentTimeMillis() - ur.predictAt < 24 * 60 * 60 * 1000L) {

                continue;

            }

            ResponseList<Status> timeline;

            try {

                timeline = api.getUserTimeline(followerId, new Paging().count(200));

            } catch (TwitterException e) {

                //send(Fn.sendText(chatId, Fn.plainText(Fn.parseTwitterException(L, e))));

                continue;

            }

            LinkedList<String> result = new Process(timeline, stat, index, followers.size()).process();

            if (result != null) {

                count++;

                send(Fn.sendText(chatId, Fn.parseHtml(archive.simpleName() + " : NSFW STATUS : \n\n" + CollectionUtil.join(result, "\n"))));

            }

            UserR.DATA.updateField(followerId, "predictAt", System.currentTimeMillis());

        }

    }

    class Process {

        public List<Status> statusList;
        public TdApi.Message stat;
        public int userIndex;
        public int userMax;

        public Process(List<Status> statusList, TdApi.Message stat, int userIndex, int userMax) {
            this.statusList = statusList;
            this.stat = stat;
            this.userIndex = userIndex;
            this.userMax = userMax;
        }

        public LinkedList<String> result = new LinkedList<>();

        private AtomicInteger index = new AtomicInteger(0);
        private AtomicBoolean finish = new AtomicBoolean(false);

        public LinkedList<String> process() {

            send(Fn.editText(stat, Fn.plainText("PRDICTING ... {} / {} - 0 / {}", userIndex + 1, userMax, statusList.size())));

            for (int index = 0; index < 5; index++) {

                new ProcessTask().start();

            }

            while (!Env.STOP.get() && !finish.get()) {

                ThreadUtil.sleep(233);

            }

            if ((float) result.size() / statusList.size() > 0.1 && result.size() > 4) {

                return result;

            }

            return null;

        }

        long last = -1;

        private synchronized Status nextStatus() {

            if (finish.get()) return null;

            int next = index.incrementAndGet();

            if (statusList.size() <= next) {

                finish.set(true);

                return null;

            }

            if ((next + 1) % 10 == 0 && System.currentTimeMillis() - last > 500) {

                last = System.currentTimeMillis();

                send(Fn.editText(stat, Fn.plainText("PRDICTING ... {} / {} - {} / {}", userIndex + 1, userMax, next + 1, statusList.size())));

            }

            return statusList.get(next);

        }

        class ProcessTask extends Thread {

            @Override
            public void run() {

                while (!Env.STOP.get() && !finish.get()) {

                    Status nextStatus = nextStatus();

                    if (nextStatus == null) return;

                    StatusR rc;

                    try {

                        rc = StatusR.predictStatus(nextStatus);

                    } catch (Exception ex) {

                        continue;

                    }

                    // log.debug("PRED : {} / {}", index, statusList.size());

                    if (rc.media == NSRC.PORN || rc.media == NSRC.SEXY || rc.text == TCRC.PORN) {

                        result.add(StrUtil.format("https://twitter.com/{}/status/{}", nextStatus.getUser().getScreenName(), nextStatus.getId()));

                        if ((float) result.size() / statusList.size() > 0.1 && result.size() > 4) {

                            UserR.DATA.updateField(nextStatus.getUser().getId(), "pornStatus", true);

                            finish.set(true);

                            return;

                        }

                    }

                }

            }
        }

    }

}
