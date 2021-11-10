package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SignUpController implements Initializable{
    @FXML
    private Label LoginLabel;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPW;
    @FXML
    private PasswordField confirmPW;

    
    public void confirmPWOnAction(Event event) {
        if (!this.inputPW.getText().equals(this.confirmPW.getText())) {
            this.LoginLabel.setText("Incorrect! Please check your password.");
        } else {
            this.LoginLabel.setText("");
        }

    }

    public void makeUser() {
        System.out.println("아이디 및 비번 불일치 확인");
    }
    
    public void signUpButtonOnAction(ActionEvent event) {
    	
    	if (!this.inputName.getText().isBlank() && !this.inputEmail.getText().isBlank()) {
        	
        	String name=inputName.getText();
        	String pw=inputPW.getText();
        	String confirm=confirmPW.getText();
        
	        if (!this.inputPW.getText().equals(this.confirmPW.getText())) {
	               this.LoginLabel.setText("Incorrect! Please check your password.");
	               inputPW.setText("");
	        	   confirmPW.setText("");
	        } else {
	        	   this.LoginLabel.setText("");
	        	   if(DBLoader.CheckUser(name))
	        	   {
	        		   this.LoginLabel.setText("The same name exists. Please log in.");
	        		   inputName.setText("");
	        	   }else {
	        		   
	        		   if(inputEmail.getText().contains("@")){
	        			   DBLoader.SignUpuser(name, inputEmail.getText(), pw);	        	   
	        		   }else {
	        			   this.LoginLabel.setText("The email address is incorrect.");
	        			   inputEmail.setText("");
	        		   }
	        	   }
	        	}
        	}else 
        		this.LoginLabel.setText("Please enter username, and Email");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		inputEmail.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				int count=inputEmail.getText().length();
				if(count>40) {
					LoginLabel.setText("Enter No more than 40 characters");
					inputEmail.setText("");
				}
			}
		});
		
		inputName.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				int count=inputName.getText().length();
				if(count>10) {
					LoginLabel.setText("Enter No more than 10 characters");
					inputName.setText("");
				}
			}
		});
		
		inputPW.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				int count=inputPW.getText().length();
				if(count>15) {
					LoginLabel.setText("Enter No more than 15 characters");
					inputPW.setText("");
				}
			}
		});
		
		confirmPW.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				int count=confirmPW.getText().length();
				if(count>15) {
					LoginLabel.setText("Enter No more than 15 characters");
					confirmPW.setText("");
				}
			}
		});
		
		
		
		
	}
}
