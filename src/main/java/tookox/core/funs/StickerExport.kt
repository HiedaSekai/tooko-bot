package tookox.core.funs

import tooko.main.Lang
import tooko.td.TdApi
import tookox.core.*
import tookox.core.client.TdBotHandler
import tookox.core.td.inlineButton
import tookox.core.td.make

class StickerExport : TdBotHandler() {

    override fun onLoad() = initData(DATA_1)

    override fun onNewMessage(userId: Int, chatId: Long, message: TdApi.Message) {

        if (message.fromPrivate && message.content is TdApi.MessageSticker) {

            val sticker = (message.content as TdApi.MessageSticker).sticker

            if (sticker.isAnimated) {

                sudo make "animated sticker not supported." to chatId

                return

            }

            val stickerFile = sticker.sticker

            if (!stickerFile.local.isDownloadingCompleted) {

                sendRaw(TdApi.SendChatAction(chatId, TdApi.ChatActionTyping()))

                send<TdApi.File>(TdApi.DownloadFile(stickerFile.id, 1, 0, 0, true)) {

                    sendImage(userId, chatId, message, sticker, stickerFile)

                } onError {

                    sudo make it sendTo chatId

                }

            } else {

                sendImage(userId, chatId, message, sticker, stickerFile)

            }


        }

    }

    private fun sendImage(userId: Int, chatId: Long, message: TdApi.Message, sticker: TdApi.Sticker, stickerFile: TdApi.File) {

        val L = Lang.get(userId)

        sudo make {

            inputPhoto = stickerFile.local.path

            captionHtml = L.STICKER_CAPTION.input(stickerFile.remote.id, sticker.emoji, sticker.setId)

            replyMarkup = inlineButton {

                dataLine(L.STICKER_EXPORT, DATA_1,0,sticker.setId.asByteArray)

            }

        } sendTo chatId

    }

    override fun onNewCallbackQuery(userId: Int, chatId: Long, messageId: Long, queryId: Long, subId: Int, data: Array<ByteArray>) {

        if (subId == 0) {

            send<TdApi.StickerSet>(TdApi.GetStickerSet(data[0].asLong)) {



            }

        }

    }

}