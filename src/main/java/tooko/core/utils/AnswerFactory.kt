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

package tooko.core.utils

import td.TdApi
import tooko.core.client.*

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

infix fun TdBotAbsHandler.confirmTo(queryId: Long) {

    AnswerFactory(this).answerTo(queryId)

}

suspend infix fun TdBotAbsHandler.syncConfirmTo(queryId: Long) {

    AnswerFactory(this).syncAnswerTo(queryId)

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

        context.sendUnit(TdApi.AnswerCallbackQuery(queryId, text, showAlert, url, cacheTime))

    }

    suspend infix fun syncAnswerTo(queryId: Long) {

        context.syncUnit(TdApi.AnswerCallbackQuery(queryId, text, showAlert, url, cacheTime))

    }

}