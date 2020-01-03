package tooko.td.http.request;

import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatMember extends BaseRequest<GetChatMember, GetChatMemberResponse> {

    public GetChatMember(Object chatId, int userId) {

        super(GetChatMemberResponse.class);
        add("chat_id", chatId).add("user_id", userId);
    }

}
