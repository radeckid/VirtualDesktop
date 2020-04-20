package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.MainPageController;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MainPageControllerTest {

    @Test
    @DisplayName("getProgressBar() throws NullPointerException for null request")
    void getProgressBar() {
        assertThrows(NullPointerException.class, () -> MainPageController.getProgressBar(null));
    }
}