@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Accepts Telegram terms of services
 *
 * @termsOfServiceId - Terms of service identifier
 */
suspend fun TdAbsHandler.acceptTermsOfService(
    termsOfServiceId: String? = null
) = sync<Ok>(
    AcceptTermsOfService(
        termsOfServiceId
    )
)

suspend fun TdAbsHandler.acceptTermsOfServiceOrNull(
    termsOfServiceId: String? = null
) = syncOrNull<Ok>(
    AcceptTermsOfService(
        termsOfServiceId
    )
)

fun TdAbsHandler.acceptTermsOfService(
    termsOfServiceId: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    AcceptTermsOfService(
        termsOfServiceId
    ),block = block
)
