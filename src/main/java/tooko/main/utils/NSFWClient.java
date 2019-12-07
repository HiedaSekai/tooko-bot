package tooko.main.utils;

import cn.hutool.core.img.*;
import cn.hutool.http.*;
import cn.hutool.json.*;
import java.awt.image.*;
import java.io.*;
import net.coobird.thumbnailator.*;
import tooko.main.*;
import cn.hutool.core.io.*;
import cn.hutool.crypto.digest.*;

public class NSFWClient {

    public String serverUrl;

    public NSFWClient(String serverUrl) throws IOException {

        this.serverUrl = serverUrl;

    }
    
    public String predict(String... images) throws IOException {

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


    public String predict(File... images) throws IOException {

        byte[][] imageBytes = new byte[images.length][];

        for (int index = 0; index < images.length; index++) {

            imageBytes[index] = FileUtil.readBytes(images[index]);

        }

        return predict(imageBytes);

    }


    public String predict(byte[]... images) {

        JSONObject request = new JSONObject();

        JSONArray instances = new JSONArray();

        for (byte[] imageBytes : images) {

            JSONObject instance = new JSONObject();
           
            BufferedImage img = ImgUtil.read(new ByteArrayInputStream(imageBytes));

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            try {

                Thumbnails.of(img).size(299, 299).outputQuality(1.0f).outputFormat("jpg").toOutputStream(output);
                
            } catch (IOException e) {}
            
            imageBytes = output.toByteArray();

            instance.put("input_image",imageBytes);
            
            instances.add(instance);
            

        }
        
        request.put("instances", instances);
        
        HttpResponse respone = HttpUtil.createPost(serverUrl + "/v1/models/nsfw:predict").body(request).execute();

        return respone.body();

    }

}
