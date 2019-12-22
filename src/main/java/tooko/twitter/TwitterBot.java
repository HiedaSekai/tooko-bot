package tooko.twitter;

import tooko.Launcher;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.main.extras.LICENCE;
import tooko.main.extras.SwitchLang;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdBot;
import tooko.td.client.TdException;
import tooko.twitter.account.AccountPanel;
import tooko.twitter.account.TwitterLogin;
import tooko.twitter.account.TwitterLogout;
import tooko.twitter.actions.*;
import tooko.twitter.follow.CheckTask;
import tooko.twitter.follow.FollowConfig;
import tooko.twitter.follow.FollowTask;
import tooko.twitter.spam.ImageTest;
import tooko.twitter.spam.UserTest;

public class TwitterBot extends TdBot {

    public TwitterBot() {

        super(Env.TWITTER_BOT_TOKEN);

    }

    @Override
    public void onLoad() {

        addHandler(new TwitterLogin());
        addHandler(new TwitterLogout());

        addHandler(new AccountPanel());

        addHandler(new TrackConfig());

        addHandler(new MuteAndBlock());

        addHandler(new SwitchLang());

        addHandler(new FollowConfig());

        addHandler(new FollowersScan());

        addHandler(new ImageTest());

        addHandler(new UserTest());

        addHandler(new LICENCE());

        super.onLoad();

    }

    @Override
    public void onLogin() {

        TrackTask.start();

        UserFetchTask.start();

        FollowTask.Companion.start();

        CheckTask.Companion.start();

    }

    @Override
    public void onLaunch(User user, long chatId, Message message) {

        if (!Fn.fromPrivate(message)) return;

        Lang L = Lang.get(user);

        deleteStartMessages(chatId);

        if (Env.TWITTER_PUBLIC && !Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.parseHtml(L.TWI_HELP + L.PUBLIC_WARN)), asStartMessage(message));

        } else {

            send(Fn.sendText(chatId, Fn.parseHtml(L.TWI_HELP)), asStartMessage(message));

        }

        if (!Lang.DATA.containsId(user.id)) {

            send(Fn.sendText(chatId, SwitchLang.langs, Fn.plainText(Lang.get(user).CHOOSE_LANG)));

        }

    }

    @Override
    public void onAuthorizationFailed(TdException ex) {

        log.error("Twitter 子机器人认证失败, 请检查机器人令牌.", ex);

        destroy();

    }

    @Override
    public void onLoggingOut() {

        log.error("Twitter 子机器人被登出.");

        destroy();

    }

    @Override
    public void destroy() {

        TrackTask.stop();

        UserFetchTask.stop();

        FollowTask.Companion.stop();

        CheckTask.Companion.stop();

        Launcher.twitter = null;

        super.destroy();

    }

}
