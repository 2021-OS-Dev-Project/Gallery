package application.gallery;

import application.model.Exhibitions;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SelectController implements Initializable {
    @FXML
    private Label LocationSet;

    @FXML
    private HBox galleryList;

    @FXML
    private Label MenuName;

    @FXML
    private JFXDrawer bar;

    @FXML
    private Button barbutton;

    @FXML
    private VBox content;

    @FXML
    private Hyperlink museumLink;
    static String site;

    private String location;
    List<Exhibitions> exhibitionsList;
    private JFXButton Click;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (barbutton != null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {
                        VBox box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        bar.setSidePane(box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EachReview.fxml"));
            VBox vBox = fxmlLoader.load();

            if(galleryList != null)
                galleryList.getChildren().add(vBox);

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(GalleryListController.countI);
        if(GalleryListController.countI==0){
            site="https://sema.seoul.go.kr/";
        }
        else if(GalleryListController.countI == 1){
            site="https://www.mmca.go.kr/main.do";
        }
        museumLink.setText(String.valueOf(site));
        museumLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent view = null;
                try {
                    view = FXMLLoader.load(getClass().getResource("WebView.fxml"));
                    Scene scene = new Scene(view, 1100, 700);
                    Stage primaryStage =(Stage) museumLink.getScene().getWindow();
                    primaryStage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
