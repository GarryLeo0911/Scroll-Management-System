package Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import Project.system.ScrollSeeker.Preview;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PreviewTest {

    private ScrollFactory mockScrollFactory;
    private Scroll mockScroll;

    @BeforeEach
    public void setUp() {
        mockScrollFactory = mock(ExistingScrollFactory.class);
        mockScroll = mock(Scroll.class);

        // Mock the scroll factory to be used in the test
        Preview.getPreview().catalog = new JSONArray();  // Setting a mock catalog
    }

    @Test
    public void testGetScrollsListWithEmptyCatalog() {
        ArrayList<Scroll> scrollsList = Preview.getScrollsList();
        assertTrue(scrollsList.isEmpty(), "Scrolls list should be empty when catalog is empty.");
    }

    @Test
    public void testGetScrollsListWithValidCatalog() {
        JSONObject scrollObject = new JSONObject();
        scrollObject.put("Scroll_ID", 1L);
        Preview.getPreview().catalog.add(scrollObject);

        when(mockScrollFactory.createScroll(1)).thenReturn(mockScroll);

        ArrayList<Scroll> scrollsList = Preview.getScrollsList();
        assertEquals(1, scrollsList.size(), "Scrolls list should contain one item.");
    }

    @Test
    public void testSetTableOfContentHandlesMissingFile() {
        Preview preview = Preview.getPreview();
        preview.setTableOfContent();
        assertNotNull(preview.catalog, "Catalog should not be null even if the file is missing.");
        assertTrue(preview.catalog.isEmpty(), "Catalog should be empty if the file is missing.");
    }
}
