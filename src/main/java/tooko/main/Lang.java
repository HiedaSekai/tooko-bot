package tooko.main;

import tooko.td.*;
import tooko.td.core.*;

import java.lang.reflect.*;
import java.util.*;

public class Lang {

    public static CacheTable<Integer, DB> DATA = new CacheTable<>("lang", DB.class);

    public static HashMap<Integer, Lang> ALL = new HashMap<>();
    public static HashMap<String, Lang> BY_NAME = new HashMap<>();

    public static Lang INVALID;
    public static Lang DEFAULT;

    static {

        INVALID = new Lang();

        INVALID.LANG_ID = -1;

        for (Field field : Lang.class.getFields()) {

            if (field.getDeclaringClass().equals(String.class)) {

                try {

                    field.set(INVALID, "Error : Load Language Field");

                } catch (IllegalAccessException ignored) {
                }

            }

        }

    }

    public static Lang get(TdApi.User user) {

        return get(user.id);

    }

    public static Lang get(Number userId) {

        DB config = DATA.getById(userId.intValue());

        if (config != null && ALL.containsKey(config.lang)) return ALL.get(config.lang);

        if (DEFAULT != null) return DEFAULT;

        return INVALID;

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

    public int LANG_ID;
    public String LANG_NAME;

    public String LAUNCH;
    public String HELP;
    public String PUBLIC_WARN;
    public String LICENSE;
    public String SPLIT;
    public String SPLIT_END;
    public String INVALID_TIME;
    public String DELAY_TOO_SHORT;
    public String CHOOSE_LANG;
    public String LANG_SELECTED;
    public String FN_PING_RESULT;
    public String FORWARD_FROM_USER;
    public String FORWARD_FROM_HIDDEN_USER;
    public String FORWARD_FROM_CHANNEL;
    public String CHANNEL_RESTRICTIONED;
    public String AUTHOR_SIGNATURE;
    public String MESSAGE_ID;
    public String MESSAGE_LINK;
    public String STICKER_CAPTION;
    public String STICKER_EXPORT;
    public String STICKER_EXPORT_DL;
    public String STICKER_DL_FAILED;
    public String STICKER_EXPORT_PACK;
    public String STICKER_EXPORT_SEND;
    public String PERMISSION_DENIED;
    public String BACK;
    public String CNF;
    public String ENABLE;
    public String DISABLE;
    public String STATUS_ENABLE;
    public String STATUS_DISABLE;
    public String DELETED;
    public String REFRESH;
    public String REFRESHED;
    public String ENABLED;
    public String DISABLED;
    public String UNDEFINE;
    public String UNDEFINED;
    public String UNKNOWN;
    public String SERVER_CLOSING;
    public String ERR_PERSIONAL;
    public String ERR_LIMIT;
    public String ERR_LOGGING_OUT;
    public String BOT_NONE;
    public String BOT_CH;
    public String BOT_INVALID;
    public String BOT_MANAGE;
    public String BOT_STATUS;
    public String BOT_ID;
    public String BOT_STATUS_UNSTARTED;
    public String BOT_STATUS_RUNNING;
    public String BOT_STATUS_ERROR;
    public String BOT_ERROR_MESSAGE;
    public String BOT_DELETE;
    public String BOT_DELETE_WARN;
    public String BOT_DELETE_CONFIRM;
    public String BOT_NEW;
    public String BOT_INPUT_TOKEN;
    public String BOT_TOKEN_INVALID;
    public String BOT_EXISTS;
    public String BOT_N_TYPE;
    public String BOT_TYPE_PM;
    public String BOT_ERR;
    public String BOT_CREATED;
    public String PM_WELCOME;
    public String PM_ON_START;
    public String PM_ON_PAYLOAD;
    public String PM_OK;
    public String PM_NO_WELCOME_MESSAGE;
    public String PM_WELCOME_MESSAGE;
    public String PM_WELCOME_NOTICE;
    public String PM_WELCOME_INPUT;
    public String PM_WELCOME_ADDED;
    public String PM_WELCOME_FD_WARN;
    public String PM_WELCOME_FINISH;
    public String PM_PAYLOADS;
    public String PM_PAYLOAD;
    public String PM_PAYLOAD_INVALID;
    public String USER_ID;
    public String USER_ID_NOT_FOUND;
    public String USER_NAME;
    public String PM_DELETE_MESSAGE;
    public String PM_DELETED_RECEIVED_FROM;
    public String PM_DELETED_SENDED_FROM;
    public String PM_DELETED_RECEIVED;
    public String PM_DELETED_SENDED;
    public String PM_DELETED_BY;
    public String EDIT;
    public String SUCCEED;
    public String FAILED;
    public String EDIT_SUCCEED;
    public String EDIT_FAILED;
    public String EDIT_MESSAGE_INVALID;
    public String EDIT_TYPE_INVALID;
    public String EDITED_BY;
    public String NO_ENTERED_CHAT;
    public String INVALID_REPLY;
    public String INVALID_CHAT_ID;
    public String REPLY_OR_ID;
    public String GET_CHAT_FAILED;
    public String NO_PRIVATE_WARN;
    public String CHAT_ENTERED;
    public String CHAT_EXITED;
    public String NO_CHAT_ENTERED;
    public String CHAT_NO_RECORD;
    public String CHAT_MANAGE;
    public String CHAT_MANAGE_HELP;
    public String CHAT_MSG_SENDED;
    public String CHAT_MSG_RECEIVED;
    public String CHAT_MSG_DEL_RECEIVED;
    public String CHAT_MSG_DEL_SENDED;
    public String CHAT_MSG_DEL_ALL;
    public String CHAT_IS_BLOCKED;
    public String CHAT_BLOCK;
    public String CHAT_UNBLOCK;
    public String CHAT;
    public String CHAT_ID;
    public String CHAT_NOT_FOUND;
    public String CHAT_BLOCKED;
    public String CHAT_UNBLOCKED;
    public String CHAT_EMPTY;
    public String FINISH_MANAGE;
    public String SEND_FAILED;
    public String SEND_SUCCEED;
    public String SETTED;
    public String TWI_HELP;
    public String TWI_ACCOUNT;
    public String TWI_NEW_FOLLOWERS;
    public String TWI_LOST_FOLLOWERS;
    public String TWI_JOIN_AT;
    public String TWI_JOIN_FORMAT;
    public String TWI_NAME_HISTORY;
    public String TWI_SN_HISTORY;
    public String TWI_FRIENDS;
    public String TWI_FOLLOWERS;
    public String TWI_FOLLOWING;
    public String TWI_ACC_DELETED;
    public String TWI_ACC_SUSPENDED;
    public String TWI_TWUF;
    public String TWI_OWUF;
    public String TWI_CH_API;
    public String TWI_CUS_API;
    public String TWI_CH_TOKEN;
    public String TWI_API_KEY;
    public String TWI_API_SECRET_KEY;
    public String TWI_ACCESS_TOKEN;
    public String TWI_ACCESS_TOKEN_SECRET;
    public String TWI_INPUT_API_KEY;
    public String TWI_INPUT_API_SECRET_KEY;
    public String TWI_INPUT_ACCESS_TOKEN;
    public String TWI_INPUT_ACCESS_TOKEN_SECRET;
    public String TWI_CH_LT;
    public String TWI_OAUTH;
    public String TWI_AUTH_INPUT_CODE;
    public String TWI_AUTHED_BY;
    public String TWI_WELCOME;
    public String TWI_AUTH_REQUIRED;
    public String TWI_CH_ACCOUNT;
    public String TWI_ANF;
    public String TWI_CHOSED;
    public String TWI_LOGOUT;
    public String TWI_ACC_MNG;
    public String TWI_TRACK_MNG;
    public String TWI_TRACK_ENABLE;
    public String TWI_ACC_EXPORT;
    public String TWI_ACC_TRACK;
    public String TWI_INPUT_CSV;
    public String TWI_NOT_CSV_FILE;
    public String TWI_INVALID_CSV_FILE;
    public String TWI_INVALID_CSV_LINK;
    public String DOWNLOAD_FIALD;
    public String WAITING_LAST_FUNCTION;
    public String TWI_REMOVING;
    public String TWI_ADDING;
    public String TWI_BL_FINISH;
    public String TWI_UB_FINISH;
    public String TWI_UM_FINISH;
    public String TWI_LIST_EMPTY;
    public String TWI_LIST_BUCKUP;
    public String CANCELED;
    public String TIMEOUTED;
    public String TWI_ERR;
    public String TWI_ERR_32;
    public String TWI_ERR_50;
    public String TWI_ERR_63;
    public String TWI_ERR_64;
    public String TWI_ERR_88_205;
    public String TWI_ERR_89_99;
    public String TWI_ERR_93;
    public String TWI_ERR_130;
    public String TWI_ERR_131;
    public String TWI_ERR_135;
    public String TWI_ERR_139;
    public String TWI_ERR_144;
    public String TWI_ERR_150;
    public String TWI_ERR_160;
    public String TWI_ERR_161;
    public String TWI_ERR_179;
    public String TWI_ERR_185;
    public String TWI_ERR_186_354;
    public String TWI_ERR_187;
    public String TWI_ERR_220;
    public String TWI_ERR_226_326;
    public String TWI_ERR_261;
    public String TWI_ERR_349;
    public String TWI_ERR_385;
    public String TWI_HTTP_304;
    public String TWI_HTTP_400;
    public String TWI_HTTP_401;
    public String TWI_HTTP_403;
    public String TWI_HTTP_404;
    public String TWI_HTTP_420;
    public String TWI_HTTP_422;
    public String TWI_HTTP_429;
    public String TWI_HTTP_500_502_504;
    public String TWI_HTTP_503;

}
