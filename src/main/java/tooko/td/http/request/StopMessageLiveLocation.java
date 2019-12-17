package tooko.td.http.request;

import tooko.td.http.model.request.InlineKeyboardMarkup;
import tooko.td.http.response.BaseResponse;
import tooko.td.http.response.SendResponse;

/**
 * Stas Parshin
 * 12 October 2017
 */
public class StopMessageLiveLocation extends BaseRequest<StopMessageLiveLocation, BaseResponse> {

    public StopMessageLiveLocation(Object chatId, int messageId) {

        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }

    public StopMessageLiveLocation(String inlineMessageId) {

        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId);
    }

    public StopMessageLiveLocation replyMarkup(InlineKeyboardMarkup replyMarkup) {

        return add("reply_markup", replyMarkup);
    }

}
