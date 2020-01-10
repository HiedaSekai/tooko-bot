package tooko.td.core;

import tooko.td.TdApi.*;

import java.util.*;

public class InlineArray extends LinkedList<InlineLine> {

    public InlineLine newLine() {

        InlineLine line = new InlineLine();

        add(line);

        return line;

    }

    public InlineLine urlLine(String text, String url) {

        return newLine().inlineUrl(text, url);

    }

    public InlineLine loginUrlLine(String text, String url, int id, String forwardText) {

        return newLine().inlineLoginUrl(text, url, id, forwardText);

    }

    public InlineLine gameLine(String text) {

        return newLine().inlineGame(text);

    }

    public InlineLine switchLine(String text, String query, boolean inCurrentChat) {

        return newLine().inlineSwitch(text, query, inCurrentChat);

    }

    public InlineLine dataLine(String text, int id, int subId, byte[]... dataArray) {

        return newLine().inlineData(text, id, subId, dataArray);

    }

    @Override
    public InlineKeyboardButton[][] toArray() {

        InlineKeyboardButton[][] array = new InlineKeyboardButton[size()][];

        for (int index = 0; index < array.length; index++) {

            array[index] = get(index).toArray();

        }

        return array;

    }


}
