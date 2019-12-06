package tooko.td.http.request;

import tooko.td.http.response.GetMeResponse;

/**
 * stas
 * 5/1/16.
 */
public class GetMe extends BaseRequest<GetMe, GetMeResponse> {

    public GetMe() {
        super(GetMeResponse.class);
    }
}
