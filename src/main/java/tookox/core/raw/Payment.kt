@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns an invoice payment form
 * This method should be called when the user presses inlineKeyboardButtonBuy
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 */
suspend fun TdAbsHandler.getPaymentForm(
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<PaymentForm>(
    GetPaymentForm(
        chatId,
        messageId
    )
)

/**
 * Returns an invoice payment form
 * This method should be called when the user presses inlineKeyboardButtonBuy
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 */
suspend fun TdAbsHandler.getPaymentFormOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<PaymentForm>(
    GetPaymentForm(
        chatId,
        messageId
    )
)

/**
 * Returns an invoice payment form
 * This method should be called when the user presses inlineKeyboardButtonBuy
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 */
fun TdAbsHandler.getPaymentForm(
    chatId: Long = 0L,
    messageId: Long = 0L,
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
    chatId: Long = 0L,
    messageId: Long = 0L,
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

/**
 * Sends a filled-out payment form to the bot for final verification
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
 * @shippingOptionId - Identifier of a chosen shipping option, if applicable
 * @credentials - The credentials chosen by user for payment
 */
suspend fun TdAbsHandler.sendPaymentFormOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L,
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

/**
 * Sends a filled-out payment form to the bot for final verification
 *
 * @chatId - Chat identifier of the Invoice message
 * @messageId - Message identifier
 * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
 * @shippingOptionId - Identifier of a chosen shipping option, if applicable
 * @credentials - The credentials chosen by user for payment
 */
fun TdAbsHandler.sendPaymentForm(
    chatId: Long = 0L,
    messageId: Long = 0L,
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
    chatId: Long = 0L,
    messageId: Long = 0L
) = sync<PaymentReceipt>(
    GetPaymentReceipt(
        chatId,
        messageId
    )
)

/**
 * Returns information about a successful payment
 *
 * @chatId - Chat identifier of the PaymentSuccessful message
 * @messageId - Message identifier
 */
suspend fun TdAbsHandler.getPaymentReceiptOrNull(
    chatId: Long = 0L,
    messageId: Long = 0L
) = syncOrNull<PaymentReceipt>(
    GetPaymentReceipt(
        chatId,
        messageId
    )
)

/**
 * Returns information about a successful payment
 *
 * @chatId - Chat identifier of the PaymentSuccessful message
 * @messageId - Message identifier
 */
fun TdAbsHandler.getPaymentReceipt(
    chatId: Long = 0L,
    messageId: Long = 0L,
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

/**
 * Deletes saved credentials for all payment provider bots
 */
suspend fun TdAbsHandler.deleteSavedCredentialsOrNull() = syncOrNull<Ok>(
    DeleteSavedCredentials()
)

/**
 * Deletes saved credentials for all payment provider bots
 */
fun TdAbsHandler.deleteSavedCredentials(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DeleteSavedCredentials(),block = block
)
