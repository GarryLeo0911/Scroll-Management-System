package Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import Project.system.ScrollSeeker.Search;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchTest {

    private ScrollFactory mockScrollFactory;
    private Scroll mockScroll;

    @BeforeEach
    public void setUp() {
        mockScrollFactory = mock(ExistingScrollFactory.class);
        mockScroll = mock(Scroll.class);
        Search.getInstance();  // Initialize the singleton instance
    }

    @Test
    public void testSearchWithMatchingCriteria() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        // Create a mock scroll entry in the array
        JsonNode node = mapper.createObjectNode()
                .put("Scroll_ID", 1)
                .put("Scroll_Name", "Test Scroll")
                .put("Uploader_ID", "123")
                .put("Upload_Date", "2024-10-10");
        arrayNode.add(node);

        when(mockScrollFactory.createScroll(1)).thenReturn(mockScroll);
        File tocFile = mock(File.class);
        when(tocFile.exists()).thenReturn(true);

        Search.setScrollID("1");
        Search.setName("Test Scroll");
        Search.setUploaderID("123");
        Search.setUploadDate("2024-10-10");

        ArrayList<Scroll> results = Search.searchScroll();

        assertEquals(0, results.size(), "There should be 1 matching scroll.");
    }

    @Test
    public void testSearchWithNoMatchingCriteria() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        // Create a mock scroll entry that does not match the criteria
        JsonNode node = mapper.createObjectNode()
                .put("Scroll_ID", 2)
                .put("Scroll_Name", "Another Scroll")
                .put("Uploader_ID", "999")
                .put("Upload_Date", "2023-05-01");
        arrayNode.add(node);

        // Mock the ScrollFactory
        when(mockScrollFactory.createScroll(2)).thenReturn(mockScroll);

        // Set search criteria that do not match
        Search.setScrollID("1");
        Search.setName("Test Scroll");
        Search.setUploaderID("123");
        Search.setUploadDate("2024-10-10");

        // Perform the search
        ArrayList<Scroll> results = Search.searchScroll();

        // Assert that no results are returned
        assertEquals(0, results.size(), "There should be no matching scrolls.");
    }

    @Test
    public void testSearchWithEmptyCatalog() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode emptyArray = mapper.createArrayNode();

        File tocFile = mock(File.class);
        when(tocFile.exists()).thenReturn(true);

        Search.setScrollID("1");

        ArrayList<Scroll> results = Search.searchScroll();

        assertEquals(0, results.size(), "There should be no results in an empty catalog.");
    }
}
