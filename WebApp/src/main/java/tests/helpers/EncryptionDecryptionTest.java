package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.EncryptionDecryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EncryptionDecryptionTest {

    @Test
    @DisplayName("encrypt() throws NullPointerException for null argument")
    void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> EncryptionDecryption.encrypt(null));
    }

    @Test
    @DisplayName("encrypt() encrypts correctly")
    void encrypt() {
        String result = EncryptionDecryption.encrypt("damiandamian");
        assertEquals("5BMnlQGuZy1N/uHZCrt4Fw==", result);
    }

    @Test
    @DisplayName("decrypt() throws IllegalArgumentException for null argument")
    void shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EncryptionDecryption.decrypt(null));
    }

    @Test
    @DisplayName("decrypt() decrypts correctly")
    void decrypt() {
        String result = EncryptionDecryption.decrypt("5BMnlQGuZy1N/uHZCrt4Fw==");
        assertEquals("damiandamian", result);
    }
}