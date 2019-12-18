package tooko.twitter.spam;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.bson.codecs.pojo.annotations.BsonId;
import tooko.main.utils.nsfw.NSFW;
import tooko.main.utils.nsfw.NSRC;
import tooko.main.utils.nsfw.TCRC;
import tooko.main.utils.nsfw.TextCensor;
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

    public NSRC media;
    public TCRC text;

    public StatusR(long statusId, long user, NSRC media, TCRC text) {

        this.statusId = statusId;
        this.user = user;
        this.media = media;
        this.text = text;
    }

    public StatusR() {
    }

    public static StatusR predictStatus(Status status) throws IOException {

        if (DATA.containsId(status.getId())) return DATA.getById(status.getId());

        StatusR rc = new StatusR();

        rc.statusId = status.getId();
        rc.user = status.getUser().getId();

        LinkedList<String> linkArray = new LinkedList<>();

        for (MediaEntity media : status.getMediaEntities()) {

            if (media.getMediaURLHttps().contains("jpg") && ArrayUtil.isEmpty(media.getVideoVariants())) {

                linkArray.add(media.getMediaURLHttps());

            }

        }

        if (!linkArray.isEmpty()) {

            rc.media = NSFW.predict(linkArray.toArray(new String[0]));

        }

        String text = status.getText();

        for (MediaEntity entity : status.getMediaEntities()) {

            text = StrUtil.removeAll(text, entity.getURL());

        }

        for (URLEntity entity : status.getURLEntities()) {

            text = text.replace(entity.getURL(), entity.getExpandedURL());

        }

        if (StrUtil.isNotBlank(text)) {

            rc.text = TextCensor.getInstance().predictText(text);

        }

        DATA.setById(status.getId(), rc);

        if (rc.media == NSRC.PORN) {

            UserR.DATA.setInsert(status.getUser().getId(), "status", status.getId(), true);

        }

        if (status.isRetweet()) {

            Status origin = status.getRetweetedStatus();

            rc.statusId = origin.getId();
            rc.user = origin.getUser().getId();

            DATA.setById(origin.getId(), rc);

            if (rc.media == NSRC.PORN) {

                UserR.DATA.setInsert(origin.getUser().getId(), "status", status.getId(), true);

            }

        }

        return rc;

    }

}