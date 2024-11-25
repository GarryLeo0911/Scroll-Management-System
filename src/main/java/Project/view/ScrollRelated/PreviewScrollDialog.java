package Project.view.ScrollRelated;

import Project.MainView;
import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollManagement.Remove;
import Project.system.ScrollSeeker.Download;
import Project.system.User.User.UserTemplate;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PreviewScrollDialog {

    public static void show(Scroll scroll) {
        Stage dialog = new Stage();
        dialog.setTitle("Preview Scroll: " + scroll.getScroll_Name());

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        Label nameLabel = new Label("Scroll Name:");
        Label nameValue = new Label(scroll.getScroll_Name());

        Label uploaderLabel = new Label("Uploaded By:");
        Label uploaderValue = new Label(String.valueOf(scroll.getUploader_ID()));

        Label dateLabel = new Label("Upload Date:");
        Label dateValue = new Label(scroll.getUpload_Date().toString());

        // Download Button
        Button downloadButton = new Button("Download Scroll");
        downloadButton.setOnAction(e -> {
            if (!MainView.getCurrentUser().getUserType().toString().equals("GUEST")) {
                Download.setUser_ID_key(MainView.getCurrentUser().getIDKeys());
                Download.setScrollID(String.valueOf(scroll.getScroll_ID()));
                Download.DownloadScroll();
            } else {
                showAlert("Access Denied","Please Log in at first!");
            }
        });

        // View Button
        Button viewButton = new Button("View Scroll");
        viewButton.setOnAction(e -> {
            ViewScrollDialog.show(scroll);
        });

        // Edit Button
        Button editButton = new Button("Edit Scroll");
        editButton.setOnAction(e -> {
            if (MainView.getCurrentUser().getIDKeys() == MainView.getCurrentUser().getIDKeys()) {
                EditScrollDialog.show(scroll);
            } else {
                showAlert("Access Denied","You are not the owner of the Scroll!");
            }
        });

        // Delete Button (only visible if the current user is the owner of the scroll)
        Button deleteButton = new Button("Delete Scroll");
        deleteButton.setOnAction(e -> {
            UserTemplate currentUser = MainView.getCurrentUser();

            if (MainView.getCurrentUser().getUserType().toString().equals("GUEST")) {
                showAlert("Access Denied","Please Log in at first!");
            }

            if (!(currentUser.getIDKeys() == scroll.getUploader_ID())) {
                showAlert("Access Denied", "You can only delete scrolls that you have uploaded.");
                return;
            }

            // Call backend to delete the scroll
            Remove.setScroll(String.valueOf(scroll.getScroll_ID()));  // Set the scroll to be deleted
            Remove.removeScroll();  // Perform the delete operation

            showAlert("Delete Scroll", "Scroll deleted successfully.");
            dialog.close();  // Close the dialog after deletion
        });

        // Add components to the grid
        grid.add(nameLabel, 0, 0);
        grid.add(nameValue, 1, 0);
        grid.add(uploaderLabel, 0, 1);
        grid.add(uploaderValue, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(dateValue, 1, 2);
        grid.add(downloadButton, 1, 3);
        grid.add(viewButton, 1, 4);
        grid.add(editButton, 1, 5);

        // Only show the delete button if the current user is the owner of the scroll
        if (MainView.getCurrentUser().getIDKeys() == scroll.getUploader_ID()) {
            grid.add(deleteButton, 1, 6);
        }

        dialog.setScene(new Scene(grid, 400, 300));
        dialog.show();
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}