package tooko.twitter.actions

import cn.hutool.core.thread.ThreadUtil
import tooko.main.Fn
import tooko.td.TdApi
import tooko.twitter.TwitterAccount
import tooko.twitter.TwitterHandler
import tookox.core.link
import tookox.core.maxPaging
import twitter4j.Paging
import twitter4j.Status
import twitter4j.TwitterException
import java.util.*
import java.util.concurrent.Executors

class TelegramLinkScan : TwitterHandler() {

    override fun onLoad() {

        initFunction("tg_scan")

    }

    override fun onFunction(user: TdApi.User, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>, account: TwitterAccount) {

        var depth = if (params.size == 0) 1 else params[0].toInt()

        val page = if (params.size < 2) 1 else params[1].toInt()

        if (depth < 1) {

            postText(chatId, "invalid : depth < 1")

            return

        }

        if (page < 1) {

            postText(chatId, "invalid : page < 1")

            return

        }

        val api = account.mkApi()

        var queue = LinkedHashSet<Long>()
        val exQueue = LinkedHashSet<Long>()

        val stat = postText(chatId, "FETCHING TL...")

        api.getHomeTimeline(Paging().count(200)).forEach {

            queue.add(it.user.id)

            if (it.inReplyToUserId > 0) {

                queue.add(it.inReplyToUserId)

            }

        }

        exQueue.toLongArray().forEach {

            if (it == account.accountId) return@forEach

            api.getUserTimeline(it, Paging().count(200)).forEach {

                queue.add(it.user.id)

                if (it.inReplyToUserId > 0) {

                    queue.add(it.inReplyToUserId)

                }

            }

        }

        depth--

        val pool = Executors.newSingleThreadExecutor()

        val matchUserOrGroup = Regex("https://t\\.me/(joinchat/|[^ /]*( |\$))")

        for (ignored in 0..depth) {

            val newQueue = LinkedHashSet<Long>()

            for ((index, accountId) in queue.withIndex()) {

                if (index.inc() % 10 == 0) {

                    editText(stat, "SEARCHING... ${index.inc()} / ${queue.size} ")

                }

                try {

                    val timeline = LinkedList<Status>()

                    var since = -1L

                    for (pageIndex in 0..page.dec()) {

                        timeline.addAll(api.getUserTimeline(accountId, maxPaging(since)).apply {

                            since = this[size.dec()].id

                        })

                    }

                    timeline.forEach { status: Status ->

                        newQueue.add(status.user.id)

                        if (status.inReplyToUserId > 0) {

                            newQueue.add(status.inReplyToUserId)

                        }

                        if (status.isRetweet && queue.contains(status.retweetedStatus.user.id)) return@forEach

                        status.urlEntities.forEach {

                            if (it.expandedURL.matches(matchUserOrGroup)) {

                                pool.execute {

                                    postText(chatId, true, "${it.expandedURL}\n\n${status.link}")

                                    ThreadUtil.sleep(2 * Fn.s)

                                }

                            }

                        }

                    }

                } catch (ignored: TwitterException) {
                }

            }

            exQueue.addAll(queue)

            queue = newQueue

            queue.removeAll(exQueue)

        }

        editText(stat, "FINISH : SEARCHED ${queue.size} USRES")

    }

}