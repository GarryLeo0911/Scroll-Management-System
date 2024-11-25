package Project.system.UserManagement;

import Project.system.User.User.UserTemplate;
import Project.system.User.UserFactory.OldUserFactory;
import Project.system.User.UserFactory.UserFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignIn {
    private UserFactory userFactory;

    private int ID_key;
    private String password = "";

    private static SignIn signIn = new SignIn();

    private SignIn() {
        this.userFactory = new OldUserFactory();
    }

    public static SignIn getInstance() {
        return signIn;
    }

    public static void setID_key(String ID_key) {
        signIn.ID_key = Integer.parseInt(ID_key);
    }

    public static void setPassword(String password) {
        signIn.password = password;
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
                    if (userNode.get("ID_key").asInt() == signIn.ID_key) {
                        return true; // ID_key found, it already exists
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // ID_key not found, it doesn't exist
    }

    public static boolean checkPassword() {
        UserTemplate user = signIn.userFactory.createUser(signIn.ID_key);
        return user.getPassword().equals(signIn.hashPassword(signIn.password));
    }

    public static UserTemplate signInUser() {
        return signIn.userFactory.createUser(signIn.ID_key);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
