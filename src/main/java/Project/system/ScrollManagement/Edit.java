package Project.system.ScrollManagement;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

public class Edit {
    private ScrollFactory scrollFactory;
    private Scroll scroll;
    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";
    private static Edit editInstance = new Edit();

    private Edit() {
        scrollFactory = new ExistingScrollFactory();
    }

    public static Edit getInstance() {
        return editInstance;
    }

    public static void setScroll(String scrollID) {
        editInstance.scroll = editInstance.scrollFactory.createScroll(Integer.parseInt(scrollID));
    }

    public static void editScrollContent(String scrollContent) {
        editInstance.scroll.setContent(scrollContent);
        editInstance.scroll.writeToFile();
    }

    public static void editScrollName(String newScrollName) {
        editInstance.scroll.setScroll_Name(newScrollName); // Update scroll object name
    }
}
