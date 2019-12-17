package tooko.td.http.response;

import tooko.td.http.model.User;

/**
 * stas
 * 8/4/15.
 */
public class GetMeResponse extends BaseResponse {

    private User result;

    GetMeResponse() {

    }

    public User user() {

        return result;
    }

    @Override
    public String toString() {

        return "GetMeResponse{" + "result=" + result + '}';
    }

}
