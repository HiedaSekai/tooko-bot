package tookox.core.client

import tooko.td.client.TdException

interface TdBotAbsHandler : TdAbsHandler {

    fun onLoginFailed(ex: TdException) {}

}