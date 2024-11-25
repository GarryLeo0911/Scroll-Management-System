package Project.view.ScrollRelated;

import Project.MainView;
import Project.system.ScrollManagement.Add;
import Project.system.User.User.UserTemplate;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;

public class AddScrollDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("Add New Scroll");

        GridPane grid = new GridPane();
        Label scrollNameLabel = new Label("Scroll Name:");
        TextField scrollNameField = new TextField();
        Label scrollContentLabel = new Label("Scroll Content:");
        TextField scrollContentField = new TextField();

        // File chooser for uploading the scroll file
        Button uploadButton = new Button("Upload Scroll");
        Label fileLabel = new Label("No file selected");

        uploadButton.setOnAction(e -> {
            UserTemplate user = MainView.getCurrentUser();
            if (user.getUserType().toString().equals("USER") || user.getUserType().toString().equals("ADMIN")) {
                if (!scrollNameField.getText().isEmpty()) {
                    Add.uploadScroll();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a Scroll Name", ButtonType.OK);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please sign in at first", ButtonType.OK);
                alert.showAndWait();
            }
        });

        Button addButton = new Button("Add Scroll");
        addButton.setOnAction(e -> {
            String scrollName = scrollNameField.getText();
            String scrollContent = scrollContentField.getText();

            UserTemplate user = MainView.getCurrentUser();

            if (user.getUserType().toString().equals("USER") || user.getUserType().toString().equals("ADMIN")) {
                Add.createScroll(scrollName, Integer.parseInt(String.valueOf(user.getIDKeys())));
                Add.setContent(scrollContent);
                user.addScroll(String.valueOf(Add.getScroll_ID()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please sign in at first!", ButtonType.OK);
                alert.showAndWait();
            }
        });

        grid.add(scrollNameLabel, 0, 0);
        grid.add(scrollNameField, 1, 0);
        grid.add(scrollContentLabel, 0, 1);
        grid.add(scrollContentField, 1, 1);
        grid.add(uploadButton, 0, 2);
        grid.add(fileLabel, 1, 2);
        grid.add(addButton, 1, 3);

        dialog.setScene(new Scene(grid, 400, 300));
        dialog.show();
    }
}