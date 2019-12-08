package tooko.twitter.nsfw;

import tooko.twitter.*;
import tooko.td.*;
import twitter4j.*;
import tooko.main.*;
import tooko.twitter.archives.*;
import tooko.twitter.nsfw.NSFWDetector.*;

public class NSFWTest extends TwitterHandler {

    @Override
    public void onLoad() {

        initFunction("user_predict");

    }


    @Override
    public void onFunction(TdApi.User user, long chatId, TdApi.Message message, String function, String param, String[] params, String[] originParams, TwitterAccount account) {

        try {
            
            NSFWDetector.NSRC[] rcs = NSFWDetector.predetectStatus(account.mkApi().getUserTimeline(Fn.parseScreenName(param), new Paging().count(200)));

            int drawings = 0,hentai = 0,porn = 0,sexy = 0;
            
            for (NSFWDetector.NSRC rc : rcs) {
                
                switch (rc) {
                    
                  case DRAWINGS: drawings ++;break;
                  case HENTAI: hentai ++; break;
                  case PORN: porn ++;break;
                  case SEXY: sexy ++;break;
                  
                }
                
            }
            
            send(Fn.sendText(chatId, Fn.plainText("DRAWINGS: {}\nHENTAI: {}\nPORN: {}\nSEXY: {}",drawings,hentai,porn,sexy)));

        } catch (Exception e) {

            send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

        }

    }

}
