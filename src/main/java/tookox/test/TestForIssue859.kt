package tookox.test

import tooko.td.TdApi
import tookox.core.DATA_2
import tookox.core.client.TdBotHandler
import tookox.core.td.delete
import tookox.core.td.inlineButton
import tookox.core.td.make

class TestForIssue859 : TdBotHandler() {

    override fun onLoad() {

        initFunction("test_0")

        initData(DATA_2)

    }

    override fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        sudo make {

            inputText = "TEST"

            replyMarkup = inlineButton {

                newLine {

                    dataButton("EDIT", DATA_2, 0)

                    dataButton("DELETE", DATA_2, 1)

                }

            }

        } sendTo chatId

    }

    override fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        if (subId == 0) {

            sudo make "EDITED" at chatId editTo messageId onError {

                sudo make it sendTo chatId

            }

        } else {

            delete(chatId, messageId) onError {

                sudo make it sendTo chatId

            }

        }

    }

}