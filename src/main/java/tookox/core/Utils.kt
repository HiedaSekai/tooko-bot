package tookox.core

import cn.hutool.core.util.StrUtil
import java.math.BigInteger
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

class WriteOnlyField<T>(val setter: (T) -> Unit) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = error("WriteOnlyField : ${property.name}")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        setter.invoke(value)

    }

}

operator fun <F> KProperty0<F>.getValue(thisRef: Any?, property: KProperty<*>): F = get()
operator fun <F> KMutableProperty0<F>.setValue(thisRef: Any?, property: KProperty<*>, value: F) = set(value)