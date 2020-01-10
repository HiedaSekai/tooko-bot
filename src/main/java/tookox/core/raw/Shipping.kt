@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

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
