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

package tooko.core.funs

import td.TdApi
import tooko.core.DATA_4
import tooko.core.client.TdBotHandler
import tooko.core.env.Lang
import tooko.core.fromChannel
import tooko.core.fromPrivate
import tooko.core.langFor
import tooko.core.utils.inlineButton
import tooko.core.utils.make

class SwitchLang : TdBotHandler() {

    val DATA_ID = DATA_4

    override fun onLoad() {

        initFunction("lang")

        initData(DATA_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = chatId.langFor

        if (message.fromPrivate) {

            sudo make L.CHOOSE_LANG withMarkup inlineButton {

                Lang.BY_NAME.forEach { (name, lang) ->

                    dataLine(name, DATA_ID, lang.LANG_ID)

                }

            } sendTo chatId

        } else if (message.fromChannel) {

            sudo make L.CHOOSE_CHANNEL_LANG withMarkup inlineButton {

                Lang.BY_NAME.forEach { (name, lang) ->

                    dataLine(name, DATA_ID, lang.LANG_ID)

                }

            } sendTo chatId

        } else {

            sudo make L.CHOOSE_GROUP_LANG withMarkup inlineButton {

                Lang.BY_NAME.forEach { (name, lang) ->

                    dataLine(name, DATA_ID, lang.LANG_ID)

                }

            } sendTo chatId


        }

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        val selected = subId.langFor

        Lang.DATA.setById(chatId, Lang.DB(chatId, subId))

        sudo make selected.LANG_SELECTED at messageId editTo chatId

    }

}