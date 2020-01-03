package tooko.twitter;

import org.bson.codecs.pojo.annotations.*;
import tooko.main.*;
import tooko.td.core.*;
import tooko.twitter.archives.*;
import twitter4j.*;

import java.util.*;

public class TwitterAccount {

    public static Table<Long, TwitterAccount> DATA = new Table<>("accounts", TwitterAccount.class);

    @BsonId
    public long accountId;

    public int owner;

    public String apiKey;
    public String apiSecretKey;

    public String accessToken;
    public String accessTokenSecret;

    public Boolean track;
    public Long track_delay;
    public Long track_last;

    public int last_friends;
    public int last_followers;

    public Boolean follow;

    public static TwitterAccount getByAccountId(long accountId) {

        return DATA.getById(accountId);

    }

    public static List<TwitterAccount> getByOwner(int owner) {

        return DATA.getAllByField("owner", owner);

    }

    public UserA archive() {

        return UserA.get(accountId);

    }

    public Twitter mkApi() {

        return Fn.mkApi(apiKey, apiSecretKey, accessToken, accessTokenSecret);

    }

}
