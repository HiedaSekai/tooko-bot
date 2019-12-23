package tooko.twitter.actions

import tooko.td.TdApi
import tooko.twitter.TwitterAccount
import tooko.twitter.TwitterHandler
import tookox.core.link
import tookox.core.maxPaging
import twitter4j.Paging
import twitter4j.Status
import twitter4j.TwitterException
import java.util.*

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


        for ((index, accountId) in queue.withIndex()) {

            editText(stat, "SEARCHING... ${index.inc()} / ${queue.size} ")

            try {

                api.getUserTimeline(accountId, maxPaging).forEach { status: Status ->

                    status.urlEntities.forEach {

                        if (it.expandedURL.contains("https://t.me")) {

                            postText(chatId, "${it.expandedURL}\n\n${status.link}")

                        }

                    }

                }

            } catch (ignored: TwitterException) {
            }

        }

        editText(stat, "FINISH : SEARCHED ${queue.size} USRES")

    }

}