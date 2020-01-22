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
 * Returns an invoice payment form
 * This method should be called when the user presses inlineKeyboardButtonBuy
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 */
suspend fun TdAbsHandler.getPaymentForm(
    chatId: Long,
    messageId: Long
) = sync<PaymentForm>(
    GetPaymentForm(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getPaymentFormOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<PaymentForm>(
    GetPaymentForm(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getPaymentForm(
    chatId: Long,
    messageId: Long,
    block: (suspend CoroutineScope.(PaymentForm) -> Unit)
) = send(
    GetPaymentForm(
        chatId,
        messageId
    ),block = block
)

/**
 * Sends a filled-out payment form to the bot for final verification
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
 * @shippingOptionId - Identifier of a chosen shipping option, if applicable
 * @credentials - The credentials chosen by user for payment
 */
suspend fun TdAbsHandler.sendPaymentForm(
    chatId: Long,
    messageId: Long,
    orderInfoId: String? = null,
    shippingOptionId: String? = null,
    credentials: InputCredentials? = null
) = sync<PaymentResult>(
    SendPaymentForm(
        chatId,
        messageId,
        orderInfoId,
        shippingOptionId,
        credentials
    )
)

suspend fun TdAbsHandler.sendPaymentFormOrNull(
    chatId: Long,
    messageId: Long,
    orderInfoId: String? = null,
    shippingOptionId: String? = null,
    credentials: InputCredentials? = null
) = syncOrNull<PaymentResult>(
    SendPaymentForm(
        chatId,
        messageId,
        orderInfoId,
        shippingOptionId,
        credentials
    )
)

fun TdAbsHandler.sendPaymentForm(
    chatId: Long,
    messageId: Long,
    orderInfoId: String? = null,
    shippingOptionId: String? = null,
    credentials: InputCredentials? = null,
    block: (suspend CoroutineScope.(PaymentResult) -> Unit)
) = send(
    SendPaymentForm(
        chatId,
        messageId,
        orderInfoId,
        shippingOptionId,
        credentials
    ),block = block
)

/**
 * Returns information about a successful payment
 *
 * @chatId - Chat identifier of the PaymentSuccessful message
 * @messageId - Message identifier
 */
suspend fun TdAbsHandler.getPaymentReceipt(
    chatId: Long,
    messageId: Long
) = sync<PaymentReceipt>(
    GetPaymentReceipt(
        chatId,
        messageId
    )
)

suspend fun TdAbsHandler.getPaymentReceiptOrNull(
    chatId: Long,
    messageId: Long
) = syncOrNull<PaymentReceipt>(
    GetPaymentReceipt(
        chatId,
        messageId
    )
)

fun TdAbsHandler.getPaymentReceipt(
    chatId: Long,
    messageId: Long,
    block: (suspend CoroutineScope.(PaymentReceipt) -> Unit)
) = send(
    GetPaymentReceipt(
        chatId,
        messageId
    ),block = block
)

/**
 * Deletes saved credentials for all payment provider bots
 */
suspend fun TdAbsHandler.deleteSavedCredentials() = sync<Ok>(
    DeleteSavedCredentials()
)

suspend fun TdAbsHandler.deleteSavedCredentialsOrNull() = syncOrNull<Ok>(
    DeleteSavedCredentials()
)

fun TdAbsHandler.deleteSavedCredentials(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteSavedCredentials(),block = block
)
