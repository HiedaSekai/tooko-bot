package tooko.td.http.response;

import tooko.td.http.model.Poll;

/**
 * Stas Parshin
 * 17 April 2019
 */
public class PollResponse extends BaseResponse {

    private Poll result;

    public Poll poll() {
        return result;
    }

    @Override
    public String toString() {
        return "PollResponse{" +
                "result=" + result +
                '}';
    }
}
