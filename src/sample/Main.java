package sample;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    private Button change;
    private Button add;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        VBox hb = new VBox();
        hb.setAlignment(Pos.TOP_CENTER);
        HBox layout1 = new HBox();
        HBox layout2 = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(20);
        layout1.setMinWidth(250);
        layout1.setStyle("-fx-background-color: #336699;");
        layout2.setAlignment(Pos.CENTER);
        layout2.setSpacing(55);
        layout2.setMinWidth(250);
        layout2.setStyle("-fx-background-color: #996699;");

        Label productIdLabel = new Label("Product ID:");
        TextField textField = new TextField ();
        textField.setPromptText("Product ID (Number ONLY)");
        Label productPrice = new Label("Price");
        TextField textField2 = new TextField();
        textField2.setPromptText("Product New Price (Number ONLY)");
        layout1.getChildren().addAll(productIdLabel, textField);
        layout2.getChildren().addAll(productPrice, textField2);
        change = new Button("Change Price");
        change.setOnAction(e -> {
            WebDriver driver = null;
            try {
                driver = RegularActions.Execute();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (textField.getText().isEmpty() && textField2.getText().isEmpty()) {
                AlertBox.display("Price and Product ID");
            } else if (textField.getText().isEmpty()) {
                AlertBox.display("Product ID");
            } else if (textField2.getText().isEmpty()){
                AlertBox.display("Price");
            } else {
                String productid = textField.getText();
                String price = textField2.getText();
                RegularActions act = new RegularActions();
                try {
                    act.productPriceModification(driver, Constant.SECOND_URL + productid, price, "");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        HBox layout3 = new HBox();
        HBox layout4 = new HBox();
        layout3.setAlignment(Pos.CENTER);
        layout3.setSpacing(20);
        layout3.setMinWidth(250);
        layout3.setStyle("-fx-background-color: #336699;");
        layout4.setAlignment(Pos.CENTER);
        layout4.setSpacing(55);
        layout4.setMinWidth(250);
        layout4.setStyle("-fx-background-color: #996699;");

        Label productid2 = new Label("Product ID:");
        TextField prodtext = new TextField ();
        textField.setPromptText("Product ID");
        Label sheetName = new Label("Sheet Name");
        TextField sheetText = new TextField();
        textField2.setPromptText("Sheet Name");
        layout3.getChildren().addAll(productid2, prodtext);
        layout4.getChildren().addAll(sheetName, sheetText);
        add = new Button("Add Product");
        add.setOnAction(e -> {
            WebDriver driver = null;
            try {
                driver = RegularActions.Execute();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (prodtext.getText().isEmpty() && sheetText.getText().isEmpty()) {
                AlertBox.display("Price and Product ID");
            } else if (prodtext.getText().isEmpty()) {
                AlertBox.display("Product ID");
            } else if (sheetText.getText().isEmpty()){
                AlertBox.display("Price");
            } else {
                String productid = prodtext.getText();
                String sheet = sheetText.getText();
                RegularActions act = new RegularActions();
                try {
                    act.productVariation(driver, Constant.SECOND_URL + productid, sheet);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });



        hb.getChildren().addAll(layout1, layout2, change, layout3, layout4, add);
        Scene scene = new Scene(hb, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
