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

package tooko.core.totp

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator
import td.TdApi
import tooko.core.DATA_3
import tooko.core.asBlod
import tooko.core.asCode
import tooko.core.client.TdBotHandler
import tooko.core.langFor
import tooko.core.utils.*
import java.time.Instant
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec


class TotpPanel : TdBotHandler() {

    val DATA_ID = DATA_3

    val totp = TimeBasedOneTimePasswordGenerator()

    override fun onLoad() {

        initFunction("2fa")

        initData(DATA_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = chatId.langFor

        if (params.isEmpty()) {

            val data = TotpData.DATA.getById(chatId)

            if (data == null) {

                sudo make L.TOTP_EMPTY sendTo chatId

                return

            }

            var code = ""

            data.totp.forEach { (name, secret) ->

                val key = SecretKeySpec(secret.toByteArray(), totp.algorithm)

                code += name.asBlod + ": " + totp.generateOneTimePassword(key, Instant.now())

            }

            sudo makeHtml code withMarkup inlineButton {

                dataLine(chatId.langFor.REFRESH, DATA_ID, 0)

            } sendTo chatId

        } else if (params[0] == "gen") {

            val keyGenerator = KeyGenerator.getInstance(totp.algorithm)

            // SHA-1 and SHA-256 prefer 64-byte (512-bit) keys; SHA512 prefers 128-byte (1024-bit) keys
            keyGenerator.init(512)

            val key = keyGenerator.generateKey()

            sudo makeHtml String(key.encoded).asCode sendTo chatId

        } else if (params[0] == "add") {

            if (params.size < 3) {

                sudo make "/$function add <name> <key>" sendTo chatId

                return

            }

            runCatching {

                SecretKeySpec(params[2].toByteArray(), totp.algorithm)

                val data = TotpData.DATA.getById(chatId) ?: TotpData().also {

                    it.chatId = chatId

                }

                data.totp = data.totp ?: linkedMapOf<String, String>()

                if (data.totp.containsKey(params[1])) {

                    sudo make L.TOTP_EXISTS sendTo chatId

                    return

                }

                data.totp[params[1]] = params[2]

                TotpData.DATA.setById(chatId, data)

            }.onFailure {

                sudo make L.TOTP_KEY_INVALID sendTo chatId

            }

        } else if (params[0] == "del") {

            if (params.size < 2) {

                sudo make "/$function del <name>" sendTo chatId

                return

            }

            val data = TotpData.DATA.getById(chatId)

            if (data?.totp?.containsKey(params[1]) != true) {

                sudo make L.TOTP_NOT_FOUNT sendTo chatId

                return

            }

            data.totp?.remove(params[1])

            TotpData.DATA.setById(chatId, data)

            sudo make L.DELETED sendTo chatId

        }

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        val tdata = TotpData.DATA.getById(chatId)

        if (tdata == null) {

            sudo makeAlert chatId.langFor.TOTP_EMPTY answerTo queryId

            return

        }

        var code = ""

        val totp = TimeBasedOneTimePasswordGenerator()

        tdata.totp.forEach { (name, secret) ->

            val key = SecretKeySpec(secret.toByteArray(), totp.algorithm)

            code += name.asBlod + ": " + totp.generateOneTimePassword(key, Instant.now())

        }

        sudo makeHtml code withMarkup inlineButton {

            dataLine(chatId.langFor.REFRESH, DATA_ID, 0)

        } at messageId editTo chatId

        sudo makeAnswer chatId.langFor.REFRESHED cacheTime 10 answerTo queryId

    }

}