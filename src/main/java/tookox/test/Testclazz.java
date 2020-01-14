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

package tookox.test;

import cn.hutool.core.util.*;

import java.io.*;
import java.math.*;
import java.util.*;

public class Testclazz {

    public static void main(String[] args) {

        var byteNum = BigInteger.valueOf(1069424563).toByteArray();

        var raw = mkData(0, 0, byteNum);

        System.out.println(Arrays.toString(raw));

        System.out.println(new BigInteger(readData(raw)[0]).intValue());

    }


    public static byte[] mkData(int id, int subId, byte[]... dataArray) {

        ByteArrayOutputStream format = new ByteArrayOutputStream();

        format.write(id - 128);
        format.write(subId);

        for (byte[] data : dataArray) {

            format.write(127);

            try {

                format.write(data);

            } catch (IOException ignored) {
            }

        }

        format.write(dataArray.length - 128);

        byte[] data = format.toByteArray();

        byte[] zlibData = ZipUtil.zlib(data, 9);

        return data.length < zlibData.length ? data : zlibData;

    }

    public static byte[][] readData(byte[] data) {

        int length = data[data.length - 1] + 128;

        data = ArrayUtil.sub(data, 3, data.length - 1);

        byte[][] arr = new byte[length][];

        int current = 0;

        ByteArrayOutputStream cache = new ByteArrayOutputStream();

        for (int index = 0; index < data.length; index++) {

            if (data[index] == 127) {

                arr[current] = cache.toByteArray();

                cache = new ByteArrayOutputStream();

            } else {

                cache.write(data[index]);

                if (index == data.length - 1 && current == arr.length - 1) {

                    arr[current] = cache.toByteArray();

                }

            }

        }

        return arr;

    }

}
