package application.gallery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    private Label LoginLabel;
    @FXML
    private TextField inputID;
    @FXML
    private PasswordField inputPW;
    @FXML
    private Button LogInButton;

    public void loginButtonOnAction(ActionEvent event){
        if(!inputID.getText().isBlank() && !inputPW.getText().isBlank()){
            LoginLabel.setText("");
            validateLogin();
        }else{
            LoginLabel.setText("Please enter username and password.");
        }
    }

    public void validateLogin(){
        System.out.println("아이디 및 비번 불일치 확인");
        // 만약 불일치의 경우,
        // LoginLabel.setText("Worng password!");
    }

    public void Move2SignUp(ActionEvent event){
        try {
            Parent login = FXMLLoader.load(getClass().getResource("SignUpScene.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage =(Stage) LogInButton.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
