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

package tooko.core.nsfw;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.*;
import cn.hutool.core.thread.*;
import cn.hutool.core.util.*;
import cn.hutool.crypto.digest.*;
import cn.hutool.http.*;
import cn.hutool.json.*;
import cn.hutool.log.*;
import tooko.core.env.*;

import java.io.*;
import java.util.*;

public class NSFW {

    private static Log log = LogFactory.get("NSFW");

    public static NSRC predict(String... images) throws IOException {

        LinkedList<File> imageArray = new LinkedList<>();

        for (String url : images) {

            File cacheFile = Env.getFile("cache/image_cache/" + DigestUtil.md5Hex(url));

            if (!cacheFile.isFile()) {

                try {

                    HttpResponse response = HttpUtil.createGet(url).execute();

                    if (response.isOk()) {

                        response.writeBody(cacheFile);

                    } else if (response.getStatus() >= 300 && response.getStatus() <= 308) {

                        HttpUtil.downloadFile(response.header(Header.LOCATION), cacheFile);

                    } else {

                        continue;

                    }

                } catch (Exception ignored) {

                    FileUtil.del(cacheFile);

                    ThreadUtil.sleep(100);

                    try {

                        HttpUtil.downloadFile(url, cacheFile);

                    } catch (Exception ex) {

                        log.warn(ex, "ERROR DOWNLOAD IMAGE {}", url);

                        continue;

                    }

                }

                imageArray.add(cacheFile);

            }

        }

        return predict(imageArray.toArray(new File[0]));

    }

    public static float[][] predictRaw(String... images) throws IOException {

        LinkedList<File> imageArray = new LinkedList<>();

        for (String url : images) {

            File cacheFile = Env.getFile("cache/image_cache/" + DigestUtil.md5Hex(url));

            if (!cacheFile.isFile()) {

                try {

                    HttpResponse response = HttpUtil.createGet(url).execute();

                    if (response.isOk()) {

                        response.writeBody(cacheFile);

                    } else if (response.getStatus() >= 300 && response.getStatus() <= 308) {

                        HttpUtil.downloadFile(response.header(Header.LOCATION), cacheFile);

                    } else {

                        continue;

                    }

                } catch (Exception ignored) {

                    FileUtil.del(cacheFile);

                    ThreadUtil.sleep(100);

                    try {

                        HttpUtil.downloadFile(url, cacheFile);

                    } catch (Exception ex) {

                        log.warn(ex, "ERROR DOWNLOAD IMAGE {}", url);

                        continue;

                    }

                }

            }

            imageArray.add(cacheFile);

        }

        return predictRaw(imageArray.toArray(new File[0]));

    }

    public static NSRC predict(File... images) throws IOException {

        LinkedList<byte[]> imageArray = new LinkedList<>();

        for (File image : images) {

            try {

                imageArray.add(FileUtil.readBytes(image));

            } catch (Exception ex) {

                log.warn(ex, "ERROR READ IMAGE {}", image);

            }

        }

        return predict(imageArray.toArray(new byte[0][]));

    }

    public static float[][] predictRaw(File... images) throws IOException {

        LinkedList<byte[]> imageArray = new LinkedList<>();

        for (File image : images) {

            try {

                imageArray.add(FileUtil.readBytes(image));

            } catch (Exception ex) {

                log.warn(ex, "ERROR READ IMAGE {}", image);

            }

        }

        return predictRaw(imageArray.toArray(new byte[0][]));

    }

    public static NSRC predict(byte[]... images) throws IOException {

        float[][] results = predictRaw(images);

        for (float[] result : results) {

            if (result[3] > 0.8f) {

                return NSRC.PORN;

            }

        }

        for (float[] result : results) {

            if (result[4] > 0.8f) {

                return NSRC.SEXY;

            }

        }

        for (float[] result : results) {

            if (result[1] > 0.8f) {

                return NSRC.HENTAI;

            }

        }

        return null;

    }

    public static float[][] predictRaw(byte[]... images) throws IOException {

        if (ArrayUtil.isEmpty(images)) return new float[0][];

        try {

            JSONArray request = new JSONArray();

            for (byte[] imageBytes : images) {

                request.add(Base64.encode(imageBytes));

            }

            HttpResponse respone = HttpUtil.createPost(Env.NSFW_SERVER + "/predict").body(request.toString()).execute();

            if (respone.getStatus() != 200) {

                throw new IOException("HTTP " + respone.getStatus() + " : " + respone.body());

            }

            JSONObject result = new JSONObject(respone.body());

            if (result.containsKey("error")) {

                throw new IOException(result.getStr("error"));

            }

            JSONArray predictionsArray = result.getJSONArray("predictions");

            float[][] predictions = new float[predictionsArray.size()][];

            for (int index = 0; index < predictions.length; index++) {

                predictions[index] = (float[]) predictionsArray.getJSONArray(index).toArray(float.class);

            }

            return predictions;

        } catch (IOException ioEx) {

            throw ioEx;

        } catch (Exception ex) {

            throw new IOException(ex);

        }

    }

}
