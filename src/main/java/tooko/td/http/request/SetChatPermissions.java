package tooko.td.http.request;

import tooko.td.http.model.ChatPermissions;
import tooko.td.http.response.BaseResponse;

/**
 * Stas Parshin
 * 30 July 2019
 */
public class SetChatPermissions extends BaseRequest<SetChatPermissions, BaseResponse> {

    public SetChatPermissions(Object chatId, ChatPermissions permissions) {
        super(BaseResponse.class);
        add("chat_id", chatId).add("permissions", serialize(permissions));
    }
}
