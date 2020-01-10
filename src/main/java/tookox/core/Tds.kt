@file:Suppress("unused")

package tookox.core

import td.TdApi
import td.TdNative
import tookox.core.client.*
import td.TdApi.*

val User.displayName get() = "$firstName $lastName".trim()

val Message.fromPrivate get() = chatId > 0L
val Message.fromBasicGroup get() = chatId > -1000000000000L
val Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val Message.fromChannel get() = isChannelPost

val Message.text
    get() = if (content is MessageText) {

        (content as MessageText).text.text

    } else null

fun <T : Object> syncRaw(function: TdApi.Function): T {

    val result = TdNative.nativeClientExecute(function)

    if (result is Error) {

        throw TdException(result)

    } else {

        @Suppress("UNCHECKED_CAST")
        return result as T

    }

}