package application.gallery;

import com.jfoenix.controls.JFXDrawer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class WebController implements Initializable {
    @FXML
    private Button barbutton;

    @FXML
    private Label MenuName;

    @FXML
    private TextField inputID;

    @FXML
    private Label setLo;

    @FXML
    private WebView browser;

    @FXML
    private JFXDrawer bar;

    WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (barbutton != null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {						VBox Box;
                        if(Object.user==null)
                            Box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        else
                            Box = FXMLLoader.load(getClass().getResource("NavigationBarP.fxml"));
                        bar.setSidePane(Box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });

        if (browser != null) {
            webEngine = browser.getEngine();
            webEngine.getLoadWorker().stateProperty()
                    .addListener(new ChangeListener<Worker.State>() {
                        @Override
                        public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

                            if (newState == Worker.State.SUCCEEDED) {
                                setLo.setText(webEngine.getLocation());
                            }

                        }
                    });
            webEngine.load("http://localhost:8080/gallery.html");
        } else System.out.println("..!");

        inputID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    for(ProvinceInfo tmp:Object.province) {
                        if (inputID.getText().contains(tmp.getProvince())){
                            GalleryListController.selectExhibitionName=tmp.getProvince()+" ";
                            GalleryListController.MyLocationid=tmp.getId();
                            try {
                                Stage primaryStage = (Stage) inputID.getScene().getWindow();
                                Parent signup = (Parent) FXMLLoader.load(this.getClass().getResource("GalleryList.fxml"));
                                Scene scene = new Scene(signup);
                                primaryStage.setScene(scene);
                            }catch(IOException var5){
                                var5.printStackTrace();
                            }
                        }

                    }
                }
            }
        });
    }
}
