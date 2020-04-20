package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.Authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationTest {

    @Test
    @DisplayName("createToken() throws IllegalArgumentException for null argument")
    void createToken() {
        assertThrows(IllegalArgumentException.class, () -> Authentication.createToken(null));
    }

    @Test
    @DisplayName("checkToken() throws IllegalArgumentException for null arguments")
    void checkToken() {
        assertThrows(IllegalArgumentException.class, () -> Authentication.checkToken(null, null));
    }

    @Test
    @DisplayName("deleteToken() throws IllegalArgumentException for null argument")
    void deleteToken() {
        assertThrows(IllegalArgumentException.class, () -> Authentication.deleteToken(null));
    }
}