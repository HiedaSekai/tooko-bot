package tookox.core

import cn.hutool.core.util.StrUtil
import java.math.BigInteger
import kotlin.reflect.KProperty

fun <T : Any> T.applyIf(boolean: Boolean, block: (T.() -> Unit)?): T {

    if (boolean) block?.invoke(this)

    return this

}

fun <T : Any> T.applyIfNot(boolean: Boolean, block: (T.() -> Unit)?): T {

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