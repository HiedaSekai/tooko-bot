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

    public static class TCRC {

        private Boolean politics;
        private Boolean spam;
        private Boolean porn;

        public TCRC() {
        }

        public TCRC(Boolean politics, Boolean spam, Boolean porn) {
            this.politics = politics ? true : null;
            this.spam = spam ? true : null;
            this.porn = porn ? true : null;
        }

        public boolean isPolitics() {
            return politics != null;
        }

        public boolean isSpam() {
            return spam != null;
        }

        public boolean isPorn() {
            return porn != null;
        }

    }

    public abstract TCRC predictText(String text);

    public static class None extends TextCensor {

        @Override
        public TCRC predictText(String text) {
            return new TCRC(false, false, false);
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

                boolean politics = false,spam = false,porn = false;

                for (int index = 0;index < reject.size();index ++) {

                    int label = reject.getJSONObject(index).getInt("label");

                    if (label == 1 || label == 3) {

                        politics = true;

                    } else if (label == 2) {

                        porn = true;

                    } else if (label != 4) {

                        spam = true;

                    }

                }

                return new TCRC(politics, spam, porn);

            }

            return new TCRC(false, false, false);

        }

    }

}
