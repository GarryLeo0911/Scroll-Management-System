package Project.system.User.UserFactory;

import Project.system.User.User.UserTemplate;

public interface UserFactory {
    //Used for create the normal user
    UserTemplate createUser(String password, String full_name, String email, String phone, int ID_key);

    //Used for get the existing user
    UserTemplate createUser(int ID_key);
}
