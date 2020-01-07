package tookox.core.bots

import tooko.main.Fn
import tooko.main.Lang
import tooko.main.bots.BotData
import tooko.main.bots.BotImage
import tooko.td.TdApi
import tookox.core.*
import tookox.core.client.*
import tookox.core.utils.*

class BotPanel : TdBotHandler() {

    override fun onLoad() {

        initFunction("bots")

        initData(DATA_2)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (message.fromPrivateOrdelete) {

            showList(Lang.get(userId), userId, chatId, message.id, true)

        }

    }

    fun showList(L: Lang, userId: Int, chatId: Long, messageId: Long, send: Boolean) {

        val bots = BotData.DATA.getAllByField("owner", userId)

        if (bots.isEmpty()) {

            if (send) {

                sudo make L.BOT_NONE sendTo chatId

            } else {

                getMessage(chatId, messageId) {

                    sudo make L.BOT_NONE at chatId editTo messageId

                }

            }

            return

        }

        sudo make {

            inputText = L.BOT_CH

            replyMarkup = inlineButton {

                var line = newLine()

                var index = 0

                bots.forEach {

                    index++

                    if (index + 1 % 4 == 0) {

                        line = newLine()

                    }

                    line.dataButton("@${it.userName}", DATA_2, 1, it.botId.asByteArray)

                }

            }

            sendOrEditTo(send, chatId, messageId)

        }

    }


    fun showPanel(L: Lang, botData: BotData, chatId: Long, messageId: Long, data: Array<ByteArray>) {

        var stat = L.SPLIT

        stat += "\n\n" + L.BOT_MANAGE + " : " + Fn.a("@" + botData.userName, "https://t.me/" + botData.userName)
        stat += "\n" + L.BOT_ID + " : " + Fn.code(botData.botId)
        stat += "\n" + L.BOT_STATUS + " : "

        stat += if (!BotImage.images.containsKey(botData.botId)) {

            L.BOT_STATUS_UNSTARTED

        } else {

            val image = BotImage.images[botData.botId]

            if (image?.status == BotImage.STATUS_RUNNING) {

                L.BOT_STATUS_RUNNING

            } else {

                L.BOT_STATUS_ERROR

            }

        }

        sudo make {

            inputHtml = stat

            replyMarkup = inlineButton {

                newLine {

                    dataButton(L.REFRESH, DATA_2, 2, * data)
                    dataButton(L.BOT_DELETE, DATA_2, 3, * data)

                }

                dataLine(L.BACK, DATA_2, 0)

            }

        } at chatId editTo messageId

    }

    override suspend fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        val L = Lang.get(userId)

        if (subId == 0) {

            sudo confirmTo chatId

            showList(L, userId, chatId, messageId, false)

            return

        }

        val botId = data[0].asInt

        val botData = BotData.DATA.getById(botId)

        if (botData == null || botData.owner != userId) {

            sudo makeAlert L.BOT_INVALID answerTo queryId

            fetchAndDelete(chatId, messageId)

            return

        }

        if (subId == 1) {

            showPanel(L, botData, chatId, messageId, data)

        } else if (subId == 2) {

            showPanel(L, botData, chatId, messageId, data)

            sudo makeAnswer L.REFRESHED answerTo queryId

        } else if (subId == 3) {

            sudo make {

                inputText = L.BOT_DELETE_WARN

                replyMarkup = inlineButton {

                    dataLine(L.BOT_DELETE_CONFIRM, DATA_2, 4)

                    dataLine(L.BACK, DATA_2, 1, *data)

                }

            } at chatId editTo messageId

        } else if (subId == 4) {

            sudo makeAlert L.DELETED answerTo queryId

            BotImage.delete(botData)

            showList(L, userId, chatId, messageId, false)

        }

    }

}