package tooko.td.http.passport;

import tooko.td.http.request.*;
import tooko.td.http.response.*;

/**
 * Stas Parshin
 * 30 July 2018
 */
public class SetPassportDataErrors extends BaseRequest<SetPassportDataErrors, BaseResponse> {

    public SetPassportDataErrors(int userId, PassportElementError... errors) {

        super(BaseResponse.class);
        add("user_id", userId).add("errors", serialize(errors));
    }

}
