package tooko.transfer;

import tooko.td.TdApi.*;
import tooko.td.client.TdClient;
import tooko.td.client.TdOptions;

public abstract class UserAgent extends TdClient {

    public String phone;

    public UserAgent(int accountId, String phone) {

        super(new TdOptions().databaseDirectory("data/agent/" + accountId));

        this.phone = phone;

    }

    @Override
    public void onAuthorizationState(AuthorizationState authorizationState) {

        super.onAuthorizationState(authorizationState);

        if (authorizationState instanceof AuthorizationStateWaitPhoneNumber) {

            send(new SetAuthenticationPhoneNumber(phone, new PhoneNumberAuthenticationSettings(false, false, false)));

        } else if (authorizationState instanceof AuthorizationStateWaitCode) {

            send(new SendPhoneNumberConfirmationCode());

            onInputCode();

        }

    }

    public abstract void onInputCode();

}
