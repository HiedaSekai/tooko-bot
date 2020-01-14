/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tookox.core.env;

import cn.hutool.core.collection.*;
import cn.hutool.core.util.*;
import twitter4j.*;

import java.io.*;
import java.util.*;

public class Fn {

    /*

    public static long s = 1000L;
    public static long m = 60 * s;
    public static long h = 60 * m;
    public static long d = 24 * h;
    public static long M = 30 * d;

    public static byte[] num2byte(Number integer) {

        return BigInteger.valueOf(integer.longValue()).toByteArray();

    }

    public static long byte2long(byte[] integer) {

        return new BigInteger(integer).longValue();

    }

    public static int byte2int(byte[] integer) {

        return new BigInteger(integer).intValue();

    }

    public static <T> LinkedList<T> newList() {

        return new LinkedList<>();

    }

    public static <E> void addAll(Collection<E> collection, E[] array) {

        Collections.addAll(collection, array);

    }

    public static <T> T[] shift(T[] array) {

        return shift(array, 1);

    }

    public static <T> T[] shift(T[] array, int size) {

        return ArrayUtil.sub(array, size, array.length);

    }


    public static <T> Collection<T> shift(Collection<T> array) {

        return shift(array, 1);

    }

    public static <T> Collection<T> shift(Collection<T> array, int size) {

        return CollectionUtil.sub(array, size, array.size());

    }


    public static KeyboardButton[][] vertical(String... buttons) {

        KeyboardButton[][] keyboard = new KeyboardButton[buttons.length][];

        for (int index = 0; index < buttons.length; index++) {

            keyboard[index] = keyboardTextLine(buttons[index]);

        }

        return keyboard;

    }

    public static KeyboardButton[][] horizontal(String... buttons) {

        KeyboardButton[] keyboard = new KeyboardButton[buttons.length];

        for (int index = 0; index < buttons.length; index++) {

            keyboard[index] = keyboardText(buttons[index]);

        }

        return new KeyboardButton[][]{keyboard};

    }

    public static SendMessage sendText(long chatId, KeyboardArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static KeyboardButton keyboardText(String text) {

        return new KeyboardButton(text, new KeyboardButtonTypeText());

    }

    public static KeyboardButton[] keyboardTextLine(String text, String... textes) {

        KeyboardButton[] textLine = new KeyboardButton[1 + textes.length];

        textLine[0] = keyboardText(text);

        for (int index = 0; index < textes.length; index++) {

            textLine[index + 1] = keyboardText(textes[index]);

        }

        return textLine;

    }

    public static InlineKeyboardButton inlineUrl(String text, String url) {

        return new InlineKeyboardButton(text, new InlineKeyboardButtonTypeUrl(url));

    }

    public static InlineKeyboardButton[] inlineUrlLine(String text, String url) {

        return new InlineKeyboardButton[]{inlineUrl(text, url)};

    }

    public static InlineKeyboardButton inlineLoginUrl(String text, String url, int id, String forwardText) {

        return new InlineKeyboardButton(text, new InlineKeyboardButtonTypeLoginUrl(url, id, forwardText));

    }

    public static InlineKeyboardButton[] inlineLoginUrlLine(String text, String url, int id, String forwardText) {

        return new InlineKeyboardButton[]{inlineLoginUrl(text, url, id, forwardText)};

    }

    public static InlineKeyboardButton inlineGame(String text) {

        return new InlineKeyboardButton(text, new InlineKeyboardButtonTypeCallbackGame());

    }

    public static InlineKeyboardButton[] inlineGameLine(String text) {

        return new InlineKeyboardButton[]{inlineGame(text)};

    }

    public static InlineKeyboardButton inlineSwitch(String text, String query, boolean inCurrentChat) {

        return new InlineKeyboardButton(text, new InlineKeyboardButtonTypeSwitchInline(query, inCurrentChat));

    }

    public static InlineKeyboardButton[] inlineSwitchLine(String text, String query, boolean inCurrentChat) {

        return new InlineKeyboardButton[]{inlineSwitch(text, query, inCurrentChat)};

    }

    public static InlineKeyboardButton inlineData(String text, int id, int subId, byte[]... dataArray) {

        return new InlineKeyboardButton(text, new InlineKeyboardButtonTypeCallback(mkData(id, subId, dataArray)));

    }

    public static InlineKeyboardButton[] inlineDataLine(String text, int id, int subId, byte[]... dataArray) {

        return new InlineKeyboardButton[]{inlineData(text, id, subId, dataArray)};

    }

    public static SendMessage serverClosed(Message message) {

        return serverClosed(message.chatId, message.senderUserId);

    }

    public static SendMessage serverClosed(long chatId, int userId) {

        return sendText(chatId, plainText(Lang.get(userId).SERVER_CLOSING));

    }

    public static AnswerCallbackQuery answerServerClosed(long queryId, int userId) {

        return answerAlert(queryId, Lang.get(userId).SERVER_CLOSING);

    }



    public static boolean isPrivate(long chatId) {

        return chatId > 0L;

    }

    public static boolean isBasicGroup(long chatId) {

        return chatId > -1000000000000L;

    }

    public static boolean isSuperGroup(long chatId) {

        return chatId < -1000000000000L;

    }

    public static boolean fromPrivate(Message message) {

        return message.chatId > 0L;

    }

    public static boolean fromBasicGroup(Message message) {

        return message.chatId > -1000000000000L;

    }

    public static boolean fromSuperGroup(Message message) {

        return message.chatId < -1000000000000L && !message.isChannelPost;

    }

    public static boolean fromChannel(Message message) {

        return message.isChannelPost;

    }

    public static InputMessageDocument inputFile(File file) {

        return inputFile(file, null);

    }

    public static InputMessageDocument inputFile(File file, FormattedText caption) {

        return inputFile(new InputFileLocal(file.getPath()), caption);

    }

    public static InputMessageDocument inputFile(InputFile file) {

        return inputFile(file, null);

    }

    public static InputMessageDocument inputFile(InputFile file, FormattedText caption) {

        return new InputMessageDocument(file, null, caption);

    }

    public static InputMessagePhoto inputPhoto(InputFile file) {

        return inputPhoto(file, null);

    }

    public static InputMessagePhoto inputPhoto(InputFile file, int width, int height) {

        return inputPhoto(file, width, height, null);

    }

    public static InputMessagePhoto inputPhoto(InputFile file, FormattedText caption) {

        return inputPhoto(file, caption, 0);

    }

    public static InputMessagePhoto inputPhoto(InputFile file, int width, int height, FormattedText caption) {

        return inputPhoto(file, width, height, caption, 0);

    }

    public static InputMessagePhoto inputPhoto(InputFile file, FormattedText caption, int ttl) {

        return inputPhoto(file, 0, 0, caption, 0);

    }

    public static InputMessagePhoto inputPhoto(InputFile file, int width, int height, FormattedText caption, int ttl) {

        return new InputMessagePhoto(file, null, new int[0], width, height, caption, ttl);

    }

    public static FormattedText plainText(String text, Object... params) {

        return new FormattedText(StrUtil.format(text, params), new TextEntity[0]);

    }


    public static FormattedText parseHtml(String text, Object... params) {

        return MessageFactoryKt.getAsHtml(StrUtil.format(text, params));

    }

    public static FormattedText parseMarkdown(String text, java.lang.Object... params) {

        return Launcher.INSTANCE.sync(new ParseTextEntities(StrUtil.format(text, params), new TextParseModeMarkdown()));

    }

    public static SendMessage sendText(long chatId, FormattedText inputMessageContent) {

        return sendText(chatId, (ReplyMarkup) null, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, (ReplyMarkup) null, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, (ReplyMarkup) null, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, KeyboardArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, InlineArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, ReplyMarkup replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, KeyboardArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, ReplyMarkup replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, 0, replyMarkup, inputMessageContent, enableLinkPreView);

    }


    public static SendMessage sendText(long chatId, long replyToMessageId, InlineArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, ReplyMarkup replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent, enableLinkPreView);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, ReplyMarkup replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreView) {

        return sendText(chatId, replyToMessageId, false, false, replyMarkup, inputMessageContent, enableLinkPreView);

    }


    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, false);

    }


    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineArray replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, ReplyMarkup replyMarkup, FormattedText inputMessageContent) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, ReplyMarkup replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return sendText(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static SendMessage sendSticker(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardArray replyMarkup, String fileId) {

        InputMessageSticker InputSticker = new InputMessageSticker(new InputFileRemote(fileId), null, 500, 500);

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, InputSticker);

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static SendMessage sendText(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, ReplyMarkup replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static SendMessage sendSticker(long chatId, String fileId) {

        return sendSticker(chatId, 0, false, false, (ReplyMarkup) null, fileId);

    }

    public static SendMessage sendMessage(long chatId, KeyboardArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendSticker(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineArray replyMarkup, String fileId) {

        InputMessageSticker InputSticker = new InputMessageSticker(new InputFileRemote(fileId), null, 500, 500);

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, InputSticker);

    }

    public static SendMessage sendSticker(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineKeyboardButton[][] replyMarkup, String fileId) {

        InputMessageSticker InputSticker = new InputMessageSticker(new InputFileRemote(fileId), null, 500, 500);

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, InputSticker);

    }

    public static SendMessage sendSticker(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardButton[][] replyMarkup, String fileId) {

        InputMessageSticker InputSticker = new InputMessageSticker(new InputFileRemote(fileId), null, 500, 500);

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, InputSticker);

    }

    public static SendMessage sendSticker(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, ReplyMarkup replyMarkup, String fileId) {

        InputMessageSticker InputSticker = new InputMessageSticker(new InputFileRemote(fileId), null, 500, 500);

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup, InputSticker);

    }

    public static SendMessage sendMessage(long chatId, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, (ReplyMarkup) null, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, KeyboardArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, KeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, 0, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, (ReplyMarkup) null, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, KeyboardArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, KeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup != null ? replyMarkup.toArray() : null, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, KeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, false, replyMarkup, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, replyMarkup != null ? replyMarkup.toArray() : null, inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, new ReplyMarkupInlineKeyboard(replyMarkup), inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, KeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return sendMessage(chatId, replyToMessageId, enableNotification, fromBackground, new ReplyMarkupShowKeyboard(replyMarkup, true, true, true), inputMessageContent);

    }

    public static SendMessage sendMessage(long chatId, long replyToMessageId, boolean enableNotification, boolean fromBackground, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

        return new SendMessage(chatId, replyToMessageId, new SendMessageOptions(!enableNotification, fromBackground, null), replyMarkup, inputMessageContent);

    }

    public static EditMessageText editText(Message message, FormattedText inputMessageContent) {

        return editText(message.chatId, message.id, inputMessageContent);

    }

    public static EditMessageText editText(long chatId, long messageId, FormattedText inputMessageContent) {

        return editText(chatId, messageId, (InlineArray) null, inputMessageContent);

    }

    public static EditMessageText editText(Message message, FormattedText inputMessageContent, boolean enableLinklPreview) {

        return editText(message.chatId, message.id, inputMessageContent, enableLinklPreview);

    }

    public static EditMessageText editText(long chatId, long messageId, FormattedText inputMessageContent, boolean enableLinklPreview) {

        return editText(chatId, messageId, (InlineArray) null, inputMessageContent, enableLinklPreview);

    }

    public static EditMessageText editText(Message message, InlineArray replyMarkup, FormattedText inputMessageContent) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent);

    }

    public static EditMessageText editText(Message message, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent);

    }


    public static EditMessageText editText(long chatId, long messageId, InlineArray replyMarkup, FormattedText inputMessageContent) {

        return editText(chatId, messageId, replyMarkup, inputMessageContent, false);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent) {

        return editText(chatId, messageId, replyMarkup, inputMessageContent, false);

    }

    public static EditMessageText editText(Message message, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent, enableLinkPreview);

    }

    public static EditMessageText editText(Message message, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent, enableLinkPreview);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return editText(chatId, messageId, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview) {

        return editText(chatId, messageId, replyMarkup, inputMessageContent, enableLinkPreview, false);

    }

    public static EditMessageText editText(Message message, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent, enableLinkPreview, clearDraft);

    }

    public static EditMessageText editText(Message message, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent, enableLinkPreview, clearDraft);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineArray replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return editText(chatId, messageId, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static EditMessageText editText(long chatId, long messageId, InlineKeyboardButton[][] replyMarkup, FormattedText inputMessageContent, boolean enableLinkPreview, boolean clearDraft) {

        return editText(chatId, messageId, replyMarkup, new InputMessageText(inputMessageContent, !enableLinkPreview, clearDraft));

    }

    public static EditMessageText editText(Message message, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent);

    }

    public static EditMessageText editText(Message message, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return editText(message.chatId, message.id, replyMarkup, inputMessageContent);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineArray replyMarkup, InputMessageContent inputMessageContent) {

        return editText(chatId, messageId, replyMarkup != null ? replyMarkup.toArray() : null, inputMessageContent);

    }

    public static EditMessageText editText(long chatId, long messageId, InlineKeyboardButton[][] replyMarkup, InputMessageContent inputMessageContent) {

        return new EditMessageText(chatId, messageId, replyMarkup == null ? null : new ReplyMarkupInlineKeyboard(replyMarkup), inputMessageContent);

    }

    public static EditMessageReplyMarkup editMarkup(Message message, InlineArray replyMarkup) {

        return editMarkup(message.chatId, message.id, replyMarkup != null ? replyMarkup.toArray() : null);

    }

    public static EditMessageReplyMarkup editMarkup(Message message, InlineKeyboardButton[][] replyMarkup) {

        return editMarkup(message.chatId, message.id, replyMarkup);

    }

    public static EditMessageReplyMarkup editMarkup(long chatId, long messageId, InlineKeyboardButton[][] replyMarkup) {

        return new EditMessageReplyMarkup(chatId, messageId, replyMarkup == null ? null : new ReplyMarkupInlineKeyboard(replyMarkup));

    }

    public static AnswerCallbackQuery answerText(long queryId, String text, Object... params) {

        return answerText(queryId, 0, text, params);
    }

    public static AnswerCallbackQuery answerText(long queryId, int cacheTime, String text, Object... params) {

        return new AnswerCallbackQuery(queryId, StrUtil.format(text, params), false, null, cacheTime);

    }

    public static AnswerCallbackQuery answerAlert(long queryId, String alert, Object... params) {

        return answerAlert(queryId, 0, alert, params);
    }

    public static AnswerCallbackQuery answerAlert(long queryId, int cacheTime, String alert, Object... params) {

        return new AnswerCallbackQuery(queryId, StrUtil.format(alert, params), true, null, cacheTime);

    }

    public static AnswerCallbackQuery answerUrl(long queryId, String url, Object... params) {

        return answerUrl(queryId, 0, url, params);

    }

    public static AnswerCallbackQuery answerUrl(long queryId, int cacheTime, String url, Object... params) {

        return new AnswerCallbackQuery(queryId, null, false, StrUtil.format(url, params), cacheTime);

    }

    public static AnswerCallbackQuery answerConfirm(long queryId, int cacheTime) {

        return new AnswerCallbackQuery(queryId, null, false, null, cacheTime);

    }

    public static AnswerCallbackQuery answerConfirm(long queryId) {

        return answerConfirm(queryId, 0);

    }

    public static DeleteMessages deleteMessage(Message message) {

        return deleteMessages(message.chatId, message.id);

    }

    public static DeleteMessages deleteMessages(long chatId, long... messageIds) {

        return new DeleteMessages(chatId, messageIds, true);

    }

    public static ForwardMessages forwardMessages(long chatId, long fromChatId, long... messageIds) {

        return new ForwardMessages(chatId, fromChatId, messageIds, null, false, false, false);

    }

    public static InputMessageForwarded forward(Message message) {

        return forward(message.chatId, message.id);

    }

    public static InputMessageForwarded forward(long chatId, long messageId) {

        return new InputMessageForwarded(chatId, messageId, false, false, false);

    }

    public static InputMessageContent convertToInput(MessageContent message) {

        if (message instanceof MessageText) {

            MessageText content = (MessageText) message;

            return new InputMessageText(content.text, content.webPage == null, false);

        } else if (message instanceof MessageAnimation) {

            MessageAnimation content = (MessageAnimation) message;

            InputFileRemote file = new InputFileRemote(content.animation.animation.remote.id);

            InputThumbnail thumbnail = content.animation.thumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.animation.thumbnail.photo.remote.id), content.animation.thumbnail.width, content.animation.thumbnail.height);

            return new InputMessageAnimation(file, thumbnail, content.animation.duration, content.animation.width, content.animation.height, content.caption);

        } else if (message instanceof MessageAudio) {

            MessageAudio content = (MessageAudio) message;

            InputFileRemote file = new InputFileRemote(content.audio.audio.remote.id);

            InputThumbnail thumbnail = content.audio.albumCoverThumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.audio.albumCoverThumbnail.photo.remote.id), content.audio.albumCoverThumbnail.width, content.audio.albumCoverThumbnail.height);

            return new InputMessageAudio(file, thumbnail, content.audio.duration, content.audio.title, content.audio.performer, content.caption);

        } else if (message instanceof MessageDocument) {

            MessageDocument content = (MessageDocument) message;

            InputFileRemote file = new InputFileRemote(content.document.document.remote.id);

            InputThumbnail thumbnail = content.document.thumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.document.thumbnail.photo.remote.id), content.document.thumbnail.width, content.document.thumbnail.height);

            return new InputMessageDocument(file, thumbnail, content.caption);

        } else if (message instanceof MessagePhoto) {

            MessagePhoto content = (MessagePhoto) message;

            InputFileRemote file = new InputFileRemote(content.photo.sizes[0].photo.remote.id);

            return new InputMessagePhoto(file, null, null, content.photo.sizes[0].width, content.photo.sizes[0].height, content.caption, 0);

        } else if (message instanceof MessageSticker) {

            MessageSticker content = (MessageSticker) message;

            InputFileRemote file = new InputFileRemote(content.sticker.sticker.remote.id);

            InputThumbnail thumbnail = content.sticker.thumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.sticker.thumbnail.photo.remote.id), content.sticker.thumbnail.width, content.sticker.thumbnail.height);

            return new InputMessageSticker(file, thumbnail, content.sticker.width, content.sticker.height);

        } else if (message instanceof MessageVideo) {

            MessageVideo content = (MessageVideo) message;

            InputFileRemote file = new InputFileRemote(content.video.video.remote.id);

            InputThumbnail thumbnail = content.video.thumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.video.thumbnail.photo.remote.id), content.video.thumbnail.width, content.video.thumbnail.height);

            return new InputMessageVideo(file, thumbnail, null, content.video.duration, content.video.width, content.video.height, content.video.supportsStreaming, content.caption, 0);

        } else if (message instanceof MessageVideoNote) {

            MessageVideoNote content = (MessageVideoNote) message;

            InputFileRemote file = new InputFileRemote(content.videoNote.video.remote.id);

            InputThumbnail thumbnail = content.videoNote.thumbnail == null ? null : new InputThumbnail(new InputFileRemote(content.videoNote.thumbnail.photo.remote.id), content.videoNote.thumbnail.width, content.videoNote.thumbnail.height);

            return new InputMessageVideoNote(file, thumbnail, content.videoNote.duration, content.videoNote.length);

        } else if (message instanceof MessageLocation) {

            MessageLocation content = (MessageLocation) message;

            return new InputMessageLocation(content.location, content.livePeriod);

        } else if (message instanceof MessageVoiceNote) {

            MessageVoiceNote content = (MessageVoiceNote) message;

            InputFileRemote file = new InputFileRemote(content.voiceNote.voice.remote.id);

            return new InputMessageVoiceNote(file, content.voiceNote.duration, content.voiceNote.waveform, content.caption);

        } else if (message instanceof MessageVenue) {

            MessageVenue content = (MessageVenue) message;

            return new InputMessageVenue(content.venue);

        } else if (message instanceof MessageContact) {

            MessageContact content = (MessageContact) message;

            return new InputMessageContact(content.contact);

        }

        return null;

    }

    public static String displayName(User user) {

        String name = user.firstName;

        if (!StrUtil.isBlank(user.lastName)) {

            name += " " + user.lastName;

        }

        return name;

    }


    public static String b(Object text) {

        return "<b>" + HtmlUtil.escape(text == null ? "" : text.toString()) + "</b>";

    }

    public static String i(Object text) {

        return "<i>" + HtmlUtil.escape(text == null ? "" : text.toString()) + "</i>";

    }

    public static String a(String link) {

        if (link == null) return "";

        if (link.startsWith("http")) link = URLUtil.url(link).toString();

        return "<a href=\"" + link + "\">" + HtmlUtil.escape(link) + "</a>";

    }

    public static String a(String text, String href) {

        if (href == null) return text;

        if (href.startsWith("http")) href = URLUtil.url(href).toString();

        return "<a href=\"" + href + "\">" + HtmlUtil.escape(text) + "</a>";

    }

    public static String mention(User user) {

        return user == null ? "" : mention(displayName(user), user.id);

    }

    public static String mention(String text, int user) {

        return a(text, "tg://user?id=" + user);

    }

    public static String code(Object code) {

        return "<code>" + HtmlUtil.escape(code == null ? "null" : code.toString()) + "</code>";

    }

    public static Document getFile(Message message) {

        return message.content instanceof MessageDocument ? ((MessageDocument) message.content).document : null;

    }

    public static String getText(Message message) {

        return message.content instanceof MessageText ? ((MessageText) message.content).text.text : null;

    }

    public static InputMessageDocument mkCacheFile(String name, String content, FormattedText caption) {

        String cacheFile = Env.getPath("cache/cache_file/" + UUID.fastUUID() + "/" + name);

        FileUtil.writeUtf8String(content, cacheFile);

        return new InputMessageDocument(new InputFileLocal(cacheFile), null, caption);

    }

    public static void deleteCacheFile(InputMessageDocument file) {

        File cacheFile = new File(((InputFileLocal) file.document).path);

        FileUtil.del(cacheFile.getParent());

    }

    public static long[] toArray(Collection<Long> array) {

        return ArrayUtil.unWrap(array.toArray(new Long[0]));

    }

    public static <T> T[] toArray(Collection<T> array, Class<T> clazz) {

        return array.toArray(ArrayUtil.newArray(clazz, array.size()));

    }

    public static String parseScreenName(String status) {

        if (status.contains("/status")) status = StrUtil.subBefore(status, "/status", false);

        if (status.contains("/")) status = StrUtil.subAfter(status, "/", true);

        if (status.startsWith("@")) status = status.substring(1);

        return status;

    }

    public static long parseStatusId(String status) {

        if (NumberUtil.isLong(status)) return NumberUtil.parseLong(status);

        if (status.contains("/")) status = StrUtil.subAfter(status, "/", true);

        if (status.contains("?")) status = StrUtil.subBefore(status, "?", false);

        if (NumberUtil.isLong(status)) return NumberUtil.parseLong(status);

        return -1;

    }

    public static long parseTime(String message) {

        long time = 0;

        int count = -1;

        for (int index = 0; index < message.length(); index++) {

            String str = message.substring(index, index + 1);

            if (NumberUtil.isInteger(str)) {

                int integer = NumberUtil.parseInt(str);

                if (count == -1) {

                    count = integer;

                } else {

                    count = count * 10 + integer;

                }

                continue;

            }

            if (count == -1) return -1;

            if ("s".equals(str)) {

                time += count;

            } else if ("m".equals(str)) {

                time += count * 60;

            } else if ("h".equals(str)) {

                time += count * 60 * 60;

            } else if ("d".equals(str)) {

                time += count * 24 * 60 * 60;

            } else {

                throw new IllegalArgumentException(str);

            }

            count = -1;

        }

        if (count != -1) time += count * 60;

        return time * 1000;

    }

    public static LinkedList<User> fetchUsers(Twitter api, Collection<Long> ids) throws TwitterException {

        LinkedList<User> users = new LinkedList<>();

        while (!ids.isEmpty()) {

            List<Long> array;

            if (ids.size() <= 100) {

                array = new LinkedList<>(ids);

                ids.clear();

            } else {

                List<Long> arrayList = CollectionUtil.sub(ids, 0, 100);

                array = new LinkedList<>(arrayList);

                ids.removeAll(arrayList);

            }

            users.addAll(api.lookupUsers(toArray(array)));

        }

        return users;

    }

    public static Twitter mkApi(String apiKey, String apiSecret) {

        return new TwitterFactory(new ConfigurationBuilder().setOAuthConsumerKey(apiKey).setOAuthConsumerSecret(apiSecret).build()).getInstance();

    }

    public static Twitter mkApi(String apiKey, String apiSecret, String accessToken, String accessTokenSecret) {

        return new TwitterFactory(new ConfigurationBuilder().setOAuthConsumerKey(apiKey).setOAuthConsumerSecret(apiSecret).setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret).build()).getInstance();

    }

    public static LinkedList<Long> fetchFollowerIDs(Twitter api) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs followers = api.getFollowersIDs(-1);

        CollectionUtil.addAll(ids, followers.getIDs());

        while (followers.hasNext()) {

            followers = api.getFollowersIDs(followers.getNextCursor());

            CollectionUtil.addAll(ids, followers.getIDs());

        }

        return ids;

    }

    public static LinkedList<Long> fetchFriendIDs(Twitter api) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs friends = api.getFriendsIDs(-1);

        CollectionUtil.addAll(ids, friends.getIDs());

        while (friends.hasNext()) {

            friends = api.getFriendsIDs(friends.getNextCursor());

            CollectionUtil.addAll(ids, friends.getIDs());

        }

        return ids;

    }

    public static LinkedList<Long> fetchFollowerIDs(Twitter api, long accountId) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs followers = api.getFollowersIDs(accountId, -1);

        CollectionUtil.addAll(ids, followers.getIDs());

        while (followers.hasNext()) {

            followers = api.getFollowersIDs(accountId, followers.getNextCursor());

            CollectionUtil.addAll(ids, followers.getIDs());

        }

        return ids;

    }

    public static LinkedList<Long> fetchFriendIDs(Twitter api, long accountId) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs friends = api.getFriendsIDs(accountId, -1);

        CollectionUtil.addAll(ids, friends.getIDs());

        while (friends.hasNext()) {

            friends = api.getFriendsIDs(accountId, friends.getNextCursor());

            CollectionUtil.addAll(ids, friends.getIDs());

        }

        return ids;

    }

    public static LinkedList<Long> fetchBlockIDs(Twitter api) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs blocks = api.getBlocksIDs(-1);

        CollectionUtil.addAll(ids, blocks.getIDs());

        while (blocks.hasNext()) {

            blocks = api.getBlocksIDs(blocks.getNextCursor());

            CollectionUtil.addAll(ids, blocks.getIDs());

        }

        return ids;

    }

     */

