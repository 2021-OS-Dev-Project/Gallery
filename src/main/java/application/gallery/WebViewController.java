package application.gallery;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewController implements Initializable {
    @FXML
    private WebView webview;
    @FXML
    private Button backbutton;

    WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (webview != null) {
            webEngine = webview.getEngine();
            webEngine.load(SelectController.site);
        }
        backbutton.setOnAction((ActionEvent) -> {
            Parent view = null;
            try {
                view = FXMLLoader.load(getClass().getResource("Select.fxml"));
                Scene scene = new Scene(view, 1100, 700);
                Stage primaryStage =(Stage) backbutton.getScene().getWindow();
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
