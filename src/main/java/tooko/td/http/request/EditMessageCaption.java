package tooko.td.http.request;

import tooko.td.http.model.request.*;
import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageCaption extends BaseRequest<EditMessageCaption, BaseResponse> {

    public EditMessageCaption(Object chatId, int messageId) {

        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }

    public EditMessageCaption(String inlineMessageId) {

        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId);
    }

    public EditMessageCaption caption(String caption) {

        return add("caption", caption);
    }

    public EditMessageCaption parseMode(ParseMode parseMode) {

        return add("parse_mode", parseMode.name());
    }

    public EditMessageCaption replyMarkup(InlineKeyboardMarkup replyMarkup) {

        return add("reply_markup", replyMarkup);
    }

}
