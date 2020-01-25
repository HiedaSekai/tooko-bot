package tooko.twitter;

import tooko.core.env.*;
import twitter4j.*;

public class ApiToken {

    public String name;

    public String apiKey;

    public String apiSecretKey;

    public ApiToken(String name, String apiKey, String apiSecretKey) {

        this.name = name;
        this.apiKey = apiKey;
        this.apiSecretKey = apiSecretKey;

    }

    public Twitter mkApi() {

        return Fn.mkApi(apiKey, apiSecretKey);

    }

}

