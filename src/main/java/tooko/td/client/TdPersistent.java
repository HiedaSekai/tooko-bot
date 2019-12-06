package tooko.td.client;

public class TdPersistent {

    public int id;

    public int dataId;

    public int subId;

    public long createAt;

    public boolean acceptFunction;

    public TdPersistent(int id, int dataId, int subId, long createAt, boolean acceptFunction) {

        this.id = id;
        this.dataId = dataId;
        this.subId = subId;
        this.createAt = createAt;
        this.acceptFunction = acceptFunction;

    }

}
