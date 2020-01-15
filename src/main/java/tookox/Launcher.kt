/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tookox

import cn.hutool.core.getter.OptNullBasicTypeFromObjectGetter
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.json.JSONException
import cn.hutool.log.LogFactory
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoException
import com.mongodb.client.MongoClients
import kotlinx.coroutines.*
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.ArrayPropertyCodecProvider
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.codecs.pojo.PropertyCodecProvider
import org.bson.codecs.pojo.SubClassPropertyCodecProvider
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import td.TdApi
import tookox.agent.AgentData
import tookox.agent.AgentImage
import tookox.agent.BotFlood
import tookox.agent.clean.TestClean
import tookox.agent.stickers.SyncStickers
import tookox.core.*
import tookox.core.agent.*
import tookox.core.bots.*
import tookox.core.client.*
import tookox.core.db.*
import tookox.core.env.*
import tookox.core.funs.*
import tookox.core.nsfw.*
import tookox.core.raw.*
import tookox.core.utils.*
import java.io.File
import java.lang.Thread.UncaughtExceptionHandler
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.system.exitProcess

class Launcher : TdBot(Env.BOT_TOKEN), UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {

        defaultLog.error(e)

    }

    override fun onLoad() {

        addHandler(BaseFuncs())

        addHandler(StickerExport())

        // Bots

        addHandler(CreateBot())

        addHandler(BotPanel())

        // Agent

        addHandler(CreateAgent())

        addHandler(AgentTransfer())

        addHandler(BotFlood())

        addHandler(SyncStickers())

        addHandler(TestClean())

        // Licence

        addHandler(LICENCE())

    }

    override suspend fun onLogin() = coroutineScope {

        /*

        twitter = TwitterBot().apply { start() }

         */

        val deferreds = LinkedList<Deferred<*>>()

        BotData.DATA.all.forEach { bot ->

            run<Unit> {

                deferreds.add(async<Unit> {

                    BotImage.start(bot)

                })

            }

        }

        AgentData.DATA.all.forEach { agent ->

            /*

            if (!Env.getFile("data/agent/${agent.userId}/td.binlog").isFile) {

                AgentData.DATA.deleteById(agent.userId)

                return@forEach

            }

             */

            run<Unit> {

                deferreds.add(async<Unit> {

                    AgentImage.start(agent)

                })

            }

        }

        deferreds.awaitAll()

        defaultLog.info("远子 基于 Apache License 2.0 协议发行")

        val time = (System.currentTimeMillis() - startAt).toDouble() / 1000

        defaultLog.info("启动完成! 用时 ${time}s.")

    }

    override suspend fun onDestroy() {

        super.onDestroy()

        cachedTables.forEach { it.saveAll() }

    }

    override suspend fun onLaunch(userId: Int, chatId: Long, message: TdApi.Message) {

        val L = userId.langFor

        sudo makeHtml (if (Env.isAdmin(userId)) L.HELP else "${L.HELP}\n\n${L.PUBLIC_WARN}") sendTo chatId

    }

    override suspend fun onAuthorizationFailed(ex: TdException) {

        super.onAuthorizationFailed(ex)

        defaultLog.error(ex, "本体认证失败.")

        INSTANCE.stop()

        exitProcess(100)

    }

    companion object {

        val startAt = System.currentTimeMillis()

        val cachedTables = LinkedList<CacheTable<*, *>>()

        // lateinit var twitter: TwitterBot

        @Suppress("UNCHECKED_CAST")
        class TypedMap(map: Any) : HashMap<String, Any>(map as Map<String, Any>), OptNullBasicTypeFromObjectGetter<String> {

            override fun getObj(key: String, defaultValue: Any?): Any? = get(key) ?: defaultValue

        }

        fun Any.toTypedMap() = TypedMap(this)

        lateinit var INSTANCE: Launcher

        val isInitialized get() = ::INSTANCE.isInitialized

        @JvmStatic
        fun main(args: Array<String>) = runBlocking<Unit> {

            Logger.getLogger("org.mongodb.driver").apply {

                level = Level.WARNING

                // disable mongo logs

            }

            LoggerFactory::class.java.getDeclaredField("INITIALIZATION_STATE").apply {

                isAccessible = true

                set(null, 4)

                // Disable slf4j warnning

            }

            LogFactory.setCurrentLogFactory(TookoLogFactory)

            defaultLog.info("正在加载 (๑•̀ㅂ•́)√")

            try {

                LibsLoader.load("tdjni")

            } catch (ex: Exception) {

                defaultLog.error("加载 TDLib 失败. ( {} )", ex.message)

                exitProcess(100)

            }

            try {

                LibsLoader.load("webp-imageio")

            } catch (ex: Exception) {

                defaultLog.error("加载 WebP 失败. ( {} )", ex.message)

                exitProcess(100)

            }

            setLogVerbosityLevel(1)

            val configFile = File(Env.ROOT_PATH, "config.yml")

            if (!configFile.isFile) {

                defaultLog.error("配置文件 (config.yml) 不存在, 请放置.")

                exitProcess(100)

            }

            val config = try {

                TypedMap(Yaml().load(FileUtil.readUtf8String(configFile)))

            } catch (err: JSONException) {

                defaultLog.error("配置文件无法解析, 请检查JSON语法错误 : {}", err.message)

                exitProcess(100)

            }

            try {

                checkConfig(config)

            } catch (err: IllegalStateException) {

                defaultLog.error(err)

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

                defaultLog.error("MongoDB 连接失败, 请检查配置 ( {}:{} ) : {}", dbAddress, dbPort, err.message)

                exitProcess(100)
            }

            Env.PUBLIC_BOT_CREATE = config.getBool("public_bot_create")
            Env.BOT_CREATE_MAX = config.getInt("bot_create_max")
            Env.LOG_CHANNEL = config.getLong("log_channel")
            Env.USE_SERVICE = config.getBool("use_service")
            Env.SERVICE = config.getStr("service")
            Env.USE_TEST_DC = config.getBool("use_test_dc")

            Env.PASSWORD = config.getStr("password")
            Env.BOT_TOKEN = config.getStr("bot_token")
            Env.DEF_LANG = config.getStr("def_lang")

            try {

                LibsLoader.loadLanguages()

            } catch (ex: Exception) {

                defaultLog.error(ex.message)

                exitProcess(100)

            }

            @Suppress("UNCHECKED_CAST")
            Env.FUN_PREFIX = (config["fun_prefix"] as List<String>).toTypedArray()

            Env.ADMINS = (config["admins"] as List<*>).map { (it as Number).toInt() }.toIntArray()

            /*

            val twitterObj = config["twitter"]!!.toTypedMap()

            if (!twitterObj.isEmpty()) {

                try {

                    checkTwitter(twitterObj)

                } catch (err: IllegalStateException) {

                    defaultLog.error(err)

                    exitProcess(100)

                }

                Env.TWITTER_BOT_TOKEN = twitterObj.getStr("bot_token")

                Env.TWITTER_PUBLIC = twitterObj.getBool("public")

                @Suppress("UNCHECKED_CAST") val apiTokenArray = twitterObj.get("api_tokens") as List<Any>?

                if (apiTokenArray == null || apiTokenArray.isEmpty()) {

                    defaultLog.error("启用了 Twitter 子机器人, 但没有填写 Twitter Api Token 列表.")

                    exitProcess(100)

                }

                val apiTokens = arrayOfNulls<ApiToken>(apiTokenArray.size)

                for (index in apiTokenArray.indices) {

                    val apiObj = apiTokenArray[index].toTypedMap()

                    if (apiObj.isEmpty()) {

                        defaultLog.error("Twitter Api Token 第 {} 项格式非法.", index + 1)

                        exitProcess(100)

                    }

                    val name = apiObj.getStr("name")

                    if (name == null) {

                        defaultLog.error("Twitter Api Token 第 {} 项没有填写名称 ( name 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    val apiKey = apiObj.getStr("api_key")

                    if (apiKey == null) {

                        defaultLog.error("Twitter Api Token 第 {} 项没有填写 Api Key ( api_key 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    val apiSecretKey = apiObj.getStr("api_secret_key")

                    if (apiSecretKey == null) {

                        defaultLog.error("Twitter Api Token 第 {} 项没有填写 Api Secret Key ( api_secret_key 字段 ).", index + 1)

                        exitProcess(100)

                    }

                    apiTokens[index] = ApiToken(name, apiKey, apiSecretKey)

                }

                Env.TWITTER_API_TOKENS = apiTokens

            }

            Env.NSFW_SERVER = config.getStr("nsfw_server")

            val textCensor = config.get("text_censor")?.toTypedMap()

            if (textCensor?.containsKey("provider") == true) {

                if ("baidu" == textCensor.getStr("provider")) {

                    Env.TEXT_CENSOR_PROVIDER = "baidu"

                    Env.BAIDU_APP_ID = textCensor.getStr("app_id")

                    Env.BAIDU_API_KEY = textCensor.getStr("api_key")

                    Env.BAIDU_SECRET_KEY = textCensor.getStr("secret_key")

                }

            }

             */

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

        private fun checkConfig(config: TypedMap) {

            val fields = arrayOf(
                    "db_address", "db_port", "db_name",
                    "use_service", "service", "use_test_dc",
                    "bot_token", "def_lang", "fun_prefix",
                    "password", "public_bot_create", "bot_create_max",
                    "admins", "log_channel",
                    "twitter",
                    "nsfw_server",
                    "text_censor")

            for (field in fields) {

                check(config.containsKey(field)) { StrUtil.format("配置中 {} 值缺失, 请检查.", field) }

            }

        }

        private fun checkTwitter(config: TypedMap) {

            val fields = arrayOf(
                    "bot_token", "public", "api_tokens")

            for (field in fields) {

                check(config.containsKey(field)) { StrUtil.format("Twitter 子配置中 {} 值缺失, 请检查.", field) }

            }

        }


    }

}