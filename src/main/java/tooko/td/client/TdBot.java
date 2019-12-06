package tooko.td.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.td.TdApi.*;
import tooko.td.core.LongLongArrayMap;

import java.io.File;
import java.util.HashMap;
import java.util.*;

public class TdBot extends TdClient {

    public File dataDir;
    public LongLongArrayMap startMessages = new LongLongArrayMap();
    public HashMap<String, LongLongArrayMap> startPayloads = new HashMap<>();
    public HashMap<Integer, TdPersistent> persists = new HashMap<>();
    public HashMap<Integer, TdHandler> persistHandlers = new HashMap<>();
    public HashMap<String, TdHandler> payloads = new HashMap<>();
    public HashMap<String, TdHandler> functions = new HashMap<>();
    public HashMap<Integer, TdHandler> callbacks = new HashMap<>();
    public String botToken;

    public TdBot(String botToken) {

        this(botToken, new TdOptions().databaseDirectory(initDataDir(botToken)));

        this.dataDir = Env.getFile("data/" + StrUtil.subBefore(botToken, ":", false));

    }

    public TdBot(String botToken, TdOptions options) {

        super(options);

        this.botToken = botToken;

    }

    private static String initDataDir(String botToken) {

        File dataDir = Env.getFile("data/" + StrUtil.subBefore(botToken, ":", false));

        dataDir.mkdirs();

        mkLink(dataDir, "stickers");
        mkLink(dataDir, "profile_photos");
        mkLink(dataDir, "thumbnails");
        mkLink(dataDir, "wallpapers");

        return dataDir.getPath();

    }

    private static void mkLink(File dataDir, String target) {

        File sourceDir = new File(dataDir, target);

        File targetDir = Env.getFile("cache/files/" + target);

        if (!sourceDir.isDirectory()) {

            targetDir.mkdirs();

            RuntimeUtil.execForStr("ln -s " + targetDir.getPath() + " " + sourceDir.getPath());

        }

    }

    @Override
    public void onAuthorizationState(AuthorizationState authorizationState) {

        super.onAuthorizationState(authorizationState);

        if (authorizationState instanceof TdApi.AuthorizationStateWaitPhoneNumber) {

            try {

                execute(new TdApi.CheckAuthenticationBotToken(botToken));

            } catch (TdException ex) {

                onAuthorizationFailed(ex);

            }

        }
    }

    public void onAuthorizationFailed(TdException ex) {
    }

 

    @Override
    public void onLoad() {

        File file = new File(dataDir, "persist.csv");

        if (file.isFile()) {

            CsvData csv = CsvUtil.getReader().read(file);

            for (CsvRow row : csv.getRows()) {

                int userId = NumberUtil.parseInt(row.get(0));
                int dataId = NumberUtil.parseInt(row.get(1));
                int subId = NumberUtil.parseInt(row.get(2));
                long createAt = NumberUtil.parseLong(row.get(3));
                boolean acceptFunction = NumberUtil.parseInt(row.get(4)) == 1;

                String[] data = Fn.shift(row.getRawList(), 4).toArray(new String[row.getFieldCount() - 4]);

                if (persistHandlers.containsKey(dataId)) {

                    persists.put(userId, new TdPersistent(userId, dataId, subId, createAt, acceptFunction));

                    persistHandlers.get(dataId).onPersistRestore(userId, subId, data);

                }

            }

            FileUtil.del(file);

        }

    }

    @Override
    public void onDestroy() {

        if (!persists.isEmpty()) {

            File file = new File(dataDir, "persist.csv");

            CsvWriter csv = CsvUtil.getWriter(file, CharsetUtil.CHARSET_UTF_8, false);

            for (TdPersistent persist : persists.values()) {

                if ((System.currentTimeMillis() - persist.createAt) > 3 * 60 * 60 * 1000L || persistHandlers.get(persist.dataId).rejectPersistStore(persist.id, persist.subId)) {

                    persistHandlers.get(persist.dataId).onPersistTimeouted(persist.id, persist.subId);

                    continue;

                }

                LinkedList<String> data = new LinkedList<>();

                Collections.addAll(data,new String[]{persist.id + "", persist.dataId + "", persist.subId + "", persist.createAt + "", (persist.acceptFunction ? 1 : 0) + ""});
                
                persistHandlers.get(persist.dataId).onPersistStore(persist.id, persist.subId, data);
                
                csv.write(Fn.toArray(data,String.class));
                
            }

            csv.flush();

            csv.close();

        }

    }

