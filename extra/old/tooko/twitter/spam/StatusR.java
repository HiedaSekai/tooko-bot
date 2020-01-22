package tooko.twitter.spam;

import cn.hutool.core.util.*;
import org.bson.codecs.pojo.annotations.*;
import tooko.main.utils.nsfw.*;
import tooko.td.core.*;
import twitter4j.*;

import java.io.*;
import java.util.*;

public class StatusR {

    public static Table<Long, StatusR> DATA = new CacheTable<>("spam", StatusR.class);

    @BsonId
    public long statusId;

    public Long user;
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

        String text = statusText(status);

        if (StrUtil.isNotBlank(text)) {

            rc.text = TextCensor.getInstance().predictText(text);

        }

        if (rc.media == null && rc.text == null) {

            rc.user = null;

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

    public static String statusText(Status status) {

        String text = status.isRetweet() ? status.getRetweetedStatus().getText() : status.getText();

        for (MediaEntity entity : status.getMediaEntities()) {

            text = StrUtil.removeAll(text, entity.getURL());

        }

        for (URLEntity entity : status.getURLEntities()) {

            text = text.replace(entity.getURL(), entity.getExpandedURL());

        }

        if (status.getQuotedStatusId() > 0) {

            if (text.matches(".*https://(mobile.)?twitter.com/([^/]*)/status/[0-9]*")) {

                text = StrUtil.subBefore(text, "https://", true);

            }

        }

        if (status.getInReplyToStatusId() > 0) {

            while (text.startsWith("@") && text.contains(" ")) text = StrUtil.subAfter(text, " ", false);

        }

        return text;

    }

}