package tooko.td.http.request;

import tooko.td.http.response.GetStickerSetResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class GetStickerSet extends BaseRequest<GetStickerSet, GetStickerSetResponse> {

    public GetStickerSet(String name) {
        super(GetStickerSetResponse.class);
        add("name", name);
    }
}
