package tooko.td.http.request;

import tooko.td.http.response.GetChatResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChat extends BaseRequest<GetChat, GetChatResponse> {

    public GetChat(Object chatId) {
        super(GetChatResponse.class);
        add("chat_id", chatId);
    }
}
