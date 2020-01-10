@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns a web page preview by the text of the message
 * Do not call this function too often
 * Returns a 404 error if the web page has no preview
 *
 * @text - Message text with formatting
 */
suspend fun TdAbsHandler.getWebPagePreview(
    text: FormattedText? = null
) = sync<WebPage>(
    GetWebPagePreview(
        text
    )
)

/**
 * Returns a web page preview by the text of the message
 * Do not call this function too often
 * Returns a 404 error if the web page has no preview
 *
 * @text - Message text with formatting
 */
suspend fun TdAbsHandler.getWebPagePreviewOrNull(
    text: FormattedText? = null
) = syncOrNull<WebPage>(
    GetWebPagePreview(
        text
    )
)

/**
 * Returns a web page preview by the text of the message
 * Do not call this function too often
 * Returns a 404 error if the web page has no preview
 *
 * @text - Message text with formatting
 */
fun TdAbsHandler.getWebPagePreview(
    text: FormattedText? = null,
    block: (suspend CoroutineScope.(WebPage) -> Unit)
) = send(
    GetWebPagePreview(
        text
    ),block = block
)

/**
 * Returns an instant view version of a web page if available
 * Returns a 404 error if the web page has no instant view page
 *
 * @url - The web page URL
 * @forceFull - If true, the full instant view for the web page will be returned
 */
suspend fun TdAbsHandler.getWebPageInstantView(
    url: String? = null,
    forceFull: Boolean = false
) = sync<WebPageInstantView>(
    GetWebPageInstantView(
        url,
        forceFull
    )
)

/**
 * Returns an instant view version of a web page if available
 * Returns a 404 error if the web page has no instant view page
 *
 * @url - The web page URL
 * @forceFull - If true, the full instant view for the web page will be returned
 */
suspend fun TdAbsHandler.getWebPageInstantViewOrNull(
    url: String? = null,
    forceFull: Boolean = false
) = syncOrNull<WebPageInstantView>(
    GetWebPageInstantView(
        url,
        forceFull
    )
)

/**
 * Returns an instant view version of a web page if available
 * Returns a 404 error if the web page has no instant view page
 *
 * @url - The web page URL
 * @forceFull - If true, the full instant view for the web page will be returned
 */
fun TdAbsHandler.getWebPageInstantView(
    url: String? = null,
    forceFull: Boolean = false,
    block: (suspend CoroutineScope.(WebPageInstantView) -> Unit)
) = send(
    GetWebPageInstantView(
        url,
        forceFull
    ),block = block
)

/**
 * Disconnects website from the current user's Telegram account
 *
 * @websiteId - Website identifier
 */
suspend fun TdAbsHandler.disconnectWebsite(
    websiteId: Long = 0L
) = sync<Ok>(
    DisconnectWebsite(
        websiteId
    )
)

/**
 * Disconnects website from the current user's Telegram account
 *
 * @websiteId - Website identifier
 */
suspend fun TdAbsHandler.disconnectWebsiteOrNull(
    websiteId: Long = 0L
) = syncOrNull<Ok>(
    DisconnectWebsite(
        websiteId
    )
)

/**
 * Disconnects website from the current user's Telegram account
 *
 * @websiteId - Website identifier
 */
fun TdAbsHandler.disconnectWebsite(
    websiteId: Long = 0L,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DisconnectWebsite(
        websiteId
    ),block = block
)

/**
 * Disconnects all websites from the current user's Telegram account
 */
suspend fun TdAbsHandler.disconnectAllWebsites() = sync<Ok>(
    DisconnectAllWebsites()
)

/**
 * Disconnects all websites from the current user's Telegram account
 */
suspend fun TdAbsHandler.disconnectAllWebsitesOrNull() = syncOrNull<Ok>(
    DisconnectAllWebsites()
)

/**
 * Disconnects all websites from the current user's Telegram account
 */
fun TdAbsHandler.disconnectAllWebsites(
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    DisconnectAllWebsites(),block = block
)
