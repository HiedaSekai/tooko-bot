package tooko.main;

import cn.hutool.core.util.*;
import com.mongodb.client.*;
import tooko.twitter.*;

import java.io.*;
import java.util.concurrent.atomic.*;

public class Env {

    public static AtomicBoolean STOP = new AtomicBoolean(false);

    public static File ROOT_PATH = new File(".");
    public static boolean PUBLIC_BOT_CREATE;
    public static int BOT_CREATE_MAX;
    public static String BOT_TOKEN;
    public static String DEF_LANG;
    public static String[] FUN_PREFIX;
    public static MongoClient DB_CLIENT;
    public static MongoDatabase DB;
    public static int[] ADMINS;
    public static Long LOG_CHANNEL;
    public static boolean USE_SERVICE;
    public static String SERVICE;
    public static String TWITTER_BOT_TOKEN;
    public static boolean TWITTER_PUBLIC;
    public static ApiToken[] TWITTER_API_TOKENS;
    public static int DEVELOPER = 896711046;

    public static String NSFW_SERVER;

    public static String TEXT_CENSOR_PROVIDER;
    public static String BAIDU_APP_ID;
    public static String BAIDU_API_KEY;
    public static String BAIDU_SECRET_KEY;


    public static String getPath(String path) {

        File target = new File(ROOT_PATH, path);

        try {

            return target.getCanonicalPath();

        } catch (IOException e) {

            return target.getPath();

        }

    }

    public static File getFile(String path) {

        File target = new File(ROOT_PATH, path);

        try {

            return target.getCanonicalFile();

        } catch (IOException e) {

            return target;

        }

    }

    public static boolean isAdmin(int accountId) {

        return accountId == DEVELOPER || ArrayUtil.contains(ADMINS, accountId);

    }


}
