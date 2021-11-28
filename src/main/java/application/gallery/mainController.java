package application.gallery;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
				Stage primaryStage = (Stage) this.StartButton.getScene().getWindow();
				Parent signup = (Parent) FXMLLoader.load(this.getClass().getResource("HomeDefault.fxml"));
				Scene scene = new Scene(signup);
				primaryStage.setScene(scene);
			}catch(IOException var5){
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
