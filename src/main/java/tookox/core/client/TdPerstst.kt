package tookox.core.client

data class TdPerstst(

        val userId: Int,
        val persistId: Int,
        val subId: Int,
        val allowFuction: Boolean,
        val allowCancel: Boolean,
        val createAt: Int = (System.currentTimeMillis() / 1000).toInt()

)