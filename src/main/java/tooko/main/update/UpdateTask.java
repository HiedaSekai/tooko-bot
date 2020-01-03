package tooko.main.update;

import cn.hutool.core.util.*;
import tooko.*;
import tooko.main.*;
import tooko.td.client.*;

import java.util.*;
import java.util.concurrent.*;

public class UpdateTask extends TimerTask {

    public static Timer timer;

    public static void start() {

        stop();

        timer = new Timer("AutoUpdateTask");

        timer.schedule(new UpdateTask(), new Date(System.currentTimeMillis() + (30 * 1000L)), 30 * 60 * 1000L);

    }

    public static void stop() {

        if (timer != null) {

            timer.cancel();

            timer = null;

        }

    }

    public static void slientUpdate() {

        String local = getLocalHead();

        String remote = getRemoteHead();

        if (local.equals(remote)) return;

        Launcher.INSTANCE.send(Fn.sendText(Env.LOG_CHANNEL, Fn.parseHtml("程序更新 : {} --> {}", Fn.b(local.substring(0, 7)), Fn.b(remote.substring(0, 7)))));

        new Thread("SlientUpdateTask") {

            @Override
            public void run() {

                Process process = RuntimeUtil.exec("./tooko.sh update");

                try {

                    if (process.waitFor() == 1) {

                        Launcher.INSTANCE.send(Fn.sendText(Env.LOG_CHANNEL, Fn.plainText(Lang.DEFAULT.UPDATE_FAILED, RuntimeUtil.getResult(process))));

                        return;

                    }

                } catch (InterruptedException ignored) {
                }

                Env.STOP.set(true);

                Launcher.INSTANCE.stop();

                TdClient.publicPool.shutdown();
                TdClient.asyncPool.shutdown();

                try {

                    while (!TdClient.publicPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                    }

                } catch (InterruptedException ignored) {
                }

                try {

                    while (!TdClient.asyncPool.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                    }

                } catch (InterruptedException ignored) {
                }

                Launcher.INSTANCE.destroy();

                if (Env.USE_SERVICE) {

                    RuntimeUtil.exec("systemctl restart " + Env.SERVICE);

                } else {

                    RuntimeUtil.exec("bash -c 'exec ./tooko.sh start'");

                }

            }

        }.start();

    }

    public static void fetchHead() {

        try {

            RuntimeUtil.exec("git fetch").waitFor();

        } catch (InterruptedException ignored) {
        }

    }

    public static String getLocalHead() {

        return RuntimeUtil.execForStr("git rev-parse HEAD");

    }

    public static String getRemoteHead() {

        return RuntimeUtil.execForStr("git rev-parse FETCH_HEAD");

    }

    public static Boolean checkUpdate() {

        return !getLocalHead().equals(getRemoteHead());

    }

    @Override
    public void run() {

        fetchHead();

        slientUpdate();

    }

}
