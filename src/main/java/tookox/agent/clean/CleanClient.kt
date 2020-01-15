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

package tookox.agent.clean

import td.TdApi.*
import tookox.core.*
import tookox.core.TookoLogFactory.createLog
import tookox.core.client.*
import tookox.core.raw.*

val Int.asXXXX: String
    get() {

        var str = "$this"

        while (str.length < 4) {

            str = "0$str"

        }

        return str

    }

class CleanClient(val dcId: Int, val number: Int) : TdClient(TdOptions()
        .useTestDc(true)
        .databaseDirectory("data/test/$dcId${number.asXXXX}")) {

    val log = createLog("$dcId${number.asXXXX}")

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            setAuthenticationPhoneNumber("99966$dcId${number.asXXXX}")

        } else if (authorizationState is AuthorizationStateWaitCode) {

            checkAuthenticationCode("$dcId$dcId$dcId$dcId$dcId")

        } else if (authorizationState is AuthorizationStateWaitPassword) {

            log.debug("发起注销")

            deleteAccountOrNull("Delete Test Account")

            stop()

        } else if (authorizationState is AuthorizationStateWaitRegistration) {

            // log.debug("跳过")

            stop()

            /*

            registerUser("User#$dcId${number.asXXXX}")

            log.debug("注册用户")

            isNew = true

            processed = false


             */
        } else if (authorizationState is AuthorizationStateReady) {

            onLogin()

        } else {

            super.onAuthorizationState(authorizationState)

        }

    }

    override suspend fun onLogin() {

        runCatching {

            me = getMe()

            // if (!getPasswordState().hasPassword) {

            //setPassword(null, "114514", "_(:з」∠)_", false)

            // }

            getCreatedPublicChats(PublicChatTypeHasUsername()).chatIds.forEach {

                val type = getChat(it).type

                if (type is ChatTypeSupergroup) {

                    deleteSupergroup(type.supergroupId)

                }

            }

        }.onFailure {
        }

        log.debug("发起注销: ${me.displayName}")

        deleteAccountOrNull("Delete Test Account")

    }


}