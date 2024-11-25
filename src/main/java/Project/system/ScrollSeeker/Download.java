package Project.system.ScrollSeeker;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import Project.system.ScrollManagement.Add;

public class Download {
    private String TableOfContentPath = "src/main/resources/Project/Scroll/TableOfContent.json";

    private String UserListFile = "src/main/resources/Project/User/UserList.json";

    private int User_ID_key;

    public ScrollFactory scrollFactory;

    private Scroll scroll;

    private String ScrollID;

    private static Download download = new Download();

    private Download() {
        scrollFactory = new ExistingScrollFactory();
    }

    public static Download getInstance() {
        return download;
    }

    public static void setUser_ID_key(int user_ID_key) {
        download.User_ID_key = user_ID_key;
    }

    public static void setScrollID(String scrollID) {
        download.ScrollID = scrollID;
        download.scroll = download.scrollFactory.createScroll(Integer.parseInt(scrollID));
    }

    public static void DownloadScroll() {
        download.scroll.setDownloadTimes();
        Add.createScroll(download.scroll.getScroll_Name(), download.User_ID_key);
        Add.setContent(download.scroll.getContent());
        Add.uploadScroll();
    }
}