package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.Mail;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MailTest {

    @Test
    @DisplayName("send() throws IllegalArgumentException if Mail object has null field(s)")
    void send() {
        assertThrows(IllegalArgumentException.class, () -> {
            Mail mail = new Mail(null, "", "");
            mail.send();
        });
    }
}
