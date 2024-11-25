package Project.system.Scroll.Scroll;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.json.simple.JSONObject;

public class BlankScroll implements Scroll {
    private String Scroll_Name;
    private int Scroll_ID;
    private int Uploader_ID;
    private LocalDate Upload_Date;
    private String Content;
    private int DownloadTimes;
    private int UploadTimes;
    private File file;

    // Path to the JSON file
    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";

    // The Object of Catalog
    private JSONObject catalog;

    // Constructor
    public BlankScroll(String Scroll_Name, int Scroll_ID, int Uploader_ID) {
        this.Scroll_Name = Scroll_Name;
        this.Scroll_ID = Scroll_ID;
        this.Uploader_ID = Uploader_ID;
        this.UploadTimes = 1;
        this.DownloadTimes = 0;
        this.Upload_Date = LocalDate.now();
    }

    public String getScroll_Name() {
        return Scroll_Name;
    }

    public void setScroll_Name(String scroll_Name) {
        this.Scroll_Name = scroll_Name;
    }

    public int getScroll_ID() {
        return Scroll_ID;
    }

    public void setScroll_ID(int scroll_ID) {
        this.Scroll_ID = scroll_ID;
    }

    public int getUploader_ID() {
        return Uploader_ID;
    }

    public void setUploader_ID(int uploader_ID) {
        this.Uploader_ID = uploader_ID;
    }

    public void setDownloadTimes() {
        this.DownloadTimes++;
    }

    public int getDownloadTimes() {
        return DownloadTimes;
    }

    public int getUploadTimes() {
        return UploadTimes;
    }

    public LocalDate getUpload_Date() {
        return Upload_Date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public void createFile() {
        try {
            this.file = new File("src/main/resources/Project/Scroll/" + Scroll_ID + ".txt");

            // Create scroll file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                writeToFile();

                // Add scroll metadata to the TableOfContent.json file
                addToTableOfContents();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter("src/main/resources/Project/Scroll/" + Scroll_ID + ".txt");
            writer.write(getContent());
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void addToTableOfContents() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File tocFile = new File(TOC_JSON_PATH);
            ArrayNode arrayNode;

            // Check if file exists and read the current data
            if (tocFile.exists()) {
                JsonNode rootNode = mapper.readTree(tocFile);
                if (rootNode.isArray()) {
                    arrayNode = (ArrayNode) rootNode;
                } else {
                    arrayNode = mapper.createArrayNode();
                }
            } else {
                arrayNode = mapper.createArrayNode();
            }

            // Create new JSON object for the scroll data
            ObjectNode scrollData = mapper.createObjectNode();
            scrollData.put("Scroll_Name", Scroll_Name);
            scrollData.put("Scroll_ID", Scroll_ID);
            scrollData.put("Uploader_ID", Uploader_ID);
            scrollData.put("UploadTimes", UploadTimes);
            scrollData.put("DownloadTimes", DownloadTimes);
            scrollData.put("Upload_Date", Upload_Date.toString());

            // Add the new scroll metadata to the array
            arrayNode.add(scrollData);

            // Write the updated data to the TableOfContent.json file
            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(TOC_JSON_PATH).toFile(), arrayNode);

        } catch (IOException e) {
            System.err.println("Error updating Table of Contents: " + e.getMessage());
            e.printStackTrace();  // Still print the stack trace for debugging purposes
        }
    }
}
