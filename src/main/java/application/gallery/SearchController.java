package application.gallery;


import com.jfoenix.controls.JFXDrawer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchController implements Initializable {
	@FXML
	private Button barbutton;
	@FXML
	private Label MenuName;
	@FXML
	private ScrollPane List;
	@FXML
	private TextField searchText;
	@FXML
	private JFXDrawer bar;
	@FXML
	private VBox searchList;
	@FXML
	private ImageView click;

	HashMap<Integer,HBox> hBoxHashMap=new HashMap<Integer, HBox>();
	ArrayList<FXMLLoader> fxmlLoader = new ArrayList<FXMLLoader>();
	static int countI=0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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

		Collections.sort(Object.art, new ArtComparator());

		for (ArtInfo tmp : Object.art) {
			fxmlLoader.add(new FXMLLoader());
			fxmlLoader.get(fxmlLoader.size() - 1).setLocation(this.getClass().getResource("EachSearch.fxml"));
			try {
				hBoxHashMap.put(tmp.getArtNum(), fxmlLoader.get(fxmlLoader.size() - 1).load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			EachGalleryController eachGalleryController = fxmlLoader.get(fxmlLoader.size() - 1).getController();
			if (eachGalleryController != null) {
				eachGalleryController.setData(tmp);
				if (searchList != null)
					searchList.getChildren().add(hBoxHashMap.get(tmp.getArtNum()));
			}
		}
		if (searchList != null) {
			for (Map.Entry<Integer,HBox> entry : hBoxHashMap.entrySet()){
				entry.getValue().setOnMouseClicked((e) -> {
					SearchController.countI = entry.getKey();
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
	public void SelectHandle(Event event) {
		for (int i = 0; i < hBoxHashMap.size(); i++) {
			fxmlLoader.clear();
			hBoxHashMap.clear();
			searchList.getChildren().clear();
		}
		for (ArtInfo tmp : Object.art) {
			if (tmp.getArtName().contains(searchText.getText())) {
				fxmlLoader.add(new FXMLLoader());
				fxmlLoader.get(fxmlLoader.size() - 1).setLocation(this.getClass().getResource("EachSearch.fxml"));

				try {
					hBoxHashMap.put(tmp.getArtNum(), fxmlLoader.get(fxmlLoader.size() - 1).load());
				} catch (IOException e) {
					e.printStackTrace();
				}
				EachGalleryController eachGalleryController = fxmlLoader.get(fxmlLoader.size() - 1).getController();
				if (eachGalleryController != null) {
					eachGalleryController.setData(tmp);
					if (searchList != null)
						searchList.getChildren().add(hBoxHashMap.get(tmp.getArtNum()));
				}
			}
		}
		if (searchList != null) {
			for (Map.Entry<Integer, HBox> entry : hBoxHashMap.entrySet()) {
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

class ArtComparator implements Comparator<ArtInfo>{

	@Override
	public int compare(ArtInfo o1, ArtInfo o2) {
		String[] str1 = o1.getStartPeriod().split("-");
		String[] str2 = o2.getStartPeriod().split("-");

		if (Integer.parseInt(str1[0]) == Integer.parseInt(str2[0])) {
			if (Integer.parseInt(str1[1]) == Integer.parseInt(str2[1])) {
				return Integer.parseInt(str1[2]) - Integer.parseInt(str2[2]);
			}
			return Integer.parseInt(str1[1]) - Integer.parseInt(str2[1]);
		} else
			return Integer.parseInt(str1[0]) - Integer.parseInt(str2[0]);
	}
}