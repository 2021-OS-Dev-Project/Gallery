package application.gallery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.lcdtext", "false");
        Font.loadFont(getClass().getResourceAsStream("MapoFlowerIsland.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("MapoGoldenPier.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("Roboto-Medium.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("MapoPeacefull.ttf"), 10);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MyPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 700);
        stage.setTitle("한눈의 갤러리");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}