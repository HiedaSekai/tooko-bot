package tooko.td.core;

import java.util.*;

public class LongLongArrayMap extends HashMap<Long, LinkedList<Long>> {

    public LinkedList<Long> rawGet(Object key) {

        return super.get(key);

    }

    @Override
    public LinkedList<Long> get(Object key) {

        LinkedList<Long> list = super.get(key);

        if (list == null) {

            list = new LinkedList<>();

            put((Long) key, list);

        }

        return list;

    }

    public int countElements() {

        int count = 0;

        for (LinkedList<Long> elements : values()) {

            count += elements.size();

        }

        return count;

    }

}
