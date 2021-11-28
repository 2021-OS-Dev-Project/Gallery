package application.gallery;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GalleryListController implements Initializable {
	@FXML
	private HBox galleryList;
	@FXML
	private Label MenuName;
	@FXML
	private Label LocationSet;
	@FXML
	private Button barbutton;
	@FXML
	private JFXDrawer bar;

	HashMap<Integer,VBox> vBoxHashMap=new HashMap<Integer,VBox>();
	ArrayList<VBox> vbox = new ArrayList<VBox>();

	static int MyLocationid=-1;
	static String selectExhibitionName="";

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// TODO Auto-generated method stub
		SearchController.countI = 0;
		if (barbutton != null)
			barbutton.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				if (bar.isOpened()) {
					bar.close();
				} else {
					try {
						VBox Box;
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

		if (MyLocationid != -1) {
			for (LocationInfo loc : Object.location) {
				if (loc.getProvince() == MyLocationid) {
					System.out.println(1);
					if (LocationSet != null)
						LocationSet.setText(selectExhibitionName);
					for (ExhibitionInfo tmp : Object.exhibition)
						if (tmp.getLocationNum() == loc.getLocNum()) {
							for (ArtInfo art : Object.art)
								if (tmp.getNum() == art.getExhibitionNum()) {
									System.out.println(3);
									FXMLLoader fxmlLoader = new FXMLLoader();
									fxmlLoader.setLocation(this.getClass().getResource("EachGallery.fxml"));
									try {
										vBoxHashMap.put(art.getArtNum(), fxmlLoader.load());
									} catch (IOException e) {
										e.printStackTrace();
									}
									EachGalleryController eachGalleryController = (EachGalleryController) fxmlLoader.getController();
									eachGalleryController.setData(art);
									this.galleryList.getChildren().add(vBoxHashMap.get(art.getArtNum()));
								}
						}

				}
			}
			if (galleryList != null) {
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
}


