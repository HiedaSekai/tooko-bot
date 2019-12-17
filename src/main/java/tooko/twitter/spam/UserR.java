package tooko.twitter.spam;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import tooko.main.utils.TextCensor;
import tooko.td.core.Table;
import tooko.twitter.archives.UserA;

public class UserR {

    public static Table<Long, UserR> DATA = new Table<>("user_spam", UserR.class);

    @BsonId
    public long accountId;

    public Long[] status;
    public Boolean pornStatus;

    public TextCensor.TCRC name;
    public TextCensor.TCRC bio;

    public int hash;

    public static UserR predictUser(UserA user) {

        UserR rc;

        if (DATA.containsId(user.accountId)) {

            rc = DATA.getById(user.accountId);

        } else {

            rc = new UserR();

            rc.accountId = user.accountId;
            rc.hash = -1;

            rc.status = new Long[0];

        }

        int hash = (user.name + user.description).hashCode();

        if (rc.hash != hash) {

            // rc.name = TextCensor.getInstance().predictText(user.name);

            TextCensor.TCRC newName = TextCensor.getInstance().predictText(user.name);

            if (rc.name != TextCensor.TCRC.PORN) {

                rc.name = newName;

            }


            if (StrUtil.isNotBlank(user.description)/* && rc.bio != TextCensor.TCRC.PORN*/) {

                rc.bio = TextCensor.getInstance().predictText(user.description);

            } else /*if (StrUtil.isBlank(user.description) && rc.bio != TextCensor.TCRC.PORN) */ {

                rc.bio = null;

            }

            rc.hash = hash;

            DATA.setById(user.accountId, rc);

        }

        return rc;

    }

    @BsonIgnore
    public boolean isSpam() {

        return pornStatus != null || name == TextCensor.TCRC.PORN || bio == TextCensor.TCRC.PORN;

    }

    public String parseReason() {

        if (pornStatus != null) {

            return "PORN STATUS : \n\nhttps://twitter.com/show/status/" + ArrayUtil.join(status, "\nhttps://twitter.com/show/status/");

        } else if (name == TextCensor.TCRC.PORN) {

            return "PORN NAME";

        } else if (bio != null && bio == TextCensor.TCRC.PORN) {

            return "PORN DESCRIPTION";

        }

        return "ERROR";

    }

}