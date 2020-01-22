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

package tooko.pm;

import cn.hutool.core.map.*;
import cn.hutool.core.util.*;
import org.apache.commons.collections4.bidimap.*;
import org.bson.codecs.pojo.annotations.*;
import td.TdApi.*;
import tooko.core.db.*;

import java.util.*;

public class PmData {

    public static CacheTable<Integer, PmData> DATA = new CacheTable<>("pm", PmData.class) {

        @Override
        public boolean save(PmData data) {

            return ArrayUtil.isEmpty(data.welcome.messages)

                    && !data.welcome.notice

                    && MapUtil.isEmpty(data.payloads)

                    && MapUtil.isEmpty(data.sended)

                    && MapUtil.isEmpty(data.received);

        }

    };

    public int id;

    public Payload welcome;

    public HashMap<String, Payload> payloads;
    public DualHashBidiMap<String, Long> sended;
    public DualHashBidiMap<String, Long> received;

    // message id / target chat id
    public HashSet<Long> blocked;

    // message id / target message id
    public transient CacheTable<Long, Session> sessions;

    // message id / target message id

    public PmData() {

    }

    public void initSessions() {

        if (sessions != null) return;

        sessions = new CacheTable<>("pm_" + id, Session.class);

    }

    public Session getSession(long chatId) {

        initSessions();

        Session session = sessions.getById(chatId);

        if (session == null) {

            session = new Session();

            session.chatId = chatId;

            sessions.setById(chatId, session);

        }

        if (session.sended == null) session.sended = new HashSet<>();
        if (session.received == null) session.received = new HashSet<>();
        if (session.reports == null) session.reports = new HashSet<>();

        return session;

    }

    public Session getSessionByElement(String array, long messageId) {

        initSessions();

        return sessions.getByField(array, messageId);

    }

    public void clear() {

        received.clear();

        sended.clear();

        if (sessions != null) sessions.clear();

    }

    public static class Payload {

        public boolean notice;

        public InputMessageContent[] messages;

    }

    public static class Session {

        @BsonId
        public long chatId;

        public HashSet<Long> sended;

        public HashSet<Long> received;

        public HashSet<Long> reports;

        public HashSet<Long> extras;

    }

}
