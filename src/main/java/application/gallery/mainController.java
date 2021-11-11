package application.gallery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class mainController {
    @FXML
    private Button LoginNsign, StartButton;

    public void Move2Login(ActionEvent event){
        try {
            Parent login =FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage =(Stage) LoginNsign.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Move2SignUp(ActionEvent event){
        try {
            Parent login =FXMLLoader.load(getClass().getResource("SignUpScene.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage =(Stage) StartButton.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}