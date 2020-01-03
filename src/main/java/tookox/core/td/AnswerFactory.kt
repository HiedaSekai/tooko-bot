package tookox.core.td

import tooko.td.TdApi
import tookox.core.client.TdAbsHandler
import tookox.core.client.TdBotAbsHandler

infix fun TdBotAbsHandler.makeAnswer(block: AnswerFactory.() -> Unit): AnswerFactory {

    return AnswerFactory(this).apply(block)

}

infix fun TdBotAbsHandler.makeAnswer(text: String): AnswerFactory {

    return AnswerFactory(this).also { it.text = text }

}

infix fun TdBotAbsHandler.makeAlert(text: String): AnswerFactory {

    return makeAnswer(text).also { it.showAlert = true }

}


infix fun TdBotAbsHandler.makeAnswerUrl(url: String): AnswerFactory {

    return AnswerFactory(this).also { it.url = url }

}


class AnswerFactory(val context: TdAbsHandler) {

    var text: String? = null

    var showAlert = false

    var url: String? = null

    var cacheTime = 0

    infix fun showAlert(alert: Boolean): AnswerFactory {

        showAlert = alert

        return this

    }

    infix fun cacheTime(time: Int): AnswerFactory {

        cacheTime = time

        return this

    }

    infix fun answerTo(queryId: Long) {

        context.sendRaw(TdApi.AnswerCallbackQuery(queryId, text, showAlert, url, cacheTime))

    }

    infix fun postAnswerTo(queryId: Long) {

        context.post(TdApi.AnswerCallbackQuery(queryId, text, showAlert, url, cacheTime))

    }

}