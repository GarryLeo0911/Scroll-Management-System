package Project;

import static org.junit.jupiter.api.Assertions.*;
import Project.system.User.User.UserTemplate;
import Project.system.UserManagement.ListUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ListUserTest {

    @Test
    public void testGetUsers() {
        // Get the list of users
        ArrayList<UserTemplate> users = ListUser.getUsers();

        // Check that the list is not null
        assertNotNull(users);

        // You can also add more specific assertions depending on your expected data
        // For example, check that the user list contains at least one user
        assertTrue(users.size() > 0);

        // Further assertions can be added depending on the structure of UserTemplate
        // Example: check the type of user
        UserTemplate firstUser = users.get(0);
        assertEquals("ADMIN", firstUser.getUserType().toString());
    }
}
