package tooko.main.bots;

import cn.hutool.core.util.*;
import tooko.main.*;
import tooko.pm.*;
import tooko.td.client.*;

import java.util.*;

public class BotImage {

    public static HashMap<Integer, BotImage> images = new HashMap<>();

    public static int STATUS_RUNNING = 0;
    public static int STATUS_ERROR = 1;

    public BotData data;

    public int status;

    public TdException error;

    public TdBot bot;

    public BotImage(BotData data) {

        this.data = data;

    }


    public static void start(BotData data) {

        if (images.containsKey(data.botId)) return;

        BotImage image = new BotImage(data);

        images.put(data.botId, image);

        if (data.type == 1) {

            image.bot = new PmBot(image);

            image.bot.start();

        }

    }


    public static void delete(BotData data) {

        if (images.containsKey(data.botId)) {

            images.get(data.botId).bot.destroy();

        }

        BotData.DATA.deleteById(data.botId);

        if (data.type == 1) {

            PmData.DATA.deleteById(data.botId);

            Env.DB.getCollection("pm_" + data.botId).drop();

        }

        RuntimeUtil.exec("rm -rf \"" + Env.getPath("data/" + data.botId) + "\"");

    }

}
