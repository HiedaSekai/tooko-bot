package tooko.td.client;

import tooko.td.*;

public interface TdCallback<T extends TdApi.Object> {

    void onCallback(boolean isOk, T result, TdApi.Error error);

}
