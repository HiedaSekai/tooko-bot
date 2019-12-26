package tookox

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.json.JSONException
import cn.hutool.json.JSONObject
import cn.hutool.log.LogFactory
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoException
import com.mongodb.client.MongoClients
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.ArrayPropertyCodecProvider
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.codecs.pojo.PropertyCodecProvider
import org.bson.codecs.pojo.SubClassPropertyCodecProvider
import tooko.main.Env
import tooko.main.bots.BotData
import tooko.main.bots.BotImage
import tooko.main.utils.nsfw.NSRC
import tooko.main.utils.nsfw.TCRC
import tooko.td.Log
import tooko.td.TdApi
import tooko.td.client.TdClient.EventTask
import tooko.td.core.TookoLog
import tooko.twitter.ApiToken
import tooko.twitter.TwitterBot
import tookox.core.client.TdBot
import java.io.File
import java.lang.Thread.UncaughtExceptionHandler
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.system.exitProcess

class Launcher : TdBot(Env.BOT_TOKEN), UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {

        log.error(e)

    }

    override fun onLogin() {

        TwitterBot().start()

        val allBots = BotData.DATA.all

        if (!allBots.isEmpty()) {

            log.debug("加载 托管机器人 (๑•̀ㅂ•́)√")

            for (data in allBots) BotImage.start(data)

        }

        EventTask().start()

    }

    companion object {

        lateinit var INSTANCE: Launcher

        @JvmStatic
        fun main(args: Array<String>) {

            val mongoLogger = Logger.getLogger("org.mongodb.driver")

            mongoLogger.level = Level.WARNING

            LogFactory.setCurrentLogFactory(TookoLog.Factory())

            log.debug("正在加载 (๑•̀ㅂ•́)√")

            try {

                System.load(Env.getPath("libs/jni/libtdjni.so"))

            } catch (ex: Exception) {

                log.error("加载 TDLib 失败. ( {} )", ex.message)

                exitProcess(100)

            }

            try {

                System.load(Env.getPath("libs/jni/libwebp-imageio.so"))

            } catch (ex: Exception) {

                log.error("加载 WebP 失败. ( {} )", ex.message)

                exitProcess(100)

            }

            Log.setVerbosityLevel(1)

            val configFile = File(Env.ROOT_PATH, "config.json")

            if (!configFile.isFile) {

                log.error("配置文件 (config.json) 不存在, 请放置.")

                exitProcess(100)

            }

            val config: JSONObject

            config = try {

                JSONObject(FileUtil.readUtf8String(configFile))

            } catch (err: JSONException) {

                log.error("配置文件无法解析, 请检查JSON语法错误 : {}", err.message)

                exitProcess(100)

            }

            try {

                checkConfig(config)

            } catch (err: IllegalStateException) {

                log.error(err)

                exitProcess(100)

            }

            val dbAddress = config.getStr("db_address")
            val dbPort = config.getStr("db_port")

            val provider = PojoCodecProvider.builder().register(ArrayPropertyCodecProvider<Any?>()).register(*registerSubClasses(TdApi::class.java)).automatic(true).build()

            val registry = CodecRegistries.fromCodecs(
                    NSRC.CODEC(), TCRC.CODEC()
            )

            try {

                val settings = MongoClientSettings.builder()
                        .applyConnectionString(ConnectionString("mongodb://$dbAddress:$dbPort"))
                        .applyToClusterSettings { builder ->
                            builder.serverSelectionTimeout(1, TimeUnit.SECONDS)
                        }.codecRegistry(CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(provider), registry))
                        .build()

                val dbClient = MongoClients.create(settings)
                val db = dbClient.getDatabase(config.getStr("db_name"))

                db.listCollectionNames().iterator().tryNext()

                Env.DB_CLIENT = dbClient
                Env.DB = db

            } catch (err: MongoException) {

                log.error("MongoDB 连接失败, 请检查配置 ( {}:{} ) : {}", dbAddress, dbPort, err.message)

                exitProcess(100)
            }

            Env.PUBLIC_BOT_CREATE = config.getBool("public_bot_create")
            Env.BOT_CREATE_MAX = config.getInt("bot_create_max")
            Env.LOG_CHANNEL = config.getLong("log_channel")
            Env.USE_SERVICE = config.getBool("use_service")
            Env.SERVICE = config.getStr("service")
            Env.BOT_TOKEN = config.getStr("bot_token")
            Env.ADMINS = config.getJSONArray("admins").toArray(Int::class.javaPrimitiveType) as IntArray

            val twitterObj = config.getJSONObject("twitter")

            if (!twitterObj.isEmpty()) {

                try {

                    checkTwitter(twitterObj)

                } catch (err: IllegalStateException) {

                    log.error(err)

                    exitProcess(100)

                }

                Env.TWITTER_BOT_TOKEN = twitterObj.getStr("bot_token")

                Env.TWITTER_PUBLIC = twitterObj.getBool("public")

                val apiTokenArray = twitterObj.getJSONArray("api_tokens")

                if (apiTokenArray == null || apiTokenArray.isEmpty()) {

                    log.error("启用了 Twitter 子机器人, 但没有填写 Twitter Api Token 列表.")

                    exitProcess(100)

                }

                val apiTokens = arrayOfNulls<ApiToken>(apiTokenArray.size)

                for (index in apiTokenArray.indices) {

                    val apiObj = apiTokenArray.getJSONObject(index)

                    if (apiObj == null || apiObj.isEmpty()) {

                        log.error("Twitter Api Token 第 {} 项格式非法.", index + 1)

                        exitProcess(100)

                    }

                    val name = apiObj.getStr("name")

                    if (name == null) {

                        log.error("Twitter Api Token 第 {} 项没有填写名称 ( name 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    val apiKey = apiObj.getStr("api_key")

                    if (apiKey == null) {

                        log.error("Twitter Api Token 第 {} 项没有填写 Api Key ( api_key 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    val apiSecretKey = apiObj.getStr("api_secret_key")

                    if (apiSecretKey == null) {

                        log.error("Twitter Api Token 第 {} 项没有填写 Api Secret Key ( api_secret_key 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    apiTokens[index] = ApiToken(name, apiKey, apiSecretKey)

                }

                Env.TWITTER_API_TOKENS = apiTokens

            }

            Env.NSFW_SERVER = config.getStr("nsfw_server")

            val textCensor = config.getJSONObject("text_censor")

            if (textCensor.containsKey("provider")) {

                if ("baidu" == textCensor.getStr("provider")) {

                    Env.TEXT_CENSOR_PROVIDER = "baidu"

                    Env.BAIDU_APP_ID = textCensor.getStr("app_id")

                    Env.BAIDU_API_KEY = textCensor.getStr("api_key")

                    Env.BAIDU_SECRET_KEY = textCensor.getStr("secret_key")

                }

            }

            if (Env.ADMINS.isEmpty()) {

                log.warn("没有设置管理员账号 (ﾟ⊿ﾟ)ﾂ 请使用 /id 命令获取用户ID并填入 ADMINS 配置中 ~")

            }

            UpdateScript.beforeLaunch()

            INSTANCE = Launcher()

            INSTANCE.start()

        }

        private fun registerSubClasses(apiClazz: Class<*>): Array<PropertyCodecProvider> {

            val clazzes = HashMap<Class<*>, LinkedList<Class<*>>>()

            for (clazz in apiClazz.declaredClasses) {

                if (clazz.isInterface) continue

                if (clazz.superclass != null) {

                    val intClazz = clazz.superclass

                    var subClazz = clazzes[intClazz]

                    if (subClazz == null) {

                        subClazz = LinkedList()

                        clazzes[intClazz] = subClazz
                    }

                    subClazz.add(clazz)

                }

            }

            val providers = LinkedList<SubClassPropertyCodecProvider<*>>()

            for ((key, value) in clazzes) {

                providers.add(SubClassPropertyCodecProvider(key, value))

            }

            return providers.toTypedArray()

        }

        fun checkConfig(config: JSONObject) {

            val fields = arrayOf(
                    "db_address", "db_port", "db_name",
                    "use_service", "service",
                    "bot_token",
                    "public_bot_create", "bot_create_max",
                    "admins", "log_channel",
                    "twitter",
                    "nsfw_server",
                    "text_censor")

            for (field in fields) {

                check(config.containsKey(field)) { StrUtil.format("配置中 {} 值缺失, 请检查.", field) }

            }

        }

        fun checkTwitter(config: JSONObject) {

            val fields = arrayOf(
                    "bot_token", "public", "api_tokens")

            for (field in fields) {

                check(config.containsKey(field)) { StrUtil.format("Twitter 子配置中 {} 值缺失, 请检查.", field) }

            }

        }


    }

}