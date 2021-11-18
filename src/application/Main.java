package application;
	
import java.net.URL;

import javax.swing.text.Document;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Main extends Application {
	public void initialScene() {
		Font.loadFont(this.getClass().getResourceAsStream("MapoFlowerIsland.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("MapoGoldenPier.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("Roboto-Medium.ttf"), 10.0D);
        Font.loadFont(this.getClass().getResourceAsStream("MapoPeacefull.ttf"), 10.0D);	
	}
	@Override
	public void start(Stage primaryStage) {
		try {

			DBLoader db=new DBLoader();
			Thread thread = new Thread(db);
	        thread.setDaemon(true);  // 데몬 Thread로 설정(main Thread가 종료되면 자동으로 종료)
	        thread.start();
			
			SearchController sc=new SearchController();
			
			sc.Sorting();
			sc.SortPrint();
			
			initialScene(); 
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
		    Scene scene = new Scene((Parent)fxmlLoader.load(), 1100.0D, 700.0D);
		    primaryStage.setScene(scene);
		    primaryStage.setResizable(false);
		    primaryStage.setTitle("한눈의 갤러리");
		    primaryStage.show();
		} catch(Exception e) {		
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
