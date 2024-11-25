package Project.system.Scroll.ScrollFactory;

import Project.system.Scroll.Scroll.BlankScroll;
import Project.system.Scroll.Scroll.Scroll;

public class BlankScrollFactory implements ScrollFactory {

    @Override
    public Scroll createScroll(String scrollName, int scrollID, int uploaderId) {
        // Creates and returns a BlankScroll object
        return new BlankScroll(scrollName, scrollID, uploaderId);
    }

    // Optionally remove this if you don't need the method
    @Override
    public Scroll createScroll(int scrollID) {
        throw new UnsupportedOperationException("Uploader ID and scroll name are required for BlankScroll.");
    }
}
