package tookox.core

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