package tooko.td.http.model.request;

import tooko.td.http.request.*;

import java.io.*;

/**
 * Stas Parshin
 * 28 July 2018
 */
public class InputMediaDocument extends InputMedia<InputMediaDocument> implements Serializable {

    private final static long serialVersionUID = 0L;

    public InputMediaDocument(String media) {

        super("document", media);
    }

    public InputMediaDocument(File media) {

        super("document", media);
    }

    public InputMediaDocument(byte[] media) {

        super("document", media);
    }

    @Override
    public String getDefaultFileName() {

        return ContentTypes.DOC_FILE_NAME;
    }

    @Override
    public String getContentType() {

        return ContentTypes.DOC_MIME_TYPE;
    }

}
