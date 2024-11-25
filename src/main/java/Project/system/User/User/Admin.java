package Project.system.User.User;

import Project.system.User.State.UserState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Admin implements UserTemplate {
    private String password;
    private String full_name;
    private String email;
    private String phone;
    private int ID_key;
    private ArrayList<String> Scrolls_Owned;
    private UserType userType;
    private UserState userState;
    private String profilePicturePath;

    private final String TOC_JSON_PATH = "src/main/resources/Project/User/UserList.json";
    private JSONArray catalog;

    // Constructor for signing up.
    public Admin(String password, String full_name, String email, String phone, int ID_key) {
        this.password = hashPassword(password);
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.ID_key = ID_key;
        this.userType = UserType.ADMIN;
        this.userState = new Project.system.User.State.Admin();
        this.Scrolls_Owned = new ArrayList<>();
        signUp();
    }


    // Override the Constructor so that it can be used to get the existing User
    public Admin(int ID_key){
        this.ID_key = ID_key;
        searchUser();
    }


    @Override
    public void signUp() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File tocFile = new File(TOC_JSON_PATH);
            ArrayNode arrayNode;

            // Read existing JSON data or create new ArrayNode if the file doesn't exist
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);
                if (rootNode.isArray()) {
                    arrayNode = (ArrayNode) rootNode;
                } else {
                    arrayNode = mapper.createArrayNode();
                }
            } else {
                arrayNode = mapper.createArrayNode();
            }

            // Create new JSON object for this scroll
            ObjectNode userData = mapper.createObjectNode();
            userData.put("ID_key", ID_key);
            userData.put("Full_Name", full_name);
            userData.put("Phone_Number", phone);
            userData.put("Email", email);
            userData.put("userType", userType.toString());
            userData.put("Password", password);
            userData.put("ProfilePicturePath", "src/main/resources/Project/UserProfilePictures/" + ID_key + ".png");
            // Serialize Scrolls_Owned (ArrayList<String>) into JSON
            ArrayNode scrollsArrayNode = mapper.createArrayNode();
            for (String scroll : Scrolls_Owned) {
                scrollsArrayNode.add(scroll);  // Since it's just a string, directly add it to the array
            }

            // Add the serialized scrolls list to the user data
            userData.set("Scrolls_Owned", scrollsArrayNode);

            // Append new user metadata to array
            arrayNode.add(userData);

            // Write updated data to TableOfContent.json
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(TOC_JSON_PATH).toFile(), arrayNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public UserType getUserType() {
        return this.userType;
    }


    @Override
    public void setPassword(String password) {
        this.password = hashPassword(password);
    }


    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.full_name;
    }


    @Override
    public void setUsername(String username) {
        this.full_name = username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }


    @Override
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String getPhoneNumber() {
        return this.phone;
    }


    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }


    @Override
    public int getIDKeys() {
        return this.ID_key;
    }


    @Override
    public void setIDKeys(String IDKeys) {
        this.ID_key = Integer.parseInt(IDKeys);
    }


    @Override
    public UserState getUserState() {
        return this.userState;
    }

    @Override
    public void addScroll(String ScrollID) {
        this.Scrolls_Owned.add(ScrollID);
    }


    @Override
    public ArrayList<String> getScrollsOwned() {
        return this.Scrolls_Owned;
    }


    private void searchUser() {
        // Get the file's information
        JSONParser parser = new JSONParser();

        try {
            this.catalog = (JSONArray) parser.parse(new FileReader(TOC_JSON_PATH)); // Parse the JSON array directly
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (ParseException e) {
            System.out.println("Error parsing config file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error reading config file");
            System.exit(0);
        }

        // Search the JSON Array
        JSONObject userobj = null;
        for (Object obj : catalog) {
            JSONObject temporaryObj = (JSONObject) obj;
            // Assuming Scroll_ID is stored as a number in the JSON object
            if (temporaryObj.get("ID_key") instanceof Number) {
                int scrollIdFromJson = ((Number) temporaryObj.get("ID_key")).intValue();
                if (scrollIdFromJson == ID_key) {
                    userobj = temporaryObj;
                }
            }
        }

        if (userobj != null) {
            this.full_name = userobj.get("Full_Name").toString();
            this.email = userobj.get("Email").toString();
            this.phone = userobj.get("Phone_Number").toString();
            this.password = userobj.get("Password").toString();

            // Deserialize Scrolls_Owned from JSON
            JSONArray scrollsArray = (JSONArray) userobj.get("Scrolls_Owned");
            this.Scrolls_Owned = new ArrayList<>();
            if (scrollsArray != null) {
                for (Object scroll : scrollsArray) {
                    Scrolls_Owned.add(scroll.toString());
                }
            }

            // Handle userType deserialization
            String userTypeStr = userobj.get("userType").toString();
            this.userType = UserType.valueOf(userTypeStr);

            if (this.userType == UserType.ADMIN) {
                this.userState = new Project.system.User.State.Admin();
            }
        }
    }


    // Update the json file after the information of user is updated
    @Override
    public void updateUser() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File tocFile = new File(TOC_JSON_PATH);
            ArrayNode arrayNode;

            // Read existing JSON data
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);
                if (rootNode.isArray()) {
                    arrayNode = (ArrayNode) rootNode;
                } else {
                    arrayNode = mapper.createArrayNode();
                }
            } else {
                System.out.println("UserList.json not found");
                return;
            }

            // Iterate through the array to find the user by ID_key
            boolean userFound = false;
            for (int i = 0; i < arrayNode.size(); i++) {
                ObjectNode userObj = (ObjectNode) arrayNode.get(i);
                if (userObj.get("ID_key").asInt() == this.ID_key) {
                    // Update user fields
                    userObj.put("Full_Name", this.full_name);
                    userObj.put("Phone_Number", this.phone);
                    userObj.put("Email", this.email);
                    userObj.put("userType", this.userType.toString());
                    userObj.put("Password", this.password);
                    userObj.put("ProfilePicturePath", this.profilePicturePath);
                    // Update Scrolls_Owned
                    ArrayNode updatedScrollsArray = mapper.createArrayNode();
                    for (String scroll : Scrolls_Owned) {
                        updatedScrollsArray.add(scroll);
                    }
                    userObj.set("Scrolls_Owned", updatedScrollsArray);

                    userFound = true;
                    break;
                }
            }

            if (userFound) {
                // Write updated data back to the JSON file
                mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(TOC_JSON_PATH).toFile(), arrayNode);
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("User with ID_key " + this.ID_key + " not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String hashPassword(String password) {
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
    @Override
    public void setProfilePicturePath(String path) {
        this.profilePicturePath = path;
        updateUser();  // Save the updated profile picture to the JSON file
    }

    @Override
    public String getProfilePicturePath() {
        System.out.println("Retrieving profile picture path: " + this.profilePicturePath);  // Debugging line
        return this.profilePicturePath;
    }
}
