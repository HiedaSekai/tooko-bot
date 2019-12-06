package tooko.main.manage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import tooko.Launcher;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.td.TdApi.*;
import tooko.td.client.TdException;
import tooko.td.client.TdFunction;

import java.io.File;
import java.util.HashMap;

public class SysDebug extends TdFunction {

    public int PERSIST_ID = Fn.PerststId._1;
    public HashMap<Integer, File> cache = new HashMap<>();

    @Override
    public void onLoad() {

        initFunction("sys_get_config", "sys_set_config", "sys_cat_file", "sys_get_file", "sys_upload_file", "sys_exec");

        initPersist(PERSIST_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (!Fn.fromPrivate(message)) {

            send(Fn.deleteMessage(message));

            return;

        }

        if (!Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).PERMISSION_DENIED)));

            return;

        }

        if ("sys_get_config".equals(function)) {

            send(Fn.sendText(chatId, Fn.parseHtml(Fn.code(FileUtil.readUtf8String(Env.getFile("config.json"))))));

        } else if ("sys_set_config".equals(function)) {

            if (StrUtil.isBlank(param)) {

                send(Fn.sendText(chatId, Fn.plainText("/sys_set_config <json> or /sys_set_config <key> <value>")));

                return;

            }

            if (param.trim().startsWith("{")) {

                try {

                    JSONObject json = new JSONObject(param);

                    Launcher.checkConfig(json);

                    FileUtil.writeUtf8String(param, Env.getFile("config.json"));

                    send(Fn.sendText(chatId, Fn.plainText("已写入")));

                } catch (JSONException ex) {

                    send(Fn.sendText(chatId, Fn.plainText("JSON语法错误 : {}", ex.getMessage())));

                    return;

                } catch (IllegalStateException ex) {

                    send(Fn.sendText(chatId, Fn.plainText(ex.getMessage())));

                    return;
                    
                }

            } else {

                JSONObject json = new JSONObject(FileUtil.readUtf8String(Env.getFile("config.json")));

                json.putByPath(params[0], Fn.shift(originParams));

                try {

                    Launcher.checkConfig(json);    

                } catch (IllegalStateException ex) {

                    send(Fn.sendText(chatId, Fn.plainText(ex.getMessage())));

                    return;

                }

                FileUtil.writeUtf8String(json.toStringPretty(), Env.getFile("config.json"));

                send(Fn.sendText(chatId, Fn.plainText("已写入")));


            }

        } else if ("sys_get_file".equals(function)) {

            if (StrUtil.isBlank(param)) {

                send(Fn.sendText(chatId, Fn.plainText("/sys_get_file <path>")));

                return;

            }

            File file = new File(params[0]);

            if (!file.isFile()) {

                send(Fn.sendText(chatId, Fn.plainText("不是文件")));

                return;

            }

            send(Fn.sendMessage(chatId, Fn.inputFile(file)));

        } else if ("sys_cat_file".equals(function)) {

            if (StrUtil.isBlank(param)) {

                send(Fn.sendText(chatId, Fn.plainText("/sys_cat_file <path>")));

                return;

            }

            File file = new File(params[0]);

            if (params.length == 1) {

                if (!file.isFile()) {

                    send(Fn.sendText(chatId, Fn.plainText("不是文件")));

                    return;

                }

                String content = FileUtil.readUtf8String(file);

                if (StrUtil.isBlank(content)) {

                    send(Fn.sendText(chatId, Fn.plainText("空文件")));

                    return;

                }

                send(Fn.sendText(chatId, Fn.plainText(content)));

            } else {

                if (!file.getParentFile().isDirectory()) file.getParentFile().mkdirs();

                String content = ArrayUtil.join(ArrayUtil.remove(originParams, 0), " ");

                FileUtil.writeUtf8String(content, file);

                send(Fn.sendText(chatId, Fn.plainText("已写入")));

            }

        } else if ("sys_upload_file".equals(function)) {

            if (StrUtil.isBlank(param)) {

                send(Fn.sendText(chatId, Fn.plainText("/sys_upload_file <save path>")));

                return;

            }

            File file = new File(params[0]);

            if (!file.isDirectory()) {

                if (!file.getParentFile().isDirectory()) file.getParentFile().mkdirs();

            }

            cache.put(user.id, file);

            writePersist(user, PERSIST_ID);

            send(Fn.sendText(chatId, Fn.plainText("发送文件 :")));

        } else if ("sys_exec".equals(function)) {

            if (StrUtil.isBlank(param)) {

                send(Fn.sendText(chatId, Fn.plainText("/sys_exec <shell>")));

                return;

            }

            try {

                String result = RuntimeUtil.execForStr(param);

                if (!StrUtil.isBlank(result)) {

                    send(Fn.sendText(chatId, Fn.plainText(result)));

                }

            } catch (Exception ex) {

                send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(ex))));

            }

        }

    }

    @Override
    public void onPersistFinished(int userId, int subId) {

        cache.remove(userId);

    }

    @Override
    public void onPersistMessage(User user, long chatId, Message message, int subId) {

        File target = cache.get(user.id);

        removePersist(user);

        if (!(message.content instanceof MessageDocument)) {

            send(Fn.sendText(chatId, Fn.plainText("上传已取消")));

            return;

        }

        Document documemt = ((MessageDocument) message.content).document;

        TdApi.File file;

        try {

            file = execute(new DownloadFile(documemt.document.id, 1, 0, 0, true));

        } catch (TdException ex) {

            if (ex.getCode() == -1) {

                send(Fn.serverClosed(message));

            } else {

                send(Fn.sendText(chatId, Fn.plainText("下载文件失败 : {}", ex)));

            }

            return;

        }

        if (target.isDirectory()) {

            target = new File(target, documemt.fileName);

        }

        FileUtil.move(new File(file.local.path), target, true);

        send(Fn.sendText(chatId, Fn.plainText("上传完成")));

    }


}
