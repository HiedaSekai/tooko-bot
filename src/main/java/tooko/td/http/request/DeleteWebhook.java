package tooko.td.http.request;

import tooko.td.http.response.BaseResponse;

/**
 * Stas Parshin
 * 07 December 2016
 */
public class DeleteWebhook extends BaseRequest<DeleteWebhook, BaseResponse> {

    public DeleteWebhook() {
        super(BaseResponse.class);
    }
}
