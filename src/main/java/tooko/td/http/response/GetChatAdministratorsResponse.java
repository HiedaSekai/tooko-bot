package tooko.td.http.response;

import tooko.td.http.model.*;

import java.util.*;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatAdministratorsResponse extends BaseResponse {

    private List<ChatMember> result;

    public List<ChatMember> administrators() {

        return result;
    }

    @Override
    public String toString() {

        return "GetChatAdministratorsResponse{" + "result=" + result + '}';
    }

}
