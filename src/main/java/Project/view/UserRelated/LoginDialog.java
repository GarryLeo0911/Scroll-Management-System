package Project.view.UserRelated;

import Project.system.User.User.UserTemplate;
import Project.system.UserManagement.SignIn;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import Project.MainView;


public class LoginDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("Login");

        GridPane grid = new GridPane();
        Label usernameLabel = new Label("ID Key:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Validate login here
            String IDKey = usernameField.getText();
            String password = passwordField.getText();

            SignIn.setID_key(IDKey);
            SignIn.setPassword(password);

            if (SignIn.checkPassword()) {
                // Successful login
                UserTemplate user = SignIn.signInUser();
                MainView.updateUser(user);
                MainView.updateUserStatus(user.getUsername(), String.valueOf(user.getUserType()));  // Update user status in main window
                dialog.close();
            } else {
                // Show error message
                Alert alert = new Alert(AlertType.ERROR, "Invalid login credentials.");
                alert.show();
            }
        });

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        dialog.setScene(new Scene(grid, 300, 200));
        dialog.show();
    }
}

