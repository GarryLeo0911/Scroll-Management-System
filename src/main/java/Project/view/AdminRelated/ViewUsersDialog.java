package Project.view.AdminRelated;

import Project.system.User.User.UserTemplate;
import Project.system.UserManagement.ListUser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.List;

public class ViewUsersDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("All Users");

        GridPane grid = new GridPane();

        // Retrieve the list of users from the backend or database
        List<UserTemplate> users = ListUser.getUsers();

        int row = 0;
        for (UserTemplate user : users) {
            Label userInfo = new Label("ID Key: " + String.valueOf(user.getIDKeys()) + ", User name: " + user.getUsername() + ", User Email: " + user.getEmail() + ", User Phone: " + user.getPhoneNumber());  // Adjust to display user details as needed
            grid.add(userInfo, 0, row);
            row++;
        }

        dialog.setScene(new Scene(grid, 400, 400));
        dialog.show();
    }
}

