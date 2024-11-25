package Project.view.AdminRelated;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollSeeker.Preview;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewStatsDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("View Scroll Stats");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label statsLabel = new Label("Scroll Statistics:");
        grid.add(statsLabel, 0, 0);

        // Fetch real stats from the Preview class
        ArrayList<Scroll> scrollsList = Preview.getScrollsList();

        // Add scroll stats dynamically
        int rowIndex = 1;
        for (Scroll scroll : scrollsList) {
            String scrollID = String.valueOf(scroll.getScroll_ID());
            String scrollName = scroll.getScroll_Name();
            int downloadTimes = scroll.getDownloadTimes();
            int uploadTimes = scroll.getUploadTimes();

            Label scrollDetails = new Label("Scroll ID:" + scrollID + "  |  " + scrollName + ": " +
                    downloadTimes + " downloads, " +
                    uploadTimes + " uploads");

            grid.add(scrollDetails, 0, rowIndex++);
        }

        dialog.setScene(new Scene(grid, 400, 300));
        dialog.show();
    }
}
