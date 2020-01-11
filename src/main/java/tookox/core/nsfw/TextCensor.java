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

package tookox.core.nsfw;

import cn.hutool.json.*;
import com.baidu.aip.contentcensor.*;
import tookox.core.env.*;

public abstract class TextCensor {

    private static TextCensor INSTANCE;

    public static TextCensor getInstance() {

        if (INSTANCE != null) return INSTANCE;

        if ("baidu".equals(Env.TEXT_CENSOR_PROVIDER)) {

            INSTANCE = new BaiduAntiSpam(Env.BAIDU_APP_ID, Env.BAIDU_API_KEY, Env.BAIDU_SECRET_KEY);

        } else {

            INSTANCE = new None();

        }

        return INSTANCE;

    }

    public abstract String predictRaw(String text);

    public abstract TCRC parseRaw(String raw);

    public TCRC predictText(String text) {

        return parseRaw(predictRaw(text));

    }

    public static class None extends TextCensor {

        @Override
        public String predictRaw(String text) {

            return null;

        }

        @Override
        public TCRC parseRaw(String raw) {

            return null;

        }

    }

    public static class BaiduAntiSpam extends TextCensor {

        public String appId;
        public String apiKey;
        public String secretKey;

        public AipContentCensor censor;

        public BaiduAntiSpam(String appId, String apiKey, String secretKey) {

            this.appId = appId;
            this.apiKey = apiKey;
            this.secretKey = secretKey;

            this.censor = new AipContentCensor(appId, apiKey, secretKey);

        }

        @Override
        public String predictRaw(String text) {

            JSONObject result = new JSONObject(censor.antiSpam(text, null).getJSONObject("result").toString());

            return result.toStringPretty();

        }

        @Override
        public TCRC parseRaw(String raw) {

            JSONObject result = new JSONObject(raw);

            if (result.getInt("spam") == 1) {

                JSONArray reject = result.getJSONArray("reject");

                //                reject.addAll(result.getJSONArray("review"));

                boolean politics = false, spam = false, porn = false, ad = false;

                for (int index = 0; index < reject.size(); index++) {

                    JSONObject label = reject.getJSONObject(index);

                    int labelIndex = reject.getJSONObject(index).getInt("label");
                    double score = reject.getJSONObject(index).getDouble("score");

                    if (labelIndex == 1 || labelIndex == 3) {

                        politics = true;

                    } else if (labelIndex == 2) {

                        porn = true;

                    } else if (labelIndex == 4) {

                        ad = true;

                    } else if (labelIndex == 5) {

                        spam = true;

                    }

                    if (politics) return TCRC.POLITICS;
                    else if (spam) return TCRC.SPAM;
                    else if (ad) return TCRC.AD;
                    else if (porn) return TCRC.PORN;

                }

            }

            return null;

        }

    }

}
