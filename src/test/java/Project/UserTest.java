package Project;

import static org.junit.jupiter.api.Assertions.*;

import Project.system.User.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;
    private User existingUser;

    @BeforeEach
    public void setUp() {
        // Initialize a new user for testing
        user = new User("password123", "Test User", "testuser@example.com", "1234567890", 12345);

        // Load an existing user for testing (assuming user with ID 114514 exists)
        existingUser = new User(114514);
    }

    @Test
    public void testSignUp() {
        assertEquals("Test User", user.getUsername());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhoneNumber());
        assertNotNull(user.getPassword()); // Ensure password is hashed
    }

    @Test
    public void testSetProfilePicturePath() {
        String path = "src/main/resources/Project/UserProfilePictures/test.png";
        user.setProfilePicturePath(path);
        assertEquals(path, user.getProfilePicturePath());
    }



}
