package tookox.test

import tooko.td.TdApi.GetMessage
import tooko.td.TdApi.Message
import tookox.core.DATA_20
import tookox.core.client.TdBotHandler
import tookox.core.utils.inlineButton
import tookox.core.utils.make
import tookox.core.utils.syncDelete

class TestForIssue859 : TdBotHandler() {

    override fun onLoad() {

        initFunction("test_0")

        initData(DATA_20)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        sudo make {

            inputText = "TEST"

            replyMarkup = inlineButton {

                newLine {

                    dataButton("EDIT", DATA_20, 0)

                    dataButton("DELETE", DATA_20, 1)

                }

            }

        } sendTo chatId

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        runCatching<Unit> {

            if (subId == 0) {

                syncUnit(GetMessage(chatId, messageId))

                sudo make "EDITED" at chatId syncEditTo messageId

            } else {

                syncDelete(chatId, messageId)

            }

        }.onFailure {

            sudo make it sendTo chatId

        }

    }

}