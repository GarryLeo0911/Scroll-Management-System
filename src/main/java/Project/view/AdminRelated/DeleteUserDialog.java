package Project.view.AdminRelated;

import Project.system.UserManagement.ManageUser;
import Project.system.UserManagement.SignIn;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteUserDialog {

    public static void show() {
        Stage dialog = new Stage();
        dialog.setTitle("Delete User");

        GridPane grid = new GridPane();
        Label idKeyLabel = new Label("Enter ID Key to Delete:");
        TextField idKeyField = new TextField();

        Button deleteButton = new Button("Delete User");
        deleteButton.setOnAction(e -> {
            String idKey = idKeyField.getText();
            SignIn.setID_key(idKey);

            if (SignIn.checkID_key()) {
                // Logic to delete the user with the specified ID Key
                ManageUser.setID_key(Integer.parseInt(idKey));
                ManageUser.deleteUser();
                dialog.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID key does not exist", ButtonType.OK);
                alert.showAndWait();
            }
        });

        grid.add(idKeyLabel, 0, 0);
        grid.add(idKeyField, 1, 0);
        grid.add(deleteButton, 1, 1);

        dialog.setScene(new Scene(grid, 300, 150));
        dialog.show();
    }
}

