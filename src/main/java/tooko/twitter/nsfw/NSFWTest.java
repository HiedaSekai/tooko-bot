package tooko.twitter.nsfw;

import tooko.twitter.*;
import tooko.td.*;
import twitter4j.*;
import tooko.main.*;
import tooko.twitter.archives.*;
import tooko.twitter.nsfw.NSFWDetector.*;
import tooko.td.TdApi.*;

public class NSFWTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("user_predict");

    }


    @Override
    public void onFunction(TdApi.User user, final long chatId, TdApi.Message message, String function, final String param, String[] params, String[] originParams, final TwitterAccount account) {

        client.asyncPool.execute(new Runnable() {

                @Override
                public void run() {

                    try {

                        TdApi.Message stat = sync(Fn.sendText(chatId, Fn.plainText("fetching statuses...")));

                        ResponseList<Status> statuses =  account.mkApi().getUserTimeline(Fn.parseScreenName(param), new Paging().count(200));

                        int drawings = 0,hentai = 0,porn = 0,sexy = 0;

                        send(Fn.editText(stat, Fn.plainText("predicting...")));
                        
                        for (int index = 0;index > statuses.size();index ++) {

                            Status status = statuses.get(index);
                            
                            if (status.getMediaEntities().length == 0) continue;
                          
                            NSFWDetector.NSRC rc = NSFWDetector.predetectStatus(status)[0];

                            switch (rc) {

                                case DRAWINGS: drawings ++;break;
                                case HENTAI: hentai ++; break;
                                case PORN: porn ++;break;
                                case SEXY: sexy ++;break;

                            }

                            send(Fn.editText(stat, Fn.plainText("predicting... {} / {},\nDRAWINGS: {}\nHENTAI: {}\nPORN: {}\nSEXY: {}",index + 1,statuses.size(), drawings, hentai, porn, sexy)));

                        }

                    } catch (Exception e) {

                        send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

                    }


                }
            });


    }

}
