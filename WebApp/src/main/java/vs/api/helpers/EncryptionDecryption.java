//Klasa do szyfrowania hasła metodą AES

package vs.api.helpers;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncryptionDecryption {
    private static final String secretKey = "FEA063689C7546DC6CB790716CAFAB0D";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String AES_ENCRYPTION_SCHEME = "AES";

    //Szyfrowania hasła
    public static String encrypt(String value) {
        if (value != null) {
            try {
                SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(UTF_8), AES_ENCRYPTION_SCHEME);

                Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_SCHEME);
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] encrypted = cipher.doFinal(value.getBytes(UTF_8));
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else throw new NullPointerException("Value can't be null");

        return null;
    }

    //Rozszyfrownia hasła w celu sprawdzenia czy użytkownik podał takie same hasło przy logowaniu
    public static String decrypt(String encrypted) {
        if (encrypted != null) {
            try {
                SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(UTF_8), AES_ENCRYPTION_SCHEME);

                Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_SCHEME);
                cipher.init(Cipher.DECRYPT_MODE, key);

                byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
                return new String(original);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else throw new IllegalArgumentException("Value can't be null");

        return null;
    }

}
