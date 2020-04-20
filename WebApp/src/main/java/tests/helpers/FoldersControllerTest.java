package tests.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.api.helpers.FoldersController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoldersControllerTest {

    @Test
    @DisplayName("Constructor throws IllegalArgumentException for null or 0 length user name")
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> new FoldersController(null, null));
        assertThrows(IllegalArgumentException.class, () -> new FoldersController("", ""));
    }

    @Test
    @DisplayName("copyFileToTempFolder() throws NullPointerException and IllegalArgumentException for invalid arguments")
    void copyFileToTempFolder() {
        FoldersController controller = new FoldersController("user", "currFolder");
        byte[] buffer = new byte[10];
        InputStream inStream = new ByteArrayInputStream(buffer);

        assertThrows(NullPointerException.class, () -> controller.copyFileToTempFolder(null, "xd"));
        assertThrows(IllegalArgumentException.class, () -> controller.copyFileToTempFolder(inStream, null));
    }

    @Test
    @DisplayName("addFolder() throws IllegalArgumentException for null or 0 length folder name")
    void addFolder() {
        FoldersController controller = new FoldersController("user", "folder");

        assertThrows(IllegalArgumentException.class, () -> controller.addFolder(null));
        assertThrows(IllegalArgumentException.class, () -> controller.addFolder(""));
    }

    @Test
    @DisplayName("getCurrentPath returns correct path")
    void getCurrentPath() {
        FoldersController controller = new FoldersController("user", "currFolder");
        assertEquals("..\\VirtualSpace\\user\\currFolder", controller.getCurrentPath());
    }
}