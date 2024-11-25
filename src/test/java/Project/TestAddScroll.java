package Project;

import Project.system.Scroll.Scroll.BlankScroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestAddScroll {

    private BlankScroll scroll;

    @BeforeEach
    void setUp() {
        scroll = new BlankScroll("Test Scroll", 1, 12345); // Initialize with a scroll name, ID, and uploader ID
    }

    @Test
    void testScrollCreation() {
        assertNotNull(scroll);
        assertEquals("Test Scroll", scroll.getScroll_Name());
        assertEquals(1, scroll.getScroll_ID());
        assertEquals(12345, scroll.getUploader_ID());
    }

    @Test
    void testSetAndGetContent() {
        scroll.setContent("This is a test content");
        assertEquals("This is a test content", scroll.getContent());
    }


    @Test
    void testSetScrollName() {
        scroll.setScroll_Name("Updated Scroll Name");
        assertEquals("Updated Scroll Name", scroll.getScroll_Name());
    }
}