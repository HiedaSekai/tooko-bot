package tooko.main.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import tooko.main.Env;

import java.io.File;
import java.io.IOException;

public class NSFWClient {
    
    // drawings, hentai, neutral, porn, sexy

    public static float[][] predict(String... images) throws IOException {

        File[] imageFiles = new File[images.length];

        for (int index = 0; index < images.length; index++) {

            String url = images[index];

            File cacheFile = Env.getFile("cache/image_cache/" + DigestUtil.md5Hex(url));

            if (!cacheFile.isFile()) {

                HttpUtil.downloadFile(url, cacheFile);

            }

            imageFiles[index] = cacheFile;

        }

        try {

            return predict(imageFiles);

        } catch (IOException ex) {

            throw new IOException("ERROR PREDICE IMAGES : " + ArrayUtil.join(images, " "), ex);

        }

    }


    public static float[][] predict(File... images) throws IOException {

        JSONArray request = new JSONArray();

        for (File imageFile : images) request.add(Base64.encode(imageFile));

        HttpResponse respone = HttpUtil.createPost(Env.NSFW_SERVER + "/predict").body(request.toString()).execute();

        if (respone.getStatus() != 200) {

            throw new IOException("HTTP " + respone.getStatus() + " : " + respone.body());

        }

        JSONObject result = new JSONObject(respone.body());

        if (result.containsKey("error")) {

            throw new IOException(result.getStr("error"));

        }

        JSONArray predictionsArray =  result.getJSONArray("predictions");

        float[][] predictions = new float[predictionsArray.size()][];

        for (int index = 0;index < predictions.length;index ++) {

            predictions[index] = (float[]) predictionsArray.getJSONArray(index).toArray(float.class);

        }

        return predictions;
       
    }


    public static float[][] predict(byte[]... images) throws IOException  {
        
        JSONArray request = new JSONArray();
        
        for (byte[] imageBytes : images) request.add(Base64.encode(imageBytes));

        HttpResponse respone = HttpUtil.createPost(Env.NSFW_SERVER + "/predict").body(request.toString()).execute();

        if (respone.getStatus() != 200) {
           
            throw new IOException("HTTP " + respone.getStatus() + " : " + respone.body());
            
        }
        
        JSONObject result = new JSONObject(respone.body());
        
        if (result.containsKey("error")) {
            
            throw new IOException(result.getStr("error"));
            
        }
        
        JSONArray predictionsArray =  result.getJSONArray("predictions");

        float[][] predictions = new float[predictionsArray.size()][];
        
        for (int index = 0;index < predictions.length;index ++) {
            
            predictions[index] = (float[]) predictionsArray.getJSONArray(index).toArray(float.class);
            
        }
        
        return predictions;
        
    }

}
