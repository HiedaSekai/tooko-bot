package tooko.twitter.spam;

import tooko.twitter.*;
import tooko.td.*;
import twitter4j.*;
import tooko.main.*;
import tooko.twitter.archives.*;
import tooko.twitter.spam.NSFWDetector.*;
import tooko.td.TdApi.*;
import tooko.main.utils.*;
import cn.hutool.core.util.*;
import tooko.main.utils.TextCensor.*;

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

                        int policies = 0,text_porn = 0,spam = 0;
                        
                        send(Fn.editText(stat, Fn.plainText("predicting...")));

                        for (int index = 0;index < statuses.size();index ++) {

                            Status status = statuses.get(index);

                            String content = status.getText();

                            for (URLEntity entiry : status.getMediaEntities()) {

                                content =  StrUtil.removeAll(content,entiry.getURL());

                            }

                            for (URLEntity entiry : status.getMediaEntities()) {

                                content = content.replace(entiry.getURL(),entiry.getExpandedURL());

                            }
                            
                            if (status.getMediaEntities().length == 0 && StrUtil.isBlank(content)) continue;

                            NSFWDetector.NSRC rc = NSFWDetector.predetectStatus(status)[0];

                            switch (rc) {

                                case DRAWINGS: drawings ++;break;
                                case HENTAI: hentai ++; break;
                                case PORN: porn ++;break;
                                case SEXY: sexy ++;break;

                            }
                            
                            if (StrUtil.isNotBlank(content)) {
                            
                                TextCensor.TCRC text_rc = TextCensor.getInstance().predictText(status.getText());

                                if (text_rc.isPolitics()) policies ++;
                                if (text_rc.isPorn()) text_porn ++;
                                if (text_rc.isSpam()) spam ++;
                                
                            }
                            
                            send(Fn.editText(stat, Fn.plainText("predicting... {} / {}\n\nDRAWINGS: {}\nHENTAI: {}\nPORN: {}\nSEXY: {}\n\nTEXT POLITIC: {}\nTEXT PORN: {}\nTEXT SPAM: {}", index + 1, statuses.size(), drawings, hentai, porn, sexy,policies,text_porn,spam)));

                        }
                        
                        send(Fn.editText(stat, Fn.plainText("DRAWINGS: {}\nHENTAI: {}\nPORN: {}\nSEXY: {}\n\nTEXT POLITIC: {}\nTEXT PORN: {}\nTEXT SPAM: {}", drawings, hentai, porn, sexy,policies,text_porn,spam)));
                        

                    } catch (Exception e) {

                        send(Fn.sendText(chatId, Fn.plainText(Fn.parseError(e))));

                    }


                }
            });


    }

}
