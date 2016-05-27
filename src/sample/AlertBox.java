package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by tkittirudeekul on 5/14/16.
 */
public class AlertBox {

    public static void display(String message) {
        Stage window = new Stage();
        window.setTitle("Make Sure");
        window.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        Label label = new Label(message + " is required");
        Button button = new Button("ok");
        button.setOnAction(e -> {
            window.close();
        });
        layout.getChildren().addAll(label, button);
        layout.setSpacing(10);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
