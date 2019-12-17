package tooko.twitter.archives;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.mongodb.client.model.UpdateOptions;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.core.Table;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;

public class UserA {

    public static Table<Long, UserA> DATA = new Table<>("users", UserA.class);
    @BsonId
    public long accountId;
    public Long createAt;
    public String lang;
    public String name;
    public String screenName;
    public String url;
    public String location;
    public String description;
    public String profileImage;
    public String bannerImage;
    public boolean isProtected;
    public Integer followers;
    public Integer friends;
    public Integer status;
    public LinkedHashSet<String> nameHistory;
    public LinkedHashSet<String> snHistory;

    public static UserA show(Twitter api, long accountId) {

        try {

            return save(api.showUser(accountId));

        } catch (TwitterException e) {

            UserA archive = get(accountId);

            if (archive != null) {

                if (e.getErrorCode() == 50) {

                    archive.status = 1;

                    DATA.updateField(archive.accountId, "status", 1);

                } else if (e.getErrorCode() == 63) {

                    archive.status = 2;

                    DATA.updateField(archive.accountId, "status", 2);

                }

            }

            return archive;

        }

    }

    public static UserA show(Twitter api, String screenName) {

        try {

            return save(api.showUser(screenName));

        } catch (TwitterException e) {

            UserA archive = get(screenName);

            if (archive != null) {

                if (e.getErrorCode() == 50) {

                    archive.status = 1;

                    DATA.updateField(archive.accountId, "status", 1);

                } else if (e.getErrorCode() == 63) {

                    archive.status = 2;

                    DATA.updateField(archive.accountId, "status", 2);

                }

            }

            return archive;

        }

    }

    public static UserA save(User user) {

        UserA archive = get(user.getId());

        if (archive == null) {

            archive = new UserA();

            archive.accountId = user.getId();

        }

        Bson updates;

        if ((updates = archive.readUpdate(user)) != null) {

            DATA.collection.updateOne(eq("_id", archive.accountId), updates, new UpdateOptions().upsert(true));

        }

        return archive;

    }

    public static UserA get(long accountId) {

        return DATA.getById(accountId);

    }

    public static UserA get(String screenName) {

        return DATA.getByField("screenName", screenName);

    }

    public Bson readUpdate(User user) {

        LinkedList<Bson> updates = new LinkedList<>();

        if (createAt == null) {

            updates.add(set("_id", accountId));

            createAt = user.getCreatedAt().getTime();

            updates.add(set("createAt", createAt));

        }

        if (!ObjectUtil.equal(lang, user.getLang())) {

            lang = user.getLang();

            updates.add(set("lang", lang));

        }

        if (!ObjectUtil.equal(name, user.getName())) {

            if (name != null) {

                if (nameHistory == null) nameHistory = new LinkedHashSet<>();

                nameHistory.add(name);

                updates.add(addToSet("nameHistory", name));

            }

            name = user.getName();

            updates.add(set("name", name));

        }

        if (!ObjectUtil.equal(screenName, user.getScreenName())) {

            if (screenName != null) {

                if (snHistory == null) snHistory = new LinkedHashSet<>();

                snHistory.add(screenName);

                updates.add(addToSet("snHistory", screenName));

            }

            screenName = user.getScreenName();

            updates.add(set("screenName", screenName));

        }

        String originURL = user.getURL();

        if (StrUtil.isNotBlank(originURL)) {

            URLEntity entity = user.getURLEntity();

            if (entity != null) {

                originURL = entity.getExpandedURL();

            }

        }

        if (!ObjectUtil.equal(url, originURL)) {

            url = originURL;

            updates.add(set("url", url));

        }

        if (!ObjectUtil.equal(location, user.getLocation())) {

            location = user.getLocation();

            updates.add(set("location", location));

        }

        String originDescription = user.getDescription();

        URLEntity[] entities = user.getDescriptionURLEntities();

        if (entities != null) {

            for (URLEntity entity : entities) {

                originDescription = originDescription.replace(entity.getURL(), entity.getExpandedURL());

            }

        }

        if (!ObjectUtil.equal(description, originDescription)) {

            description = originDescription;

            updates.add(set("description", description));

        }

        if (!ObjectUtil.equal(profileImage, user.getOriginalProfileImageURLHttps())) {

            profileImage = user.getOriginalProfileImageURLHttps();

            updates.add(set("profileImage", profileImage));

        }

        if (!ObjectUtil.equal(bannerImage, user.getProfileBannerURL())) {

            bannerImage = user.getOriginalProfileImageURLHttps();

            updates.add(set("bannerImage", bannerImage));

        }

        if (!ObjectUtil.equal(isProtected, user.isProtected())) {

            isProtected = user.isProtected();

            updates.add(set("isProtected", isProtected));

        }

        if (!ObjectUtil.equal(followers, user.getFollowersCount())) {

            followers = user.getFollowersCount();

            updates.add(set("followers", followers));

        }

        if (!ObjectUtil.equal(friends, user.getFriendsCount())) {

            friends = user.getFriendsCount();

            updates.add(set("friends", friends));

        }

        return updates.isEmpty() ? null : and(updates);

    }

    public String toURL() {

        return "https://twitter.com/" + screenName;

    }

    public String toMention() {

        return Fn.a(name, toURL());

    }

    @Override
    public String toString() {

        return toMention();

    }

    public String simpleName() {

        String report = Fn.b(name);

        if (isProtected) {

            report += " " + Fn.b("🔒");

        }

        report += " " + Fn.a("@" + screenName, "https://twitter.com/" + screenName);

        return report;

    }

    /*

     TestUser *🔒* *@test_user* #ID_123456

     user bio

     🌐 位置 🔗 http://example.com
     📅 于 2019年12月 加入

     历史名称 :
     NAMEA
     NAMEB

     历史用户名 : test_userA test_userB

     *114*个跟随中 *514* 个跟随者

     */

    public String parseInfo(Lang L) {

        String report = simpleName() + "\n\n";

        if (StrUtil.isNotBlank(description)) {

            report += HtmlUtil.escape(description) + "\n\n";

        }

        if (StrUtil.isNotBlank(location)) {

            report += Fn.b("🌐") + " " + HtmlUtil.escape(location) + " ";

        }

        if (StrUtil.isNotBlank(url)) {

            report += Fn.b("🔗") + " " + HtmlUtil.escape(url) + "\n";

        }

        report += Fn.b("📅") + " " + StrUtil.format(L.TWI_JOIN_AT, DateUtil.format(new Date(createAt), L.TWI_JOIN_FORMAT)) + "\n";

        if (CollectionUtil.isNotEmpty(nameHistory)) {

            report += "\n" + Fn.b(L.TWI_NAME_HISTORY) + " :\n";

            for (String name : nameHistory) {

                report += Fn.code(name) + "\n";

            }

        }

        if (CollectionUtil.isNotEmpty(snHistory)) {

            report += "\n" + Fn.b(L.TWI_SN_HISTORY) + " :";

            for (String screenName : snHistory) {

                report += " " + Fn.b(screenName);

            }

            report += "\n";

        }

        report += "\n" + Fn.b(friends) + L.TWI_FRIENDS + "     " + Fn.b(followers) + L.TWI_FOLLOWERS;

        return report;

    }

}
