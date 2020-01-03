package tooko.td.http.login;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.math.*;
import java.net.*;
import java.security.*;
import java.util.*;

/**
 * Stas Parshin
 * 18 June 2019
 */
public class CheckTelegramAuth {

    private final String botToken, dataCheck, hash;
    private final long authDate;

    private CheckTelegramAuth(String botToken, String authQueryParams) {

        String hash = null;
        long authDate = 0;
        String[] params = authQueryParams.split("&");
        TreeSet<String> set = new TreeSet<>();
        for (String p : params) {
            if (p.startsWith("hash=")) {
                hash = p.substring(5);
            } else {
                set.add(p);
            }
            if (p.startsWith("auth_date=")) {
                authDate = Long.parseLong(p.substring(10));
            }
        }
        this.hash = hash;
        this.authDate = authDate;
        this.dataCheck = join(set, "\n");
        this.botToken = botToken;
    }

    public static CheckTelegramAuth fromUrl(String botToken, String authUrl) {

        return new CheckTelegramAuth(botToken, URI.create(authUrl).getQuery());
    }

    private static byte[] sha256(byte[] string) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(string);
    }

    private static String hmacSha256(byte[] key, String data) throws NoSuchAlgorithmException, InvalidKeyException {

        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
        hmacSha256.init(secret_key);
        byte[] result = hmacSha256.doFinal(data.getBytes());
        return hex(result);
    }

    private static String hex(byte[] str) {

        return String.format("%040x", new BigInteger(1, str));
    }

    private static String join(Iterable<String> elements, CharSequence separator) {

        StringBuilder builder = new StringBuilder();
        Iterator<String> it = elements.iterator();
        if (it.hasNext()) {
            builder.append(it.next());
            while (it.hasNext()) {
                builder.append(separator).append(it.next());
            }
        }
        return builder.toString();
    }

    public Date authDate() {

        return new Date(authDate * 1000L);
    }

    public boolean isFromTelegram() throws Exception {

        byte[] secret = sha256(botToken.getBytes());
        String result = hmacSha256(secret, dataCheck);
        return result.equals(hash);
    }

}
