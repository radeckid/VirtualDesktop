//Klasa do obłsugi tokenów

package vs.api.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Authentication {
    private static Map<String, String> tokens = new HashMap<>();

    //Tworzy nowego tokena przypisanego do danego e-mailu
    public static String createToken(String userEmail) {
        if (userEmail != null) {
            String token = UUID.randomUUID().toString();
            tokens.put(userEmail, token);
            return token;
        } else throw new IllegalArgumentException("Email can't be null!");
    }

    //Sprawdza czy dany token jest powiązany z danym email
    public static boolean checkToken(String userEmail, String userToken) {
        if (userEmail != null && userToken != null) {
            String token = tokens.get(userEmail);

            if (token != null) {
                return token.equals(userToken);
            }

        } else throw new IllegalArgumentException("Email nor Token can be null");

        return false;
    }

    //Usuwa token powiązany z email
    public static void deleteToken(String userEmail) {
        if (userEmail != null) {
            tokens.remove(userEmail);
        } else throw new IllegalArgumentException("Email can't be null");
    }
}
