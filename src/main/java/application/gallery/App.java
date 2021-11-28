package application.gallery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public void initialScene() {
        Font.loadFont(this.getClass().getResourceAsStream("MapoFlowerIsland.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("MapoGoldenPier.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("Roboto-Medium.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("MapoPeacefull.ttf"), 10.0D);
    }
    @Override
   public void start(Stage primaryStage) throws IOException {
        try {
            DBLoader db=new DBLoader();
            Thread thread = new Thread(db);
            thread.setDaemon(true);  // ���� Thread�� ����(main Thread�� ����Ǹ� �ڵ����� ����)
            thread.start();
            initialScene();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene((Parent)fxmlLoader.load(), 1100.0D, 700.0D);
            primaryStage.getIcons().add(new Image("C:\\Users\\ASUS\\IdeaProjects\\Gallery\\src\\main\\resources\\application\\gallery\\image\\6.png"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("한눈의 전시회");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
