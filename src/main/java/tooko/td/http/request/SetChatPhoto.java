package tooko.td.http.request;

import tooko.td.http.response.*;

import java.io.*;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class SetChatPhoto extends BaseRequest<SetChatPhoto, BaseResponse> {

    public SetChatPhoto(Object chatId, byte[] photo) {

        super(BaseResponse.class);
        add("chat_id", chatId).add("photo", photo);
    }

    public SetChatPhoto(Object chatId, File photo) {

        super(BaseResponse.class);
        add("chat_id", chatId).add("photo", photo);
    }

    @Override
    public boolean isMultipart() {

        return true;
    }

}
