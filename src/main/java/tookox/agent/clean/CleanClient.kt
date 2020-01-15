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

    var processed = false
    var isNew = false

    val log = createLog("$dcId${number.asXXXX}")

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            setAuthenticationPhoneNumber("99966$dcId${number.asXXXX}")

        } else if (authorizationState is AuthorizationStateWaitCode) {

            checkAuthenticationCode("$dcId$dcId$dcId$dcId$dcId")

        } else if (authorizationState is AuthorizationStateWaitPassword) {

            try {

                checkAuthenticationPassword("114514")

                processed = true

            } catch (ex: TdException) {

                log.debug("跳过")

                // deleteAccount("Delete Test Account")

                stop()

            }

        } else if (authorizationState is AuthorizationStateWaitRegistration) {

            registerUser("User#$dcId${number.asXXXX}")

            log.debug("注册用户")

            isNew = true

        } else if (authorizationState is AuthorizationStateReady) {

            if (processed) {

              //  log.debug("跳过")

                // stop()

                return

            }

            onLogin()

        } else {

            super.onAuthorizationState(authorizationState)

        }

    }

    override suspend fun onLogin() {

        /*

        runCatching {

            if (!getPasswordState().hasPassword) {

                setPassword(null, "114514", "_(:з」∠)_", false)

            }


            joinGroupOrChannel("Tooko")
            joinGroupOrChannel("ISSTC")


        }

         */

        stop()

        defaultLog.debug("完成: $dcId${number.asXXXX}")

    }

    suspend fun joinGroupOrChannel(username: String) {

        val chat = searchPublicChat(username)

        if (getChatMember(chat.id, me.id).status is ChatMemberStatusLeft) {

            joinChat(chat.id)

        }

    }

}