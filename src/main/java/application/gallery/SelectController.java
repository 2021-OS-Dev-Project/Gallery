package application.gallery;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectController implements Initializable  {
    @FXML
    private Label exhibitionName;
    @FXML
    private Button barButton;
    @FXML
    private Label subscribe;
    @FXML
    private JFXDrawer bar;
    @FXML
    private Label museumName;
    @FXML
    private Label message;
    @FXML
    private ImageView artImage;
    final String LongSrc="C:/Users/ASUS/IdeaProjects/Gallery/src/main/java/application/gallery/img/";
    int checkExhibition=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (barButton != null)
            barButton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (bar.isOpened()) {
                    bar.close();
                } else {
                    try {
                        VBox box;
                        if(Object.user==null)
                            box = FXMLLoader.load(getClass().getResource("NavigationBar.fxml"));
                        else
                            box = FXMLLoader.load(getClass().getResource("NavigationBarP.fxml"));
                        bar.setSidePane(box);
                        bar.open();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            for (ArtInfo tmp : Object.art) {
                if (tmp.getArtNum() == SearchController.countI) {
                    try {
                        FileInputStream fis = new FileInputStream(LongSrc + tmp.getSrc());
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        Image image = new Image(bis);
                        artImage.setImage(image);
                        try {
                            bis.close();
                            fis.close();
                        } catch (IOException var6) {
                            var6.printStackTrace();
                        }
                    } catch (FileNotFoundException var7) {
                        var7.printStackTrace();
                    }

                    for (ExhibitionInfo exhibition : Object.exhibition)
                        if (exhibition.getNum() == tmp.getExhibitionNum()) {
                            exhibitionName.setText("전시 미술관 : " + exhibition.getExhibitionName());
                            checkExhibition=tmp.getExhibitionNum();
                        }
                    museumName.setText("전시회명 : " + tmp.getArtName());
                    message.setText(String.valueOf(tmp.PrintArt()));
                }
            }
        }

        public void SubscribeHandler(Event e){
            if(Object.user==null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("회원가입시 사용 가능합니다.!");

                alert.showAndWait();
            }else{
                for(Integer i:Object.user.subscription){
                    if(i==checkExhibition){
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText("Look, a Warning Dialog");
                            alert.setContentText("현재 구독중입니다.");
                            alert.showAndWait();
                            return;
                        }
                }
                DBLoader.SubscribeUser(Object.user.getUserNum(),checkExhibition);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Look, a Warning Dialog");
                alert.setContentText("성공");

                alert.showAndWait();

            }

        }
    }

