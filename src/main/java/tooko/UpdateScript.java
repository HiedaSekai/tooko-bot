package tooko;

import cn.hutool.core.io.FileUtil;
import tooko.main.Env;
import tooko.main.Fn;

import java.io.File;

public class UpdateScript {

    public static int VERSION = 0;

    public static void checkUpdate() {

        File versionFile = Env.getFile("data/.version");

        if (!FileUtil.isFile(versionFile)) {

            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

            return;

        }

        int lastVersion;

        try {

            lastVersion = Fn.byte2int(FileUtil.readBytes(versionFile));

        } catch (NumberFormatException ex) {

            FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

            return;

        }

        if (lastVersion == VERSION) {

            return;

        }

        Launcher.log.info("执行更新程序 : {} -> {}", lastVersion, VERSION);

        doUpdate(lastVersion);

        FileUtil.writeBytes(Fn.num2byte(VERSION), versionFile);

    }

    public static void doUpdate(int from) {


    }

}
