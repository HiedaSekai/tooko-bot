package tookox.core

fun <T : Any> T.applyIf(boolean: Boolean, block: T.() -> Unit): T {

    if (boolean) block.invoke(this)

    return this

}

fun <T : Any> T.applyIfNot(boolean: Boolean, block: T.() -> Unit): T {

    if (!boolean) block.invoke(this)

    return this

}