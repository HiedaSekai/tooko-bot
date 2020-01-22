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
 * Searches for recently used hashtags by their prefix
 *
 * @prefix - Hashtag prefix to search for
 * @limit - The maximum number of hashtags to be returned
 */
suspend fun TdAbsHandler.searchHashtags(
    prefix: String? = null,
    limit: Int
) = sync<Hashtags>(
    SearchHashtags(
        prefix,
        limit
    )
)

suspend fun TdAbsHandler.searchHashtagsOrNull(
    prefix: String? = null,
    limit: Int
) = syncOrNull<Hashtags>(
    SearchHashtags(
        prefix,
        limit
    )
)

fun TdAbsHandler.searchHashtags(
    prefix: String? = null,
    limit: Int,
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
