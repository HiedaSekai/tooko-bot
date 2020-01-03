package tooko.td.http.request;

import tooko.td.http.model.request.*;
import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 17 April 2019
 */
public class StopPoll extends BaseRequest<StopPoll, PollResponse> {

    public StopPoll(Object chatId, int messageId) {

        super(PollResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }

    public StopPoll replyMarkup(Keyboard replyMarkup) {

        return add("reply_markup", replyMarkup);
    }

}
