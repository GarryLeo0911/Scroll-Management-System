package Project.system.UserManagement;

import Project.system.User.UserFactory.NewUserFactory;
import Project.system.User.UserFactory.OldUserFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ManageUser {
    private static final String TOC_JSON_PATH = "src/main/resources/Project/User/UserList.json";
    private NewUserFactory newUserFactory;
    private OldUserFactory oldUserFactory;

    private String password;
    private String full_name;
    private String email;
    private String phone;
    private int ID_key;

    private static ManageUser manageUser = new ManageUser();

    private ManageUser() {
        this.newUserFactory = new NewUserFactory();
        this.oldUserFactory = new OldUserFactory();
    }

    public static ManageUser getInstance() {
        return manageUser;
    }

    public static void setPassword(String password) {
        manageUser.password = password;
    }

    public static void setFullName(String full_name) {
        manageUser.full_name = full_name;
    }

    public static void setEmail(String email) {
        manageUser.email = email;
    }

    public static void setPhone(String phone) {
        manageUser.phone = phone;
    }

    public static void setID_key(int ID_key) {
        manageUser.ID_key = ID_key;
    }

    public static boolean checkID_key() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read the JSON file
            File tocFile = new File("src/main/resources/Project/User/UserList.json");
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);

                // Iterate through the array of users to check if the ID_key exists
                for (JsonNode userNode : rootNode) {
                    if (userNode.get("ID_key").asInt() == manageUser.ID_key) {
                        return true; // ID_key found, it already exists
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // ID_key not found, it doesn't exist
    }

    public static void addUser() {
        manageUser.newUserFactory.createUser(manageUser.password, manageUser.full_name, manageUser.email, manageUser.phone, manageUser.ID_key);
    }

    public static void deleteUser() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read the existing JSON data
            File tocFile = new File(TOC_JSON_PATH);
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(tocFile);

            // Find and remove the user by ID_key
            for (int i = 0; i < arrayNode.size(); i++) {
                ObjectNode userObj = (ObjectNode) arrayNode.get(i);
                if (userObj.get("ID_key").asInt() == manageUser.ID_key) {
                    arrayNode.remove(i);
                    break;
                }
            }

            //Write the data back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(TOC_JSON_PATH).toFile(), arrayNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
