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

package tooko.twitter.login

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import td.TdApi
import tooko.core.*
import tooko.core.client.TdBotHandler
import tooko.core.env.Env
import tooko.core.utils.make
import tooko.core.utils.makeHtml
import tooko.lottie.mkTwitterDriver
import tooko.lottie.waitForId
import tooko.lottie.waitForTag
import tooko.twitter.AccessToken
import tooko.twitter.AuthToken
import tooko.twitter.login.test.TokenAddTest

class TokenAdd : TdBotHandler() {

    val PERSIST_ID = PERSIST_4

    override fun onLoad() {

        initFunction("new_token")

        initPersist(PERSIST_ID)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        val L = userId.langFor

        if (params.isEmpty()) {

            writePersist(userId, PERSIST_ID)

            sudo makeHtml L.TWI_INPUT_TOKEN sendTo chatId

        } else {

            params.forEach {

                runCatching {

                    checkTwitterAuthToken(userId, chatId, it)

                }.onFailure {

                    sudo make L.TWI_INVALID_TOKEN sendTo chatId

                }

            }

        }

    }

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: TdApi.Message, subId: Int) {

        val L = userId.langFor

        sudo removePersist userId

        val authToken = message.text

        if (authToken == null) {

            onSendCanceledMessage(userId)

            return

        }

        runCatching {

            checkTwitterAuthToken(userId, chatId, authToken)

        }.onFailure {

            sudo make it sendTo chatId

            // sudo make L.TWI_INVALID_TOKEN sendTo chatId

        }


    }

    private suspend fun checkTwitterAuthToken(userId: Int, chatId: Long, authToken: String) {

        GlobalScope.launch {

            val L = userId.langFor

            val status = sudo make L.TWI_CHECKING syncTo chatId

            val driver = mkTwitterDriver(authToken)

            val screenName = driver.findElementByXPath(TokenAddTest.userPage).getAttribute("href").substringAfterLast("/")

            val apiToken = Env.TWITTER_API_TOKENS[0]

            var api = apiToken.mkApi()

            sudo make L.TWI_GEN_API editTo status

            val requestToken = api.getOAuthRequestToken("oob")

            driver.get(requestToken.authenticationURL)

            driver.waitForId("allow").click()

            val code = driver.waitForTag("code").text

            val accessToken = api.getOAuthAccessToken(code)

            val account = AccessToken()

            account.owner = userId
            account.accountId = accessToken.userId
            account.apiKey = apiToken.apiKey
            account.apiSecretKey = apiToken.apiSecretKey
            account.accessToken = accessToken.token
            account.accessTokenSecret = accessToken.tokenSecret

            AccessToken.DATA.setById(account.accountId, account)

            val token = AuthToken()

            token.owner = userId
            token.accountId = account.accountId
            token.authToken = authToken

            AuthToken.DATA.setById(account.accountId, token)

            api = account.mkApi()

            driver.close()

            sudo makeHtml L.TWI_WELCOME.input(api.verifyCredentials().asInlineMention) editTo status

        }

    }

}