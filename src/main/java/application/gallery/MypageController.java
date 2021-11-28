package application.gallery;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class MypageController implements Initializable {
    @FXML
    private Button barbutton;
    @FXML
    private Label MenuName;

    @FXML
    private Label Explanation;

    @FXML
    private ScrollPane sList;

    @FXML
    private HBox subscribeList;

    @FXML
    private ScrollPane rList;

    @FXML
    private HBox reviewList;

    @FXML
    private JFXDrawer bar;

    HashMap<Integer,VBox> vBoxHashMap=new HashMap<Integer, VBox>();

    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(barbutton!=null)
            barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if(bar.isOpened()){
                    bar.close();
                }

                else{
                    try {
                        VBox Box = FXMLLoader.load(getClass().getResource("NavigationBarP.fxml"));
                        bar.setSidePane(Box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });
        DBLoader.LoadSubscribe();
        Explanation.setText(Object.user.getId()+"\n"+Object.user.getEmail());

        if(DBLoader.CheckSubscribe(Object.user.getUserNum())){
            System.out.println(111);
            for(int i=0;i<Object.user.subscription.size();i++) {
                for (ExhibitionInfo tmp : Object.exhibition)
                    if (tmp.getNum() == Object.user.subscription.get(i)) {
                        for (ArtInfo art : Object.art)
                            if (art.getExhibitionNum() == tmp.getNum()) {
                                System.out.println(art.getArtNum());
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(this.getClass().getResource("EachSubscribe.fxml"));
                                try {
                                    vBoxHashMap.put(art.getArtNum(), fxmlLoader.load());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                EachGalleryController eachGalleryController = (EachGalleryController) fxmlLoader.getController();
                                eachGalleryController.setData(art);
                                this.subscribeList.getChildren().add(vBoxHashMap.get(art.getArtNum()));
                            }
                    }
            }
        }
        if (this.subscribeList != null) {
                for (Map.Entry<Integer,VBox> entry : vBoxHashMap.entrySet()){
                    int finalI = 0;
                    entry.getValue().setOnMouseClicked((e) -> {
                        SearchController.countI = entry.getKey();
                        System.out.println(finalI);
                        try {
                            Parent select = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Select.fxml")));
                            Scene scene = new Scene(select);
                            Stage stage = (Stage) entry.getValue().getScene().getWindow();
                            stage.setScene(scene);
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    });
                }
            }
        }

}
