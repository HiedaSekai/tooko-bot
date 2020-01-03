package tooko.td.http.request;

import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class DeleteStickerFromSet extends BaseRequest<DeleteStickerFromSet, BaseResponse> {

    public DeleteStickerFromSet(String sticker) {

        super(BaseResponse.class);
        add("sticker", sticker);
    }

}
