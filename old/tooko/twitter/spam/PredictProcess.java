package tooko.twitter.spam;

import cn.hutool.core.thread.*;
import cn.hutool.core.util.*;
import tooko.main.*;
import tooko.main.utils.nsfw.*;
import twitter4j.*;

import java.util.*;
import java.util.concurrent.atomic.*;

public class PredictProcess {

    public static boolean predict(Twitter api, UserR user) throws TwitterException {

        if (user.isSpam() || (user.predictAt != null && System.currentTimeMillis() - user.predictAt < 24 * 60 * 60 * 1000L)) {

            return true;

        }

        ResponseList<Status> timeline = api.getUserTimeline(user.accountId, new Paging().count(200));

        PredictProcess process = new PredictProcess(timeline);

        process.process();

        return process.spam.get();

    }

    public List<Status> statusList;

    public PredictProcess(List<Status> statusList) {
        this.statusList = statusList;
    }

    public LinkedList<String> result = new LinkedList<>();

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicBoolean finish = new AtomicBoolean(false);
    private AtomicBoolean spam = new AtomicBoolean(false);

    public LinkedList<String> process() {

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

                if (rc.media == NSRC.PORN || rc.media == NSRC.SEXY || rc.text == TCRC.PORN) {

                    result.add(StrUtil.format("https://twitter.com/{}/status/{}", nextStatus.getUser().getScreenName(), nextStatus.getId()));

                    if ((float) result.size() / statusList.size() > 0.1 && result.size() > 4) {

                        UserR.DATA.updateField(nextStatus.getUser().getId(), "pornStatus", true);

                        spam.set(true);

                        finish.set(true);

                        return;

                    }

                }

            }

        }
    }

}
