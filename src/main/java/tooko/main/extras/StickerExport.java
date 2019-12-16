package tooko.main.extras;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import tooko.main.Env;
import tooko.main.Fn;
import tooko.main.Img;
import tooko.main.Lang;
import tooko.td.TdApi;
import tooko.td.TdApi.*;
import tooko.td.client.TdClient;
import tooko.td.client.TdException;
import tooko.td.client.TdHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class StickerExport extends TdHandler {

    public int DATA_ID = Fn.DataId._2;
    private HashMap<Integer, Long> pedding = new HashMap<>();

    @Override
    public void onLoad() {

        initData(DATA_ID);

    }

    @Override
    public void onNewMessage(int userId, long chatId, Message message) {

        if (Fn.fromPrivate(message) && message.content instanceof MessageSticker) {

            send(new SendChatAction(message.chatId, new ChatActionUploadingPhoto()));

            Sticker sticker = ((MessageSticker) message.content).sticker;

            TdApi.File stickerFile = sticker.sticker;

            if (!(stickerFile.local.isDownloadingCompleted || stickerFile.local.isDownloadingActive)) {

                try {

                    stickerFile = execute(new DownloadFile(stickerFile.id, 32, 0, 0, true));

                } catch (TdException ex) {

                    if (ex.getCode() == -1) {

                        send(Fn.serverClosed(message));

                    } else {

                        send(Fn.sendText(chatId, Fn.plainText(Lang.get(userId).STICKER_DL_FAILED, ex)));

                    }

                }

            }

            if (sticker.isAnimated) {

                FileUtil.copy(stickerFile.local.path, Env.getPath("cache/sticker"), true);

                send(Fn.sendMessage(message.chatId, new InputMessageDocument(new InputFileLocal(Env.getPath("cache/sticker")), null, Fn.plainText("scource file (.tgs)"))));

            } else {

                final String setName;

                FormattedText caption = Fn.parseHtml(Lang.get(message.senderUserId).STICKER_CAPTION, sticker.sticker.remote.id, sticker.emoji, sticker.setId);

                try {

                    setName = ((StickerSet) execute(new GetStickerSet(sticker.setId))).name;

                } catch (TdException ex) {

                    if (ex.getCode() == -1) {

                        send(Fn.serverClosed(message));

                    } else {

                        send(Fn.sendText(chatId, Fn.plainText(Lang.get(userId).STICKER_DL_FAILED, ex)));

                    }

                    return;

                }

                InputMessagePhoto local = Fn.inputPhoto(new InputFileLocal(stickerFile.local.path), 500, 500, caption);

                InlineKeyboardButton[][] options = sticker.setId == 0 ? null : new InlineKeyboardButton[][]{

                    Fn.inlineDataLine(Lang.get(userId).STICKER_EXPORT, DATA_ID, 0, StrUtil.utf8Bytes(setName))


                };


                send(Fn.sendMessage(message.chatId, options, local));

            }

            Fn.finishEvent();

        }

    }

    @Override
    public void onNewCallbackQuery(final int userId, final long chatId, final long messageId, long queryId, int subId, byte[][] data) {

        if (pedding.containsKey(userId)) {

            send(Fn.answerAlert(queryId, Lang.get(userId).STICKER_EXPORT_WAIT));

            return;

        } else if (pedding.size() >= 5) {

            send(Fn.answerAlert(queryId, Lang.get(userId).STICKER_EXPORT_FLOOD));

            return;

        }

        final StickerSet stickerSet;

        try {

            stickerSet = execute(new SearchStickerSet(StrUtil.utf8Str(data[0])));

        } catch (TdException err) {

            send(Fn.answerAlert(queryId, "{} : {}", err.getCode(), err));

            return;

        }


        if (pedding.containsValue(stickerSet.id)) {

            send(Fn.answerAlert(queryId, Lang.get(userId).STICKER_EXPORT_OTHER));

            return;

        }

        pedding.put(userId, stickerSet.id);

        TdClient.asyncPool.execute(new Runnable() {

                @Override
                public void run() {

                    startDownload(userId, chatId, messageId, stickerSet);

                }
            });


    }

    void startDownload(int userId, long chatId, long messageId, StickerSet stickerSet) {

        send(Fn.deleteMessages(chatId, messageId));

        Message status;

        try {

            status = sync(Fn.sendText(chatId, Fn.parseHtml(Lang.get(userId).STICKER_EXPORT_DL, stickerSet.title, 0, stickerSet.stickers.length)));

        } catch (TdException ex) {

            if (ex.getCode() == -1) {

                send(Fn.serverClosed(chatId, userId));

            }

            return;

        }

        File cachePath = new File(Env.getPath("cache/stickers_pack_export/" + stickerSet.id));

        File zip = new File(cachePath, stickerSet.title + ".zip");

        if (!zip.isFile()) {

            for (int index = 0; index < stickerSet.stickers.length; index++) {

                Sticker sticker = stickerSet.stickers[index];

                TdApi.File stickerFile = sticker.sticker;

                if (stickerFile.local.isDownloadingCompleted || stickerFile.local.isDownloadingActive) continue;

                try {

                    execute(Fn.editText(chatId, status.id, Fn.parseHtml(Lang.get(userId).STICKER_EXPORT_DL, stickerSet.title, index + 1, stickerSet.stickers.length)));

                    sticker.sticker = execute(new DownloadFile(stickerFile.id, 1, 0, 0, true));

                } catch (TdException ex) {

                    if (ex.getCode() == -1) {

                        send(Fn.deleteMessage(status));

                        send(Fn.serverClosed(chatId, userId));

                    } else {

                        send(Fn.sendText(chatId, Fn.plainText(Lang.get(userId).STICKER_DL_FAILED, ex)));

                    }

                    return;

                }


            }

            E(Fn.editText(chatId, status.id, Fn.plainText(Lang.get(userId).STICKER_EXPORT_PACK)));

            File cacheDir = new File(cachePath, stickerSet.title);

            new File(cacheDir, "src").mkdirs();

            for (int index = 0; index < stickerSet.stickers.length; index++) {

                Sticker sticker = stickerSet.stickers[index];

                TdApi.File stickerFile = sticker.sticker;

                File localFile = new File(stickerFile.local.path);

                if (localFile.isFile()) {

                    File src = new File(cacheDir, "src/" + index + ".webp");

                    FileUtil.copy(localFile, src, true);

                    try {

                        BufferedImage image = ImgUtil.read(src);

                        Img img = new Img(image.getWidth(), image.getHeight(), Color.WHITE);

                        img.drawImage(0, 0, image, image.getWidth(), image.getHeight());

                        img.toFile(new File(cacheDir, index + ".jpg"), "jpg");

                    } catch (Exception ignored) {
                    }

                }

            }

            ZipUtil.zip(cacheDir.getPath(), zip.getPath(), true);

            FileUtil.del(cacheDir);

        }

        send(Fn.editText(chatId, status.id, Fn.plainText(Lang.get(userId).STICKER_EXPORT_SEND)));

        send(new SendChatAction(chatId, new ChatActionUploadingDocument()));

        try {

            sync(Fn.sendMessage(chatId, Fn.inputFile(zip, Fn.plainText("https://t.me/addstickers/{}", stickerSet.name))));

        } catch (TdException ex) {

            if (ex.getCode() == -1) {

                I(Fn.sendMessage(chatId, Fn.inputFile(zip, Fn.plainText("https://t.me/addstickers/{}", stickerSet.name))));

            } else {

                send(Fn.sendText(chatId, Fn.plainText(Lang.get(userId).STICKER_DL_FAILED, ex)));

            }

        }

        send(Fn.deleteMessage(status));

        pedding.remove(userId);

    }

}
