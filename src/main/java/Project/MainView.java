package Project;

import Project.system.User.User.UserTemplate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class MainView extends Application {

    // The current user (initialized as a Guest)
    private static UserTemplate currentUser = new Project.system.User.User.Guest();

    static Label userStatusLabel;  // Displays current user type (Guest, Admin, etc.)
    static ImageView profilePictureView;  // Displays user's profile picture
    static Label nameLabel;
    static Label emailLabel;
    static Label phoneLabel;  // User profile details labels

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Digital Scroll Management System");

        BorderPane root = new BorderPane();

        // Initialize the MenuBar and status bar
        root.setTop(Project.view.SystemMenuBar.getMenuBar());
        userStatusLabel = new Label(getUserStatusMessage());
        root.setBottom(userStatusLabel);
        // Initialize profile picture
        profilePictureView = new ImageView();
        profilePictureView.setFitWidth(100);  // Set appropriate size for the profile image
        profilePictureView.setFitHeight(100);

        // Initialize user profile labels
        nameLabel = new Label();
        emailLabel = new Label();
        phoneLabel = new Label();

        // Load the current user's profile picture and details
        updateUserProfile();

        // Create a VBox for the User Profile section
        VBox profileBox = new VBox(10);  // 10px spacing between elements
        profileBox.setPadding(new Insets(10));  // Add padding
        profileBox.getChildren().addAll(profilePictureView, nameLabel, emailLabel, phoneLabel);

        // Add the profileBox to the right side of the BorderPane (or choose a preferred position)
        root.setRight(profileBox);

        // Set the scene and display the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Function to update the user status label (e.g., when user logs in or out)
    public static void updateUserStatus(String username, String userType) {
        if (userStatusLabel != null) {
            userStatusLabel.setText("Signed in as " + username + " (" + userType + ")");
        }
    }

    // Function to update the current user and refresh UI elements
    public static void updateUser(UserTemplate user) {
        if (user != null) {
            updateUserStatus(user.getUsername(), String.valueOf(user.getUserType()));
        }

        currentUser = user;
        updateUserStatus(user.getUsername(), String.valueOf(user.getUserType()));

        // Update the profile picture and details according to the new user's profile
        updateUserProfile();

        // Update the MenuBar according to the new user's role
        Project.view.SystemMenuBar.refreshMenuItems();
    }

    // Function to get the current user
    public static UserTemplate getCurrentUser() {
        return currentUser;
    }

    // Helper function to get the user's status message (for the status bar)
    private static String getUserStatusMessage() {
        return "Signed in as " + currentUser.getUsername() + " (" + currentUser.getUserType() + ")";
    }

    // Function to load and display the profile picture and user details
    public static void updateUserProfile() {
        String profilePicPath = currentUser.getProfilePicturePath();
        if (profilePicPath != null && !profilePicPath.isEmpty()) {
            File profilePicFile = new File(profilePicPath);
            if (profilePicFile.exists()) {
                Image profileImage = new Image(profilePicFile.toURI().toString());
                profilePictureView.setImage(profileImage);  // Set the image in ImageView
            } else {
                System.out.println("Profile picture file not found: " + profilePicPath);
                profilePictureView.setImage(null);  // Clear image if file not found
            }
        } else {
            System.out.println("No profile picture path found for user: " + currentUser.getUsername());
            profilePictureView.setImage(null);  // Clear image if no path
        }

        // Update user details
        nameLabel.setText("Name: " + currentUser.getUsername());
        emailLabel.setText("Email: " + currentUser.getEmail());
        phoneLabel.setText("Phone: " + currentUser.getPhoneNumber());
    }

}