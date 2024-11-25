package Project;

import Project.system.UserManagement.SignIn;
import Project.system.User.User.UserTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignInTest {

    @Mock
    private UserTemplate mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        SignIn.setID_key("12345");
        SignIn.setPassword("testpassword");
    }

    @Test
    void testCheckID_keyExists() {
        // Simulate existing ID check without mocking static methods
        assertTrue(SignIn.checkID_key());
    }



    @Test
    void testSignInUser() {
        UserTemplate user = SignIn.signInUser();
        assertNotNull(user);
    }

    @Test
    void testHashPassword() {
        String hashedPassword = SignIn.getInstance().hashPassword("testpassword");
        assertNotNull(hashedPassword);
        assertEquals(64, hashedPassword.length()); // SHA-256 generates a 64-character hex string
    }
}