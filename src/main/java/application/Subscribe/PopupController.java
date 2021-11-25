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
    static private boolean MuseumOnOff[];

    public PopupController(){
        MuseumOnOff = new boolean[3];
    }

    public boolean[] getWhichMuseum(){
        return MuseumOnOff;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        museum1.setOnAction((ActionEvent) -> {
            if(museum1.isSelected()) {
                MuseumOnOff[0] = true;
                System.out.println("국립현대미술관 선택되었습니다.");
            }
            else {
                MuseumOnOff[0] = false;
                System.out.println("국립현대미술관 선택 해제되었습니다.");
            }
        });

        museum2.setOnAction((ActionEvent) -> {
            if(museum2.isSelected()) {
                MuseumOnOff[1] = true;
                System.out.println("서울시립미술관 선택되었습니다.");
            }
            else {
                MuseumOnOff[1] = false;
                System.out.println("서울시립미술관 선택 해제되었습니다.");
            }
        });

        museum3.setOnAction((ActionEvent) -> {
            if(museum3.isSelected()) {
                MuseumOnOff[2] = true;
                System.out.println("경남도립미술관 선택되었습니다.");
            }
            else {
                MuseumOnOff[2] = false;
                System.out.println("경남도립미술관 선택 해제되었습니다.");
            }
        });
    }
}
