package tooko.main;

import tooko.main.lang.*;
import tooko.td.*;
import tooko.td.core.*;

public class Lang {

    public static Lang DEFAULT = new Lang();

    public static CacheTable<Integer, DB> DATA = new CacheTable<>("lang", DB.class);

    public String LANG_NAME;

    public String LAUNCH = "「 你知道怎样拿回失去的东西吗？」";

    public String HELP =

            "这里是远子. 使用前请阅读 " + wikiLink("Wiki", "Home") + " .\n\n";

    public String PUBLIC_WARN =

            "<b>注意, 这是一个公开实例 !</b>\n\n" +

                    "虽然这是一个开源项目, 但您应该了解 : 实例所有者可以任意修改源代码, 查看数据库 等, 请尽量 " + wikiLink("自行搭建实例.", "Install");

    public String SPLIT = "==========  (｡･∀･)ﾉﾞヾ(･ω･。)  ==========";
    public String SPLIT_END = "========================================";
    public String INVALID_TIME = "时间格式有误 ({}) \n\n格式 : 1d2h3s (一小时二分钟三秒）/ 10m （十分钟）/ 30s （三十秒）";
    public String DELAY_TOO_SHORT = "延时时间过短 ( < {} )";
    public String CHOOSE_LANG = "请选择语言 _(:з」∠)_";
    public String LANG_SELECTED = "欢迎使用 ~";
    public String FN_PING_RESULT = "喵...";
    public String FORWARD_FROM_USER = "来自用户";
    public String FORWARD_FROM_HIDDEN_USER = "来自匿名用户";
    public String FORWARD_FROM_CHANNEL = "来自频道";
    public String CHANNEL_RESTRICTIONED = "频道被限制";
    public String AUTHOR_SIGNATURE = "作者签名";
    public String MESSAGE_ID = "消息ID";
    public String MESSAGE_LINK = "消息链接";
    public String STICKER_CAPTION = "贴纸ID : <code>{}</code>\n贴纸表情 : <code>{}</code>\n贴纸集 : <code>{}</code>";
    public String STICKER_EXPORT = "下载贴纸集";
    public String STICKER_EXPORT_DL = "正在下载贴纸集 <b>{}</b> {} / {}";
    public String STICKER_DL_FAILED = "贴纸下载失败 _(:з」∠)_ ( {} )";
    public String STICKER_EXPORT_PACK = "正在转码 (≖‿≖)✧";
    public String STICKER_EXPORT_SEND = "完成！正在发送 (๑•̀ㅂ•́)√";
    public String STICKER_EXPORT_OTHER = "有人正在下载这个贴纸集 请稍后再试 _(:з」∠)_";
    public String STICKER_EXPORT_WAIT = "请等待上一个贴纸集下载完成 _(:з」∠)_";
    public String STICKER_EXPORT_FLOOD = "同时下载人数过多 请稍后再试 _(:з」∠)_";
    public String PERMISSION_DENIED = "啊！不行啦！";

    public String BACK = "🔙";

