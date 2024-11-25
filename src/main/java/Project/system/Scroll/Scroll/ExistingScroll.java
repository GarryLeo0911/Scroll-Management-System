package Project.system.Scroll.Scroll;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ExistingScroll implements Scroll {
    private String Scroll_Name;
    private int Scroll_ID;
    private int Uploader_ID;
    private int DownloadTimes;
    private int UploadTimes;
    private LocalDate Upload_Date;
    private String Content;

    // Path to the JSON file
    private static final String TOC_JSON_PATH = "src/main/resources/Project/Scroll/TableOfContent.json";

    // The JSON array representing the catalog
    private JSONArray catalog;

    // Constructor
    public ExistingScroll(int Scroll_ID) {
        setCatalog(); // Initialize the catalog from the JSON file
        this.Scroll_ID = Scroll_ID;

        // Find the specific scroll data by name
        JSONObject scrollData = findScrollDataByID(Scroll_ID);

        if (scrollData != null) {
            this.Scroll_Name = (String) scrollData.get("Scroll_Name");  // Cast Long to int
            this.Uploader_ID = ((Long) scrollData.get("Uploader_ID")).intValue(); // Cast Long to int
            this.UploadTimes = ((Long) scrollData.get("UploadTimes")).intValue();
            this.DownloadTimes = ((Long) scrollData.get("DownloadTimes")).intValue();
            String date = (String) scrollData.get("Upload_Date");
            this.Upload_Date = LocalDate.parse(date);  // Parse the date correctly
            readScroll();
        } else {
            System.out.println("Scroll with name '" + Scroll_Name + "' not found.");
        }
    }

    // Method to find the scroll data based on the id from the JSON array
    private JSONObject findScrollDataByID(int Scroll_ID) {
        for (Object obj : catalog) {
            JSONObject scrollObj = (JSONObject) obj;
            // Assuming Scroll_ID is stored as a number in the JSON object
            if (scrollObj.get("Scroll_ID") instanceof Number) {
                int scrollIdFromJson = ((Number) scrollObj.get("Scroll_ID")).intValue();
                if (scrollIdFromJson == Scroll_ID) {
                    return scrollObj;
                }
            }
        }
        return null;  // Return null if scroll not found
    }

    public String getScroll_Name() {
        return Scroll_Name;
    }

    public void setScroll_Name(String scroll_Name) {
        this.Scroll_Name = scroll_Name;
        update();
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

    public int getDownloadTimes() {
        return DownloadTimes;
    }

    public void setDownloadTimes() {
        this.DownloadTimes ++;
        updateDownloadTimesInJSON();
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
        writeToFile();
        update();
    }

    public void createFile() {
        return;
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

    // Read the JSON File and set the catalog as a JSONArray
    public void setCatalog() {
        JSONParser parser = new JSONParser();

        try {
            this.catalog = (JSONArray) parser.parse(new FileReader(TOC_JSON_PATH)); // Parse the JSON array directly
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        } catch (ParseException e) {
            System.out.println("Error parsing config file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error reading config file");
            System.exit(0);
        }
    }

    // Read the Scroll content from a text file
    private void readScroll() {
        try {
            File file = new File("src/main/resources/Project/Scroll/" + Scroll_ID + ".txt");
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                StringBuilder contentBuilder = new StringBuilder();
                while (reader.hasNextLine()) {
                    contentBuilder.append(reader.nextLine()).append("\n");
                }
                this.Content = contentBuilder.toString();
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    // Method to update the DownloadTimes in the JSON file
    public void updateDownloadTimesInJSON() {
        JSONParser parser = new JSONParser();
        try {
            // Read the existing JSON file
            File tocFile = new File(TOC_JSON_PATH);
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(tocFile));

            // Find the specific scroll by ID and update its DownloadTimes
            for (Object obj : jsonArray) {
                JSONObject scrollObj = (JSONObject) obj;
                if (scrollObj.get("Scroll_ID") instanceof Number) {
                    int scrollIdFromJson = ((Number) scrollObj.get("Scroll_ID")).intValue();
                    if (scrollIdFromJson == this.Scroll_ID) {
                        // Update the DownloadTimes in the JSON object
                        scrollObj.put("DownloadTimes", this.DownloadTimes);
                        break;
                    }
                }
            }

            // Write the updated JSON array back to the file
            FileWriter fileWriter = new FileWriter(TOC_JSON_PATH);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("JSON file not found.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    // Method to update the entire scroll data in the JSON file
    public void update() {
        JSONParser parser = new JSONParser();
        try {
            // Read the existing JSON file
            File tocFile = new File(TOC_JSON_PATH);
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(tocFile));

            // Find the specific scroll by ID and update its fields
            for (Object obj : jsonArray) {
                JSONObject scrollObj = (JSONObject) obj;
                if (scrollObj.get("Scroll_ID") instanceof Number) {
                    int scrollIdFromJson = ((Number) scrollObj.get("Scroll_ID")).intValue();
                    if (scrollIdFromJson == this.Scroll_ID) {
                        this.UploadTimes++;
                        this.Upload_Date = LocalDate.now();
                        // Update all relevant fields
                        scrollObj.put("Scroll_Name", this.Scroll_Name);
                        scrollObj.put("Uploader_ID", this.Uploader_ID);
                        scrollObj.put("UploadTimes", this.UploadTimes);
                        scrollObj.put("Upload_Date", this.Upload_Date.toString());

                        break;
                    }
                }
            }

            // Write the updated JSON array back to the file
            FileWriter fileWriter = new FileWriter(TOC_JSON_PATH);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("JSON file not found.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Scroll Name: " + Scroll_Name + ", Scroll ID: " + Scroll_ID + ", Uploader ID: " + Uploader_ID + ", Content: " + Content;
    }

}
