package Project.system.UserManagement;

import Project.system.User.User.UserTemplate;
import Project.system.User.UserFactory.OldUserFactory;

public class UpdateProfile {
    private OldUserFactory oldUserFactory;

    private int ID_key;

    private UserTemplate user;

    private static UpdateProfile updateProfile = new UpdateProfile();

    private UpdateProfile() {
        this.oldUserFactory = new OldUserFactory();
    }

    public static UpdateProfile getInstance() {
        return updateProfile;
    }

    public static void setID_key(String ID_key) {
        updateProfile.ID_key = Integer.parseInt(ID_key);
    }

    public static void setUser() {
        updateProfile.user = updateProfile.oldUserFactory.createUser(updateProfile.ID_key);
    }

    public static void setPassword(String password) {
        updateProfile.user.setPassword(password);
    }

    public static void setFull_name(String full_name) {
        updateProfile.user.setUsername(full_name);
    }

    public static void setEmail(String email) {
        updateProfile.user.setEmail(email);
    }

    public static void setPhone(String phone) {
        updateProfile.user.setPhoneNumber(phone);
    }

    public static void update() {
        updateProfile.user.updateUser();
    }
}
