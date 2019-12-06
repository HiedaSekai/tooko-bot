package tooko.td.core;

import tooko.td.TdApi.KeyboardButton;

import java.util.LinkedList;

public class KeyboardArray extends LinkedList<KeyboardLine> {

    public KeyboardLine newLine() {

        KeyboardLine line = new KeyboardLine();

        add(line);

        return line;

    }

    public KeyboardLine textLine(String text) {

        return newLine().text(text);

    }

    @Override
    public KeyboardButton[][] toArray() {

        KeyboardButton[][] array = new KeyboardButton[size()][];

        for (int index = 0; index < array.length; index++) {

            array[index] = get(index).toArray();

        }

        return array;

    }

}
