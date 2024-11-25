package Project;

import static org.junit.jupiter.api.Assertions.*;

import Project.system.User.User.Admin;
import Project.system.User.User.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AdminTest {

    private Admin admin;

    @BeforeEach
    public void setUp() {
        // Initialize a new Admin instance for testing
        admin = new Admin("adminpassword", "Admin User", "admin@example.com", "1234567890", 12345);
    }

    @Test
    public void testSignUp() {
        assertEquals("Admin User", admin.getUsername());
        assertEquals("admin@example.com", admin.getEmail());
        assertEquals("1234567890", admin.getPhoneNumber());
        assertNotNull(admin.getPassword()); // Ensure password is hashed
        assertEquals(UserType.ADMIN, admin.getUserType());
    }

    @Test
    public void testUpdateUser() {
        admin.setUsername("Updated Admin");
        admin.setEmail("updatedadmin@example.com");
        admin.setPhoneNumber("0987654321");
        admin.updateUser();

        assertEquals("Updated Admin", admin.getUsername());
        assertEquals("updatedadmin@example.com", admin.getEmail());
        assertEquals("0987654321", admin.getPhoneNumber());
    }

    @Test
    public void testAddScroll() {
        admin.addScroll("NewScrollID");
        ArrayList<String> scrollsOwned = admin.getScrollsOwned();
        assertNotNull(scrollsOwned);
        assertTrue(scrollsOwned.contains("NewScrollID"));
    }

    @Test
    public void testSetAndGetProfilePicturePath() {
        String path = "src/main/resources/Project/UserProfilePictures/12345.png";
        admin.setProfilePicturePath(path);
        assertEquals(path, admin.getProfilePicturePath());
    }

    @Test
    public void testHashPassword() {
        String plainPassword = "adminpassword";
        String hashedPassword = admin.getPassword();
        assertNotEquals(plainPassword, hashedPassword); // Ensure password is hashed
    }
}
