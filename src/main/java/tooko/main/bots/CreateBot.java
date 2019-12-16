package tooko.main.bots;

import cn.hutool.core.util.NumberUtil;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.ReplyMarkupRemoveKeyboard;
import tooko.td.TdApi.User;
import tooko.td.client.TdException;
import tooko.td.client.TdFunction;
import tooko.td.core.KeyboardArray;
import tooko.td.http.request.GetMe;

import java.util.HashMap;
import java.util.LinkedList;

public class CreateBot extends TdFunction {

    public int PERSIST_ID = Fn.PerststId._2;
    private HashMap<Integer, BotData> createCache = new HashMap<>();

    @Override
    public void onLoad() {

        initFunction("new_bot");

        initPersist(PERSIST_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (!Fn.fromPrivate(message)) {

            send(Fn.deleteMessage(message));

            return;

        }

        Lang L = Lang.get(user.id);

        if (!Env.isAdmin(user.id)) {

            if (!Env.PUBLIC_BOT_CREATE) {

                send(Fn.sendText(chatId, Fn.plainText(L.ERR_PERSIONAL)));

                return;

            }

            long count = BotData.DATA.countByField("owner", user.id);

            if (Env.BOT_CREATE_MAX >= 0 && count >= Env.BOT_CREATE_MAX) {

                send(Fn.sendText(chatId, Fn.plainText(L.ERR_LIMIT)));

                return;

            }

        }

        writePersist(user, PERSIST_ID);

        send(Fn.sendText(chatId, Fn.parseHtml(L.BOT_INPUT_TOKEN)));

    }

    @Override
    public boolean rejectPersistStore(int userId, int subId) {

        return subId > 0;

    }

    @Override
    public void onPersistStore(int userId, int subId, LinkedList<String> data) {
        
        if (subId == 1) {

            BotData cache = createCache.remove(userId);

            data.add(cache.botId + "");
            
            data.add(cache.userName);
            
            data.add(cache.botToken);
            
        }
        
    }
    
    @Override
    public void onPersistRestore(int userId, int subId, String[] data) {
        
        if (subId == 2) {
            
            BotData cache = new BotData();
            
            cache.botId = NumberUtil.parseInt(data[0]);
            
            cache.owner = userId;
            
            cache.userName = data[1];
            
            cache.botToken = data[2];
            
        }
        
    }

    @Override
    public void onPersistRemoved(int userId, int subId, boolean timeout) {
        
        super.onPersistRemoved(userId, subId, timeout);
        
        createCache.remove(userId);

    }

    @Override
    public void onPersistMessage(User user, long chatId, Message message, int subId) {

        Lang L = Lang.get(user);

        removePersist(user);

        if (subId == 0) {

            String botToken = Fn.getText(message);

            tooko.td.http.model.User botMe;

            try {

                botMe = execute(botToken, new GetMe()).user();

            } catch (TdException ex) {

                send(Fn.sendText(chatId, Fn.plainText(L.BOT_TOKEN_INVALID,ex)));

                return;

            }

            BotData bot = new BotData();

            bot.botId = botMe.id();

            if (BotImage.images.containsKey(bot.botId)) {

                send(Fn.sendText(chatId, Fn.plainText(L.BOT_EXISTS)));

                return;

            }

            bot.userName = botMe.username();

            bot.botToken = botToken;

            bot.owner = user.id;

            createCache.put(user.id, bot);

            writePersist(user, PERSIST_ID, 1);

            KeyboardArray types = new KeyboardArray();

            types.textLine(L.BOT_TYPE_PM);

            send(Fn.sendText(chatId, types, Fn.plainText(L.BOT_N_TYPE)));

        } else if (subId == 1) {

            String type = Fn.getText(message);

            BotData data = createCache.remove(user.id);

            if (L.BOT_TYPE_PM.equals(type)) {

                data.type = 1;

            } else {

                onPersistRemoved(user.id, subId, false);

                return;

            }

            BotData.DATA.setById(data.botId, data);

            BotImage.start(data);

            send(Fn.sendText(chatId, new ReplyMarkupRemoveKeyboard(), Fn.parseHtml(L.BOT_CREATED, data.userName)));

        }

    }

}
