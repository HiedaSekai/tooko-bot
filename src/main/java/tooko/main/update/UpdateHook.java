package tooko.main.update;

import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.manage.SysConfig;
import tooko.td.TdApi.Message;
import tooko.td.client.TdHandler;

public class UpdateHook extends TdHandler {

    @Override
    public boolean containsThis() {

        return true;

    }

    @Override
    public void onNewMessage(int userId, long chatId, Message message) {

        if (Env.LOG_CHANNEL.equals(chatId) && "!UPDATE".equals(Fn.getText(message))) {

            send(Fn.deleteMessage(message));

            UpdateTask.fetchHead();

            if (SysConfig.isHookUpdateEnable()) {

                UpdateTask.slientUpdate();

            }

        }

    }


}
