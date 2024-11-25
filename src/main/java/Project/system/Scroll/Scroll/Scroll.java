package Project.system.Scroll.Scroll;

import java.time.LocalDate;

public interface Scroll {
    public String getScroll_Name();

    public void setScroll_Name(String scroll_Name);

    public int getScroll_ID();

    public void setScroll_ID(int scroll_ID);

    public int getUploader_ID();

    public void setUploader_ID(int uploader_ID);

    public LocalDate getUpload_Date();

    public String getContent();

    public void setContent(String content);

    public void createFile();

    public void writeToFile();

    public void setDownloadTimes();

    public int getDownloadTimes();

    public int getUploadTimes();
}
