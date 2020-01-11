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

package tookox.core.db;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.jetbrains.annotations.*;
import tookox.core.env.*;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Table<ID, T> {

    static final String FIELD_ID = "_id";
    public MongoCollection<T> collection;

    public Table(Class<T> clazz) {

        this(clazz.getSimpleName(), clazz);

    }

    public Table(String collectionName, Class<T> clazz) {

        collection = Env.DB.getCollection(collectionName, clazz);

    }

    public boolean containsId(ID id) {

        synchronized (this) {

            return collection.countDocuments(eq(FIELD_ID, id)) > 0;

        }

    }

    public List<T> getAll() {

        LinkedList<T> all = new LinkedList<>();

        for (T t : collection.find()) {

            all.add(t);

        }

        return all;

    }

    @Nullable
    public T getById(ID id) {

        return collection.find(eq(FIELD_ID, id)).first();

    }

    public long countByField(String field, Object value) {

        return collection.countDocuments(eq(field, value));

    }

    public boolean fieldEquals(ID id, String field, Object value) {

        return collection.countDocuments(and(eq(FIELD_ID, id), eq(field, value))) > 0;

    }

    public FindIterable<T> findByField(String field, Object value) {

        return collection.find(eq(field, value));

    }

    public List<T> getAllByField(String field, Object value) {

        LinkedList<T> all = new LinkedList<>();

        for (T t : findByField(field, value)) {

            all.add(t);

        }

        return all;

    }


    public T getByField(String field, Object value) {

        return findByField(field, value).first();

    }

    public T setById(ID id, T object) {

        if (containsId(id)) {

            collection.replaceOne(eq(FIELD_ID, id), object);

        } else {

            synchronized (this) {

                collection.insertOne(object);

            }

        }

        return object;

    }

    public boolean setInsert(ID id, String array, Object element) {

        return setInsert(id, array, element, false);

    }

    public boolean setInsert(ID id, String array, Object element, boolean upset) {

        synchronized (this) {

            if (upset) {

                return collection.updateOne(eq("_id", id), and(set(FIELD_ID, id), addToSet(array, element)), new UpdateOptions().upsert(true)).getModifiedCount() > 1;

            } else {

                return collection.updateOne(eq("_id", id), addToSet(array, element)).getModifiedCount() > 1;

            }

        }

    }

    public boolean updateField(ID id, String field, Object data) {

        return updateField(id, field, data, false);

    }

    public boolean updateField(ID id, String field, Object data, boolean upset) {

        synchronized (this) {

            if (data == null) {

                return collection.updateOne(eq("_id", id), unset(field)).getModifiedCount() > 1;

            } else if (upset) {

                return collection.updateOne(eq("_id", id), and(set("_id", id), set(field, data)), new UpdateOptions().upsert(true)).getModifiedCount() > 1;

            } else {

                return collection.updateOne(eq("_id", id), set(field, data)).getModifiedCount() > 1;

            }

        }

    }

    public boolean arrayInsert(ID id, String array, Object element) {

        return arrayInsert(id, array, element, false);

    }

    public boolean arrayInsert(ID id, String array, Object element, boolean upset) {

        synchronized (this) {

            if (upset) {

                return collection.updateOne(eq("_id", id), and(set("_id", id), push(array, element)), new UpdateOptions().upsert(true)).getModifiedCount() > 1;

            } else {

                return collection.updateOne(eq("_id", id), push(array, element)).getModifiedCount() > 1;

            }

        }

    }

    public boolean arrayReomve(ID id, String array, Object element) {

        synchronized (this) {

            return collection.updateOne(eq("_id", id), and(set("_id", id), pull(array, element)), new UpdateOptions().upsert(true)).getModifiedCount() > 1;
        }

    }

    public boolean arrayIsIn(ID id, String array, Object element) {

        return collection.countDocuments(and(eq(FIELD_ID, id), in(array, element))) > 0;

    }

    public boolean deleteById(ID id) {

        synchronized (this) {

            return collection.deleteOne(eq("_id", id)).getDeletedCount() > 0;

        }

    }

    public boolean deleteByField(String field, Object value) {

        synchronized (this) {

            return collection.deleteOne(eq(field, value)).getDeletedCount() > 0;

        }

    }

    public void clear() {

        collection.drop();

    }

}

