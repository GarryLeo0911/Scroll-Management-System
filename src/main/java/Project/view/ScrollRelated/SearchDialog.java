package Project.view.ScrollRelated;

import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollSeeker.Search;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SearchDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("Search and Filter Scrolls");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        Label uploaderLabel = new Label("Uploader ID:");
        TextField uploaderField = new TextField();

        Label scrollIdLabel = new Label("Scroll ID:");
        TextField scrollIdField = new TextField();

        Label nameLabel = new Label("Scroll Name:");
        TextField nameField = new TextField();

        Label dateLabel = new Label("Upload Date:");
        DatePicker datePicker = new DatePicker();

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            String uploader = uploaderField.getText().trim();
            String scrollId = scrollIdField.getText().trim();
            String name = nameField.getText().trim();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;

            // Perform search using criteria
            List<Scroll> results = performSearch(uploader, scrollId, name, date);

            if (results.isEmpty()) {
                showAlert("No Results", "No scrolls found matching the search criteria.");
            } else {
                ResultsDialog.show(results);  // Show results in the ResultsDialog
            }

            dialog.close();
        });

        grid.add(uploaderLabel, 0, 0);
        grid.add(uploaderField, 1, 0);
        grid.add(scrollIdLabel, 0, 1);
        grid.add(scrollIdField, 1, 1);
        grid.add(nameLabel, 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(dateLabel, 0, 3);
        grid.add(datePicker, 1, 3);
        grid.add(searchButton, 1, 4);

        dialog.setScene(new Scene(grid, 400, 300));
        dialog.show();
    }

    public static List<Scroll> performSearch(String uploader, String scrollId, String name, String date) {
        Search.setUploaderID(uploader.isEmpty() ? null : uploader);
        Search.setScrollID(scrollId.isEmpty() ? null : scrollId);
        Search.setName(name.isEmpty() ? null : name);
        Search.setUploadDate(date);

        ArrayList<Scroll> results = Search.searchScroll();
        return results;
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}