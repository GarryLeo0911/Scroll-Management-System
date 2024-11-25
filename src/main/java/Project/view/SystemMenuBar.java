


package Project.view;

import Project.MainView;
import Project.system.User.User.Admin;
import Project.system.User.User.Guest;
import Project.system.User.User.UserTemplate;
import Project.view.AdminRelated.AddUserDialog;
import Project.view.AdminRelated.DeleteUserDialog;
import Project.view.AdminRelated.ViewStatsDialog;
import Project.view.AdminRelated.ViewUsersDialog;
import Project.view.ScrollRelated.AddScrollDialog;
import Project.view.ScrollRelated.EditScrollDialog;
import Project.view.ScrollRelated.SearchDialog;
import Project.view.UserRelated.LoginDialog;
import Project.view.UserRelated.RegistrationDialog;
import Project.view.UserRelated.UpdateProfileDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;

public class SystemMenuBar {

    private static MenuBar menuBar = new MenuBar();

    public static MenuBar getMenuBar() {
        refreshMenuItems();  // Refresh the menu items based on the current user
        return menuBar;
    }

    // Method to refresh the MenuBar based on the current user's role
    public static void refreshMenuItems() {
        menuBar.getMenus().clear();  // Clear existing menus

        // User Management Menu
        Menu userManagement = new Menu("User Management");

        // Check the current user
        UserTemplate currentUser = MainView.getCurrentUser();

        if (currentUser instanceof Guest) {
            // Show login and registration options for guests
            MenuItem login = new MenuItem("Login");
            login.setOnAction(e -> {
                LoginDialog.show();
            });

            MenuItem registration = new MenuItem("Registration");
            registration.setOnAction(e -> {
                RegistrationDialog.show();
            });

            userManagement.getItems().addAll(login, registration);
        } else {
            // Show update profile and logout options for logged-in users
            MenuItem updateProfile = new MenuItem("Update Profile");
            updateProfile.setOnAction(e -> {
                UpdateProfileDialog.show();
            });

            MenuItem logout = new MenuItem("Logout");
            logout.setOnAction(e -> {
                MainView.updateUser(new Guest());  // Set the user back to Guest
                showAlert("Logout", "You have successfully logged out.");
                refreshMenuItems();  // Refresh menu items after logout
            });

            userManagement.getItems().addAll(updateProfile, logout);
        }

        // Scroll Management Menu
        Menu scrollMenu = new Menu("Scroll");

        // Add Scroll
        MenuItem addScroll = new MenuItem("Add Scroll");
        addScroll.setOnAction(e -> {
            if (currentUser instanceof Guest) {
                showAlert("Access Denied", "Guests cannot add scrolls.");
            } else {
                AddScrollDialog.show();
            }
        });

        // Edit Scroll (user can only edit their own scrolls)
        MenuItem editScroll = new MenuItem("Edit My Scrolls");
        editScroll.setOnAction(e -> {
            if (currentUser instanceof Guest) {
                showAlert("Access Denied", "Guests cannot edit scrolls.");
            } else {
                // Show edit dialog for user's own scrolls (this would be connected to the user's scrolls list)
                EditScrollDialog.show(null);  // Placeholder for user's scroll
            }
        });

        // View Scrolls (opens search dialog to find and preview scrolls)
        MenuItem viewScrolls = new MenuItem("View and Search Scrolls");
        viewScrolls.setOnAction(e -> {
            SearchDialog.show();  // Show search dialog to view and search scrolls
        });

        scrollMenu.getItems().addAll(addScroll, editScroll, viewScrolls);
        // In the user management menu
        MenuItem updateProfile = new MenuItem("Update Profile");
        updateProfile.setOnAction(e -> {
            UpdateProfileDialog.show();  // Opens the profile dialog with picture update
        });

        // Admin Menu (only visible to Admin users)
        if (currentUser instanceof Admin) {
            Menu adminMenu = new Menu("Admin");

            MenuItem AddUser = new MenuItem("Add User");
            AddUser.setOnAction(e -> {
                AddUserDialog.show();
            });

            MenuItem DeleteUser = new MenuItem("Delete User");
            DeleteUser.setOnAction(e -> {
                DeleteUserDialog.show();
            });

            MenuItem ViewUser = new MenuItem("View User");
            ViewUser.setOnAction(e -> {
                ViewUsersDialog.show();
            });

            MenuItem ViewStats = new MenuItem("View Stats");
            ViewStats.setOnAction(e -> {
                ViewStatsDialog.show();
            });

            adminMenu.getItems().addAll(AddUser, DeleteUser, ViewUser, ViewStats);

            // Add additional admin options here (view users, add users, etc.)
            menuBar.getMenus().add(adminMenu);
        }

        // Add the User Management and Scroll Management menus
        menuBar.getMenus().addAll(userManagement, scrollMenu);
    }

    // Helper method to show a basic alert (for demo purposes)
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

