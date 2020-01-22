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

package tooko.agent.fmt

import td.TdApi.*
import tooko.core.*
import tooko.core.TookoLogFactory.createLog
import tooko.core.client.*
import tooko.core.raw.*


class FormatClient(val dcId: String, val number: String) : TdClient(TdOptions()
        .useTestDc(true)
        .databaseDirectory("data/test/$dcId$number")) {

    val log = createLog("$dcId$number")

    override suspend fun onAuthorizationState(authorizationState: AuthorizationState) {

        if (authorizationState is AuthorizationStateWaitPhoneNumber) {

            setAuthenticationPhoneNumber("99966$dcId$number")

        } else if (authorizationState is AuthorizationStateWaitCode) {

            checkAuthenticationCode("$dcId$dcId$dcId$dcId$dcId")

        } else if (authorizationState is AuthorizationStateWaitPassword) {

            log.debug("发起注销")

            deleteAccountOrNull("Delete Test Account")

            stop()

        } else if (authorizationState is AuthorizationStateWaitRegistration) {

            if (number.toInt() % 100 == 0) {

                log.debug("跳过")

            }

            stop()

            /*

            registerUser("User#$dcId$number")

            log.debug("注册用户")

            isNew = true

            processed = false


             */
        } else if (authorizationState is AuthorizationStateReady) {

            me = getMe()

            log.debug("发起注销: ${me.displayName}")

            deleteAccountOrNull("Delete Test Account")

        } else {

            super.onAuthorizationState(authorizationState)

        }

    }


}