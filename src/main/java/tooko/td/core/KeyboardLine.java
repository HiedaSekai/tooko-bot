package tooko.td.core;

import tooko.main.Fn;
import tooko.td.TdApi.KeyboardButton;

import java.util.LinkedList;

public class KeyboardLine extends LinkedList<KeyboardButton> {

    public KeyboardLine text(String text) {

        add(Fn.keyboardText(text));

        return this;

    }

    @Override
    public KeyboardButton[] toArray() {

        return toArray(new KeyboardButton[size()]);

    }

}
