package tooko.td.client;

import tooko.td.TdApi.*;

public abstract class TdFunction extends TdHandler {

    @Override
    public abstract void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams);


}
