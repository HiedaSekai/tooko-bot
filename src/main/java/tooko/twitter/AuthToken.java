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

package tooko.twitter;

import org.bson.codecs.pojo.annotations.*;
import tooko.core.db.*;
import tooko.twitter.archives.*;
import twitter4j.*;

import java.util.*;

public class AuthToken {

    public static Table<Long, AuthToken> DATA = new Table<>("tokens", AuthToken.class);

    @BsonId
    public long accountId;

    public int owner;

    public String authToken;

    public static AuthToken getByAccountId(long accountId) {

        return DATA.getById(accountId);

    }

    public static List<AuthToken> getByOwner(int owner) {

        return DATA.getAllByField("owner", owner);

    }

    public UserA archive() {

        return UserA.get(accountId);

    }

    public Twitter mkApi() {

        return AccessToken.getByAccountId(accountId).mkApi();

    }


}