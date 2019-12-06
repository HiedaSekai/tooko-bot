package tooko.twitter.actions;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Document;
import tooko.td.TdApi.InputMessageDocument;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdException;
import tooko.twitter.TwitterAccount;
import tooko.twitter.TwitterCallback;
import tooko.twitter.TwitterHandler;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MuteAndBlock extends TwitterHandler {

    public int PERSIST_ID = Fn.PerststId._6;

    @Override
    public void onLoad() {

        initFunction("mute_csv", "unmute_csv", "unmute_all", "block_csv", "unblock_csv", "unblock_all");

        initPersist(PERSIST_ID);

    }

    @Override
    public void onFunction(User user, final long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        final Lang L = Lang.get(user);

        final boolean undo = function.startsWith("un");

        final boolean mute = function.contains("mute");

        if (function.endsWith("_csv")) {


            if (!StrUtil.isBlank(param)) {

                if (!param.trim().matches("http(s)?://[^ ]+.csv(\\?.*)?")) {

                    send(Fn.sendText(chatId, Fn.parseHtml(L.TWI_INVALID_CSV_LINK)));

                    return;

                }

                final Message status = sync(Fn.sendText(chatId, Fn.plainText(undo ? L.TWI_REMOVING : L.TWI_ADDING + "...")));

                File cacheFile = Env.getFile("cache/csv_cache/" + UUID.fastUUID().toString(true) + ".csv");

                writePersist(user, PERSIST_ID, -1);

                try {

                    HttpUtil.downloadFile(param, cacheFile);

                } catch (Exception ex) {

                    removePersist(user);

                    send(Fn.editText(status, Fn.plainText(L.DOWNLOAD_FIALD, ex.getMessage())));

                    return;

                }

                try {

                    LinkedList<Long> targets = new LinkedList<>();

                    for (CsvRow row : CsvUtil.getReader().read(cacheFile)) {

                        targets.add(Long.parseLong(row.get(0)));

                    }

                    FileUtil.del(cacheFile);

                    requestTwitter(user, new Task(targets, status, undo, mute));

                } catch (Exception ex) {

                    removePersist(user);

                    send(Fn.editText(status, Fn.parseHtml(L.TWI_INVALID_CSV_FILE, HtmlUtil.escape(ex.getMessage()))));

                }

            } else {

                writePersist(user, PERSIST_ID, undo ? mute ? 0 : 1 : mute ? 2 : 3);

                send(Fn.sendText(chatId, Fn.plainText(L.TWI_INPUT_CSV, function)));

            }

        } else {

            requestTwitter(user, new TwitterCallback() {

                @Override
                public void onCallback(int userId, TwitterAccount account) {

                    writePersist(userId, PERSIST_ID, -1);

                    final Message status = sync(Fn.sendText(chatId, Fn.plainText(L.TWI_REMOVING + "...")));

                    final Twitter api = account.mkApi();

                    final LinkedList<Long> blocks;

                    try {

                        if (mute) {

                            blocks = Fn.fetchMuteIDs(api);

                        } else {

                            blocks = Fn.fetchBlockIDs(api);

                        }

                    } catch (TwitterException e) {

                        send(Fn.editText(status, Fn.plainText(Fn.parseTwitterException(L, e))));

                        return;

                    }

                    new Task(blocks, status, undo, mute).onCallback(userId, account);

                }

            });


        }

    }

    @Override
    public void onPersistMessage(User user, long chatId, Message message, int subId) {

        removePersist(user);

        boolean undo = subId < 2;

        boolean mute = subId % 2 == 0;

        Lang L = Lang.get(user);

        Document file = Fn.getFile(message);

        final Message status = sync(Fn.sendText(chatId, Fn.plainText(L.TWI_REMOVING + "...")));

        if (file == null || !file.fileName.endsWith(".csv")) {

            send(Fn.editText(status, Fn.plainText(L.TWI_NOT_CSV_FILE)));

            return;

        }

        writePersist(user, PERSIST_ID, -1);

        File csv;

        try {

            csv = getFile(file.document);

        } catch (TdException ex) {

            removePersist(user);

            send(Fn.editText(status, Fn.plainText(L.DOWNLOAD_FIALD, ex)));

            return;

        }

        try {

            CsvData content = CsvUtil.getReader().read(csv);

            LinkedList<Long> targets = new LinkedList<>();

            for (CsvRow row : content.getRows()) {

                targets.add(Long.parseLong(row.get(0)));

            }

            requestTwitter(user, new Task(targets, status, undo, mute));

        } catch (Exception ex) {

            removePersist(user);

            send(Fn.editText(status, Fn.parseHtml(L.TWI_INVALID_CSV_FILE, HtmlUtil.escape(ex.getMessage()))));

        }

    }

    public HashMap<Integer, Task> threads = new HashMap<>();

    @Override
    public void onPersistCancel(User user, long chatId, Message message, int subId) {

        super.onPersistCancel(user, chatId, message, subId);

        if (subId == -1 && threads.containsKey(user.id)) {

            threads.remove(user.id).cancel.set(true);

        }

    }

    public class Task extends Thread implements TwitterCallback {

        public AtomicBoolean cancel = new AtomicBoolean(false);

        public LinkedList<Long> targets;
        public Message status;

        public boolean undo = true;
        public boolean mute = false;

        public int userId;
        public TwitterAccount account;

        public Task(LinkedList<Long> targets, Message status, boolean undo, boolean mute) {

            this.targets = targets;
            this.status = status;
            this.undo = undo;

        }

        @Override
        public void onCallback(int userId, TwitterAccount account) {

            this.userId = userId;

            this.account = account;

            start();

        }

        @Override
        public void run() {

            Lang L = Lang.get(userId);

            if (targets.isEmpty()) {

                send(Fn.editText(status, Fn.plainText(L.TWI_LIST_EMPTY)));

                return;

            }

            threads.put(userId, this);

            Twitter api = account.mkApi();

            int current = -1;

            for (int index = 0; index < targets.size(); index++) {

                if (Env.STOP.get() || cancel.get()) {

                    send(Fn.deleteMessage(status));

                    return;

                }

                try {

                    if (mute) {

                        if (undo) {

                            api.destroyMute(targets.get(index));

                        } else {

                            api.createMute(targets.get(index));

                        }

                    } else {

                        if (undo) {

                            api.destroyBlock(targets.get(index));

                        } else {

                            api.createBlock(targets.get(index));

                        }

                    }

                } catch (TwitterException e) {
                }

                int last = current;

                if (index != 0 && (current = index * 100 / targets.size()) != last) {

                    send(Fn.editText(status, Fn.plainText(undo ? L.TWI_REMOVING : L.TWI_ADDING + " {}% ...", current)));

                }

            }

            send(Fn.editText(status, Fn.plainText(mute ? L.TWI_UM_FINISH : L.TWI_UB_FINISH, targets.size())));

            InputMessageDocument buckupFile = Fn.mkCacheFile("buckup.csv", CollectionUtil.join(targets, "\n"), Fn.plainText(L.TWI_LIST_BUCKUP));

            sync(Fn.sendMessage(userId, buckupFile));

            Fn.deleteCacheFile(buckupFile);

            removePersist(userId);

        }

    }

}
