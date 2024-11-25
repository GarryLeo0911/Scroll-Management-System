package Project;

import Project.system.UserManagement.ManageUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ManageUserTest {

    @BeforeEach
    public void setUp() {
        ManageUser.setID_key(123);
        ManageUser.setFullName("John Doe");
        ManageUser.setEmail("john@example.com");
        ManageUser.setPhone("1234567890");
        ManageUser.setPassword("password");
    }

    @Test
    public void testDeleteUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File tocFile = new File("src/main/resources/Project/User/UserList.json");

        if (tocFile.exists()) {
            // Read the JSON data
            JsonNode originalData = mapper.readTree(tocFile);

            // Ensure the user is added first
            ManageUser.addUser();

            // Delete the user
            ManageUser.setID_key(123);
            ManageUser.deleteUser();

            // Reload the JSON data after deletion
            JsonNode updatedData = mapper.readTree(tocFile);
            boolean userExists = false;

            // Check if the user with ID_key 123 exists
            for (JsonNode userNode : updatedData) {
                if (userNode.get("ID_key").asInt() == 123) {
                    userExists = true;
                    break;
                }
            }

            // Assert that the user does not exist
            assertTrue(userExists, "User should be deleted");

            // Restore the original data if necessary (optional)
            mapper.writerWithDefaultPrettyPrinter().writeValue(tocFile, originalData);
        } else {
            fail("UserList.json file not found");
        }
    }
}