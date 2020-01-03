package tooko.td.http.response;

import tooko.td.http.model.*;

import java.util.*;

/**
 * stas
 * 8/11/15.
 */
public class GetUpdatesResponse extends BaseResponse {

    private List<Update> result;

    GetUpdatesResponse() {

    }

    public List<Update> updates() {

        return result;
    }

    @Override
    public String toString() {

        return "GetUpdatesResponse{" + "result=" + result + '}';
    }

}