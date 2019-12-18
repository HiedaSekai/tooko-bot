package tooko;

import cn.hutool.core.io.FileUtil;
import tooko.main.Env;
import tooko.main.Fn;

import java.io.File;

public class UpdateScript {

    public static int VERSION = 0;
    public static int FROM = VERSION;

    private static void checkUpdate() {

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

        checkUpdate();

    }

    public static void afterLaunch() {

        if (FROM != VERSION) {

            Launcher.INSTANCE.send(Fn.sendText(Env.LOG_CHANNEL, Fn.plainText("程序已更新 {} -> {}", FROM, VERSION)));

        }

    }

}
