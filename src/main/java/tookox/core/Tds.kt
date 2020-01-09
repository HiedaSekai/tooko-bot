@file:Suppress("unused")

package tookox.core

import tooko.td.Client
import tooko.td.TdApi
import tooko.td.TdApi.*
import tooko.td.client.TdException

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

    val result = Client.nativeClientExecute(function)

    if (result is Error) {

        throw TdException(result)

    } else {

        @Suppress("UNCHECKED_CAST")
        return result as T

    }

}