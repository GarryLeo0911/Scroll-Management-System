package Project.system.User.UserFactory;

import Project.system.User.User.User;
import Project.system.User.User.UserTemplate;

public class NewUserFactory implements UserFactory {
    @Override
    public UserTemplate createUser(String password, String full_name, String email, String phone, int ID_key) {
        return new User(password, full_name, email, phone, ID_key);
    }

    @Override
    public UserTemplate createUser(int ID_key) {
        System.out.println("Lack of Information");
        return null;
    }
}
