package tookox.core

import cn.hutool.core.util.StrUtil

fun <T : Any> T.applyIf(boolean: Boolean, block: T.() -> Unit): T {

    if (boolean) block.invoke(this)

    return this

}

fun <T : Any> T.applyIfNot(boolean: Boolean, block: T.() -> Unit): T {

    if (!boolean) block.invoke(this)

    return this

}

fun String.input(vararg params: Any): String {

    return StrUtil.format(this, params)

}