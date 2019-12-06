package tooko.pm.config;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.pm.PmBot;
import tooko.pm.PmData;
import tooko.td.TdApi.InputMessageContent;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WelcomeConfig extends TdHandler {

    public int PERSISTD_ID = Fn.PerststId._3;

    public PmBot bot;
    private HashMap<Integer, EditCache> cache = new HashMap<>();

    @Override
    public void onLoad() {

        bot = (PmBot) client;

        initFunction("msg");

        initPersist(PERSISTD_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        Lang L = Lang.get(user);

        if (params.length == 0) {

            stat(L, user, chatId, message);

            return;

        }

        if ("show".equals(params[0])) {

            show(L, user, chatId, message, Fn.shift(params));

        } else if ("notice".equals(params[0])) {

            notice(L, user, chatId, message, Fn.shift(params));

        } else if ("set".equals(params[0])) {

            set(L, user, chatId, message, Fn.shift(params));

        } else if ("del".equals(params[0])) {

            del(L, user, chatId, message, Fn.shift(params));

        }
    }

    void set(Lang L, User user, long chatId, Message message, String[] params) {

        EditCache edit = new EditCache();

        if (params.length > 0) {

            String payload = params[0];

            if (!payload.matches("[a-zA-Z0-9_-]*") || payload.length() == 0 || payload.length() > 64) {

                send(Fn.sendText(chatId, Fn.plainText(L.PM_PAYLOAD_INVALID)));

                return;

            }

            edit.payload = payload;

        }

        cache.put(user.id, edit);

        writePersist(user, PERSISTD_ID);

        send(Fn.sendText(chatId, Fn.plainText(L.PM_WELCOME_INPUT)));

    }

    @Override
    public boolean rejectPersistStore(int userId, int subId) {

        return true;

    }

    @Override
    public void onPersistMessage(User user, long chatId, Message message, int subId) {

        Lang L = Lang.get(user);

        InputMessageContent input = null;

        if (message.forwardInfo == null) {

            input = Fn.convertToInput(message.content);

            // make a copy

        }

        if (input == null) {

            send(Fn.sendText(chatId, Fn.plainText(L.PM_WELCOME_FD_WARN)));

            input = Fn.forward(message);

        }

        cache.get(user.id).messages.add(input);

        send(Fn.sendText(chatId, Fn.plainText(L.PM_WELCOME_ADDED)));

    }

    @Override
    public void onPersistFunction(User user, long chatId, Message message, int subId, String function, String param, String[] params, String[] originParams) {

        if ("submit".equals(function)) {

            removePersist(user);

            Lang L = Lang.get(user);

            EditCache edit = cache.remove(user.id);

            PmData.Payload payload;

            if (edit.payload == null) {

                payload = bot.data.welcome;

            } else {

                if (bot.data.payloads == null) bot.data.payloads = new HashMap<>();

                payload = bot.data.payloads.get(edit.payload);

                if (payload == null) {

                    payload = new PmData.Payload();

                    payload.notice = false;

                    bot.data.payloads.put(edit.payload, payload);

                }

            }

            payload.messages = edit.messages.toArray(new InputMessageContent[edit.messages.size()]);

            send(Fn.sendText(chatId, Fn.plainText(L.PM_WELCOME_FINISH)));

            return;

        }

        super.onPersistFunction(user, chatId, message, subId, function, param, params, originParams);


    }

    void del(Lang L, User user, long chatId, Message message, String[] params) {

        if (params.length == 0) {

            bot.data.welcome.messages = null;

            send(Fn.sendText(chatId, Fn.plainText(L.DELETED)));

            return;

        }

        if (bot.data.payloads == null || !bot.data.payloads.containsKey(params[0])) {

            send(Fn.sendText(chatId, Fn.plainText(L.UNDEFINED + L.PM_PAYLOAD + " : {}", params[0])));

            return;

        }

        bot.data.payloads.remove(params[0]);

        send(Fn.sendText(chatId, Fn.plainText(L.DELETED)));

        return;

    }

    void notice(Lang L, User user, long chatId, Message message, String[] params) {

        String usage = "/msg notice <enable/disable> [payload]";

        if (params.length == 0) {

            send(Fn.sendText(chatId, Fn.plainText(usage)));

            return;

        }

        boolean target;

        if ("enable".equals(params[0])) {

            target = true;

        } else if ("disable".equals(params[0])) {

            target = false;

        } else {

            send(Fn.sendText(chatId, Fn.plainText(usage)));

            return;

        }

        PmData.Payload payload;

        if (params.length != 1) {

            if (bot.data.payloads == null || !bot.data.payloads.containsKey(params[0])) {

                send(Fn.sendText(chatId, Fn.plainText(L.UNDEFINED + L.PM_PAYLOAD + " : {}", params[0])));

                return;

            }

            payload = bot.data.payloads.get(params[1]);

        } else {

            payload = bot.data.welcome;

        }

        send(Fn.sendText(chatId, Fn.plainText(target ? L.ENABLED : L.DISABLED)));

        payload.notice = target;

    }

    void show(Lang L, User user, long chatId, Message message, String[] params) {

        PmData.Payload payload;

        if (params.length != 0) {

            if (bot.data.payloads == null || !bot.data.payloads.containsKey(params[0])) {

                send(Fn.sendText(chatId, Fn.plainText(L.UNDEFINED + L.PM_PAYLOAD + " : {}", params[0])));

                return;

            }

            payload = bot.data.payloads.get(params[0]);

        } else {

            payload = bot.data.welcome;

        }

        if (payload.messages == null || payload.messages.length == 0) {

            send(Fn.sendText(chatId, Fn.parseHtml(L.PM_WELCOME)));

        } else {

            for (InputMessageContent input : payload.messages) {

                syncE(Fn.sendMessage(chatId, input));

            }

        }

    }

    void stat(Lang L, User user, long chatId, Message message) {

        String stat;

        int welcomeMessagesCount = bot.data.welcome.messages == null ? 0 : bot.data.welcome.messages.length;

        if (welcomeMessagesCount == 0) {

            stat = L.PM_NO_WELCOME_MESSAGE;

        } else {

            stat = StrUtil.format(L.PM_WELCOME_MESSAGE, welcomeMessagesCount);

        }

        stat += "\n" + L.PM_WELCOME_NOTICE + " : ";

        if (bot.data.welcome.notice) {

            stat += L.ENABLED;

        } else {

            stat += L.DISABLED;

        }

        stat += "\n\n";

        if (MapUtil.isEmpty(bot.data.payloads)) {

            stat += L.PM_PAYLOAD + " : " + L.UNDEFINE;

        } else {

            stat += L.PM_PAYLOADS + " : \n\n";

            for (Map.Entry<String, PmData.Payload> payload : bot.data.payloads.entrySet()) {

                stat += payload.getKey() + " :\n";

                int messagesCount = payload.getValue().messages.length;

                if (messagesCount == 0) {

                    stat += "  - " + L.PM_NO_WELCOME_MESSAGE;

                } else {

                    stat += "  - " + StrUtil.format(L.PM_WELCOME_MESSAGE, messagesCount);

                }

                stat += "\n  - " + L.PM_WELCOME_NOTICE + " : ";

                if (bot.data.welcome.notice) {

                    stat += L.ENABLED;

                } else {

                    stat += L.DISABLED;

                }

            }

        }

        send(Fn.sendText(chatId, Fn.plainText(stat)));

    }

    private static class EditCache {

        public String payload;

        public LinkedList<InputMessageContent> messages = new LinkedList<>();

    }


}
