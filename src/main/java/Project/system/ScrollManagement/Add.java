package Project.system.ScrollManagement;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.BlankScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add {
    private ScrollFactory Scroll_factory;

    private int Scroll_ID = 0;

    private static final String TOC_DIR_PATH = "src/main/resources/Project/Scroll/";

    private Scroll Scroll;

    private static Add Add = new Add();

    private Add() {
        this.Scroll_factory = new BlankScrollFactory();
        initializeScrollID();
    }

    public static Add getInstance() {
        return Add;
    }

    public static void createScroll(String Scroll_Name, int Uploader_ID) {
        Add.Scroll = Add.Scroll_factory.createScroll(Scroll_Name, Add.Scroll_ID, Uploader_ID);
    }

    public static int getScroll_ID() {
        return Add.Scroll_ID;
    }

    public static void setContent(String Content) {
        Add.Scroll.setContent(Content);
    }

    public static void uploadScroll() {
        Add.Scroll.createFile();
        Add.Scroll_ID = Add.Scroll_ID + 1;
    }

    // Method to initialize the Scroll_ID based on the files in the directory
    private void initializeScrollID() {
        File directory = new File(TOC_DIR_PATH);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                int maxID = 0;
                Pattern pattern = Pattern.compile("(\\d+)\\.txt");

                for (File file : files) {
                    Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        if (id > maxID) {
                            maxID = id;
                        }
                    }
                }
                // Initialize Scroll_ID to maxID + 1 to avoid collision
                this.Scroll_ID = maxID + 1;
            }
        }
    }
}
