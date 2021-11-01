package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("login.fxml"));
            AnchorPane loginLayoutAnchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(loginLayoutAnchorPane);
            //scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}