package tookox.pm

import java.util.*

class LongLongArrayMap : HashMap<Long, LinkedList<Long>>() {

    fun rawGet(key: Long): LinkedList<Long>? = super.get(key)

    override fun get(key: Long): LinkedList<Long> {

        var list = super.get(key)

        if (list == null) {

            list = LinkedList()

            put(key, list)

        }

        return list

    }

    fun countElements(): Int {

        var count = 0

        for (elements in values) {

            count += elements.size

        }

        return count
    }

}