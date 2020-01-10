@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Adds a proxy server for network requests
 * Can be called before authorization
 *
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @enable - True, if the proxy should be enabled
 * @type - Proxy type
 */
suspend fun TdAbsHandler.addProxy(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = sync<Proxy>(
    AddProxy(
        server,
        port,
        enable,
        type
    )
)

suspend fun TdAbsHandler.addProxyOrNull(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull<Proxy>(
    AddProxy(
        server,
        port,
        enable,
        type
    )
)

fun TdAbsHandler.addProxy(
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null,
    block: (suspend CoroutineScope.(Proxy) -> Unit)
) = send(
    AddProxy(
        server,
        port,
        enable,
        type
    ),block = block
)

/**
 * Edits an existing proxy server for network requests
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 * @server - Proxy server IP address
 * @port - Proxy server port
 * @enable - True, if the proxy should be enabled
 * @type - Proxy type
 */
suspend fun TdAbsHandler.editProxy(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = sync<Proxy>(
    EditProxy(
        proxyId,
        server,
        port,
        enable,
        type
    )
)

suspend fun TdAbsHandler.editProxyOrNull(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null
) = syncOrNull<Proxy>(
    EditProxy(
        proxyId,
        server,
        port,
        enable,
        type
    )
)

fun TdAbsHandler.editProxy(
    proxyId: Int,
    server: String? = null,
    port: Int,
    enable: Boolean,
    type: ProxyType? = null,
    block: (suspend CoroutineScope.(Proxy) -> Unit)
) = send(
    EditProxy(
        proxyId,
        server,
        port,
        enable,
        type
    ),block = block
)

/**
 * Enables a proxy
 * Only one proxy can be enabled at a time
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdAbsHandler.enableProxy(
    proxyId: Int
) = sync<Ok>(
    EnableProxy(
        proxyId
    )
)

suspend fun TdAbsHandler.enableProxyOrNull(
    proxyId: Int
) = syncOrNull<Ok>(
    EnableProxy(
        proxyId
    )
)

fun TdAbsHandler.enableProxy(
    proxyId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    EnableProxy(
        proxyId
    ),block = block
)

/**
 * Disables the currently enabled proxy
 * Can be called before authorization
 */
suspend fun TdAbsHandler.disableProxy() = sync<Ok>(
    DisableProxy()
)

suspend fun TdAbsHandler.disableProxyOrNull() = syncOrNull<Ok>(
    DisableProxy()
)

fun TdAbsHandler.disableProxy(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DisableProxy(),block = block
)

/**
 * Removes a proxy server
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 */
suspend fun TdAbsHandler.removeProxy(
    proxyId: Int
) = sync<Ok>(
    RemoveProxy(
        proxyId
    )
)

suspend fun TdAbsHandler.removeProxyOrNull(
    proxyId: Int
) = syncOrNull<Ok>(
    RemoveProxy(
        proxyId
    )
)

fun TdAbsHandler.removeProxy(
    proxyId: Int,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveProxy(
        proxyId
    ),block = block
)

/**
 * Computes time needed to receive a response from a Telegram server through a proxy
 * Can be called before authorization
 *
 * @proxyId - Proxy identifier
 *            Use 0 to ping a Telegram server without a proxy
 */
suspend fun TdAbsHandler.pingProxy(
    proxyId: Int
) = sync<Seconds>(
    PingProxy(
        proxyId
    )
)

suspend fun TdAbsHandler.pingProxyOrNull(
    proxyId: Int
) = syncOrNull<Seconds>(
    PingProxy(
        proxyId
    )
)

fun TdAbsHandler.pingProxy(
    proxyId: Int,
    block: (suspend CoroutineScope.(Seconds) -> Unit)
) = send(
    PingProxy(
        proxyId
    ),block = block
)
