package Project.view.ScrollRelated;

import Project.system.Scroll.Scroll.Scroll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class ResultsDialog {

    public static void show(List<Scroll> scrolls) {
        Stage dialog = new Stage();
        dialog.setTitle("Search Results");

        VBox vbox = new VBox(10);
        vbox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        if (scrolls.isEmpty()) {
            Label noResults = new Label("No scrolls found matching the given criteria.");
            vbox.getChildren().add(noResults);
        } else {
            TableView<Scroll> tableView = new TableView<>();

            // Update to match property names from the interface
            TableColumn<Scroll, Integer> idColumn = new TableColumn<>("Scroll ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("scroll_ID"));  // Matches getScroll_ID()

            TableColumn<Scroll, String> nameColumn = new TableColumn<>("Scroll Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("scroll_Name"));  // Matches getScroll_Name()

            TableColumn<Scroll, Integer> uploaderColumn = new TableColumn<>("Uploader ID");
            uploaderColumn.setCellValueFactory(new PropertyValueFactory<>("uploader_ID"));  // Matches getUploader_ID()

            TableColumn<Scroll, String> dateColumn = new TableColumn<>("Upload Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("upload_Date"));  // Matches getUpload_Date()

            tableView.getColumns().addAll(idColumn, nameColumn, uploaderColumn, dateColumn);

            ObservableList<Scroll> data = FXCollections.observableArrayList(scrolls);
            tableView.setItems(data);

            tableView.setRowFactory(tv -> {
                TableRow<Scroll> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        Scroll rowData = row.getItem();
                        PreviewScrollDialog.show(rowData);  // Open preview dialog
                    }
                });
                return row;
            });

            vbox.getChildren().add(tableView);
        }

        Scene scene = new Scene(vbox, 600, 400);
        dialog.setScene(scene);
        dialog.show();
    }
}
