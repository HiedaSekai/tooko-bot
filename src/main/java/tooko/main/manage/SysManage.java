package tooko.main.manage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RuntimeUtil;
import tooko.Launcher;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.main.update.UpdateTask;
import tooko.td.TdApi.InlineKeyboardButton;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.ReplyMarkupInlineKeyboard;
import tooko.td.TdApi.User;
import tooko.td.client.TdClient;
import tooko.td.client.TdException;
import tooko.td.client.TdFunction;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class SysManage extends TdFunction {

    public int DATA_ID = Fn.DataId._3;

    @Override
    public void onLoad() {

        initFunction("sys");

        initData(DATA_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (!Fn.fromPrivate(message)) {

            I(Fn.deleteMessage(message));

            return;

        }

        if (!Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).PERMISSION_DENIED)));

            return;

        }

        send(Fn.sendText(chatId, new ReplyMarkupInlineKeyboard(parseOptions(user.id)), Fn.parseHtml(parseStat(user.id))));

    }

    @Override
    public void onLogin() {

        File file = Env.getFile("cache/restart_from");

        if (file.isFile()) {

            String[] from = FileUtil.readUtf8String(file).split(" ");

            int chatId = NumberUtil.parseInt(from[0]);

            long messageId = NumberUtil.parseLong(from[1]);

            send(Fn.deleteMessages(chatId, messageId));

            send(Fn.sendText(chatId, new ReplyMarkupInlineKeyboard(parseOptions(chatId)), Fn.parseHtml(parseStat(chatId))));

            FileUtil.del(file);

        }

    }

    private String parseStat(int userId) {

        Lang L = Lang.get(userId);

        MemoryInfo.reload();

        String sys = L.SPLIT;

        sys += "\n\n" + L.ACTIVE_THREADS + " : " + Thread.activeCount();

        sys += "\n" + L.MEM_TOTAL + " : " + parseMemStr(MemoryInfo.memTotal);

        sys += "\n" + L.MEM_USED + " : " + parseMemStr(MemoryInfo.memTotal - MemoryInfo.memFree - MemoryInfo.buffers - MemoryInfo.cached);

        sys += "\n" + L.MEM_CACHED + " : " + parseMemStr(MemoryInfo.cached + MemoryInfo.buffers);

        sys += "\n" + L.MEM_FREE + " : " + parseMemStr(MemoryInfo.memFree);

        try {

            String local = UpdateTask.getLocalHead();
            String remote = UpdateTask.getRemoteHead();

            sys += "\n\n" + L.LOCAL_VERSION + " : " + Fn.b(local.substring(0, 7));
            sys += "\n" + L.REMOTE_VERSION + " : " + Fn.b(remote.substring(0, 7));
            sys += "\n" + L.VER_UPDATE + " : " + Fn.b(SysConfig.isHookUpdateEnable() ? Lang.get(userId).HOOK_UPDATE : SysConfig.isAutoUpdateEnable() ? Lang.get(userId).AUTO_UPDATE : Lang.get(userId).DISABLED);

        } catch (Exception ex) {

            sys += "\nn" + L.CHECK_UPDATE_FAILED;

        }

        return sys;

    }

    private InlineKeyboardButton[][] parseOptions(int userId) {

        InlineKeyboardButton[][] options = new InlineKeyboardButton[2][];

        options[0] = new InlineKeyboardButton[]{

                Fn.inlineData(Lang.get(userId).REFRESH, DATA_ID, 0), Fn.inlineData(Lang.get(userId).RESTART, DATA_ID, 2)

        };

        options[1] = new InlineKeyboardButton[]{

                UpdateTask.checkUpdate() ? Fn.inlineData(Lang.get(userId).UPDATE_NOW, DATA_ID, 3) : Fn.inlineData(Lang.get(userId).CHECK_UPDATE, DATA_ID, 4),

                Fn.inlineData(SysConfig.isAutoUpdateEnable() ? Lang.get(userId).DISABLE_AUTO_UPDATE : Lang.get(userId).ENABLE_AUTO_UPDATE, DATA_ID, 5),
                Fn.inlineData(SysConfig.isHookUpdateEnable() ? Lang.get(userId).DISABLE_HOOK_UPDATE : Lang.get(userId).ENABLE_HOOK_UPDATE, DATA_ID, 6)

        };

        return options;

    }

    private String parseMemStr(long mem) {

        if (mem > 1024 * 1024) {

            return NumberUtil.roundDown(((double) mem / 1024 / 1024), 2) + " G";

        } else {

            return (mem / 1024) + " M";

        }

    }

    @Override
    public void onNewCallbackQuery(int userId, final long chatId, long messageId, long queryId, int subId, byte[][] data) {

        if (subId == 0) {

            try {

                execute(Fn.editText(chatId, messageId, parseOptions(userId), Fn.parseHtml(parseStat(userId))));

            } catch (TdException ignored) {
            }

            send(Fn.answerText(queryId, Lang.get(userId).REFRESHED));

        } else if (subId == 1) {

            // removed

        } else if (subId == 2) {

            send(Fn.deleteMessages(chatId, messageId));

            restart(userId);

        } else if (subId == 3) {

            send(Fn.deleteMessages(chatId, messageId));

            update(userId);

        } else if (subId == 4) {

            UpdateTask.fetchHead();

            if (UpdateTask.checkUpdate()) {

                send(Fn.answerText(queryId, Lang.get(userId).NEW_UPDATE_AVILABLE));

                send(Fn.editText(chatId, messageId, parseOptions(userId), Fn.parseHtml(parseStat(userId))));

            } else {

                send(Fn.answerText(queryId, Lang.get(userId).NO_UPDATE));

            }

        } else if (subId == 5) {

            boolean target = !SysConfig.isAutoUpdateEnable();

            if (SysConfig.isHookUpdateEnable()) {

                SysConfig.setHookUpdate(false);

            }

            SysConfig.setAutoUpdate(target);

            send(Fn.answerText(queryId, target ? Lang.get(userId).ENABLED : Lang.get(userId).DISABLED));

            send(Fn.editText(chatId, messageId, parseOptions(userId), Fn.parseHtml(parseStat(userId))));

        } else if (subId == 6) {

            boolean target = !SysConfig.isHookUpdateEnable();

            if (SysConfig.isAutoUpdateEnable()) {

                SysConfig.setAutoUpdate(false);

            }

            SysConfig.setHookUpdate(target);

            send(Fn.answerText(queryId, target ? Lang.get(userId).ENABLED : Lang.get(userId).DISABLED));

            send(Fn.editText(chatId, messageId, parseOptions(userId), Fn.parseHtml(parseStat(userId))));
        }

    }

    private void writeRestartFrom(int userId, long messageId) {

        FileUtil.writeUtf8String(userId + " " + messageId, Env.getFile("cache/restart_from"));

    }

    private void safeStop() {

        Env.STOP.set(true);

        Launcher.INSTANCE.stop();

        TdClient.publicPool.shutdown();
        TdClient.asyncPool.shutdown();

        try {

            while (!TdClient.publicPool.awaitTermination(10, TimeUnit.SECONDS)) {

                Launcher.log.debug("等待公共队列退出...");

            }

        } catch (InterruptedException ignored) {
        }

        try {

            while (!TdClient.asyncPool.awaitTermination(10, TimeUnit.SECONDS)) {

                Launcher.log.debug("等待异步队列退出...");

            }

        } catch (InterruptedException ignored) {
        }

    }

    private void restart() {

        if (Env.USE_SERVICE) {

            RuntimeUtil.exec("systemctl restart " + Env.SERVICE);

        } else {

            RuntimeUtil.exec("bash -c 'exec ./tooko.sh start'");

        }

    }

    private void destroyAndRestart() {

        Launcher.INSTANCE.destroy();

        restart();

    }

    private void destroyAndRestart(int userId, long messageId) {

        writeRestartFrom(userId, messageId);

        destroyAndRestart();

    }

    private void restart(final int userId) {

        new Thread() {

            @Override
            public void run() {

                Message status = sync(Fn.sendText(userId, Fn.plainText(Lang.get(userId).RESTARTING)));

                safeStop();

                destroyAndRestart(userId, status.id);

            }

        }.start();

    }


    private void update(final int userId) {

        new Thread() {

            @Override
            public void run() {

                Message status = syncI(Fn.sendText(userId, Fn.plainText(Lang.get(userId).UPDATING)));

                Process process = RuntimeUtil.exec("./tooko.sh update");

                try {

                    if (process.waitFor() == 1) {

                        I(Fn.editText(userId, status.id, Fn.plainText(Lang.get(userId).UPDATE_FAILED, RuntimeUtil.getResult(process))));

                    }

                    safeStop();

                    destroyAndRestart(userId, status.id);

                } catch (InterruptedException ignored) {
                }


            }

        }.start();

    }

    /**
     * Snapshot of /proc/meminfo.
     * <br>Member variable's unit: kB
     *
     * @author ZHJIE
     */

    public static class MemoryInfo {

        private final static String meminfoPath = "/proc/meminfo";

        public static long memTotal = -1;
        public static long memFree = -1;
        public static long buffers = -1;
        public static long cached = -1;

        public static void reload() {

            BufferedReader br = null;

            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(meminfoPath)));

                String line;

                int hitCount = 0;

                //noinspection ConstantConditions
                while ((line = br.readLine().trim()) != null) {

                    if (line.startsWith("MemTotal:")) {

                        hitCount++;

                        String[] infoArray = line.split("\\s+");

                        memTotal = Long.parseLong(infoArray[1]);

                    } else if (line.startsWith("MemFree:")) {

                        hitCount++;

                        String[] infoArray = line.split("\\s+");

                        memFree = Long.parseLong(infoArray[1]);

                    } else if (line.startsWith("Buffers:")) {

                        hitCount++;

                        String[] infoArray = line.split("\\s+");

                        buffers = Long.parseLong(infoArray[1]);

                    } else if (line.startsWith("Cached:")) {

                        hitCount++;

                        String[] infoArray = line.split("\\s+");

                        cached = Long.parseLong(infoArray[1]);

                    }

                    if (hitCount >= 4) break;

                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                if (br != null) {

                    try {

                        br.close();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }
            }
        }

    }

}
