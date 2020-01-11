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

package tookox.core.bots;

import org.bson.codecs.pojo.annotations.*;
import tookox.core.db.*;

public class BotData {

    public static CacheTable<Integer, BotData> DATA = new CacheTable<>("bots", BotData.class);

    @BsonId
    public int botId;

    public int owner;

    public int type;

    public String userName;

    public String botToken;

}
