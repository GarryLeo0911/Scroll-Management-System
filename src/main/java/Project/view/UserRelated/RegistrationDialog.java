package Project.view.UserRelated;

import Project.system.UserManagement.SignUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("Registration");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the grid in the window
        grid.setHgap(10);  // Set horizontal gap between columns
        grid.setVgap(10);  // Set vertical gap between rows
        grid.setPadding(new Insets(20, 20, 20, 20));  // Add padding around the grid

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();
        Label idKeyLabel = new Label("Custom ID Key:");
        TextField idKeyField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            // Simulate registration process
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String idKey = idKeyField.getText();
            String password = passwordField.getText();

            SignUp.setFullName(name);
            SignUp.setEmail(email);
            SignUp.setPhone(phone);
            SignUp.setID_key(idKey);
            SignUp.setPassword(password);

            if (!SignUp.checkID_key()) {
                SignUp.signUpUser();
                dialog.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID Key already exists.");
                alert.show();
            }
        });

        // Adding components to the grid (col, row)
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(phoneLabel, 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(idKeyLabel, 0, 3);
        grid.add(idKeyField, 1, 3);
        grid.add(passwordLabel, 0, 4);
        grid.add(passwordField, 1, 4);
        grid.add(registerButton, 1, 5);

        Scene scene = new Scene(grid, 400, 300);  // Increase the size for better spacing
        dialog.setScene(scene);
        dialog.show();
    }
}
