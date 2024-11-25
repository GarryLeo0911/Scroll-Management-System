package Project.system.User.User;

import Project.system.User.State.UserState;

import java.util.ArrayList;

public interface UserTemplate {
    void signUp();

    UserType getUserType();

    void setPassword(String password);

    String getPassword();

    String getUsername();

    void setUsername(String username);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    int getIDKeys();

    void setIDKeys(String IDKeys);

    UserState getUserState();

    void addScroll(String ScrollID);

    ArrayList<String> getScrollsOwned();

    void updateUser();

    void setProfilePicturePath(String path);

    String getProfilePicturePath();
}
