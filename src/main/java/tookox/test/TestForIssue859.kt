package tookox.test

import tooko.td.TdApi.Message
import tookox.core.DATA_20
import tookox.core.client.TdBotHandler
import tookox.core.td.delete
import tookox.core.td.inlineButton
import tookox.core.td.make

class TestForIssue859 : TdBotHandler() {

    override fun onLoad() {

        initFunction("test_0")

        initData(DATA_20)

    }

    override fun onFunction(userId: Int, chatId: Long, message: Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

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