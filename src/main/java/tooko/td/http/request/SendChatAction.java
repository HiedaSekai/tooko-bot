package tooko.td.http.request;

import tooko.td.http.model.request.*;
import tooko.td.http.response.*;

/**
 * stas
 * 5/2/16.
 */
public class SendChatAction extends BaseRequest<SendChatAction, BaseResponse> {

    public SendChatAction(Object chatId, String action) {

        super(BaseResponse.class);
        add("chat_id", chatId).add("action", action);
    }

    public SendChatAction(Object chatId, ChatAction action) {

        super(BaseResponse.class);
        add("chat_id", chatId).add("action", action.name());
    }

}
