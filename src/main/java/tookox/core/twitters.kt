package tookox.core

import twitter4j.Paging
import twitter4j.Status

val maxPaging = Paging().count(200)

fun maxPaging(since: Long): Paging {

    return Paging().count(200).apply {

        sinceId(since)

    }

}

val Status.link get() = "https://twitter.com/${user.screenName}/status/$id"