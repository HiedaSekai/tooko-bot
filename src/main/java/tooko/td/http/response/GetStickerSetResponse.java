package tooko.td.http.response;

import tooko.td.http.model.StickerSet;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class GetStickerSetResponse extends BaseResponse {

    private StickerSet result;

    public StickerSet stickerSet() {
        return result;
    }

    @Override
    public String toString() {
        return "GetStickerSetResponse{" +
                "result=" + result +
                '}';
    }
}
