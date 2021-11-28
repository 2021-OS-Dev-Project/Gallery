package application.gallery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private Label MenuName;

    @FXML
    private Button MenuHome;

    @FXML
    private Button MenuSign;

    @FXML
    private Button MenuExhibition;

    @FXML
    private Button MenuBoard;

    @FXML
    private Label SignUpLabel;

    @FXML
    private TextField inputID;

    @FXML
    private PasswordField inputPW;

    @FXML
    private Label LoginLabel;

    @FXML
    private Button LogInButton;

    @FXML
    private Button SignUpBotton;

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
            try {
                Parent root = FXMLLoader.load(getClass().getResource("HomeDefault.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage =(Stage) MenuHome.getScene().getWindow();
                primaryStage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
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


    public void Move2Home(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeDefault.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) MenuHome.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Move2Exhibition(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GalleryList.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) MenuExhibition.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Move2Board(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) MenuBoard.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
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
