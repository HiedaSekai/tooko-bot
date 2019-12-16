package tooko.main.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baidu.aip.contentcensor.AipContentCensor;
import tooko.main.Env;

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

    public enum TCRC {

        POLICES,
        SPAM,
        PORN

    }

    public abstract TCRC predictText(String text);

    public static class None extends TextCensor {

        @Override
        public TCRC predictText(String text) {
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
        public TCRC predictText(String text) {

            JSONObject result = censor.antiSpam(text, null).getJSONObject("result");

            if (result != null && result.getInt("spam") == 1) {

                JSONArray reject = result.getJSONArray("reject");

//                reject.addAll(result.getJSONArray("review"));

                boolean politics = false, spam = false, porn = false;
                double spamScore = 0, adScore = 0, pornScore = 0;

                for (int index = 0; index < reject.size(); index++) {

                    JSONObject label = reject.getJSONObject(index);

                    int labelIndex = reject.getJSONObject(index).getInt("label");
                    double score = reject.getJSONObject(index).getDouble("score");

                    if (labelIndex == 1 || labelIndex == 3) {

                        politics = true;

                    } else if (labelIndex == 2) {

                        porn = true;

                        pornScore = score;

                    } else if (labelIndex == 4) {

                        adScore = score;

                    } else if (labelIndex == 5) {

                        spam = true;

                        spamScore = score;

                    }

                    if (politics) return TCRC.POLICES;
                    else if (spamScore > pornScore) return TCRC.SPAM;
                    else if (porn || spamScore < adScore) return TCRC.PORN;
                    else if (spam) return TCRC.SPAM;

                }

            }

            return null;

        }

    }

}
