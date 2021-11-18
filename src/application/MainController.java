package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController implements Initializable{
    @FXML
    private Button LoginNsign;
    @FXML
    private Button StartButton;
    private StackPane Login;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		StartButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> {
			try {
            Parent signup = (Parent)FXMLLoader.load(this.getClass().getResource("GalleryList.fxml"));
            Scene scene = new Scene(signup);
            Stage primaryStage = (Stage)this.StartButton.getScene().getWindow();
            primaryStage.setScene(scene);
			} catch (IOException var5) {
				var5.printStackTrace();
			}
		});
		
		LoginNsign.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			try {
	            Parent login = (Parent)FXMLLoader.load(this.getClass().getResource("LoginScene.fxml"));
	            Scene scene = new Scene(login);
	            Stage primaryStage = (Stage)this.LoginNsign.getScene().getWindow();
	            primaryStage.setScene(scene);
	        } catch (IOException var5) {
	            var5.printStackTrace();
	        }
		});
	}
	
}
