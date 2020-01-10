package tookox.core.bots

import td.TdApi
import tookox.core.*
import tookox.core.client.*
import tookox.core.env.*
import tookox.core.utils.*
import com.pengrad.telegrambot.request.GetMe as HttpGetMe

class CreateBot : TdBotHandler() {

    private val createCache = hashMapOf<Int, BotData>()

    override fun onLoad() {

        initFunction("new_bot")

        initPersist(PERSIST_1)

    }

    override suspend fun onFunction(userId: Int, chatId: Long, message: TdApi.Message, function: String, param: String, params: Array<String>, originParams: Array<String>) {

        if (message.fromPrivateOrdelete) {

            val L = userId.langFor

            if (!Env.isAdmin(userId)) {

                if (!Env.PUBLIC_BOT_CREATE) {

                    sudo make L.ERR_PERSIONAL sendTo chatId

                    return

                }

                val count = BotData.DATA.countByField("owner", userId)

                if (count >= Env.BOT_CREATE_MAX) {

                    sudo make L.ERR_LIMIT sendTo chatId

                    return

                }

            }

            writePersist(userId, PERSIST_1)

            sudo makeHtml L.BOT_INPUT_TOKEN sendTo chatId

        }

    }

    override suspend fun onPersistMessage(userId: Int, chatId: Long, message: TdApi.Message, subId: Int) {

        val L = Lang.get(userId)

        removePersist(userId)

        if (subId == 0) {

            val botToken = message.text

            if (botToken == null) {

                onSendCanceledMessage(userId)

                return

            }

            httpSend(botToken, HttpGetMe()) {

                val botMe = it.user()

                val bot = BotData()

                bot.botId = botMe.id()

                if (BotImage.images.containsKey(bot.botId)) {

                    sudo make L.BOT_EXISTS sendTo chatId

                    return@httpSend

                }

                bot.userName = botMe.username()
                bot.botToken = botToken
                bot.owner = userId

                createCache[userId] = bot

                writePersist(userId, PERSIST_1, 1)

                sudo make {

                    inputText = L.BOT_N_TYPE

                    replyMarkup = keyboadButton {

                        textLine(L.BOT_TYPE_PM)

                    }

                } sendTo chatId

            } onError {

                sudo make L.BOT_TOKEN_INVALID.input(it.message) sendTo chatId

            }


        } else if (subId == 1) {

            val type = message.text

            val data = createCache.remove(userId)!!

            if (L.BOT_TYPE_PM == type) {

                data.type = 1

            } else {

                onSendCanceledMessage(userId)

                return

            }

            BotData.DATA.setById(data.botId, data)

            BotImage.start(data)

            sudo make {

                inputHtml = L.BOT_CREATED.input(data.userName)

                replyMarkup = removeKeyboard()

            } sendTo chatId

        }

    }

}