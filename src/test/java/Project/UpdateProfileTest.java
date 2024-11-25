package Project;

import static org.junit.jupiter.api.Assertions.*;
import Project.system.UserManagement.UpdateProfile;
import Project.system.User.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateProfileTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("password123", "Test User", "test@example.com", "1234567890", 12345);
        UpdateProfile.getInstance();
        UpdateProfile.setID_key(String.valueOf(user.getIDKeys()));
        UpdateProfile.setUser();
    }



    @Test
    public void testSetFullName() {
        UpdateProfile.getInstance();
        String newFullName = "Test User";
        UpdateProfile.setFull_name(newFullName);
        assertEquals(newFullName, user.getUsername());
    }


    @Test
    public void testUpdate() {
        UpdateProfile.getInstance();
        // Assuming `user.updateUser()` will persist changes, we can check if user data is changed
        UpdateProfile.update();
        assertEquals("Test User", user.getUsername());
    }
}