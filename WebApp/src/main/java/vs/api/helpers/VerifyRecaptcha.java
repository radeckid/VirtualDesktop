//Klasa do obsługi reCapcha

package vs.api.helpers;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class VerifyRecaptcha {
    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secretKey = "6LcQFMcUAAAAAMtXgaa6nrLVkhbqQCxIZa8v8dIp";
    private final static String USER_AGENT = "Mozilla/5.0";

    //Weryfikacja poprawnosci reCapchy
    public static boolean verify(String recaptchaResponse) {
        if (recaptchaResponse == null || recaptchaResponse.equals("")) {
            return false;
        }

        try {
            URL obj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) obj.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Accept-Language", "pl,en;q=0.5");
            connection.setDoOutput(true);

            String postParams = "secret=" + secretKey + "&response=" + recaptchaResponse;

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
