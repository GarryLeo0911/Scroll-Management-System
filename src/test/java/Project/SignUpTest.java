package Project;

import Project.system.User.User.UserTemplate;
import Project.system.UserManagement.SignUp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class SignUpTest {

    @Test
    public void testSignUpUser() {
        SignUp signUp = SignUp.getInstance();
        SignUp.setPassword("dummyPassword");  // Provide a dummy password to avoid null
        SignUp.setFullName("John Doe");
        SignUp.setEmail("johndoe@example.com");
        SignUp.setPhone("1234567890");
        SignUp.setID_key("123");

        UserTemplate mockUser = Mockito.mock(UserTemplate.class);
        Mockito.when(mockUser.getUsername()).thenReturn("John Doe");
        Mockito.when(mockUser.getEmail()).thenReturn("johndoe@example.com");
        Mockito.when(mockUser.getPhoneNumber()).thenReturn("1234567890");
        Mockito.when(mockUser.getIDKeys()).thenReturn(123);

        UserTemplate newUser = SignUp.signUpUser();
        assertEquals("John Doe", newUser.getUsername());
        assertEquals("johndoe@example.com", newUser.getEmail());
        assertEquals("1234567890", newUser.getPhoneNumber());
        assertEquals(123, newUser.getIDKeys());
    }

    @Test
    public void testCheckID_keyExists() {
        SignUp signUp = SignUp.getInstance();
        SignUp.setID_key("12345");

        boolean exists = SignUp.checkID_key();
        assertTrue(exists);  // Adjust depending on your initial JSON content
    }
}