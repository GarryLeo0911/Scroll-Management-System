/*
package Project;
import static org.junit.jupiter.api.Assertions.*;

import Project.system.Scroll.Scroll.BlankScroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class blankScrolltest {
    private BlankScroll blankScroll;
    private static final String SCROLL_PATH = "src/main/resources/Project/Scroll/";
    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";

    @BeforeEach
    public void setUp() {
        blankScroll = new BlankScroll("Test Scroll", 123, 1);
        cleanUp(); // Clean up before each test
    }

    @Test
    public void testCreateFile() throws Exception {
        // Test file creation
        blankScroll.createFile();
        File file = new File(SCROLL_PATH + "1.txt");

        assertTrue(file.exists(), "The scroll file should be created.");
    }

    @Test
    public void testSetContentAndWriteToFile() throws Exception {
        // Test writing content to the file
        blankScroll.createFile();
        blankScroll.setContent("This is a test content");

        File file = new File(SCROLL_PATH + "1.txt");
        assertTrue(file.exists(), "The scroll file should exist after setting content.");

        // Check if content is written to file
        String content = Files.readString(Path.of(SCROLL_PATH + "1.txt"));
        assertEquals("This is a test content", content, "Content should be written to the file.");
    }

    @Test
    public void testAddToTableOfContents() throws Exception {
        // Test adding metadata to TableOfContent.json
        blankScroll.createFile();

        File tocFile = new File(TOC_JSON_PATH);
        assertTrue(tocFile.exists(), "TableOfContent.json should be created.");

        // Verify content in TableOfContent.json
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(tocFile);

        // Check if the newly created scroll data is present in JSON
        boolean found = false;
        for (JsonNode node : rootNode) {
            if (node.get("Scroll_ID").asInt() == 123) {
                found = true;
                assertEquals("Test Scroll", node.get("Scroll_Name").asText(), "Scroll name should match.");
                assertEquals(1, node.get("Uploader_ID").asInt(), "Uploader ID should match.");
                assertEquals(LocalDate.now().toString(), node.get("Upload_Date").asText(), "Upload date should match.");
                break;
            }
        }
        assertTrue(found, "The new scroll should be added to TableOfContent.json.");
    }

    // Clean up method to remove files after each test
    private void cleanUp() {
        try {
            Files.deleteIfExists(Path.of(SCROLL_PATH + "1.txt"));
            Files.deleteIfExists(Path.of(TOC_JSON_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
*/
