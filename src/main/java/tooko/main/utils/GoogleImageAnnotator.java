package tooko.main.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import com.google.cloud.vision.v1.*;
import tooko.main.Env;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GoogleImageAnnotator {

    private static Feature SAFE_SEARCH_DETECTION = Feature.newBuilder().setType(Feature.Type.SAFE_SEARCH_DETECTION).build();

    public static List<AnnotateImageResponse> safeSearchDetection(String... images) throws IOException {

        File[] imageFiles = new File[images.length];

        for (int index = 0; index < images.length; index++) {

            String url = images[index];

            File cacheFile = Env.getFile("cache/image_cache/" + DigestUtil.md5Hex(url));

            if (!cacheFile.isFile()) {

                HttpUtil.downloadFile(url, cacheFile);

            }

            imageFiles[index] = cacheFile;

        }

        return safeSearchDetection(imageFiles);

    }


    public static List<AnnotateImageResponse> safeSearchDetection(File... images) throws IOException {

        byte[][] imageBytes = new byte[images.length][];

        for (int index = 0; index < images.length; index++) {

            imageBytes[index] = FileUtil.readBytes(images[index]);

        }

        return safeSearchDetection(imageBytes);

    }

    public static List<AnnotateImageResponse> safeSearchDetection(byte[]... images) throws IOException {

        ImageAnnotatorClient imageAnnotatorClient = ImageAnnotatorClient.create();

        List<AnnotateImageRequest> requests = new LinkedList<>();

        for (byte[] image : images) {

            requests.add(AnnotateImageRequest.newBuilder().addFeatures(SAFE_SEARCH_DETECTION).setImage(Image.parseFrom(image)).build());

        }

        return imageAnnotatorClient.batchAnnotateImages(requests).getResponsesList();

    }

}
