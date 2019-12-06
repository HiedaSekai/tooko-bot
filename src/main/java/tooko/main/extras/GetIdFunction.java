package tooko.main.extras;

import cn.hutool.core.util.NumberUtil;
import tooko.main.Fn;
import tooko.td.TdApi.*;
import tooko.td.client.TdException;
import tooko.td.client.TdFunction;

public class GetIdFunction extends TdFunction {

    @Override
    public void onLoad() {

        initFunction("id");

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        if (params.length > 0) {

            try {

                if (NumberUtil.isInteger(param)) {

                    User targetUser = execute(new GetUser(NumberUtil.parseInt(param)));

                    send(Fn.sendText(chatId, Fn.parseHtml("Name : {}\nUID : {}", Fn.a(Fn.displayName(targetUser), "tg://user?id=" + targetUser.id), Fn.code(targetUser.id))));

                    return;

                }

            } catch (TdException ex) {

                if (ex.getCode() == -1) {

                    send(Fn.serverClosed(message));

                } else {

                    send(Fn.sendText(chatId, Fn.plainText("Can't find user with id {}", params[0])));

                }

                return;

            }

            for (TextEntity entity : ((MessageText) message.content).text.entities) {

                if (entity.type instanceof TextEntityTypeMentionName) {

                    int userId = ((TextEntityTypeMentionName) entity.type).userId;

                    User targetUser = E(new GetUser(userId));

                    send(Fn.sendText(chatId, Fn.parseHtml("Name : {}\nUID : {}", Fn.a(Fn.displayName(targetUser), "tg://user?id=" + targetUser.id), Fn.code(targetUser.id))));

                    return;

                }

            }

            String userName = params[0];

            try {

                if (userName.startsWith("@")) userName = userName.substring(1);

                Chat chat = execute(new SearchPublicChat(userName));

                if (chat.type instanceof ChatTypePrivate) {

                    User targetUser = execute(new GetUser(((ChatTypePrivate) chat.type).userId));

                    send(Fn.sendText(chatId, Fn.parseHtml("Name : {}\nUID : {}", Fn.a(Fn.displayName(targetUser), "tg://user?id=" + targetUser.id), Fn.code(targetUser.id))));

                }

            } catch (TdException ex) {

                if (ex.getCode() == -1) {

                    send(Fn.serverClosed(message));

                } else {

                    send(Fn.sendText(chatId, Fn.plainText("Can't find public chat with username @{}", userName)));

                }

            }

            return;

        }

        if (Fn.fromPrivate(message)) {

            send(Fn.sendText(chatId, Fn.parseHtml("{} : {}", Fn.b("UID"), Fn.code(user.id))));

        } else if (Fn.fromChannel(message)) {

            send(Fn.sendText(chatId, Fn.parseHtml("{} : {}", Fn.b("CID"), Fn.code(message.chatId))));

        } else {

            send(Fn.sendText(chatId, Fn.parseHtml("{} : {}\n{} : {}", Fn.b("CID"), Fn.code(message.chatId), Fn.b("UID"), Fn.code(user.id))));

        }

    }

}
