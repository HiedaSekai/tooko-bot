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

package tookox.core.env;

import td.*;
import tookox.core.db.*;

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
    public String LANG_NAME,

    LAUNCH,
            HELP,
            PUBLIC_WARN,
            LICENSE,
            SPLIT,
            SPLIT_END,
            INVALID_TIME,
            DELAY_TOO_SHORT,
            CHOOSE_LANG,
            LANG_SELECTED,
            FN_PING_RESULT,
            FORWARD_FROM_USER,
            FORWARD_FROM_HIDDEN_USER,
            FORWARD_FROM_CHANNEL,
            CHANNEL_RESTRICTIONED,
            AUTHOR_SIGNATURE,
            MESSAGE_ID,
            MESSAGE_LINK,
            STICKER_CAPTION,
            STICKER_EXPORT,
            STICKER_EXPORT_DL,
            STICKER_DL_FAILED,
            STICKER_EXPORT_PACK,
            STICKER_EXPORT_SEND,
            PERMISSION_DENIED,
            BACK,
            CNF,
            ENABLE,
            DISABLE,
            STATUS_ENABLE,
            STATUS_DISABLE,
            DELETED,
            REFRESH,
            REFRESHED,
            ENABLED,
            DISABLED,
            UNDEFINE,
            UNDEFINED,
            UNKNOWN,
            SERVER_CLOSING,
            ERR_PERSIONAL,
            ERR_LIMIT,
            ERR_LOGGING_OUT,
            BOT_NONE,
            BOT_CH,
            BOT_INVALID,
            BOT_MANAGE,
            BOT_STATUS,
            BOT_ID,
            BOT_STATUS_UNSTARTED,
            BOT_STATUS_RUNNING,
            BOT_STATUS_ERROR,
            BOT_ERROR_MESSAGE,
            BOT_DELETE,
            BOT_DELETE_WARN,
            BOT_DELETE_CONFIRM,
            BOT_NEW,
            BOT_INPUT_TOKEN,
            BOT_TOKEN_INVALID,
            BOT_EXISTS,
            BOT_N_TYPE,
            BOT_TYPE_PM,
            BOT_ERR,
            BOT_CREATED;

    public String AGENT_CHT,
            AGENT_IMPORT,
            AGENT_INPUT_DB,
            AGENT_INPUT_BINLOG,
            AGENT_AUTHING,
            AGENT_AUTH_INVALID,
            AGENT_AUTH_OK,
            AGENT_LOGIN;

    public String PM_WELCOME,
            PM_ON_START,
            PM_ON_PAYLOAD,
            PM_OK,
            PM_NO_WELCOME_MESSAGE,
            PM_WELCOME_MESSAGE,
            PM_WELCOME_NOTICE,
            PM_WELCOME_INPUT,
            PM_WELCOME_ADDED,
            PM_WELCOME_FD_WARN,
            PM_WELCOME_FINISH,
            PM_PAYLOADS,
            PM_PAYLOAD,
            PM_PAYLOAD_INVALID,
            PM_PAYLOAD_UNDEFINED,
            USER_ID,
            USER_ID_NOT_FOUND,
            USER_NAME,
            PM_DELETE_MESSAGE,
            PM_DELETED_RECEIVED_FROM,
            PM_DELETED_SENDED_FROM,
            PM_DELETED_RECEIVED,
            PM_DELETED_SENDED,
            PM_DELETED_BY,
            EDIT,
            SUCCEED,
            FAILED,
            EDIT_SUCCEED,
            EDIT_FAILED,
            EDIT_MESSAGE_INVALID,
            EDIT_TYPE_INVALID,
            EDITED_BY,
            NO_ENTERED_CHAT,
            INVALID_REPLY,
            INVALID_CHAT_ID,
            REPLY_OR_ID,
            GET_CHAT_FAILED,
            NO_PRIVATE_WARN,
            CHAT_ENTERED,
            CHAT_EXITED,
            NO_CHAT_ENTERED,
            CHAT_NO_RECORD,
            CHAT_MANAGE,
            CHAT_MANAGE_HELP,
            CHAT_MSG_SENDED,
            CHAT_MSG_RECEIVED,
            CHAT_MSG_DEL_RECEIVED,
            CHAT_MSG_DEL_SENDED,
            CHAT_MSG_DEL_ALL,
            CHAT_IS_BLOCKED,
            CHAT_BLOCK,
            CHAT_UNBLOCK,
            CHAT,
            CHAT_ID,
            CHAT_NOT_FOUND,
            CHAT_BLOCKED,
            CHAT_UNBLOCKED,
            CHAT_EMPTY,
            FINISH_MANAGE,
            SEND_FAILED,
            SEND_SUCCEED,
            SETTED;

    public String TWI_HELP,
            TWI_ACCOUNT,
            TWI_NEW_FOLLOWERS,
            TWI_LOST_FOLLOWERS,
            TWI_JOIN_AT,
            TWI_JOIN_FORMAT,
            TWI_NAME_HISTORY,
            TWI_SN_HISTORY,
            TWI_FRIENDS,
            TWI_FOLLOWERS,
            TWI_FOLLOWING,
            TWI_ACC_DELETED,
            TWI_ACC_SUSPENDED,
            TWI_TWUF,
            TWI_OWUF,
            TWI_CH_API,
            TWI_CUS_API,
            TWI_CH_TOKEN,
            TWI_API_KEY,
            TWI_API_SECRET_KEY,
            TWI_ACCESS_TOKEN,
            TWI_ACCESS_TOKEN_SECRET,
            TWI_INPUT_API_KEY,
            TWI_INPUT_API_SECRET_KEY,
            TWI_INPUT_ACCESS_TOKEN,
            TWI_INPUT_ACCESS_TOKEN_SECRET,
            TWI_CH_LT,
            TWI_OAUTH,
            TWI_AUTH_INPUT_CODE,
            TWI_AUTHED_BY,
            TWI_WELCOME,
            TWI_AUTH_REQUIRED,
            TWI_CH_ACCOUNT,
            TWI_ANF,
            TWI_CHOSED,
            TWI_LOGOUT,
            TWI_ACC_MNG,
            TWI_TRACK_MNG,
            TWI_TRACK_ENABLE,
            TWI_ACC_EXPORT,
            TWI_ACC_TRACK,
            TWI_INPUT_CSV,
            TWI_NOT_CSV_FILE,
            TWI_INVALID_CSV_FILE,
            TWI_INVALID_CSV_LINK,
            DOWNLOAD_FIALD,
            WAITING_LAST_FUNCTION,
            TWI_REMOVING,
            TWI_ADDING,
            TWI_BL_FINISH,
            TWI_UB_FINISH,
            TWI_UM_FINISH,
            TWI_LIST_EMPTY,
            TWI_LIST_BUCKUP,
            CANCELED,
            TIMEOUTED,
            TWI_ERR,
            TWI_ERR_32,
            TWI_ERR_50,
            TWI_ERR_63,
            TWI_ERR_64,
            TWI_ERR_88_205,
            TWI_ERR_89_99,
            TWI_ERR_93,
            TWI_ERR_130,
            TWI_ERR_131,
            TWI_ERR_135,
            TWI_ERR_139,
            TWI_ERR_144,
            TWI_ERR_150,
            TWI_ERR_160,
            TWI_ERR_161,
            TWI_ERR_179,
            TWI_ERR_185,
            TWI_ERR_186_354,
            TWI_ERR_187,
            TWI_ERR_220,
            TWI_ERR_226_326,
            TWI_ERR_261,
            TWI_ERR_349,
            TWI_ERR_385,
            TWI_HTTP_304,
            TWI_HTTP_400,
            TWI_HTTP_401,
            TWI_HTTP_403,
            TWI_HTTP_404,
            TWI_HTTP_420,
            TWI_HTTP_422,
            TWI_HTTP_429,
            TWI_HTTP_500_502_504,
            TWI_HTTP_503;

}
