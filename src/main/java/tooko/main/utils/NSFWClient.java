package tooko.main.utils;

import cn.hutool.core.img.*;
import cn.hutool.core.io.*;
import cn.hutool.crypto.digest.*;
import cn.hutool.http.*;
import cn.hutool.json.*;
import java.awt.image.*;
import java.io.*;
import net.coobird.thumbnailator.*;
import tooko.main.*;
import cn.hutool.core.codec.*;

public class NSFWClient {

    public static String predict(String... images) throws IOException {

        File[] imageFiles = new File[images.length];

        for (int index = 0; index < images.length; index++) {

            String url = images[index];

            File cacheFile = Env.getFile("cache/image_cache/" + DigestUtil.md5Hex(url));

            if (!cacheFile.isFile()) {

                HttpUtil.downloadFile(url, cacheFile);

            }

            imageFiles[index] = cacheFile;

        }

        return predict(imageFiles);

    }


    public static String predict(File... images) throws IOException {

        JSONArray request = new JSONArray();

        for (File imageFile : images) request.add(imageFile.getCanonicalPath());
        
        HttpResponse respone = HttpUtil.createPost(Env.NSFW_SERVER + "/predict_local").body(request).execute();

        return respone.body();

    }


    public static String predict(byte[]... images) throws IOException  {
        
        JSONArray request = new JSONArray();
        
        for (byte[] imageBytes : images) request.add(Base64.encode(imageBytes));
        
        HttpResponse respone = HttpUtil.createPost(Env.NSFW_SERVER + "/predict").body(request).execute();

        return respone.body();

    }

}
