package tooko;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.ArrayPropertyCodecProvider;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.PropertyCodecProvider;
import org.bson.codecs.pojo.SubClassPropertyCodecProvider;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.main.bots.BotData;
import tooko.main.bots.BotImage;
import tooko.main.bots.BotPanel;
import tooko.main.bots.CreateBot;
import tooko.main.extras.*;
import tooko.main.manage.SysConfig;
import tooko.main.manage.SysDebug;
import tooko.main.manage.SysManage;
import tooko.main.update.UpdateHook;
import tooko.main.update.UpdateTask;
import tooko.pm.PmData;
import tooko.td.TdApi;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.User;
import tooko.td.client.TdBot;
import tooko.td.client.TdException;
import tooko.td.core.TookoLog;
import tooko.twitter.ApiToken;
import tooko.twitter.TwitterBot;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher extends TdBot implements Thread.UncaughtExceptionHandler {

    public static Launcher INSTANCE;
    public static Log log;
    public static TwitterBot twitter;

    public Launcher(String botToken) {

        super(botToken);

    }

    public static void main(String[] args) {

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

        mongoLogger.setLevel(Level.WARNING);

        LogFactory.setCurrentLogFactory(new TookoLog.Factory());

        log = LogFactory.get("Tooko");

        UpdateTask.fetchHead();

        log.debug("正在加载 (๑•̀ㅂ•́)√");

        try {

            System.load(Env.getPath("libs/jni/libtdjni.so"));

        } catch (Exception ex) {

            log.error("加载 TDLib 失败. ( {} )", ex.getMessage());

            System.exit(100);

            return;

        }

        try {

            System.load(Env.getPath("libs/jni/libwebp-imageio.so"));

        } catch (Exception ex) {

            log.error("加载 WebP 失败. ( {} )", ex.getMessage());

            System.exit(100);

            return;

        }

        tooko.td.Log.setVerbosityLevel(1);

        File configFile = new File(Env.ROOT_PATH, "config.json");

        if (!configFile.isFile()) {

            log.error("配置文件 (config.json) 不存在, 请放置.");

            System.exit(100);

            return;

        }

        JSONObject config;

        try {

            config = new JSONObject(FileUtil.readUtf8String(configFile));

        } catch (JSONException err) {

            log.error("配置文件无法解析, 请检查JSON语法错误 : {}", err.getMessage());

            System.exit(100);

            return;

        }

        try {

            checkConfig(config);

        } catch (IllegalStateException err) {

            log.error(err);

            System.exit(100);

            return;

        }

        String dbAddress = config.getStr("db_address");

        String dbPort = config.getStr("db_port");

        PojoCodecProvider provider = PojoCodecProvider.builder()
            //.register(new BLMapPropertyCodecProvider())
            .register(new ArrayPropertyCodecProvider())
            .register(registerSubClasses(TdApi.class))
            .automatic(true)
            .build();

        try {

            MongoClientSettings settings = MongoClientSettings

                .builder()
                .applyConnectionString(new ConnectionString("mongodb://" + dbAddress + ":" + dbPort))
                .applyToClusterSettings(new Block<ClusterSettings.Builder>() {

                    @Override
                    public void apply(ClusterSettings.Builder builder) {

                        builder.serverSelectionTimeout(1, TimeUnit.SECONDS);

                    }

                })
                .codecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(provider)))
                .build();

            MongoClient dbClient = MongoClients.create(settings);

            MongoDatabase db = dbClient.getDatabase(config.getStr("db_name"));

            db.listCollectionNames().iterator().tryNext();

            Env.DB_CLIENT = dbClient;
            Env.DB = db;

        } catch (MongoException err) {

            log.error("MongoDB 连接失败, 请检查配置 ( {}:{} ) : {}", dbAddress, dbPort, err.getMessage());

            System.exit(100);

            return;

        }

        Env.PUBLIC_BOT_CREATE = config.getBool("public_bot_create");
        Env.BOT_CREATE_MAX = config.getInt("bot_create_max");
        Env.LOG_CHANNEL = config.getLong("log_channel");
        Env.USE_SERVICE = config.getBool("use_service");
        Env.SERVICE = config.getStr("service");
        Env.BOT_TOKEN = config.getStr("bot_token");
        Env.ADMINS = (int[]) config.getJSONArray("admins").toArray(int.class);

        JSONObject twitterObj = config.getJSONObject("twitter");

        if (!twitterObj.isEmpty()) {

            try {

                checkTwitter(twitterObj);

            } catch (IllegalStateException err) {

                log.error(err);

                System.exit(100);

                return;

            }

            Env.TWITTER_BOT_TOKEN = twitterObj.getStr("bot_token");

            Env.TWITTER_PUBLIC = twitterObj.getBool("public");

            JSONArray apiTokenArray = twitterObj.getJSONArray("api_tokens");

            if (apiTokenArray == null || apiTokenArray.isEmpty()) {

                log.error("启用了 Twitter 子机器人, 但没有填写 Twitter Api Token 列表.");

                System.exit(100);

                return;

            }

            ApiToken[] apiTokens = new ApiToken[apiTokenArray.size()];

            for (int index = 0; index < apiTokenArray.size(); index++) {

                JSONObject apiObj = apiTokenArray.getJSONObject(index);

                if (apiObj == null || apiObj.isEmpty()) {

                    log.error("Twitter Api Token 第 {} 项格式非法.", index + 1);

                    System.exit(100);

                    return;


                }

                String name = apiObj.getStr("name");

                if (name == null) {

                    log.error("Twitter Api Token 第 {} 项没有填写名称 ( name 字段 ).", index + 1);

                    System.exit(100);

                    return;

                }

                String apiKey = apiObj.getStr("api_key");

                if (apiKey == null) {

                    log.error("Twitter Api Token 第 {} 项没有填写 Api Key ( api_key 字段 ).", index + 1);

                    System.exit(100);

                    return;

                }

                String apiSecretKey = apiObj.getStr("api_secret_key");

                if (apiSecretKey == null) {

                    log.error("Twitter Api Token 第 {} 项没有填写 Api Secret Key ( api_secret_key 字段 ).", index + 1);

                    System.exit(100);

                    return;

                }

                apiTokens[index] = new ApiToken(name, apiKey, apiSecretKey);

            }

            Env.TWITTER_API_TOKENS = apiTokens;

        }

        Env.NSFW_SERVER = config.getStr("nsfw_server");

        JSONObject textCensor = config.getJSONObject("text_censor");

        if (textCensor.containsKey("provider")) {

            if ("baidu".equals(textCensor.getStr("provider"))) {

                Env.TEXT_CENSOR_PROVIDER = "baidu";
                
                Env.BAIDU_APP_ID = textCensor.getStr("app_id");
                Env.BAIDU_API_KEY = textCensor.getStr("api_key");
                Env.BAIDU_SECRET_KEY = textCensor.getStr("secret_key");
                
            }

        }

        if (Env.ADMINS.length == 0) {

            log.warn("没有设置管理员账号 (ﾟ⊿ﾟ)ﾂ 请使用 /id 命令获取用户ID并填入 ADMINS 配置中 ~");

        }

        INSTANCE = new Launcher(Env.BOT_TOKEN);

        INSTANCE.start();

    }

    private static PropertyCodecProvider[] registerSubClasses(Class<?> apiClazz) {

        HashMap<Class<?>, LinkedList<Class<?>>> clazzes = new HashMap<>();

        for (Class<?> clazz : apiClazz.getDeclaredClasses()) {

            if (clazz.isInterface()) continue;

            if (clazz.getSuperclass() != null) {

                Class<?> intClazz = clazz.getSuperclass();

                LinkedList<Class<?>> subClazz = clazzes.get(intClazz);

                if (subClazz == null) {

                    subClazz = new LinkedList<>();

                    clazzes.put(intClazz, subClazz);

                }

                subClazz.add(clazz);

            }

        }

        LinkedList<SubClassPropertyCodecProvider<Class<?>>> providers = new LinkedList<>();

        for (Map.Entry<Class<?>, LinkedList<Class<?>>> subClazz : clazzes.entrySet()) {

            //noinspection unchecked
            providers.add(new SubClassPropertyCodecProvider(subClazz.getKey(), subClazz.getValue()));

        }

        return providers.toArray(new PropertyCodecProvider[0]);

    }

    public static void checkConfig(JSONObject config) throws IllegalStateException {

        String[] fields = new String[]{

            "db_address",
            "db_port",
            "db_name",

            "use_service",
            "service",

            "bot_token",

            "public_bot_create",
            "bot_create_max",

            "admins",
            "log_channel",


            "twitter",

            "nsfw_server",

            "text_censor",

        };

        for (String field : fields) {

            if (!config.containsKey(field)) {

                throw new IllegalStateException(StrUtil.format("配置中 {} 值缺失, 请检查.", field));

            }

        }

    }

    public static void checkTwitter(JSONObject config) throws IllegalStateException {

        String[] fields = new String[]{

            "bot_token",
            "public",
            "api_tokens",

        };

        for (String field : fields) {

            if (!config.containsKey(field)) {

                throw new IllegalStateException(StrUtil.format("Twitter 子配置中 {} 值缺失, 请检查.", field));

            }

        }

    }

    @Override
    public void uncaughtException(Thread Thread, Throwable throwable) {

        log.error(throwable, "TdFatal");

        INSTANCE.destroy();

        if (Env.USE_SERVICE) {

            RuntimeUtil.exec("systemctl restart " + Env.SERVICE);

        } else {

            RuntimeUtil.exec("bash -c 'exec ./start'");

        }

    }

    @Override
    public void onAuthorizationFailed(TdException ex) {

        log.error("认证失败, 请检查机器人令牌. ( {} )", ex);

        System.exit(100);

    }

    @Override
    public void onLoggingOut() {

        log.error("机器人本体被登出.");

        destroy();

        System.exit(100);

    }

    @Override
    public void onLoad() {

        addHandler(new PingFunction());

        addHandler(new GetIdFunction());

        addHandler(new StickerExport());

        addHandler(new ForwardInfo());

        addHandler(new SysManage());

        addHandler(new SysDebug());

        addHandler(new UpdateHook());

        addHandler(new CreateBot());

        addHandler(new BotPanel());

        addHandler(new SwitchLang());

        addHandler(new LICENCE());

        super.onLoad();

    }

    @Override
    public void onLogin() {

        Thread.setDefaultUncaughtExceptionHandler(this);

        if (SysConfig.isAutoUpdateEnable()) UpdateTask.start();

        if (Env.TWITTER_BOT_TOKEN != null) {

            log.debug("加载 Twitter子机器人 (๑•̀ㅂ•́)√");

            twitter = new TwitterBot();

            twitter.start();

        }

        List<BotData> allBots = BotData.DATA.getAll();

        if (!allBots.isEmpty()) {

            log.debug("加载 托管机器人 (๑•̀ㅂ•́)√");

            for (BotData data : allBots) BotImage.start(data);

        }

    }

    @Override
    public void start() {

        super.start();

        new EventTask().start();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        if (twitter != null) twitter.destroy();

        UpdateTask.stop();

        PmData.DATA.saveAll();

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (!Fn.fromPrivate(message)) {

            send(Fn.deleteMessage(message));

            return;

        }

        deleteStartMessages(chatId);

        send(Fn.sendText(chatId, SwitchLang.langs, Fn.plainText(Lang.get(user).CHOOSE_LANG)), asStartMessage(message));

    }

    @Override
    public void onLaunch(User user, long chatId, Message message) {

        if (!Fn.fromPrivate(message)) return;

        Lang L = Lang.get(user);

        deleteStartMessages(chatId);

        if (Env.PUBLIC_BOT_CREATE && !Env.isAdmin(user.id)) {

            send(Fn.sendText(chatId, Fn.parseHtml(L.HELP + L.PUBLIC_WARN)), asStartMessage(message));

        } else {

            send(Fn.sendText(chatId, Fn.parseHtml(L.HELP)), asStartMessage(message));

        }

        if (!Lang.DATA.containsId(user.id)) {

            send(Fn.sendText(chatId, SwitchLang.langs, Fn.plainText(Lang.get(user).CHOOSE_LANG)));

        }

    }


}


