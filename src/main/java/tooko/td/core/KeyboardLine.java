package tooko.td.core;

import tooko.main.*;
import tooko.td.TdApi.*;

import java.util.*;

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