    public static String parseTwitterException(Lang L, TwitterException ex) {

        String info = L.TWI_ERR;

        errorCode:
        {

            switch (ex.getErrorCode()) {

                case 32:
                    info += L.TWI_ERR_32;
                    break errorCode;
                case 50:
                    info += L.TWI_ERR_50;
                    break errorCode;
                case 63:
                    info += L.TWI_ERR_63;
                    break errorCode;
                case 64:
                    info += L.TWI_ERR_64;
                    break errorCode;
                case 88:
                case 205:
                    info += L.TWI_ERR_88_205;
                    break errorCode;
                case 89:
                case 99:
                    info += L.TWI_ERR_89_99;
                    break errorCode;
                case 93:
                    info += L.TWI_ERR_93;
                    break errorCode;
                case 130:
                    info += L.TWI_ERR_130;
                    break errorCode;
                case 131:
                    info += L.TWI_ERR_131;
                    break errorCode;
                case 135:
                    info += L.TWI_ERR_135;
                    break errorCode;
                case 139:
                    info += L.TWI_ERR_139;
                    break errorCode;
                case 144:
                    info += L.TWI_ERR_144;
                    break errorCode;
                case 150:
                    info += L.TWI_ERR_150;
                    break errorCode;
                case 160:
                    info += L.TWI_ERR_160;
                    break errorCode;
                case 161:
                    info += L.TWI_ERR_161;
                    break errorCode;
                case 179:
                    info += L.TWI_ERR_179;
                    break errorCode;
                case 185:
                    info += L.TWI_ERR_185;
                    break errorCode;
                case 186:
                case 354:
                    info += L.TWI_ERR_186_354;
                    break errorCode;
                case 187:
                    info += L.TWI_ERR_187;
                    break errorCode;
                case 220:
                    info += L.TWI_ERR_220;
                    break errorCode;
                case 226:
                case 326:
                    info += L.TWI_ERR_226_326;
                    break errorCode;
                case 261:
                    info += L.TWI_ERR_261;
                    break errorCode;
                case 349:
                    info += L.TWI_ERR_349;
                    break errorCode;
                case 385:
                    info += L.TWI_ERR_385;
                    break errorCode;

            }

            switch (ex.getStatusCode()) {

                case 304:
                    info += L.TWI_HTTP_304;
                    break errorCode;
                case 400:
                    info += L.TWI_HTTP_400;
                    break errorCode;
                case 401:
                    info += L.TWI_HTTP_401;
                    break errorCode;
                case 403:
                    info += L.TWI_HTTP_403;
                    break errorCode;
                case 404:
                    info += L.TWI_HTTP_404;
                    break errorCode;
                case 420:
                    info += L.TWI_HTTP_420;
                    break errorCode;
                case 422:
                    info += L.TWI_HTTP_422;
                    break errorCode;
                case 429:
                    info += L.TWI_HTTP_429;
                    break errorCode;
                case 500:
                case 502:
                case 504:
                    info += L.TWI_HTTP_500_502_504;
                    break errorCode;
                case 503:
                    info += L.TWI_HTTP_503;
                    break errorCode;

            }

            info += ex.getErrorMessage();

        }

        return info;

    }

