package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GalleryListController implements Initializable{
	@FXML
    private HBox galleryList;
    @FXML
    private Label MenuName;
    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// TODO Auto-generated method stub
		
		try {
			
			for(ArtInfo tmp:Object.art) {
				 FXMLLoader fxmlLoader = new FXMLLoader();
	             fxmlLoader.setLocation(this.getClass().getResource("EachGallery.fxml"));
	             VBox vBox = (VBox)fxmlLoader.load();
	             EachGalleryController eachGalleryController = (EachGalleryController)fxmlLoader.getController();
	             eachGalleryController.setData(tmp);
	             this.galleryList.getChildren().add(vBox);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
