package tooko.main.bots;

import org.bson.codecs.pojo.annotations.BsonId;
import tooko.td.core.CacheTable;

public class BotData {

    public static CacheTable<Integer, BotData> DATA = new CacheTable<>("bots", BotData.class);

    @BsonId
    public int botId;

    public int owner;

    public int type;

    public String userName;

    public String botToken;

}
