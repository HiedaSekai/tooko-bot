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

package tooko.core

import twitter4j.Paging
import twitter4j.Status

val maxPaging = Paging().count(200)

fun maxPaging(since: Long): Paging {

    return Paging().count(200).applyIf(since > 0L) {

        sinceId(since)

    }

}

val Status.link get() = "https://twitter.com/${user.screenName}/status/$id"

val Status.plainText: String
    get() {

        var plainText = text

        urlEntities.forEach {

            plainText = plainText.replace(it.url, "")

        }

        mediaEntities.forEach {

            plainText = plainText.replace(it.url, "")

        }

        hashtagEntities.forEach {

            plainText = plainText.replace("#${it.text}", "")

        }

        symbolEntities.forEach {

            plainText = plainText.replace(it.text, "")

        }

        userMentionEntities.forEach {

            plainText = plainText.replace("@${it.text}", "")

        }

        if (quotedStatusPermalink != null) {

            plainText = text.replace(quotedStatusPermalink.url, "")

        }

        return plainText

    }