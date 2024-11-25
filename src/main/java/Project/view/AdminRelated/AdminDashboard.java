package Project.view.AdminRelated;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminDashboard {

    public static void show() {
        Stage adminStage = new Stage();
        adminStage.setTitle("Admin Dashboard");

        GridPane grid = new GridPane();

        // View all users button
        Button viewUsersButton = new Button("View Users");
        viewUsersButton.setOnAction(e -> {
            ViewUsersDialog.show();
        });

        // Add new user button
        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(e -> {
            AddUserDialog.show();
        });

        // Delete user button
        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setOnAction(e -> {
            DeleteUserDialog.show();
        });

        // View stats button
        Button viewStatsButton = new Button("View Stats");
        viewStatsButton.setOnAction(e -> {
            ViewStatsDialog.show();
        });

        grid.add(viewUsersButton, 0, 0);
        grid.add(addUserButton, 0, 1);
        grid.add(deleteUserButton, 0, 2);
        grid.add(viewStatsButton, 0, 3);

        Scene scene = new Scene(grid, 300, 250);
        adminStage.setScene(scene);
        adminStage.show();
    }
}

