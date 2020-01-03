package tooko.td.http.model.request;

import java.io.*;

/**
 * Stas Parshin
 * 23 November 2016
 */
public class ReplyKeyboardRemove extends Keyboard implements Serializable {

    private final static long serialVersionUID = 0L;

    private final boolean remove_keyboard = true;
    private final boolean selective;

    public ReplyKeyboardRemove() {

        this(false);
    }

    public ReplyKeyboardRemove(boolean selective) {

        this.selective = selective;
    }

}
