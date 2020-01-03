package tooko.td.http.response;

import tooko.td.http.model.*;

import java.util.*;

/**
 * Stas Parshin
 * 04 October 2016
 */
public class GetGameHighScoresResponse extends BaseResponse {

    private GameHighScore[] result;

    public GameHighScore[] result() {

        return result;
    }

    @Override
    public String toString() {

        return "GetGameHighScoresResponse{" + "result=" + Arrays.toString(result) + '}';
    }

}
