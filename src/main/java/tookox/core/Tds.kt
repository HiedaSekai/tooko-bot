package tookox.core

import tooko.main.Fn
import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.client.TdAbsHandler
import twitter4j.TwitterException

fun <T : TdApi.Object> TdAbsHandler.execute(function: TdApi.Function): T = client.execute(function)
fun <T : TdApi.Object> TdAbsHandler.send(function: TdApi.Function, callback: (isOk: Boolean, result: T?, error: TdApi.Error?) -> Unit) = client.send(function, callback)

fun TdAbsHandler.postErr(chatId: Long, exception: Throwable): TdApi.Message {

    return if (exception is TwitterException) {

        postText(chatId, Fn.parseTwitterException(Lang.get(chatId), exception))

    } else {

        postText(chatId, "${exception.message}")

    }

}

fun TdAbsHandler.postText(chatId: Long, text: String, enableLinkPreview: Boolean = false): TdApi.Message {

    return execute(Fn.sendText(chatId, Fn.plainText(text), enableLinkPreview))

}

fun TdAbsHandler.postHtml(chatId: Long, text: String, enableLinkPreview: Boolean = false): TdApi.Message {

    return execute(Fn.sendText(chatId, Fn.parseHtml(text), enableLinkPreview))

}
