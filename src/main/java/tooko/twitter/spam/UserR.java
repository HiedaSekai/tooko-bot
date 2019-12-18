package tooko.twitter.spam;

import cn.hutool.core.util.ArrayUtil;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import tooko.main.utils.nsfw.NSFW;
import tooko.main.utils.nsfw.NSRC;
import tooko.td.core.Table;
import tooko.twitter.archives.UserA;

import java.io.IOException;

public class UserR {

    public static Table<Long, UserR> DATA = new Table<>("user_spam", UserR.class);

    @BsonId
    public long accountId;

    public Long[] status;
    public Boolean pornStatus;

    //    public TCRC name;
    //  public TCRC bio;
    public NSRC photo;

    public int infoHash;
    public Long predictAt;

    public static UserR predictUser(UserA user) {

        UserR rc;

        if (DATA.containsId(user.accountId)) {

            rc = DATA.getById(user.accountId);

        } else {

            rc = new UserR();

            rc.accountId = user.accountId;

        }

        int hash = (/*user.name + user.description + */user.profileImage).hashCode();

        if (hash != rc.infoHash && (rc.photo == null || rc.photo == NSRC.DRAWINGS || rc.photo == NSRC.HENTAI)) {

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