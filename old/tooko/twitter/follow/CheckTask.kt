package tooko.twitter.follow

import cn.hutool.core.date.DateUtil
import tooko.main.Fn
import tooko.main.Lang
import tooko.twitter.TwitterAccount
import tooko.twitter.actions.TrackTask
import tooko.twitter.archives.UserA
import tookox.Launcher
import twitter4j.Twitter
import java.util.*

class CheckTask : TimerTask() {

    override fun run() {

        TwitterAccount.DATA.getAllByField("follow", true).forEach {

            fetchInfo(it, it.mkApi())

        }
    }

    private fun fetchInfo(account: TwitterAccount, api: Twitter) {

        val data = AutoData.DATA.getById(account.accountId)

        data?.autoFollowed?.forEach {

            if (it.unFollowedAt != null) {

                return@forEach

            }

            if (TrackTask.followers.arrayIsIn(account.accountId, "array", it.accountId)) {

                return@forEach

            }

            if (System.currentTimeMillis() - it.followedAt > 7 * Fn.d) {

                api.destroyFriendship(it.accountId)

                it.unFollowedAt = System.currentTimeMillis()

                Launcher.twitter.postHtml(account.owner.toLong(), "UnFollowed {}", UserA.show(api, it.accountId).parseInfo(Lang.get(account.owner)))

            }


        }

    }

    companion object {

        private var timer: Timer? = null

        fun start() {

            stop()

            timer = Timer("Twitter FC Task").apply {

                scheduleAtFixedRate(TrackTask(), DateUtil.tomorrow(), 24 * 60 * 60 * 1000L)

            }

        }

        fun stop() {

            timer?.cancel()

            timer = null

        }

    }
}