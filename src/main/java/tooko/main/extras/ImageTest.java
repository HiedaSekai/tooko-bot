package tooko.main.extras;

import com.google.cloud.vision.v1.SafeSearchAnnotation;
import tooko.main.Fn;
import tooko.main.utils.GoogleImageAnnotator;
import tooko.td.TdApi;
import tooko.td.client.TdHandler;

import java.io.IOException;

public class ImageTest extends TdHandler {

    @Override
    public void onLoad() {

        initFunction("img_test");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {

        try {

            SafeSearchAnnotation result = GoogleImageAnnotator.safeSearchDetection(param).get(0).getSafeSearchAnnotation();

            send(Fn.sendText(chatId, Fn.plainText(result.toString())));

        } catch (IOException e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
