package tooko.td.http.response;

import tooko.td.http.model.*;

import java.util.*;

/**
 * Stas Parshin
 * 23 November 2017
 */
public class MessagesResponse extends BaseResponse {

    private Message[] result;

    MessagesResponse() {

    }

    public Message[] messages() {

        return result;
    }

    @Override
    public String toString() {

        return "MessagesResponse{" + "result=" + Arrays.toString(result) + '}';
    }

}