    public String CNF = "没有这个命令 :(";
    public String ACTIVE_THREADS = "运行线程";
    public String MEM_TOTAL = "物理内存";
    //public String RATE_LIMIT = "对不起... 接下来的五分钟只能不理你了呢...";
    public String MEM_USED = "已用内存";
    public String MEM_CACHED = "缓存内存";
    public String MEM_FREE = "空闲内存";
    public String LOCAL_VERSION = "本地版本";
    public String REMOTE_VERSION = "远程版本";
    public String VER_UPDATE = "版本更新";
    public String AUTO_UPDATE = "轮询";
    public String HOOK_UPDATE = "钩子";
    public String ENABLE = "启用";
    public String DISABLE = "禁用";
    public String DELETED = "已删除";
    public String CHECK_UPDATE_FAILED = "检查更新失败";
    public String REFRESH = "刷新";
    public String STOP = "停止";
    public String RESTART = "重启";
    public String CHECK_UPDATE = "检查更新";
    public String UPDATE_NOW = "立即更新";
    public String ENABLE_AUTO_UPDATE = ENABLE + AUTO_UPDATE;
    public String DISABLE_AUTO_UPDATE = DISABLE + AUTO_UPDATE;
    public String ENABLE_HOOK_UPDATE = ENABLE + HOOK_UPDATE;
    public String DISABLE_HOOK_UPDATE = DISABLE + HOOK_UPDATE;
    public String REFRESHED = "已刷新  (‵▽′)/";
    public String NEW_UPDATE_AVILABLE = "更新可用";
    public String NO_UPDATE = "暂无可用更新";
    public String ENABLED = "已启用";
    public String DISABLED = "已禁用";
    public String UNDEFINE = "暂未设定";
    public String UNDEFINED = "暂未设定";
    public String UNKNOWN = "未知";
    public String STATUS_ENABLE = ENABLE;
    public String STATUS_DISABLE = DISABLE;
    public String RESTARTING_WARN = "机器人正重启, 请稍候再试.";
    public String RESTARTING = "正在重启 →_→";
    public String UPDATING = "正在更新 (≧ω≦)";
    public String SERVER_CLOSING = "不知何时何故地, 出现了问题.";
    public String UPDATE_FAILED = "更新失败 _(:з」∠)_\n\n{}";
    public String ERR_PERSIONAL = "本实例未公开开放此功能.";
    public String ERR_LIMIT = "到达机器人创建上限.";
    public String ERR_LOGGING_OUT = "机器人被登出.";
    public String BOT_NONE = "还没有创建过机器人.";
    public String BOT_CH = "从以下列表选择机器人.";
    public String BOT_INVALID = "机器人无效";
    public String BOT_MANAGE = "管理机器人";
    public String BOT_STATUS = "机器人状态";
    public String BOT_ID = "机器人ID";
    public String BOT_STATUS_UNSTARTED = "未启动";
    public String BOT_STATUS_RUNNING = "正常";
    public String BOT_STATUS_ERROR = "出错";
    public String BOT_ERROR_MESSAGE = "错误消息";
    public String BOT_DELETE = "删除";
    public String BOT_DELETE_WARN = "真的要删除吗？您会失去它，真的很久！";
    public String BOT_DELETE_CONFIRM = "是的，我确定！";
    public String BOT_NEW = "创建新机器人";
    public String BOT_INPUT_TOKEN = "输入 " + Fn.a("BotToken", "https://core.telegram.org/bots#creating-a-new-bot") + " :";
    public String BOT_TOKEN_INVALID = "无效的BotToken, 创建已取消 ({}).";
    public String BOT_EXISTS = "该机器人已存在 (本实例中), 请删除后重新创建.";
    public String BOT_N_TYPE = "下一步 : 选择机器人类型.";
    public String BOT_TYPE_PM = "私聊机器人";
    public String BOT_ERR = "机器人出错停止, 请检查 : {}";
    public String BOT_CREATED = "机器人已创建. 请点 " + Fn.a("此", "https://t.me/{}?start=-init") + " 配置.";
    public String PM_WELCOME = "你好！\n\n你可以通过这个机器人联系我(们).";
    public String PM_ON_START = "{} 开启了私聊机器人 ({}).";
    public String PM_ON_PAYLOAD = "{} 使用 payload '{}'开启了机器人.";
    public String PM_OK = "机器人正常运行. 请勿停用本机器人, 否则将无法收到消息.  " + Fn.a("私聊机器人文档", "https://gitlab" + ".com/tooko/tooko-bot/wikis/Bots/PmBot");
    public String PM_NO_WELCOME_MESSAGE = "默认欢迎消息";
    public String PM_WELCOME_MESSAGE = "欢迎消息 : 已设定 {} 条";
    public String PM_WELCOME_NOTICE = "打开通知";
    public String PM_WELCOME_INPUT = "输入任意消息 :\n\n使用 /submit 完成设定\n使用 /cancel 取消设定";
    public String PM_WELCOME_ADDED = "消息已添加.\n\n使用 /submit 完成设定\n使用 /cancel 取消设定";
    public String PM_WELCOME_FD_WARN = "无法存储的消息类型, 或消息为投票 / 转发! 请确保机器人可以访问这条消息, 否则这条消息无法转发给接受者 (不要删除这条消息！)\n\n提示 : " + "如果需要发布对他人的匿名投票, 可以先在一个频道里发布投票, 然后转发给机器人作为消息, 这样就不会暴露身份了.";
    public String PM_WELCOME_FINISH = "消息已设定.";
    public String PM_PAYLOADS = "子欢迎消息列表";
    public String PM_PAYLOAD = "子欢迎消息";
    public String PM_PAYLOAD_INVALID = "无效的 Payload : 仅允许 [a-zA-Z_-] 且最多64字.";
    public String USER_ID = "用户ID";
    public String USER_ID_NOT_FOUND = "找不到 ID {} 对应的用户.";
    public String USER_NAME = "用户名称";
    public String PM_DELETE_MESSAGE = "删除这条消息";
    public String PM_DELETED_RECEIVED_FROM = "已删除来自 {} 的 {} 条消息";
    public String PM_DELETED_SENDED_FROM = "已删除发送至 {} 的 {} 条消息";
    public String PM_DELETED_RECEIVED = "已删除 {} 条收到的消息";
    public String PM_DELETED_SENDED = "已删除 {} 条发出的消息";
    public String PM_DELETED_BY = "这条消息已被对方删除.";
    public String EDIT = "修改";
    public String SUCCEED = "成功";
    public String FAILED = "失败";
    public String EDIT_SUCCEED = EDIT + SUCCEED;
    public String EDIT_FAILED = EDIT + FAILED + " : ";
    public String EDIT_MESSAGE_INVALID = EDIT + FAILED + " : 目标消息已不存在";
    public String EDIT_TYPE_INVALID = EDIT + FAILED + " : 消息类型未识别";
    public String EDITED_BY = "消息被对方修改, 新内容 : ";
    public String NO_ENTERED_CHAT = "当前没有进入会话";
    public String INVALID_REPLY = "在回复的消息中没有找到对应聊天.";
    public String INVALID_CHAT_ID = "提供的会话ID/用户引用无效.";
    public String REPLY_OR_ID = "用法 : 对消息使用 / 使用会话ID作为参数";

