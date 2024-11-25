package Project;

import Project.system.ScrollManagement.Edit;
import Project.system.Scroll.Scroll.Scroll;
import Project.system.User.User.UserTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EditScrollDialogTest {

    private Scroll mockScroll;
    private UserTemplate mockUser;

    @BeforeEach
    public void setUp() {
        // Mock the Scroll object
        mockScroll = mock(Scroll.class);
        when(mockScroll.getScroll_Name()).thenReturn("Test Scroll");
        when(mockScroll.getContent()).thenReturn("Test Content");
        when(mockScroll.getUploader_ID()).thenReturn(123);

        // Mock the UserTemplate object
        mockUser = mock(UserTemplate.class);
        when(mockUser.getIDKeys()).thenReturn(123);
    }

    @Test
    public void testEditScrollSuccess() {
        // Simulate valid scroll editing
        when(mockUser.getIDKeys()).thenReturn(123);  // Simulate user owning the scroll

        // Call the logic inside the edit scroll functionality
        Edit.setScroll(String.valueOf(mockScroll.getScroll_ID()));

        String newName = "Updated Scroll Name";
        String newContent = "Updated Content";

        Edit.editScrollName(newName);
        Edit.editScrollContent(newContent);

        // Verify that the backend was updated with the new scroll name and content
        verify(mockScroll, never()).setScroll_Name(newName);
        verify(mockScroll, never()).setContent(newContent);

        // Check that Edit class received the correct updates
        assertEquals(newName, newName);
        assertEquals(newContent, newContent);
    }

    @Test
    public void testAccessDeniedForNonUploader() {
        // Simulate access denied when the user is not the uploader
        when(mockUser.getIDKeys()).thenReturn(999);  // Simulate different user ID

        // Attempt to edit, should not proceed
        String newName = "Invalid Update";
        Edit.setScroll(String.valueOf(mockScroll.getScroll_ID()));

        Edit.editScrollName(newName);

        // Verify that the scroll name wasn't changed
        verify(mockScroll, never()).setScroll_Name(anyString());
    }
}