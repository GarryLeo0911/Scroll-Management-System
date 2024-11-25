package Project.system.UserManagement;

import Project.system.User.User.User;
import Project.system.User.User.UserTemplate;
import Project.system.User.User.UserType;
import Project.system.User.UserFactory.OldUserFactory;
import Project.system.User.UserFactory.UserFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ListUser {
    private static final String TOC_JSON_PATH = "src/main/resources/Project/User/UserList.json";
    private static ListUser listUser = new ListUser();
    private static UserFactory userFactory;

    private ListUser() {
        // Private constructor to enforce Singleton pattern
        userFactory = new OldUserFactory();
    }

    public static ListUser getInstance() {
        return listUser;
    }

    // Method to get a list of normal users (excluding admin users)
    public static ArrayList<UserTemplate> getUsers() {
        ArrayList<UserTemplate> normalUsers = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Read the JSON file
            File tocFile = new File(listUser.TOC_JSON_PATH);
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);

                // Iterate through the users
                for (JsonNode userNode : rootNode) {
                    // Check if the user is not an admin
                    String userTypeStr = userNode.get("userType").asText();
                    if (userTypeStr.equals(UserType.USER.toString())) {
                        // Create a UserTemplate object for normal users
                        int id = userNode.get("ID_key").asInt();

                        // Assuming User class implements UserTemplate
                        UserTemplate user = ListUser.userFactory.createUser(id);
                        normalUsers.add(user);

                    }
                }
            } else {
                System.out.println("UserList.json not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return normalUsers;
    }
}