    // public String AMBIGOUS_CALL = "模棱两可的调用方式 : 既对消息回复, 又设置了参数.";
    public String GET_CHAT_FAILED = "读取目标会话失败 : {}";
    public String NO_PRIVATE_WARN = "失败 : 这不是一个私聊会话.";
    public String CHAT_ENTERED = "已进入会话 : {}";
    public String CHAT_EXITED = "已退出会话.";
    public String NO_CHAT_ENTERED = "当前没有进入任何会话.";
    public String CHAT_NO_RECORD = "没有该对话的记录.";
    public String CHAT_MANAGE = "会话设定";
    public String CHAT_MANAGE_HELP = "管理这个会话 : 使用清除选项可批量删除聊天消息.";
    public String CHAT_MSG_SENDED = "发出消息 : {} 条";
    public String CHAT_MSG_RECEIVED = "收到消息 : {} 条";
    public String CHAT_MSG_DEL_RECEIVED = "删除已收消息";
    public String CHAT_MSG_DEL_SENDED = "删除已发消息";
    public String CHAT_MSG_DEL_ALL = "删除所有消息";
    public String CHAT_IS_BLOCKED = "<b>「 已屏蔽 」</b>";
    public String CHAT_BLOCK = "屏蔽该会话";
    public String CHAT_UNBLOCK = "取消屏蔽该会话";
    public String CHAT = "会话";
    public String CHAT_ID = "会话ID";
    public String CHAT_NOT_FOUND = "找不到这个会话.";
    public String CHAT_BLOCKED = "已屏蔽";
    public String CHAT_UNBLOCKED = "已取消";
    public String CHAT_EMPTY = "没有消息以删除";
    public String FINISH_MANAGE = "完成" + CHAT_MANAGE;
    public String SEND_FAILED = "消息发送失败, 已退出会话 : {}";
    public String SEND_SUCCEED = "发送成功";

    public String SETTED = "已设定.";

    public String TWI_HELP =

            "这里是流人. 使用前请阅读 " + wikiLink("Wiki", "Bots/TwitterBot") + " .\n\n";


    public String TWI_ACCOUNT = "账号";

