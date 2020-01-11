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

@file:Suppress("unused")

package tookox.core

import cn.hutool.core.collection.CollUtil
import cn.hutool.core.util.ArrayUtil
import cn.hutool.core.util.StrUtil
import java.math.BigInteger
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

fun <T> T.applyIf(boolean: Boolean, block: (T.() -> Unit)?): T {
    if (boolean) block?.invoke(this)
    return this
}

fun <T> T.applyIfNot(boolean: Boolean, block: (T.() -> Unit)?): T {
    if (!boolean) block?.invoke(this)
    return this
}

fun String.input(vararg params: Any): String {

    return StrUtil.format(this, *params)

}

val Number.asByteArray get() = BigInteger.valueOf(toLong()).toByteArray()

val ByteArray.asLong get() = BigInteger(this).toLong()
val ByteArray.asInt get() = BigInteger(this).toInt()

fun <T> Array<T>.shift(): Array<T> {

    return shift(1)

}

fun <T> Array<T>.shift(size: Int): Array<T> {

    return ArrayUtil.sub(this, size, this.size)

}

fun <T> Collection<T>.shift() = shift(1)

fun <T> Collection<T>.shift(size: Int): Collection<T> {

    return LinkedList(CollUtil.sub(this, size, this.size))

}

class WriteOnlyField<T>(val setter: (T) -> Unit) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = error("WriteOnlyField : ${property.name}")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        setter.invoke(value)

    }

}

class WeakField<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        this.value = value
    }
}


operator fun <F> KProperty0<F>.getValue(thisRef: Any?, property: KProperty<*>): F = get()
operator fun <F> KMutableProperty0<F>.setValue(thisRef: Any?, property: KProperty<*>, value: F) = set(value)

operator fun AtomicBoolean.getValue(thisRef: Any?, property: KProperty<*>): Boolean = get()
operator fun AtomicBoolean.setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) = set(value)

operator fun AtomicInteger.getValue(thisRef: Any?, property: KProperty<*>): Int = get()
operator fun AtomicInteger.setValue(thisRef: Any?, property: KProperty<*>, value: Int) = set(value)