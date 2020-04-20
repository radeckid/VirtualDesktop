package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.VerifyRecaptcha;

import static org.junit.jupiter.api.Assertions.assertFalse;

class VerifyRecaptchaTest {

    @Test
    @DisplayName("verify() returns false for empty recaptchaResponse")
    void verifyForEmptyString() {
        assertFalse(VerifyRecaptcha.verify(""));
    }

    @Test
    @DisplayName("verify() returns false for null recaptchaResponse")
    void verifyForNullString() {
        assertFalse(VerifyRecaptcha.verify(null));
    }
}