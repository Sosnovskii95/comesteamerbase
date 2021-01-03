package SHA1Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    public String getPasswordEncryption(String password) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte [] bytes = sha1.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes){
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
