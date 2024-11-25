package Project;

import static org.junit.jupiter.api.Assertions.*;


import Project.system.Scroll.Scroll.ExistingScroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

public class ExistingScrollTest {

    private ExistingScroll existingScroll;

    @BeforeEach
    public void setUp() {

        existingScroll = new ExistingScroll(1);
    }

    @Test
    public void testGetScrollName() {
        assertNotNull(existingScroll.getScroll_Name());
        assertEquals("Updated Scroll Name", existingScroll.getScroll_Name());
    }

    @Test
    public void testSetScrollName() {
        String newName = "Updated Scroll Name";
        existingScroll.setScroll_Name(newName);
        assertEquals(newName, existingScroll.getScroll_Name());
    }

    @Test
    public void testGetUploaderID() {
        assertTrue(existingScroll.getUploader_ID() > 0);
    }

    @Test
    public void testSetDownloadTimes() {
        int initialDownloads = existingScroll.getDownloadTimes();
        existingScroll.setDownloadTimes();
        assertEquals(initialDownloads + 1, existingScroll.getDownloadTimes());
    }

    @Test
    public void testSetContent() {
        String newContent = "This is the updated content of the scroll.";
        existingScroll.setContent(newContent);
        assertEquals(newContent, existingScroll.getContent());

        File scrollFile = new File("src/main/resources/Project/Scroll/" + existingScroll.getScroll_ID() + ".txt");
        assertTrue(scrollFile.exists());
    }

    @Test
    public void testGetUploadDate() {
        LocalDate uploadDate = existingScroll.getUpload_Date();
        assertNotNull(uploadDate);
    }

    @Test
    public void testFindScrollDataByID() {
        assertNotNull(existingScroll);
        assertEquals(1, existingScroll.getScroll_ID());
    }
}