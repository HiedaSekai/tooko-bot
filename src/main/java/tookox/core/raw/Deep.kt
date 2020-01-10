@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Returns information about a tg:// deep link
 * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
 * Returns a 404 error for unknown links
 * Can be called before authorization
 *
 * @link - The link
 */
suspend fun TdAbsHandler.getDeepLinkInfo(
    link: String? = null
) = sync<DeepLinkInfo>(
    GetDeepLinkInfo(
        link
    )
)

/**
 * Returns information about a tg:// deep link
 * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
 * Returns a 404 error for unknown links
 * Can be called before authorization
 *
 * @link - The link
 */
suspend fun TdAbsHandler.getDeepLinkInfoOrNull(
    link: String? = null
) = syncOrNull<DeepLinkInfo>(
    GetDeepLinkInfo(
        link
    )
)

/**
 * Returns information about a tg:// deep link
 * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
 * Returns a 404 error for unknown links
 * Can be called before authorization
 *
 * @link - The link
 */
fun TdAbsHandler.getDeepLinkInfo(
    link: String? = null,
    block: (suspend CoroutineScope.(DeepLinkInfo) -> Unit)
) = send(
    GetDeepLinkInfo(
        link
    ),block = block
)
