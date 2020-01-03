package tooko.twitter.spam;

import cn.hutool.core.util.*;
import org.bson.codecs.pojo.annotations.*;
import tooko.main.utils.nsfw.*;
import tooko.td.core.*;
import tooko.twitter.archives.*;

import java.io.*;

import static com.mongodb.client.model.Filters.*;

public class UserR {

    public static Table<Long, UserR> DATA = new Table<>("user_spam", UserR.class);

    public static boolean isSpam(long accountId) {

        return DATA.collection.countDocuments(and(eq("_id", accountId), or(eq("photo", NSRC.PORN), eq("photo", NSRC.SEXY), eq("pornStatus", true)))) > 0;

    }

    @BsonId
    public long accountId;

    public Long[] status;

    public Boolean pornStatus;

    //  public TCRC name;
    //  public TCRC bio;
    public NSRC photo;

    public int infoHash;
    public Long predictAt;

    public static UserR predictUser(UserA user) {

        return predictUser(user, false);

    }

    public static UserR predictUser(UserA user, boolean noCache) {

        UserR rc;

        if (DATA.containsId(user.accountId)) {

            rc = DATA.getById(user.accountId);

        } else {

            rc = new UserR();

            rc.accountId = user.accountId;

        }

        int hash = (/*user.name + user.description + */user.profileImage).hashCode();

        if (noCache || (hash != rc.infoHash && (rc.photo == null || rc.photo == NSRC.DRAWINGS || rc.photo == NSRC.HENTAI))) {

            try {

                rc.photo = NSFW.predict(user.profileImage);

            } catch (IOException ignored) {
            }

        }

        return rc;

    }

    @BsonIgnore
    public boolean isSpam() {

        return photo == NSRC.PORN || photo == NSRC.SEXY || pornStatus != null;

    }

    public String parseReason() {

        if (pornStatus != null) {

            return "NSFW STATUS : \n\nhttps://twitter.com/show/status/" + ArrayUtil.join(status, "\nhttps://twitter.com/show/status/");

        } else if (photo == NSRC.PORN || photo == NSRC.SEXY) {

            return "NSFW PHOTO";

        }

        /*else if (name == TextCensor.TCRC.PORN) {

            return "PORN NAME";

        } else if (bio != null && bio == TextCensor.TCRC.PORN) {

            return "PORN DESCRIPTION";

        }*/

        return "ERROR";

    }

}