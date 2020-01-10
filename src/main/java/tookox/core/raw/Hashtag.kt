@file:Suppress(
    "unused"
)

package tookox.core.raw

import kotlinx.coroutines.*
import td.TdApi.*
import tookox.core.client.*

/**
 * Searches for recently used hashtags by their prefix
 *
 * @prefix - Hashtag prefix to search for
 * @limit - The maximum number of hashtags to be returned
 */
suspend fun TdAbsHandler.searchHashtags(
    prefix: String? = null,
    limit: Int = 0
) = sync<Hashtags>(
    SearchHashtags(
        prefix,
        limit
    )
)

suspend fun TdAbsHandler.searchHashtagsOrNull(
    prefix: String? = null,
    limit: Int = 0
) = syncOrNull<Hashtags>(
    SearchHashtags(
        prefix,
        limit
    )
)

fun TdAbsHandler.searchHashtags(
    prefix: String? = null,
    limit: Int = 0,
    block: (suspend CoroutineScope.(Hashtags) -> Unit)
) = send(
    SearchHashtags(
        prefix,
        limit
    ),block = block
)

/**
 * Removes a hashtag from the list of recently used hashtags
 *
 * @hashtag - Hashtag to delete
 */
suspend fun TdAbsHandler.removeRecentHashtag(
    hashtag: String? = null
) = sync<Ok>(
    RemoveRecentHashtag(
        hashtag
    )
)

suspend fun TdAbsHandler.removeRecentHashtagOrNull(
    hashtag: String? = null
) = syncOrNull<Ok>(
    RemoveRecentHashtag(
        hashtag
    )
)

fun TdAbsHandler.removeRecentHashtag(
    hashtag: String? = null,
    block: (suspend CoroutineScope.(Ok) -> Unit)
) = send(
    RemoveRecentHashtag(
        hashtag
    ),block = block
)
