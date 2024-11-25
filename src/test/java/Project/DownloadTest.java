package Project;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import Project.system.ScrollManagement.Add;
import Project.system.ScrollSeeker.Download;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DownloadTest {

    private ScrollFactory mockScrollFactory;
    private Scroll mockScroll;

    @BeforeEach
    public void setUp() {
        mockScrollFactory = mock(ScrollFactory.class);
        mockScroll = mock(Scroll.class);

        // Inject the mock scroll factory into the Download instance
        Download.getInstance().scrollFactory = mockScrollFactory;
    }

    @Test
    public void testSetScrollID() {
        String scrollID = "1";
        when(mockScrollFactory.createScroll(Integer.parseInt(scrollID))).thenReturn(mockScroll);

        Download.setScrollID(scrollID);

        verify(mockScrollFactory).createScroll(Integer.parseInt(scrollID));
    }

    @Test
    public void testDownloadScroll() {
        String scrollID = "1";
        when(mockScrollFactory.createScroll(Integer.parseInt(scrollID))).thenReturn(mockScroll);
        when(mockScroll.getScroll_Name()).thenReturn("Test Scroll");
        when(mockScroll.getContent()).thenReturn("Scroll Content");

        Download.setUser_ID_key(123);
        Download.setScrollID(scrollID);
        Download.DownloadScroll();

        verify(mockScroll).setDownloadTimes();
        verify(mockScrollFactory).createScroll(Integer.parseInt(scrollID));
        verify(mockScroll).getScroll_Name();
        verify(mockScroll).getContent();
    }
}
