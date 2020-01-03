package tooko.pm.handlers;

import tooko.main.*;
import tooko.main.bots.*;
import tooko.pm.*;
import tooko.td.TdApi.*;
import tooko.td.client.*;

public class EditHandler extends TdHandler {

    public PmBot pm;

    public BotData bot;
    public PmData data;

    public Lang OL;

    @Override
    public void onLoad() {

        pm = (PmBot) client;

        bot = pm.bot;
        data = pm.data;

        OL = pm.OL;

    }

    @Override
    public void onMessageContent(long chatId, long messageId, MessageContent newContent) {

        if (chatId == bot.owner) {

            String messageIdStr = messageId + "";

            if (data.sended.containsKey(messageIdStr)) {

                long targetMessage = data.sended.get(messageIdStr);

                PmData.Session session = data.getSessionByElement("sended", targetMessage);

                long targetChat = session.chatId;

                Message oldMessage = message(targetChat, targetMessage);

                if (oldMessage == null) {

                    data.sended.remove(messageIdStr);

                    session.sended.remove(targetMessage);

                    return;

                }

                Function edit = null;

                if (newContent instanceof MessageText) {

                    edit = Fn.editText(targetChat, targetMessage, ((MessageText) newContent).text);

                } else if (newContent instanceof MessageAnimation || newContent instanceof MessageAudio || newContent instanceof MessageDocument || newContent instanceof MessagePhoto || newContent instanceof MessageVideo) {

                    edit = new EditMessageMedia(targetChat, targetMessage, null, Fn.convertToInput(newContent));

                } else if (newContent instanceof MessageVoiceNote) {

                    edit = new EditMessageCaption(targetChat, targetMessage, null, ((MessageVoiceNote) newContent).caption);

                } else if (newContent instanceof MessageLocation) {

                    edit = new EditMessageLiveLocation(targetChat, targetMessage, null, ((MessageLocation) newContent).location);

                }

                if (edit == null) {

                    send(Fn.sendText(chatId, Fn.plainText(OL.EDIT_TYPE_INVALID)));

                    return;

                }

                try {

                    execute(edit);

                    delay(Fn.sendText(chatId, messageId, Fn.plainText(OL.EDIT_SUCCEED)));

                } catch (TdException e) {

                    send(Fn.sendText(chatId, messageId, Fn.plainText(OL.EDIT_FAILED, e)));

                }

            }

        } else {

            if (data.received.containsValue(messageId)) {

                long receivedMessageId = Long.parseLong(data.received.getKey(messageId));

                Message reportMessage = syncE(Fn.sendText(bot.owner, receivedMessageId, Fn.plainText(OL.EDITED_BY)));
                Message newMessage = syncE(Fn.sendMessage(bot.owner, Fn.forward(chatId, messageId)));

                data.sessions.arrayInsert(chatId, "extras", reportMessage.id);
                data.sessions.arrayInsert(chatId, "extras", newMessage.id);

            }

        }

    }


	/*

	 private boolean formatedTextEquals(FormattedText text,FormattedText newText) {

	 if (!text.text.equals(newText.text)) return false;

	 if (text.entities.length != newText.entities.length) return false;

	 for (int index = 0;index < text.entities.length;index ++) {

	 TextEntity entity = text.entities[index];
	 TextEntity newEntity = newText.entities[index];

	 if (entity.offset != newEntity.offset) return false;

	 if (entity.length != newEntity.length) return false;

	 if (!entity.type.getClass().equals(newEntity.getClass())) return false;

	 if (entity.type instanceof TextEntityTypePreCode) {

	 TextEntityTypePreCode preCode = (TextEntityTypePreCode)entity.type;
	 TextEntityTypePreCode newCode = (TextEntityTypePreCode)newEntity.type;

	 if (!preCode.language.equals(newCode.language)) return false;

	 } else if (entity.type instanceof TextEntityTypeTextUrl) {

	 TextEntityTypeTextUrl url = (TextEntityTypeTextUrl)entity.type;
	 TextEntityTypeTextUrl newUrl = (TextEntityTypeTextUrl)newEntity.type;

	 if (!url.url.equals(newUrl.url)) return false;

	 } else if (entity.type instanceof TextEntityTypeMentionName) {

	 TextEntityTypeMentionName mention = (TextEntityTypeMentionName)entity.type;
	 TextEntityTypeMentionName newMention = (TextEntityTypeMentionName)newEntity.type;

	 if (mention.userId != newMention.userId) return false;



	 }

	 }

	 return true;

	 }

	 */

}
