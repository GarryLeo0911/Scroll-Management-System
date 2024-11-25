package Project.system.Scroll.ScrollFactory;

import Project.system.Scroll.Scroll.ExistingScroll;
import Project.system.Scroll.Scroll.Scroll;

public class ExistingScrollFactory implements ScrollFactory {

    @Override
    public Scroll createScroll(int scrollID) {
        // Existing scroll creation with only scroll name
        return new ExistingScroll(scrollID);
    }

    @Override
    public Scroll createScroll(String scrollName, int scrollID, int uploaderId) {
        return new ExistingScroll(scrollID);
    }
}