    @Override
    public void onNewMessage(int userId, long chatId, TdApi.Message message) {

        while (!hasAuthed()) ThreadUtil.sleep(233);

        if (userId == me.id) return;

        User user = E(new GetUser(userId));

        if (Fn.fromPrivate(message)) {

            synchronized (persists) {

                if (persists.containsKey(userId)) {

                    TdPersistent persist = persists.get(userId);

                    persistHandlers.get(persist.dataId).handlePersistMessage(user, chatId, message, persist.subId, persist.acceptFunction);

                    Fn.finishEvent();

                }

            }

        }

        if (!(message.content instanceof MessageText)) return;

        FormattedText content = ((MessageText) message.content).text;

        if (content.entities.length == 0 || !(content.entities[0].type instanceof TextEntityTypeBotCommand) || content.entities[0].offset != 0) {

            return;

        }

        TextEntity command = content.entities[0];

        String function = content.text.substring(1, command.length);

        if (function.endsWith("@" + me.username)) {

            function = function.substring(0, function.length() - me.username.length() - 1);

        }

        // log.debug("{} : FN /{}",Fn.displayName(user),function);

        String param = content.text.length() > command.length ? content.text.substring(command.length + 1) : null;

        String[] params;
        String[] originParams;

        if (param == null) {

            param = "";
            params = originParams = new String[0];

        } else {

            originParams = param.split(" ");
            params = param.replace("  ", " ").split(" ");

        }

        try {

            if ("start".equals(function)) {

                if (StrUtil.isBlank(param)) {

                    onLaunch(user, message.chatId, message);

                } else {

                    String data = params[0];

                    String prefix = StrUtil.subBefore(data, "_", false);

                    params = StrUtil.subAfter(data, "_", false).split("_");

                    if (!payloads.containsKey(prefix)) {

                        onUnDefPayload(user, chatId, message, prefix, params);

                        Fn.finishEvent();

                    }

                    payloads.get(prefix).onPayload(user, chatId, message, prefix, params);


                }

                Fn.finishEvent();

            }

            if (!functions.containsKey(function)) {

                onUnDefFn(user, message.chatId, message, function, param, params, originParams);

            } else {

                functions.get(function).onFunction(user, message.chatId, message, function, param, params, originParams);

            }

        } catch (Reject reject) {

            return;

        } catch (TdException ex) {

            if (ex.getCode() == -1) {

                return;

            }

            throw ex;

        }

        Fn.finishEvent();

    }

    @Override
    public void handleNewCallbackQuery(long queryId, int senderUserId, long chatId, long messageId, long chatInstance, TdApi.CallbackQueryPayload payload) {

        if (payload instanceof CallbackQueryPayloadGame) return;

        byte[] data = ((CallbackQueryPayloadData) payload).data;

        if (data[0] == 120 && data[1] == -38) {

            data = ZipUtil.unZlib(data);

        }

        int id = data[0] + 129;

        int subId = data[1];

        if (!callbacks.containsKey(id)) {

            send(Fn.answerAlert(queryId, 114, "Invalid Data #{}", id));

            return;

        }

        try {

            execute(new GetMessage(chatId, messageId));

        } catch (TdException e) {

            if (e.getCode() == -1) {

                send(Fn.answerAlert(queryId, Lang.get(senderUserId).RESTARTING_WARN));

            } else {

                send(Fn.answerAlert(queryId, 114, "Invalid Query : {}", e));

            }

            return;

        }

        callbacks.get(id).onNewCallbackQuery(senderUserId, chatId, messageId, queryId, subId, Fn.readData(data));

        Fn.finishEvent();

    }


    public void onLaunch(User user, long chatId, Message message) {

        deleteStartMessages(chatId);

        send(Fn.sendSticker(chatId, "CAADAgADJAMAAsEYngsXfXVKAnZzKxYE"), asStartMessage(message));

    }

    public void onUnDefPayload(User user, long chatId, Message message, String payload, String[] params) {

        if (Fn.fromPrivate(message)) {

            send(Fn.deleteMessage(message));

        }

    }

    public void onUnDefFn(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (Fn.fromPrivate(message)) {

            Lang L = Lang.get(user);

            delay(Fn.sendText(chatId, Fn.plainText(L.CNF)));

            delay(message);

        }

    }

}
