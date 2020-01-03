package tooko.td.http.request;

import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class SetStickerPositionInSet extends BaseRequest<SetStickerPositionInSet, BaseResponse> {

    public SetStickerPositionInSet(String sticker, int position) {

        super(BaseResponse.class);
        add("sticker", sticker).add("position", position);
    }

}
