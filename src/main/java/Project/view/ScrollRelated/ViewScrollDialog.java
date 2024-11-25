package Project.view.ScrollRelated;

import Project.system.Scroll.Scroll.Scroll;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewScrollDialog {
    public static void show(Scroll scroll) {
        Stage dialog = new Stage();
        dialog.setTitle("View Scroll: " + scroll.getScroll_Name());
        String content = scroll.getContent();

        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);

        // Set the size of the TextArea
        textArea.setPrefSize(400, 400);

        dialog.setScene(new Scene(textArea));
        dialog.show();
    }
}
