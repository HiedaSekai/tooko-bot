package tooko.twitter.spam;

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
        SEXY;

    }

    public static NSRC[] predetectStatus(Collection<Status> statuses) {

        return predetectStatus(Fn.toArray(statuses, Status.class));

    }

    public static NSRC[] predetectStatus(Status... statuses) {

        LinkedList<String> linkArray = new LinkedList<>();

        NSRC[] rcs = new NSRC[statuses.length];

        for (int index = 0;index < statuses.length;index ++) {

            Status status = statuses[index];
            
            if (status.getMediaEntities().length == 0) {
                
                rcs[index] = NSRC.NEUTRAL;

                continue;
                
            }
            
            for (MediaEntity media : status.getMediaEntities()) {

                linkArray.add(media.getMediaURLHttps());

            }

            float[][] results;

            try {

                results = NSFWClient.predict(Fn.toArray(linkArray, String.class));

            } catch (IOException e) {

                rcs[index] = NSRC.NEUTRAL;

                continue;

            }

            for (float[] result : results) {

                if (result[3] > 0.8f) {

                    rcs[index] = NSRC.PORN;

                    continue;


                }

            }

            for (float[] result : results) {

                if (result[4] > 0.8f) {

                    rcs[index] = NSRC.SEXY;

                    continue;

                }

            }

            for (float[] result : results) {

                if (result[1] > 0.8f) {

                    rcs[index] = NSRC.HENTAI;

                    continue;

                }

            }

            NSRC LIKELY = null;

            float value = -1;

            for (float[] result : results) {

                NSRC likely = null;
                
                if (result[0] > value) {

                    value = result[0];

                    likely = NSRC.DRAWINGS;

                }

                if (result[1] > value) {

                    value = result[1];

                    likely = NSRC.HENTAI;

                }

                if (result[3] > value) {

                    value = result[3];

                    likely = NSRC.PORN;

                }

                if (result[4] > value) {

                    value = result[1];

                    likely = NSRC.SEXY;

                }

                if (result[2] > value) {

                    value = -1;

                    if (LIKELY == null)  likely = NSRC.NEUTRAL;

                }
                
                if (likely != null) LIKELY = likely;

            }

            rcs[index] = LIKELY;

        }

        return rcs;
        
    }

}
