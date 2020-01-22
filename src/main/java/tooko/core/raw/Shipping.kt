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
 * Sets the result of a shipping query
 * For bots only
 *
 * @shippingQueryId - Identifier of the shipping query
 * @shippingOptions - Available shipping options
 * @errorMessage - An error message, empty on success
 */
suspend fun TdAbsHandler.answerShippingQuery(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null
) = sync<Ok>(
    AnswerShippingQuery(
        shippingQueryId,
        shippingOptions,
        errorMessage
    )
)

suspend fun TdAbsHandler.answerShippingQueryOrNull(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null
) = syncOrNull<Ok>(
    AnswerShippingQuery(
        shippingQueryId,
        shippingOptions,
        errorMessage
    )
)

fun TdAbsHandler.answerShippingQuery(
    shippingQueryId: Long,
    shippingOptions: Array<ShippingOption>,
    errorMessage: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AnswerShippingQuery(
        shippingQueryId,
        shippingOptions,
        errorMessage
    ),block = block
)
