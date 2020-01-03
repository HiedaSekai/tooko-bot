package tooko.td.http.request;

import tooko.td.http.model.request.*;
import tooko.td.http.response.*;

import java.util.*;

/**
 * Stas Parshin
 * 28 July 2018
 */
public class EditMessageMedia extends BaseRequest<EditMessageMedia, BaseResponse> {

    private boolean isMultipart;
    private InputMedia media;

    public EditMessageMedia(Object chatId, int messageId, InputMedia media) {

        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
        addMedia(media);
    }

    public EditMessageMedia(String inlineMessageId, InputMedia media) {

        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId);
        addMedia(media);
    }

    private void addMedia(InputMedia media) {

        this.media = media;
        add("media", serialize(media));
        //noinspection unchecked
        Map<String, Object> attachments = media.getAttachments();
        if (attachments != null && attachments.size() > 0) {
            addAll(attachments);
            isMultipart = true;
        }
    }

    public EditMessageMedia replyMarkup(InlineKeyboardMarkup replyMarkup) {

        return add("reply_markup", replyMarkup);
    }

    @Override
    public boolean isMultipart() {

        return isMultipart;
    }

    @Override
    public String getFileName() {

        return media.getFileName();
    }

    @Override
    public String getContentType() {

        return media.getContentType();
    }

}
