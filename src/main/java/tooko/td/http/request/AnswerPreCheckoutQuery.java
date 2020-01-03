package tooko.td.http.request;

import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 26 May 2017
 */
public class AnswerPreCheckoutQuery extends BaseRequest<AnswerPreCheckoutQuery, BaseResponse> {

    public AnswerPreCheckoutQuery(String preCheckoutQueryId) {

        super(BaseResponse.class);
        add("pre_checkout_query_id", preCheckoutQueryId).add("ok", true);
    }

    public AnswerPreCheckoutQuery(String preCheckoutQueryId, String errorMessage) {

        super(BaseResponse.class);
        add("pre_checkout_query_id", preCheckoutQueryId).add("ok", false).add("error_message", errorMessage);
    }

}
