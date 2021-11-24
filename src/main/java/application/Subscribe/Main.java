package application.Subscribe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // first window 생성
        Parent root = FXMLLoader.load(getClass().getResource("popup.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("first Window");
        stage.show();

        // Thread 돌리면서 엑셀 체크
        Thread thread = new Thread(new ThreadCheck());
        thread.setDaemon(true);  // 데몬 Thread로 설정(main Thread가 종료되면 자동으로 종료)
        thread.start();  // Thread 가동
    }
    public static void main(String[] args) {
        launch();
    }
}
