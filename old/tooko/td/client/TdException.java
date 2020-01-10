package tooko.td.client;

import org.jetbrains.annotations.*;
import tooko.td.*;

public class TdException extends RuntimeException {

    private TdApi.Error error;

    public TdException(TdApi.Error error) {

        this.error = error;

    }

    public TdException(int code, String message) {

        this(new TdApi.Error(code, message));

    }


    public TdException(String message) {

        this(-1, message);

    }

    public TdApi.Error getError() {

        return error;

    }

    public int getCode() {

        return error.code;

    }

    @Override
    @NotNull
    public String getMessage() {

        return error.message;

    }

    @Override
    public String toString() {

        return getCode() + " : " + getMessage();

    }


}
