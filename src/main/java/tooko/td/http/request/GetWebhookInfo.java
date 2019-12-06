package tooko.td.http.request;

import tooko.td.http.response.GetWebhookInfoResponse;

/**
 * Stas Parshin
 * 03 October 2016
 */
public class GetWebhookInfo extends BaseRequest<GetWebhookInfo, GetWebhookInfoResponse> {

    public GetWebhookInfo() {
        super(GetWebhookInfoResponse.class);
    }

}
