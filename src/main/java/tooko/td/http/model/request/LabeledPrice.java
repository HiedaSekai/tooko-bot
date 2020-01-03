package tooko.td.http.model.request;

import java.io.*;

/**
 * Stas Parshin
 * 24 May 2017
 */
public class LabeledPrice implements Serializable {

    private final static long serialVersionUID = 0L;

    private String label;
    private Integer amount;

    public LabeledPrice(String label, Integer amount) {

        this.label = label;
        this.amount = amount;
    }

}
