package Project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Project.system.ScrollSeeker.View;
import Project.system.ScrollSeeker.Preview;
import Project.system.User.State.Guest;
import Project.system.Scroll.Scroll.Scroll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GuestStateTest {

    private Guest guestState;

    @BeforeEach
    void setUp() {
        guestState = new Guest();
        View.view = mock(View.class);  // Mock the View object to isolate the logic
        Preview.preview = mock(Preview.class);  // Mock the Preview object
    }



    @Test
    void testManageUser() {
        // Simulate manage user behavior for guest
        guestState.Manage_User();
        // There's no assertion here as it's just printing a message
    }

    @Test
    void testDownload() {
        // Simulate download behavior for guest
        guestState.Download();
        // No assertion, just printing a message
    }

    @Test
    void testAddScroll() {
        // Simulate adding a scroll as a guest
        guestState.Add_Scroll("TestScroll", 1);
        // No assertion, just printing a message
    }

    @Test
    void testRemoveScroll() {
        // Simulate removing a scroll as a guest
        guestState.Remove_Scroll("1");
        // No assertion, just printing a message
    }

    @Test
    void testEditScroll() {
        // Simulate editing a scroll as a guest
        guestState.Edit_Scroll("1", "New Content");
        // No assertion, just printing a message
    }
}
