package tooko.td.http.request;

import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class DeleteChatPhoto extends BaseRequest<DeleteChatPhoto, BaseResponse> {

    public DeleteChatPhoto(Object chatId) {

        super(BaseResponse.class);
        add("chat_id", chatId);
    }

}
