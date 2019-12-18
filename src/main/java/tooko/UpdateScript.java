package tooko;

import cn.hutool.core.io.FileUtil;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.twitter.spam.StatusR;

import java.io.File;

public class UpdateScript {

    public static int VERSION = 1;
    public static int FROM = VERSION;

    public static void checkUpdate() {

        File versionFile = Env.getFile("data/.version");

        if (!FileUtil.isFile(versionFile)) {

            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

            return;

        }

        try {

            FROM = Fn.byte2int(FileUtil.readBytes(versionFile));

        } catch (NumberFormatException ex) {

            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

            return;

        }

        FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

    }

    public static void beforeLaunch() {

        if (FROM == 0) {

            for (StatusR status : StatusR.DATA.getAll()) {

                StatusR.DATA.setById(status.statusId, status);

            }

        }

    }

    public static void afterLaunch() {

        if (FROM != VERSION) {

            Launcher.INSTANCE.send(Fn.sendText(Env.LOG_CHANNEL, Fn.plainText("更新完成 : {} --> {}", FROM, VERSION)));

        }

    }

}