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

suspend fun TdAbsHandler.getDeepLinkInfoOrNull(
    link: String? = null
) = syncOrNull<DeepLinkInfo>(
    GetDeepLinkInfo(
        link
    )
)

fun TdAbsHandler.getDeepLinkInfo(
    link: String? = null,
    block: (suspend CoroutineScope.(DeepLinkInfo) -> Unit)
) = send(
    GetDeepLinkInfo(
        link
    ),block = block
)
