package Project.system.User.State;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollSeeker.Preview;
import Project.system.ScrollSeeker.Search;
import Project.system.ScrollSeeker.View;
import Project.system.User.User.UserTemplate;

import java.util.ArrayList;

public class Admin implements UserState {
    @Override
    public String View() {
        return View.getScrollContent();
    }

    @Override
    public ArrayList<Scroll> Preview() {
        return Preview.getScrollsList();
    }

    @Override
    public void Update_Profile() {

    }

    @Override
    public void Manage_User() {

    }

    @Override
    public void View_Scroll_State() {

    }

    @Override
    public ArrayList<UserTemplate> View_User() {
        return null;
    }

    @Override
    public ArrayList<Scroll> Search() {
        return Search.searchScroll();
    }

    @Override
    public void Download() {

    }

    @Override
    public void Add_Scroll(String Scroll_Name, int Uploader_ID) {

    }

    @Override
    public void Remove_Scroll(String ScrollID) {

    }

    @Override
    public void Edit_Scroll(String ScrollID, String Content) {

    }

}
