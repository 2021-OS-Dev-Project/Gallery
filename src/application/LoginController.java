package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private Label LoginLabel;
    @FXML
    private TextField inputID;
    @FXML
    private PasswordField inputPW;
    @FXML
    private Button LogInButton;
    
    public void loginButtonOnAction(ActionEvent event) {
        if (!this.inputID.getText().isBlank() && !this.inputPW.getText().isBlank()) {
            this.LoginLabel.setText("");
            this.validateLogin();
        } else {
            this.LoginLabel.setText("Please enter username and password.");
        }

    }

    public void validateLogin() {
        
    	if(DBLoader.LoginUser(inputID.getText(), inputPW.getText())) {
    		System.out.println("로그인 성공"+Object.user.getId());
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Non-existent ID.");
    		alert.setHeaderText("Look, a Warning Dialog");
    		alert.setContentText("Please press the membership registration window to log in.");
    		alert.showAndWait();
    		inputID.setText("");
    		inputPW.setText("");
    	}
    }

    public void Move2SignUp(ActionEvent event) {
        try {
            Parent login = (Parent)FXMLLoader.load(this.getClass().getResource("SignUpScene.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage = (Stage)this.LogInButton.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
