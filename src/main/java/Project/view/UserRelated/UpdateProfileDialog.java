package Project.view.UserRelated;

import Project.MainView;
import Project.system.User.User.UserTemplate;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateProfileDialog {

    public static void show() {
        UserTemplate currentUser = MainView.getCurrentUser();
        Stage dialog = new Stage();
        dialog.setTitle("Update Profile");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField(currentUser.getUsername());

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(currentUser.getEmail());

        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField(currentUser.getPhoneNumber());

        Label passwordLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();

        // Profile Picture Upload
        Label profilePicLabel = new Label("Profile Picture:");
        ImageView profilePictureView = new ImageView();
        profilePictureView.setFitWidth(100);
        profilePictureView.setFitHeight(100);

        // Load the existing profile picture if the path exists
        if (currentUser.getProfilePicturePath() != null) {
            File profilePicFile = new File(currentUser.getProfilePicturePath());
            if (profilePicFile.exists()) {
                Image profileImage = new Image(profilePicFile.toURI().toString());
                profilePictureView.setImage(profileImage);
            }
        }

        Label selectedFileLabel = new Label("No file selected");
        Button uploadButton = new Button("Upload Picture");

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(dialog);
            if (selectedFile != null) {
                try {
                    // Get the file extension
                    String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));

                    // Rename the file to the user's ID (e.g., "114514.png")
                    String newFileName = currentUser.getIDKeys() + fileExtension;

                    // Save the file in the project folder
                    File destination = new File("src/main/resources/Project/UserProfilePictures/" + newFileName);
                    Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Save the profile picture path in the user profile
                    String profilePicPath = destination.getAbsolutePath();
                    currentUser.setProfilePicturePath("src/main/resources/Project/UserProfilePictures/" + newFileName);

                    System.out.println("Profile picture path saved: " + profilePicPath);  // Debugging line
                    currentUser.updateUser();
                    // Update profile picture view
                    Image newProfileImage = new Image(destination.toURI().toString());
                    profilePictureView.setImage(newProfileImage);
                    MainView.updateUserProfile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            currentUser.setUsername(nameField.getText());
            currentUser.setEmail(emailField.getText());
            currentUser.setPhoneNumber(phoneField.getText());
            String password = passwordField.getText();
            if (!password.isEmpty()) {
                currentUser.setPassword(password);  // Update the password only if it's not blank
            }
            currentUser.updateUser();  // Save the changes to JSON
            dialog.close();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(phoneLabel, 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(profilePicLabel, 0, 4);
        grid.add(profilePictureView, 1, 4);
        grid.add(selectedFileLabel, 1, 5);
        grid.add(uploadButton, 1, 6);
        grid.add(saveButton, 1, 7);

        Scene scene = new Scene(grid, 400, 400);
        dialog.setScene(scene);
        dialog.show();
    }

    private static String copyFileToProjectFolder(File selectedFile, int userID) throws IOException {
        // Define the destination folder within the project
        String destinationDirectory = "src/main/resources/Project/UserProfilePictures/";
        File destinationFolder = new File(destinationDirectory);

        // Ensure the destination folder exists
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Generate a new file name based on the user's ID
        String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
        String newFileName = userID + fileExtension;

        // Create the destination file
        File destinationFile = new File(destinationFolder, newFileName);

        // Copy the selected file to the project folder
        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return destinationFile.getAbsolutePath();  // Return the destination file path
    }
}