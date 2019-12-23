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

    override fun onFunction(user: TdApi.User, chatId: Long, message: TdApi.Message, function: String?, param: String, params: Array<String>, originParams: Array<String>, account: TwitterAccount) {

        val api = account.mkApi()

        val queue = LinkedHashSet<Long>()

        val stat = postText(chatId, "FETCHING TL...")

        api.getHomeTimeline(Paging().count(200)).forEach {

            queue.add(it.user.id)

            if (it.inReplyToUserId > 0) {

                queue.add(it.inReplyToUserId)

            }

        }

        queue.remove(account.accountId)

        queue.toLongArray().forEach {

            api.getUserTimeline(it, Paging().count(200)).forEach {

                queue.add(it.user.id)

                if (it.inReplyToUserId > 0) {

                    queue.add(it.inReplyToUserId)

                }

            }

        }

        queue.remove(account.accountId)

        val pool = Executors.newSingleThreadExecutor()

        for ((index, accountId) in queue.withIndex()) {

            if (index.inc() % 10 == 0) {

                editText(stat, "SEARCHING... ${index.inc()} / ${queue.size} ")

            }

            try {

                api.getUserTimeline(accountId, maxPaging).forEach { status: Status ->

                    if (status.isRetweet && queue.contains(status.retweetedStatus.user.id)) return@forEach

                    status.urlEntities.forEach {

                        if (it.expandedURL.contains("https://t.me/joinchat/")) {

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

        editText(stat, "FINISH : SEARCHED ${queue.size} USRES")

    }

}