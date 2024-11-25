package Project;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollSeeker.Search;
import Project.view.ScrollRelated.SearchDialog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchDialogTest {

    @BeforeEach
    public void setUp() {
        // No need to initialize JavaFX stage/window components here
    }

    @Test
    public void testPerformSearch() {
        try (MockedStatic<Search> mockSearch = Mockito.mockStatic(Search.class)) {
            List<Scroll> mockResults = new ArrayList<>();
            mockSearch.when(Search::searchScroll).thenReturn(mockResults);

            List<Scroll> result = SearchDialog.performSearch("123", "456", "Test Scroll", "2024-10-23");

            assertEquals(mockResults, result);
        }
    }
}