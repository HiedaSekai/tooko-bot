package tooko.td.http.request;

import tooko.td.http.response.BaseResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class UnpinChatMessage extends BaseRequest<UnpinChatMessage, BaseResponse> {

    public UnpinChatMessage(Object chatId) {

        super(BaseResponse.class);
        add("chat_id", chatId);
    }

}
