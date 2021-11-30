package application.Subscribe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

public class PopupController implements Initializable {
    @FXML
    private CheckBox museum1;
    @FXML
    private CheckBox museum2;
    @FXML
    private CheckBox museum3;
    @FXML
    private CheckBox museum4;
    @FXML
    private CheckBox museum5;
    @FXML
    private CheckBox museum6;
    @FXML
    private CheckBox museum7;
    @FXML
    static private boolean MuseumOnOff[];

    public PopupController(){
        MuseumOnOff = new boolean[7];
    }

    public boolean[] getWhichMuseum(){
        return MuseumOnOff;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        museum1.setOnAction((ActionEvent) -> {
            if(museum1.isSelected()) {
                MuseumOnOff[0] = true;
            }
            else {
                MuseumOnOff[0] = false;
            }
        });

        museum2.setOnAction((ActionEvent) -> {
            if(museum2.isSelected()) {
                MuseumOnOff[1] = true;
            }
            else {
                MuseumOnOff[1] = false;
            }
        });

        museum3.setOnAction((ActionEvent) -> {
            if(museum3.isSelected()) {
                MuseumOnOff[2] = true;
            }
            else {
                MuseumOnOff[2] = false;
            }
        });

        museum4.setOnAction((ActionEvent) -> {
            if(museum4.isSelected()) {
                MuseumOnOff[3] = true;
            }
            else {
                MuseumOnOff[3] = false;
            }
        });

        museum5.setOnAction((ActionEvent) -> {
            if(museum5.isSelected()) {
                MuseumOnOff[4] = true;
            }
            else {
                MuseumOnOff[4] = false;
            }
        });

        museum6.setOnAction((ActionEvent) -> {
            if(museum6.isSelected()) {
                MuseumOnOff[5] = true;
            }
            else {
                MuseumOnOff[5] = false;
            }
        });

        museum7.setOnAction((ActionEvent) -> {
            if(museum7.isSelected()) {
                MuseumOnOff[6] = true;
            }
            else {
                MuseumOnOff[6] = false;
            }
        });
    }
}
