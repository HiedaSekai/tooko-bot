package tooko.twitter.spam;

import tooko.td.*;
import tooko.td.client.*;
import tooko.twitter.*;
import tooko.main.utils.*;
import tooko.main.utils.TextCensor.*;
import tooko.main.*;

public class TextDetector extends TdHandler {

    @Override
    public void onLoad() {

        initFunction("text_predict");

    }


    @Override
    public void onFunction(TdApi.User user, final long chatId, TdApi.Message message, String function, final String param, String[] params, String[] originParams) {

        TextCensor.TCRC rc = TextCensor.getInstance().predictText(param);

       send(Fn.sendText(chatId,Fn.plainText("POLITICS: {}\nPORN: {}\nSPAM: {}",rc.isPolitics(),rc.isPorn(),rc.isSpam())));
        
    }



}
