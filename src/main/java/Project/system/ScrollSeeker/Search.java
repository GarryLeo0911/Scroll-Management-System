package Project.system.ScrollSeeker;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Search {

    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";
    private String uploaderID = null;
    private String scrollID = null;
    private String name = null;
    private String uploadDate = null;

    private static Search search = new Search();

    private Search() {}

    public static Search getInstance() {
        return search;
    }

    public static void setUploaderID(String uploaderID) {
        search.uploaderID = uploaderID;
    }

    public static void setScrollID(String scrollID) {
        search.scrollID = scrollID;
    }

    public static void setName(String name) {
        search.name = name;
    }

    public static void setUploadDate(String uploadDate) {
        search.uploadDate = uploadDate;
    }

    public static ArrayList<Scroll> searchScroll() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Scroll> scrollList = new ArrayList<>();
        ScrollFactory scrollFactory = new ExistingScrollFactory();

        try {
            File tocFile = new File(TOC_JSON_PATH);
            if (!tocFile.exists()) {
                System.out.println("TableOfContent.json not found.");
                return scrollList;
            }

            ArrayNode arrayNode = (ArrayNode) mapper.readTree(tocFile);
            Iterator<JsonNode> iterator = arrayNode.elements();

            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                boolean matches = true;

                // Check for matching criteria
                if (search.name != null && !search.name.isEmpty() && !node.get("Scroll_Name").asText().equalsIgnoreCase(search.name)) {
                    matches = false;
                }
                if (search.scrollID != null && !search.scrollID.isEmpty() && !node.get("Scroll_ID").asText().equals(search.scrollID)) {
                    matches = false;
                }
                if (search.uploaderID != null && !search.uploaderID.isEmpty() && !node.get("Uploader_ID").asText().equals(search.uploaderID)) {
                    matches = false;
                }
                if (search.uploadDate != null && !search.uploadDate.isEmpty() && !node.get("Upload_Date").asText().equals(search.uploadDate)) {
                    matches = false;
                }

                if (matches) {
                    Scroll foundScroll = scrollFactory.createScroll(node.get("Scroll_ID").asInt());
                    scrollList.add(foundScroll);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scrollList;
    }
}