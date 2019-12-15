package tooko.twitter.spam;

import cn.hutool.core.util.StrUtil;
import jdk.internal.org.jline.utils.Log;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import tooko.main.Fn;
import tooko.main.utils.NSFWClient;
import tooko.main.utils.TextCensor;
import tooko.td.core.CacheTable;
import tooko.td.core.Table;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

import java.io.IOException;
import java.util.LinkedList;

public class StatusR {

    public static Table<Long, StatusR> DATA = new CacheTable<>("spam", StatusR.class);

    @BsonId
    public long statusId;

    public long user;

    public static StatusR predetectStatus(Status status) {

        LinkedList<String> linkArray = new LinkedList<>();

        if (DATA.containsId(status.getId())) return DATA.getById(status.getId());

        StatusR r = null;

        NSRC rc = null;

        predictImage:
        if (status.getMediaEntities().length != 0) {

            for (MediaEntity media : status.getMediaEntities()) {

                linkArray.add(media.getMediaURLHttps());

            }

            float[][] results;

            try {

                results = NSFWClient.predict(Fn.toArray(linkArray, String.class));

            } catch (IOException e) {

                Log.warn(e);

                rc = NSRC.NEUTRAL;

                break predictImage;

            }

            parseResult:
            {

                for (float[] result : results) {

                    if (result[3] > 0.8f) {

                        rc = NSRC.PORN;

                        break parseResult;

                    }

                }

                for (float[] result : results) {

                    if (result[4] > 0.8f) {

                        rc = NSRC.SEXY;

                        break parseResult;

                    }

                }

                for (float[] result : results) {

                    if (result[1] > 0.8f) {

                        rc = NSRC.HENTAI;

                        break parseResult;

                    }

                }

                float value = -1;

                for (float[] result : results) {

                    NSRC likely = null;

                    if (result[0] > value) {

                        value = result[0];

                        likely = NSRC.DRAWINGS;

                    }

                    if (result[1] > value) {

                        value = result[1];

                        likely = NSRC.HENTAI;

                    }

                    if (result[3] > value) {

                        value = result[3];

                        likely = NSRC.PORN;

                    }

                    if (result[4] > value) {

                        value = result[1];

                        likely = NSRC.SEXY;

                    }

                    if (result[2] > value) {

                        value = -1;

                        if (rc == null) rc = NSRC.NEUTRAL;

                    }

                    if (likely != null) rc = likely;

                }

            }

        }

        TextCensor.TCRC tcrc = null;

        String text = status.getText();

        for (MediaEntity entity : status.getMediaEntities()) {

            text = StrUtil.removeAll(text, entity.getURL());

        }

        for (URLEntity entity : status.getURLEntities()) {

            text = text.replace(entity.getURL(), entity.getExpandedURL());

        }

        if (StrUtil.isNotBlank(text)) {

            tcrc = TextCensor.getInstance().predictText(text);

        }

        DATA.setById(status.getId(), new StatusR(status.getId(), status.getUser().getId(), rc != null ? rc.type : -1, tcrc));

        if (rc == NSRC.PORN || rc == NSRC.SEXY || (tcrc != null && tcrc.isPorn())) {

            UserR.DATA.setInsert(status.getUser().getId(), "status", status.getId());

        }

        if (status.isRetweet()) {

            Status origin = status.getRetweetedStatus();

            DATA.setById(origin.getId(), new StatusR(origin.getId(), origin.getUser().getId(), rc != null ? rc.type : -1, tcrc));

            if (rc == NSRC.PORN || rc == NSRC.SEXY || (tcrc != null && tcrc.isPorn())) {

                UserR.DATA.setInsert(origin.getUser().getId(), "status", status.getId());

            }

        }

        return r;

    }

    public int type;

    public TextCensor.TCRC text;

    @BsonIgnore
    public NSRC getType() {

        return NSRC.valueOf(type);

    }

    public StatusR() {
    }

    public StatusR(long statusId, long user, int type, TextCensor.TCRC text) {
        this.statusId = statusId;
        this.user = user;
        this.type = type;
        this.text = text;
    }

    public static enum NSRC {

        DRAWINGS(0),
        HENTAI(1),
        NEUTRAL(2),
        PORN(3),
        SEXY(4);

        NSRC(int type) {
            this.type = type;
        }

        public final int type;

        public static NSRC valueOf(int type) {

            switch (type) {

                case 0:
                    return DRAWINGS;
                case 1:
                    return HENTAI;
                case 2:
                    return NEUTRAL;
                case 3:
                    return PORN;
                case 4:
                    return SEXY;

                default:
                    throw new IllegalArgumentException();

            }

        }

    }

}