package tooko.td.http.request;

import tooko.td.http.model.request.InlineKeyboardMarkup;
import tooko.td.http.response.BaseResponse;
import tooko.td.http.response.SendResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageReplyMarkup extends BaseRequest<EditMessageReplyMarkup, BaseResponse> {

    public EditMessageReplyMarkup(Object chatId, int messageId) {
        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }

    public EditMessageReplyMarkup(String inlineMessageId) {
        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId);
    }

    public EditMessageReplyMarkup replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
