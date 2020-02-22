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

@file:Suppress(
    "unused"
)

package tooko.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tooko.core.client.*

/**
 * Returns information about a bank card
 *
 * @bankCardNumber - The bank card number
 */
suspend fun TdAbsHandler.getBankCardInfo(
    bankCardNumber: String? = null
) = sync<BankCardInfo>(
    GetBankCardInfo(
        bankCardNumber
    )
)

suspend fun TdAbsHandler.getBankCardInfoOrNull(
    bankCardNumber: String? = null
) = syncOrNull<BankCardInfo>(
    GetBankCardInfo(
        bankCardNumber
    )
)

fun TdAbsHandler.getBankCardInfo(
    bankCardNumber: String? = null,
    block: (suspend CoroutineScope.(BankCardInfo) -> Unit)
) = send(
    GetBankCardInfo(
        bankCardNumber
    ),block = block
)
