package tooko.td.http.response;

import tooko.td.http.model.WebhookInfo;

/**
 * Stas Parshin
 * 03 October 2016
 */
public class GetWebhookInfoResponse extends BaseResponse {

    private WebhookInfo result;

    public WebhookInfo webhookInfo() {
        return result;
    }

    @Override
    public String toString() {
        return "GetWebhookInfoResponse{" +
                "result=" + result +
                '}';
    }
}
