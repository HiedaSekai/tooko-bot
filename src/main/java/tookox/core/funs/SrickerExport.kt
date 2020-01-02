package tookox.core.funs

import tooko.td.TdApi
import tookox.core.DATA_1
import tookox.core.client.TdBotHandler
import tookox.core.fromPrivate
import tookox.core.td.make

class SrickerExport : TdBotHandler() {

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

                send<TdApi.File>(TdApi.DownloadFile(stickerFile.id,1,0,0,true)) {



                } onError {

                    sudo make it sendTo chatId

                }

            }


        }

    }

}