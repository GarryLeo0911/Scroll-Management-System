package Project.system.User.State;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import Project.system.ScrollManagement.Add;
import Project.system.ScrollManagement.Edit;
import Project.system.ScrollManagement.Remove;
import Project.system.ScrollSeeker.Preview;
import Project.system.ScrollSeeker.Search;
import Project.system.ScrollSeeker.View;
import Project.system.User.User.UserTemplate;

import java.util.ArrayList;

public class User implements UserState {

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
        System.out.println("Sorry, you are not an Admin!");
    }

    @Override
    public void View_Scroll_State() {
        System.out.println("Sorry, you are not an Admin!");
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
        Add.createScroll(Scroll_Name, Uploader_ID);
    }

    @Override
    public void Remove_Scroll(String ScrollID) {
        Remove.setScroll(ScrollID);
        Remove.removeScroll();
    }

    @Override
    public void Edit_Scroll(String ScrollID, String Content) {
        Edit.setScroll(ScrollID);
        Edit.editScrollContent(Content);
    }

    private int getScrollOwnerID(int ScrollID) {
        ScrollFactory scrollFactory = new ExistingScrollFactory();
        Scroll scroll = scrollFactory.createScroll(ScrollID);

        return scroll.getUploader_ID();
    }
}