    public static LinkedList<Long> fetchMuteIDs(Twitter api) throws TwitterException {

        LinkedList<Long> ids = new LinkedList<>();

        IDs mutes = api.getMutesIDs(-1);

        CollectionUtil.addAll(ids, mutes.getIDs());

        while (mutes.hasNext()) {

            mutes = api.getBlocksIDs(mutes.getNextCursor());

            CollectionUtil.addAll(ids, mutes.getIDs());

        }

        return ids;

    }

    public static byte[] mkData(int id, int subId, byte[]... dataArray) {

        ByteArrayOutputStream format = new ByteArrayOutputStream();

        format.write(id - 128);
        format.write(subId - 128);

        for (byte[] data : dataArray) {

            format.write(-1);

            try {

                format.write(data);

            } catch (IOException ignored) {
            }

        }

        format.write(dataArray.length - 128);

        byte[] data = format.toByteArray();

        byte[] zlibData = ZipUtil.zlib(data, 9);

        return data.length < zlibData.length ? data : zlibData;

    }

    public static byte[][] readData(byte[] data) {

        int length = data[data.length - 1] + 128;

        data = ArrayUtil.sub(data, 3, data.length - 1);

        byte[][] arr = new byte[length][];

        int current = 0;

        ByteArrayOutputStream cache = new ByteArrayOutputStream();

        for (int index = 0; index < data.length; index++) {

            if (data[index] == -1) {

                arr[current] = cache.toByteArray();

                cache = new ByteArrayOutputStream();

            } else {

                cache.write(data[index]);

                if (index == data.length - 1 && current == arr.length - 1) {

                    arr[current] = cache.toByteArray();

                }

            }

        }

        return arr;

    }

    public static String parseError(Throwable error) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        error.printStackTrace(new PrintWriter(out, true));

        return StrUtil.utf8Str(out);

    }

}