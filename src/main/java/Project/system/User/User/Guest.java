package Project.system.User.User;

import Project.system.User.State.UserState;

import java.util.ArrayList;

public class Guest implements UserTemplate {
    private UserType userType;
    private UserState userState;
    private String profilePicturePath;

    public Guest(){
        this.userType = UserType.GUEST;
        this.userState = new Project.system.User.State.Guest();
    }

    @Override
    public void signUp() {

    }

    @Override
    public UserType getUserType() {
        return  this.userType;
    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public String getPhoneNumber() {
        return "";
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {

    }

    @Override
    public int getIDKeys() {
        return -1;
    }

    @Override
    public void setIDKeys(String IDKeys) {

    }

    @Override
    public UserState getUserState() {
        return this.userState;
    }

    @Override
    public void addScroll(String ScrollID) {

    }

    @Override
    public ArrayList<String> getScrollsOwned() {
        return null;
    }

    @Override
    public void updateUser() {

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
