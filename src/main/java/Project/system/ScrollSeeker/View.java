package Project.system.ScrollSeeker;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;

public class View {
    private String filename;
    private String directoryPath = "src/main/resources/Project/Scroll/";

    public static View view = new View();

    public View() {}

    public static View getView() {
        return view;
    }

    public static void setFilename(String filename) {
        view.filename = filename;
    }

    public static String getScrollContent() {
        int dotIndex = view.filename.lastIndexOf(".");

        String nameWithoutExtension = view.filename.substring(0, dotIndex);

        ScrollFactory ExistingScrollFactory = new ExistingScrollFactory();
        Scroll scroll = ExistingScrollFactory.createScroll(Integer.parseInt(nameWithoutExtension));
        return scroll.getContent();
    }
}
