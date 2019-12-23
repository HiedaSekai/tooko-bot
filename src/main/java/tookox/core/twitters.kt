package tookox.core

import twitter4j.Paging
import twitter4j.Status

val maxPaging = Paging().count(200)

val Status.link get() = "https://twitter.com/${user.screenName}/status/$id"