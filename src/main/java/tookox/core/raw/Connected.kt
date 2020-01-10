@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns all website where the current user used Telegram to log in
 */
suspend fun TdAbsHandler.getConnectedWebsites() = sync<ConnectedWebsites>(
    GetConnectedWebsites()
)

/**
 * Returns all website where the current user used Telegram to log in
 */
suspend fun TdAbsHandler.getConnectedWebsitesOrNull() = syncOrNull<ConnectedWebsites>(
    GetConnectedWebsites()
)

/**
 * Returns all website where the current user used Telegram to log in
 */
fun TdAbsHandler.getConnectedWebsites(
    block: (suspend CoroutineScope.(ConnectedWebsites) -> Unit)
) = send(
    GetConnectedWebsites(),block = block
)
