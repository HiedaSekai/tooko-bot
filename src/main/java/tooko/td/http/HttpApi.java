package tooko.td.http;

import cn.hutool.core.net.*;
import cn.hutool.core.util.*;
import cn.hutool.http.*;
import com.google.gson.*;
import tooko.td.client.*;
import tooko.td.http.request.*;
import tooko.td.http.response.*;

import java.io.*;
import java.util.*;

public class HttpApi {

    static final Gson GSON = new Gson();

    static final String API_URL = "https://api.telegram.org";


    public static byte[] readFile(String botToken, String filePath) {

        return HttpUtil.createGet(getFilePath(botToken, filePath)).execute().bodyBytes();

    }

    public static String getFilePath(String botToken, String filePath) {

        int slash = filePath.lastIndexOf('/') + 1;

        String path = filePath.substring(0, slash);

        String fileName = filePath.substring(slash);

        return API_URL + "/file/bot" + botToken + "/" + path + URLEncoder.DEFAULT.encode(fileName, CharsetUtil.CHARSET_UTF_8).replace("+", "%20");

    }

    public static <T extends BaseRequest, R extends BaseResponse> R execute(String botToken, final BaseRequest<T, R> request) throws TdException {

        HttpRequest httpRequest = HttpUtil.createPost(API_URL + "/bot" + botToken + "/" + request.getMethod());

        for (Map.Entry<String, Object> parameter : request.getParameters().entrySet()) {

            String name = parameter.getKey();

            Object value = parameter.getValue();

            if (value instanceof byte[]) {

                httpRequest.form(name, (byte[]) value, request.getFileName());

            } else if (value instanceof File) {

                httpRequest.form(name, (File) value, request.getFileName());

            } else {

                httpRequest.form(name, value);

            }

        }

        String body;

        try {

            HttpResponse httpResponse = httpRequest.execute();

            if (!httpResponse.isOk()) {

                throw new TdException(httpResponse.getStatus(), httpResponse.body());

            }

            body = httpResponse.body();

        } catch (Exception ex) {

            throw new TdException(ex.getMessage());

        }

        R response = GSON.fromJson(body, request.getResponseType());

        if (!response.isOk()) {

            throw new TdException(response.errorCode(), response.description());

        }

        return response;

    }

}