    public String TWI_NEW_FOLLOWERS = "新增跟随者";
    public String TWI_LOST_FOLLOWERS = "失去跟随者";

    public String TWI_JOIN_AT = "于 {} 加入";
    public String TWI_JOIN_FORMAT = "yyyy年MM月";

    public String TWI_NAME_HISTORY = "历史名称";
    public String TWI_SN_HISTORY = "历史用户名";

    public String TWI_FRIENDS = "个跟随中";
    public String TWI_FOLLOWERS = "个跟随者";
    public String TWI_FOLLOWING = "正在跟随";

    public String TWI_ACC_DELETED = "账号已删除";
    public String TWI_ACC_SUSPENDED = "账号被冻结";

    public String TWI_TWUF = "双向取消关注";
    public String TWI_OWUF = "单向取消关注";

    public String TWI_CH_API = "选择接口";
    public String TWI_CUS_API = "自定义";
    public String TWI_CH_TOKEN = "选择认证类型";


    public String TWI_API_KEY = "Api Key ";
    public String TWI_API_SECRET_KEY = "Api Secret Key";
    public String TWI_ACCESS_TOKEN = "Access Token";
    public String TWI_ACCESS_TOKEN_SECRET = "Access Token Secret";

    public String TWI_INPUT_API_KEY = "请输入 Api Key : ";
    public String TWI_INPUT_API_SECRET_KEY = "请输入 Api Secret Key : ";
    public String TWI_INPUT_ACCESS_TOKEN = "请输入 Access Token : ";
    public String TWI_INPUT_ACCESS_TOKEN_SECRET = "请输入 Access Token Secret : ";

    public String TWI_CH_LT = "请选择认证方式 : ";
    public String TWI_OAUTH = "OAuth";
    public String TWI_AUTH_INPUT_CODE = "授权链接 : {} \n\n请输入授权完成后取得的七位PIN码 :";
    public String TWI_AUTHED_BY = "你的账号 {} 已被 {} 认证, 该账号已从你的账号列表移除.";
    public String TWI_WELCOME = "欢迎, {}.";
    public String TWI_AUTH_REQUIRED = "这个命令需要 /login 授权账号才能使用.";

    public String TWI_CH_ACCOUNT = "选择用于执行该操作的账号 :";
    public String TWI_ANF = "找不到这个账号.";
    public String TWI_CHOSED = "已选择 {}.";
    public String TWI_LOGOUT = "账号已移除.";

    public String TWI_ACC_MNG = "管理您的授权";

    public String TWI_TRACK_MNG = "修改关注者变化通知";
    public String TWI_TRACK_ENABLE = "关注者变化通知";

    public String TWI_ACC_EXPORT = "导出";

    public String TWI_ACC_TRACK = "关注者变化";

    public String TWI_INPUT_CSV = "请直接发送 .csv 文件\n或使用链接 : /{} <URL>";
    public String TWI_NOT_CSV_FILE = "请发送 <b>.csv 文件</b> 或 <b>文件链接</b> .";
    public String TWI_INVALID_CSV_FILE = "无效的 .csv 文件 : {} .";

    public String TWI_INVALID_CSV_LINK = "无效的链接 .";

    public String DOWNLOAD_FIALD = "文件下载失败 : {} .";

    public String WAITING_LAST_FUNCTION = "请等待上一条命令执行完成.";

    public String TWI_REMOVING = "正在移除";
    public String TWI_ADDING = "正在添加";

    public String TWI_BL_FINISH = "完成 屏蔽了 {} 人 (๑•̀ㅂ•́)√";
    public String TWI_UB_FINISH = "完成 取消屏蔽了 {} 人 (๑•̀ㅂ•́)√";
    public String TWI_UM_FINISH = "完成 取消静音了 {} 人 (๑•̀ㅂ•́)√";
    public String TWI_LIST_EMPTY = "错误 : 列表为空";
    public String TWI_LIST_BUCKUP = "这是被执行列表的备份 : 如果进行了误操作, 您可以恢复它.";

    public String CANCELED = "操作已取消.";
    public String TIMEOUTED = "操作超时, 已取消.";

