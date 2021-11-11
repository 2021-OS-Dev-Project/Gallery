package application.gallery;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class signUpController {
    @FXML
    private Label LoginLabel;
    @FXML
    private TextField inputName, inputEmail;
    @FXML
    private PasswordField inputPW, confirmPW;


    public void signUpButtonOnAction(ActionEvent event){
        if (!inputName.getText().isBlank() && !inputEmail.getText().isBlank()){
            LoginLabel.setText("");
        } else {
            LoginLabel.setText("Please enter username, and Email");
        }
    }

    public void confirmPWOnAction(Event event){
        if(!inputPW.getText().equals(confirmPW.getText())){
            LoginLabel.setText("Incorrect! Please check your password.");
        } else{
            LoginLabel.setText("");
        }
    }

    public void makeUser(){
        System.out.println("아이디 및 비번 불일치 확인");
        // 만약 불일치의 경우,
        // LoginLabel.setText("Worng password!");
    }
}
