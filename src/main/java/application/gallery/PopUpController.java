package application.gallery;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable {
    @FXML
    private Button StartButton;
    @FXML
    private VBox POP;

    private Popup popup;
．

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popup = new Popup();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PopUp.fxml"));

            POP = fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handlePopup(ActionEvent event){
        if(popup.isShowing()){
            popup.hide();
        }else {
            final Window window = StartButton.getScene().getWindow();
            popup.setWidth(100);
            popup.setHeight(300);
            final double x = window.getX() + StartButton.localToScene(0, 0).getX() + StartButton.getScene().getX() ;
            final double y = window.getY() + StartButton.localToScene(0, 0).getY() + StartButton.getScene().getY() + StartButton.getHeight();
            popup.getContent().clear();
            popup.getContent().addAll(POP);
            popup.show(window, 989.2, 644);
        }
    }
}

