package tooko.td.http.response;

import tooko.td.http.model.Message;

import java.util.Arrays;

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
