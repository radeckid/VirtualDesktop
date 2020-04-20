package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.InitializerSessionObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializerSessionObjectTest {

    @Test
    @DisplayName("initialize() returns empty string for null argument")
    void initialize() {
        assertEquals("", InitializerSessionObject.initialize(null));
    }
}