package tooko.twitter;

@FunctionalInterface
public interface TwitterCallback {

    public void onCallback(int userId, TwitterAccount account);

}
