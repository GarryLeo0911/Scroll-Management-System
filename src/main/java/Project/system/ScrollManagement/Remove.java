package Project.system.ScrollManagement;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

public class Remove {
    private ScrollFactory scrollFactory;
    private Scroll scroll;
    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";
    private static Remove removeInstance = new Remove();

    private Remove() {
        scrollFactory = new ExistingScrollFactory();
    }

    public static void setScroll(String ScrollID) {
        removeInstance.scroll=removeInstance.scrollFactory.createScroll(Integer.parseInt(ScrollID));
    }

    public static int getScrollID() {
        return removeInstance.scroll.getScroll_ID();
    }

    public static void removeScroll() {
        if (removeInstance.scroll == null) {
            System.out.println("No scroll is currently loaded.");
            return;
        }

        int scrollID = removeInstance.scroll.getScroll_ID();
        removeInstance.removeFromTableOfContents(scrollID);
        removeInstance.deleteScrollFile(scrollID);
    }

    private void removeFromTableOfContents(int scrollID) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File tocFile = new File(TOC_JSON_PATH);
            if (!tocFile.exists()) {
                System.out.println("TableOfContent.json not found.");
                return;
            }

            // Read existing JSON data
            ArrayNode arrayNode = (ArrayNode) mapper.readTree(tocFile);

            // Iterate over the JSON array to find and remove the scroll
            Iterator<JsonNode> iterator = arrayNode.elements();
            boolean scrollFound = false;
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                if (node.has("Scroll_ID") && node.get("Scroll_ID").asInt() == scrollID) {
                    iterator.remove();
                    scrollFound = true;
                    break;
                }
            }

            if (!scrollFound) {
                System.out.println("Scroll not found in TableOfContent.json.");
                return;
            }

            // Write the updated array back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(TOC_JSON_PATH).toFile(), arrayNode);
            System.out.println("Scroll removed from TableOfContent.json.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteScrollFile(int scrollID) {
        // Build the file path
        File scrollFile = new File("src/main/resources/Project/Scroll/" + scrollID + ".txt");

        if (scrollFile.exists()) {
            if (scrollFile.delete()) {
                System.out.println("Scroll file deleted successfully.");
            } else {
                System.out.println("Failed to delete the scroll file.");
            }
        } else {
            System.out.println("Scroll file not found.");
        }
    }
}
