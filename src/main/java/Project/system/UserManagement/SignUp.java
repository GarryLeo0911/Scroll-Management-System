package Project.system.UserManagement;

import Project.system.User.User.UserTemplate;
import Project.system.User.UserFactory.NewUserFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SignUp {
    public NewUserFactory newUserFactory;

    private String password;
    private String full_name;
    private String email;
    private String phone;
    private int ID_key;

    private static SignUp signUp = new SignUp();

    private SignUp() {
        this.newUserFactory = new NewUserFactory();
    }

    public static SignUp getInstance() {
        return signUp;
    }

    public static void setPassword(String password) {
        signUp.password = password;
    }

    public static void setFullName(String full_name) {
        signUp.full_name = full_name;
    }

    public static void setEmail(String email) {
        signUp.email = email;
    }

    public static void setPhone(String phone) {
        signUp.phone = phone;
    }

    public static void setID_key(String ID_key) {
        signUp.ID_key = Integer.parseInt(ID_key);
    }

    public static UserTemplate signUpUser() {
        UserTemplate user = signUp.newUserFactory.createUser(signUp.password, signUp.full_name, signUp.email, signUp.phone, signUp.ID_key);
        return user;
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
                    if (userNode.get("ID_key").asInt() == signUp.ID_key) {
                        return true; // ID_key found, it already exists
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // ID_key not found, it doesn't exist
    }
}
