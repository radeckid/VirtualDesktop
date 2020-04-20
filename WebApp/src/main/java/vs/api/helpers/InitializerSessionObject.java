//Klasa do inicjalizowania obiektów sesji wraz z sprwadzeniem czy istnieją

package vs.api.helpers;

public class InitializerSessionObject {

    public static String initialize(Object obj) {
        if (obj != null) {
            return obj.toString();
        }

        return "";
    }
}
