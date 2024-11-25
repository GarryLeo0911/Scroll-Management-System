package Project.system.ScrollSeeker;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.Scroll.ScrollFactory.ExistingScrollFactory;
import Project.system.Scroll.ScrollFactory.ScrollFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Preview {
    private static String TableOfContentPath = "src/main/resources/Project/Scroll/TableOfContent.json";

    // The JSON array representing the catalog
    public JSONArray catalog;

    public static Preview preview = new Preview();

    private Preview() {
    }

    public static Preview getPreview() {
        return preview;
    }

    // Method to get the list of scrolls
    public static ArrayList<Scroll> getScrollsList() {
        // Ensure catalog is loaded
        preview.setTableOfContent();

        ScrollFactory scrollFactory = new ExistingScrollFactory();
        ArrayList<Scroll> scrollsList = new ArrayList<>();

        // Check if the catalog is loaded correctly
        if (preview.catalog != null) {
            for (Object obj : preview.catalog) {
                JSONObject scrollObj = (JSONObject) obj;
                try {
                    int scrollId = ((Long) scrollObj.get("Scroll_ID")).intValue();
                    Scroll scroll = scrollFactory.createScroll(scrollId);
                    scrollsList.add(scroll);
                } catch (Exception e) {
                    System.err.println("Error processing scroll: " + scrollObj);
                    e.printStackTrace();
                }
            }
        }

        return scrollsList;
    }

    // Method to load the Table of Content from JSON
    public void setTableOfContent() {
        JSONParser parser = new JSONParser();
        try {
            // Read the JSON file
            FileReader reader = new FileReader(TableOfContentPath);
            catalog = (JSONArray) parser.parse(reader); // Parse the JSON array directly
        } catch (FileNotFoundException e) {
            System.err.println("TableOfContent.json file not found.");
            catalog = new JSONArray();  // Return an empty array if file is missing
        } catch (ParseException e) {
            System.err.println("Error parsing TableOfContent.json file.");
            e.printStackTrace();
            catalog = new JSONArray();  // Return an empty array on error
        } catch (IOException e) {
            System.err.println("Error reading TableOfContent.json file.");
            e.printStackTrace();
            catalog = new JSONArray();  // Return an empty array on error
        }
    }
}
