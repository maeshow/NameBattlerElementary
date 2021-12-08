package internal.domain.usecase;

import java.math.BigInteger;
import java.security.MessageDigest;

public class GenerateHashDigestUseCase {
    public int invoke(String name, int index) {
        try {
            String digest = getHashDigest(name);
            String hex = digest.substring(
                    index * 2, index * 2 + 2);
            return Integer.parseInt(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getHashDigest(String name) {
        try {
            byte[] result = MessageDigest.getInstance("SHA-1")
                    .digest(name.getBytes());
            return String.format(
                    "%040x",
                    new BigInteger(1, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
