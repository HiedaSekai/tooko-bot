@file:Suppress("unused")

package tookox.core

import tooko.td.TdApi.*
import tookox.core.client.*

val User.displayName get() = "$firstName $lastName".trim()

val Message.fromPrivate get() = chatId > 0L
val Message.fromBasicGroup get() = chatId > -1000000000000L
val Message.fromSuperGroup get() = chatId < -1000000000000L && !isChannelPost
val Message.fromChannel get() = isChannelPost

val Message.text
    get() = if (content is MessageText) {

        (content as MessageText).text.text

    } else null

class Finish : RuntimeException("Finish Event")

fun TdAbsHandler.finishEvent() {

    throw Finish()

}