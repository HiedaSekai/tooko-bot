package tooko.main.extras;

import cn.hutool.core.util.StrUtil;
import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.*;
import tooko.td.client.TdHandler;

public class ForwardInfo extends TdHandler {

    @Override
    public void onNewMessage(int userId, long chatId, Message message) {

        if (message.forwardInfo == null) return;

        Lang L = Lang.get(userId);

        String info;

        MessageForwardOrigin origin = message.forwardInfo.origin;

        if (origin instanceof MessageForwardOriginUser) {

            MessageForwardOriginUser originUser = (MessageForwardOriginUser) origin;

            info = L.FORWARD_FROM_USER + " : " + Fn.mention(user(originUser.senderUserId));

            info += "\n" + L.USER_ID + " : " + Fn.code(originUser.senderUserId);

        } else if (origin instanceof MessageForwardOriginHiddenUser) {

            MessageForwardOriginHiddenUser originUser = (MessageForwardOriginHiddenUser) origin;

            info = L.FORWARD_FROM_HIDDEN_USER + " : " + originUser.senderName;

        } else {

            info = "";

            MessageForwardOriginChannel originChannel = (MessageForwardOriginChannel) origin;

            String channelName;

            Chat chat = chat(originChannel.chatId);

            Supergroup channel = superGroup(chat);

            if (channel != null) {

                channelName = chat.title;

                if (!StrUtil.isBlank(channel.username)) {

                    channelName = Fn.a(channelName, "https://t.me/" + channel.username);

                    info += L.FORWARD_FROM_CHANNEL + " : " + channelName + "\n";

                }

                if (!StrUtil.isBlank(channel.restrictionReason)) {

                    info += L.CHANNEL_RESTRICTIONED + " : " + channel.restrictionReason + "\n";

                }

            } else {

                info += L.FORWARD_FROM_CHANNEL + " : " + L.UNKNOWN + "\n";

            }


            info += L.CHAT_ID + " : " + Fn.code(originChannel.chatId) + "\n";

            if (StrUtil.isNotBlank(originChannel.authorSignature)) {

                info += L.AUTHOR_SIGNATURE + " : " + Fn.code(originChannel.authorSignature) + "\n";

            }

            PublicMessageLink link = I(new GetPublicMessageLink(originChannel.chatId, originChannel.messageId, false));

            if (link != null) {

                info += L.MESSAGE_LINK + " : " + link.link;

            }

        }

        send(Fn.sendText(chatId, message.id, Fn.parseHtml(info)));

    }


}
