package Project;

import Project.system.ScrollManagement.Remove;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveTest {

    @Test
    public void testSetScroll() {
        Remove.setScroll("123");
        assertEquals(123, Remove.getScrollID());
    }

    @Test
    public void testRemoveScroll_NoScrollLoaded() {
        Remove.setScroll("99999");  // Non-existing scroll
        Remove.removeScroll();
        File file = new File("src/main/resources/Project/Scroll/99999.txt");
        assertFalse(file.exists(), "Scroll file should not exist.");
    }

    @Test
    public void testRemoveScroll_ExistingScroll() {
        // Assuming scroll 123 exists for this test
        Remove.setScroll("123");
        Remove.removeScroll();
        File file = new File("src/main/resources/Project/Scroll/123.txt");
        assertFalse(file.exists(), "Scroll file should be deleted.");
    }
}
