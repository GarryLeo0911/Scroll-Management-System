package Project.system.User.State;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.User.User.UserTemplate;

import java.util.ArrayList;

public interface UserState {
    String View();

    ArrayList<Scroll> Preview();

    void Update_Profile();

    void Manage_User();

    void View_Scroll_State();

    ArrayList<UserTemplate> View_User();

    ArrayList<Scroll> Search();

    void Download();

    void Add_Scroll(String Scroll_Name, int Uploader_ID);

    void Remove_Scroll(String ScrollID);

    void Edit_Scroll(String ScrollID, String Content);
}
