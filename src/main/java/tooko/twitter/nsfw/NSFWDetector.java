package tooko.twitter.nsfw;

import twitter4j.*;
import java.util.*;
import tooko.main.utils.*;
import tooko.main.*;
import java.io.*;

public class NSFWDetector {

    public static enum NSRC {

        DRAWINGS,
        HENTAI,
        NEUTRAL, 
        PORN,
        SEXY

        }

    public static NSRC predetectStatus(Collection<Status> statuses) {

        LinkedList<String> linkArray = new LinkedList<>();

        for (Status status : statuses) {

            for (MediaEntity media : status.getMediaEntities()) {

                linkArray.add(media.getMediaURLHttps());

            }

        }

        float[][] results;

        try {

            results = NSFWClient.predict(Fn.toArray(linkArray, String.class));

        } catch (IOException e) {

            return NSRC.NEUTRAL;

        }

        for (float[] result : results) {

            if (result[3] > 0.8f) return NSRC.PORN;

        }

        for (float[] result : results) {

            if (result[4] > 0.8f) return NSRC.SEXY;

        }

        for (float[] result : results) {

            if (result[1] > 0.8f) return NSRC.HENTAI;

        }

        for (float[] result : results) {

            if (result[1] > 0.8f) return NSRC.DRAWINGS;

        }

        NSRC LIKELY = null;

        float value = -1;

        for (float[] result : results) {

            if (result[0] > value) {

                value = result[0];

                LIKELY = NSRC.DRAWINGS;

            }

            if (result[1] > value) {

                value = result[1];

                LIKELY = NSRC.HENTAI;

            }

            if (result[3] > value) {

                value = result[3];

                LIKELY = NSRC.PORN;

            }

            if (result[4] > value) {

                value = result[1];

                LIKELY = NSRC.SEXY;

            }

            if (result[2] > value) {

                value = -1;

                if (LIKELY == null)  LIKELY = NSRC.NEUTRAL;

            }

        }

        return LIKELY;

    }

}
