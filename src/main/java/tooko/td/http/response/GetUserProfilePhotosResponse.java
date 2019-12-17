package tooko.td.http.response;

import tooko.td.http.model.UserProfilePhotos;

/**
 * stas
 * 8/11/15.
 */
public class GetUserProfilePhotosResponse extends BaseResponse {

    private UserProfilePhotos result;

    GetUserProfilePhotosResponse() {

    }

    public UserProfilePhotos photos() {

        return result;
    }

    @Override
    public String toString() {

        return "GetUserProfilePhotosResponse{" + "result=" + result + '}';
    }

}
