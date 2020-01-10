package tookox.core.db;

import tookox.*;

import java.util.*;

public class CacheTable<ID, T> extends Table<ID, T> {

    {

        Launcher.Companion.getCachedTables().add(this);

    }

    public final HashMap<ID, T> idIndex = new HashMap<>();


    public CacheTable(Class<T> clazz) {

        super(clazz);

    }

    public CacheTable(String collectionName, Class<T> clazz) {

        super(collectionName, clazz);

    }

    public void saveAll() {

        synchronized (idIndex) {

            for (Map.Entry<ID, T> data : idIndex.entrySet()) {

                if (save(data.getValue())) continue;

                super.setById(data.getKey(), data.getValue());

            }

            idIndex.clear();

        }

    }

    public boolean save(T obj) {

        return false;

    }

    @Override
    public T setById(ID id, T object) {

        synchronized (idIndex) {

            idIndex.put(id, object);

        }

        return super.setById(id, object);

    }

    public T getNoCache(ID id) {

        return super.getById(id);

    }

    @Override
    public T getById(ID id) {

        if (idIndex.size() > 1000) {

            saveAll();

            idIndex.clear();

        } else if (idIndex.containsKey(id)) return idIndex.get(id);

        synchronized (idIndex) {

            if (idIndex.containsKey(id)) {

                return idIndex.get(id);

            }

            T data = super.getById(id);

            if (data != null) {

                idIndex.put(id, data);
            }

            return data;

        }

    }

    @Override
    public boolean updateField(ID id, String field, Object data, boolean upset) {

        idIndex.remove(id);

        return super.updateField(id, field, data, upset);

    }

    @Override
    public boolean arrayInsert(ID id, String array, Object element, boolean upset) {

        idIndex.remove(id);

        return super.arrayInsert(id, array, element, upset);

    }

    @Override
    public boolean setInsert(ID id, String array, Object element, boolean upset) {

        idIndex.remove(id);

        return super.setInsert(id, array, element, upset);

    }

    @Override
    public boolean arrayReomve(ID id, String array, Object element) {

        idIndex.remove(id);

        return super.arrayReomve(id, array, element);

    }

    @Override
    public void clear() {

        idIndex.clear();

        super.clear();

    }

}

