package application.gallery;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;



public class webController implements Initializable {
    @FXML
    private Button barbutton;

    @FXML
    private Label MenuName;

    @FXML
    private TextField inputID;

    @FXML
    private Label setLo;

    @FXML
    private WebView map;

    @FXML
    private JFXDrawer bar;

    WebEngine webEngine;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (barbutton != null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {
                        VBox Box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        bar.setSidePane(Box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });

        if (map != null) {
            webEngine = map.getEngine();
            webEngine.getLoadWorker().stateProperty()
                    .addListener(new ChangeListener<State>() {
                        @Override
                        public void changed(ObservableValue ov, State oldState, State newState) {

                            if (newState == Worker.State.SUCCEEDED) {
                                setLo.setText(webEngine.getLocation());
                            }

                        }
                    });
            webEngine.load("http://localhost:8080/gallery.html");
        }
    }
}
