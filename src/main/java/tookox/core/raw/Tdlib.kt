@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Sets the parameters for TDLib initialization
 * Works only when the current authorization state is authorizationStateWaitTdlibParameters
 *
 * @parameters - Parameters
 */
suspend fun TdAbsHandler.setTdlibParameters(
    parameters: TdlibParameters? = null
) = sync<Ok>(
    SetTdlibParameters(
        parameters
    )
)

/**
 * Sets the parameters for TDLib initialization
 * Works only when the current authorization state is authorizationStateWaitTdlibParameters
 *
 * @parameters - Parameters
 */
suspend fun TdAbsHandler.setTdlibParametersOrNull(
    parameters: TdlibParameters? = null
) = syncOrNull<Ok>(
    SetTdlibParameters(
        parameters
    )
)

/**
 * Sets the parameters for TDLib initialization
 * Works only when the current authorization state is authorizationStateWaitTdlibParameters
 *
 * @parameters - Parameters
 */
fun TdAbsHandler.setTdlibParameters(
    parameters: TdlibParameters? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    SetTdlibParameters(
        parameters
    ),block = block
)
