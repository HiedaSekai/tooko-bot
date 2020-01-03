package tooko.twitter.follow;

import org.bson.codecs.pojo.annotations.*;
import org.jetbrains.annotations.*;
import tooko.td.core.*;

import java.util.*;

public class AutoData {

    public static Table<Long, AutoData> DATA = new Table<>("auto", AutoData.class);

    @BsonId
    public long accountId;

    public ArrayList<AutoFollowed> autoFollowed;
    public ArrayList<Long> autoFollowedIDs;

    public static class AutoFollowed {

        public long accountId;
        public long followedAt;

        public AutoFollowed(long accountId, long followedAt) {
            this.accountId = accountId;
            this.followedAt = followedAt;
        }

        public AutoFollowed() {
        }

        @Nullable
        public Long unFollowedAt;


    }

}