    public String TWI_ERR = "错误 : ";
    public String TWI_ERR_32 = "Api Key 无效.";
    public String TWI_ERR_50 = "用户找不到.";
    public String TWI_ERR_63 = "用户被冻结.";
    public String TWI_ERR_64 = "账号被冻结.";
    public String TWI_ERR_88_205 = "请求频繁 稍后再试.";
    public String TWI_ERR_89_99 = "Access Token 无效.";
    public String TWI_ERR_93 = "Api 权限不够.";
    public String TWI_ERR_130 = "Twitter 服务器超载.";
    public String TWI_ERR_131 = "Twitter 炸了.";
    public String TWI_ERR_135 = "服务器时间出现了偏差.";
    public String TWI_ERR_139 = "已经喜欢过了.";
    public String TWI_ERR_144 = "推文找不到.";
    public String TWI_ERR_150 = "没有被对方关注.";
    public String TWI_ERR_160 = "已发送过关注请求.";
    public String TWI_ERR_161 = "关注过快.";
    public String TWI_ERR_179 = "无权查看.";
    public String TWI_ERR_185 = "到达每日发送上限.";
    public String TWI_ERR_186_354 = "文本太长.";
    public String TWI_ERR_187 = "推文重复.";
    public String TWI_ERR_220 = "Access Token 被限制.";
    public String TWI_ERR_226_326 = "被判定为自动操作.";
    public String TWI_ERR_261 = "Api 没有写权限.";
    public String TWI_ERR_349 = "无权私聊.";
    public String TWI_ERR_385 = "回复的推文已被删除或无权查看.";

    public String TWI_HTTP_304 = "内容未改变.";
    public String TWI_HTTP_400 = "非法请求.";
    public String TWI_HTTP_401 = "无效的认证信息.";
    public String TWI_HTTP_403 = "无权查看.";
    public String TWI_HTTP_404 = "内容不存在.";
    public String TWI_HTTP_420 = "全局请求频繁.";
    public String TWI_HTTP_422 = "内容无效.";
    public String TWI_HTTP_429 = "请求频繁.";
    public String TWI_HTTP_500_502_504 = "Twitter 炸了.";
    public String TWI_HTTP_503 = "Twitter 服务器超载.";

    public String TRANSFER_INPUT_CONCACT = "请发送名片 : ( 右上角 -> 分享我的联系方式 )";
    public String TRANSFER_INPUT_CODE = "请输入验证码 ( 可以直接转发验证码消息 ) : ";
    public String TRANSFER_INPUT_PASSWORD = "请输入密码 : ";

    public String LICENSE =

            "远子 基于 " + Fn.a("Apache License 2.0", "https://gitlab.com/tooko/tooko-bot/blob/master/LICENSE") + " 发行, " + "欢迎来 " + Fn.a("GitLab", "https://gitlab.com/tooko/tooko-bot") + " 访问我们." + "\n\n" +

                    wikiLink("文档主页", "Home") + " | " + wikiLink("安装说明", "Install") + " | " + wikiLink("命令与更新", "Usage") + " | " + wikiLink("语言与翻译", "Trans") + "\n\n" +

                    "子机器人文档 : " + wikiLink("Twitter", "Bots/TwitterBot") + "\n\n" +

                    "机器人文档 : " + wikiLink("私聊机器人", "Bots/PmBot");

    public Lang() {

        this("简体中文");

    }

    public Lang(String name) {

        LANG_NAME = name;
    }

    static String wikiLink(String name, String link) {

        return Fn.a(name, "https://gitlab.com/tooko/tooko-bot/wikis/" + link);

    }

    public static Lang get(TdApi.User user) {

        return user == null ? DEFAULT : get(user.id);

    }

    public static Lang get(Number userId) {

        DB config = DATA.getById(userId.intValue());

        if (config == null) return DEFAULT;

        int lang = config.lang;

        if (lang == 0) {

            return DEFAULT;

        } else if (lang == 1) {

            return ENG.INSTANCE;

        }

        return DEFAULT;


    }

    public static class DB {

        public int id;
        public int lang;

        public DB() {

        }

        public DB(int id, int lang) {

            this.id = id;
            this.lang = lang;
        }

    }

}
