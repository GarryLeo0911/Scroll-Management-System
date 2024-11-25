package Project.view.ScrollRelated;

import Project.MainView;
import Project.system.Scroll.Scroll.Scroll;
import Project.system.ScrollManagement.Edit;
import Project.system.User.User.UserTemplate;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditScrollDialog {

    public static void show(Scroll scroll) {
        Stage dialog = new Stage();
        dialog.setTitle("Edit Scroll: " + scroll.getScroll_Name());

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        // Check if the current user is the owner of the scroll
        UserTemplate currentUser = MainView.getCurrentUser();
        if (!String.valueOf(currentUser.getIDKeys()).equals(String.valueOf(scroll.getUploader_ID()))) {
            showAlert("Access Denied", "You can only edit scrolls that you have uploaded.");
            return;
        }

        // Set the scroll in Edit class backend
        Edit.setScroll(String.valueOf(scroll.getScroll_ID()));

        // Scroll Name Field
        Label nameLabel = new Label("Scroll Name:");
        TextField nameField = new TextField(scroll.getScroll_Name());

        // Text area for editing scroll content
        Label contentLabel = new Label("Scroll Content:");
        TextArea contentArea = new TextArea(scroll.getContent());  // Pre-fill with existing content
        contentArea.setPrefRowCount(10);  // Set the number of rows to display

        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> {
            String newName = nameField.getText();
            String newContent = contentArea.getText();

            if (newName.isEmpty()) {
                showAlert("Invalid Input", "Scroll name cannot be empty.");
                return;
            }

            // Logic to update the scroll details
            Edit.editScrollName(newName);  // Update the scroll name in the backend
            Edit.editScrollContent(newContent);  // Update the scroll content in the backend

            // Notify the user and close the dialog
            showAlert("Success", "Scroll updated successfully.");
            dialog.close();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(contentLabel, 0, 1);
        grid.add(contentArea, 1, 1);
        grid.add(saveButton, 1, 2);

        dialog.setScene(new Scene(grid, 400, 350));
        dialog.show();
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}