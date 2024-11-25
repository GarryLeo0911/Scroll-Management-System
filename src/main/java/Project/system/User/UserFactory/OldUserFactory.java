package Project.system.User.UserFactory;

import Project.system.User.User.Admin;
import Project.system.User.User.User;
import Project.system.User.User.UserTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class OldUserFactory implements UserFactory {
    private final String TOC_JSON_PATH = "src/main/resources/Project/User/UserList.json";

    @Override
    public UserTemplate createUser(String password, String full_name, String email, String phone, int ID_key) {
        return null;  // Not used in OldUserFactory
    }

    @Override
    public UserTemplate createUser(int ID_key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read the existing JSON data
            File tocFile = new File(TOC_JSON_PATH);
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);

                // Search for the user by ID_key in the JSON array
                for (JsonNode userNode : rootNode) {
                    if (userNode.get("ID_key").asInt() == ID_key) {
                        // Extract the user type from JSON
                        String userTypeStr = userNode.get("userType").asText();

                        // Debug statement
                        System.out.println("Creating user with ID: " + ID_key + " and type: " + userTypeStr);

                        // Create the appropriate user type object based on userTypeStr
                        if (userTypeStr.equals("ADMIN")) {
                            return new Admin(ID_key);  // Create Admin object
                        } else if (userTypeStr.equals("USER")) {
                            return new User(ID_key);  // Create User object
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no user with the given ID_key is found
    }
}
