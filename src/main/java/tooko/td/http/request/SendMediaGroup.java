package tooko.td.http.request;

import tooko.td.http.model.request.InputMedia;
import tooko.td.http.response.MessagesResponse;

import java.util.Map;

/**
 * Stas Parshin
 * 23 November 2017
 */
public class SendMediaGroup extends BaseRequest<SendMediaGroup, MessagesResponse> {

    private boolean isMultipart = false;

    public SendMediaGroup(Object chatId, InputMedia... media) {

        super(MessagesResponse.class);
        add("chat_id", chatId).add("media", serialize(media));

        for (InputMedia m : media) {
            //noinspection unchecked
            Map<String, Object> attachments = m.getAttachments();
            if (attachments != null && attachments.size() > 0) {
                addAll(attachments);
                isMultipart = true;
            }
        }
    }

    public SendMediaGroup disableNotification(boolean disableNotification) {

        return add("disable_notification", disableNotification);
    }

    public SendMediaGroup replyToMessageId(int replyToMessageId) {

        return add("reply_to_message_id", replyToMessageId);
    }

    @Override
    public boolean isMultipart() {

        return isMultipart;
    }

}
