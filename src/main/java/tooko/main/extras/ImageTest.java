package tooko.main.extras;

import com.google.cloud.vision.v1.SafeSearchAnnotation;
import tooko.main.Fn;
import tooko.main.utils.GoogleImageAnnotator;
import tooko.td.TdApi;
import tooko.td.client.TdHandler;

import java.io.IOException;
import tooko.main.utils.*;

public class ImageTest extends TdHandler {

    @Override
    public void onLoad() {

        initFunction("img_test");

    }

    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams) {

        try {
            
            String result = new NSFWClient(params[0]).predict(params[1]);

            send(Fn.sendText(chatId, Fn.plainText(result)));

        } catch (Exception e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
