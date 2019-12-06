package tooko.td.core;

import tooko.main.Fn;
import tooko.td.TdApi.InlineKeyboardButton;

import java.util.LinkedList;

public class InlineLine extends LinkedList<InlineKeyboardButton> {

    public InlineLine inlineUrl(String text, String url) {

        add(Fn.inlineUrl(text, url));

        return this;

    }

    public InlineLine inlineLoginUrl(String text, String url, int id, String forwardText) {

        add(Fn.inlineLoginUrl(text, url, id, forwardText));

        return this;

    }

    public InlineLine inlineGame(String text) {

        add(Fn.inlineGame(text));

        return this;

    }

    public InlineLine inlineSwitch(String text, String query, boolean inCurrentChat) {

        add(Fn.inlineSwitch(text, query, inCurrentChat));

        return this;

    }

    public InlineLine inlineData(String text, int id, int subId, byte[]... dataArray) {

        add(Fn.inlineData(text, id, subId, dataArray));

        return this;

    }

    @Override
    public InlineKeyboardButton[] toArray() {

        return toArray(new InlineKeyboardButton[size()]);

    }


}
