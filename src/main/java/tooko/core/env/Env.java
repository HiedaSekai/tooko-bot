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

package tooko.core.env;

import cn.hutool.core.util.*;
import com.mongodb.client.*;

import java.io.*;
import java.util.concurrent.atomic.*;

public class Env {

    public static AtomicBoolean STOP = new AtomicBoolean(false);

    public static File ROOT_PATH = new File(".");
    public static boolean PUBLIC_BOT_CREATE;
    public static int BOT_CREATE_MAX;
    public static String PASSWORD;
    public static String BOT_TOKEN;
    public static String DEF_LANG;
    public static String[] FUN_PREFIX;
    public static MongoClient DB_CLIENT;
    public static MongoDatabase DB;
    public static int[] ADMINS;
    public static Long LOG_CHANNEL;
    public static boolean USE_SERVICE;
    public static String SERVICE;
    public static boolean USE_TEST_DC;
    public static String TWITTER_BOT_TOKEN;
    public static boolean TWITTER_PUBLIC;
    //  public static ApiToken[] TWITTER_API_TOKENS;
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
