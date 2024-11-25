package Project.system.Scroll.ScrollFactory;

import Project.system.Scroll.Scroll.Scroll;

public interface ScrollFactory {
    Scroll createScroll(String scrollName, int scrollID, int uploaderId);

    // Add overloaded method for when only scrollName is provided
    Scroll createScroll(int scrollID);
}
