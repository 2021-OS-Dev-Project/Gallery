package application.gallery;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EachGalleryController{
	@FXML
    private ImageView GalleryImg;
    @FXML
    private Label GalleryName;
    @FXML
    private Label Explanation;

    final String LongSrc="C:/Users/ASUS/IdeaProjects/Gallery/src/main/java/application/gallery/img/";

    public void setData(ArtInfo art) {

            try {
                FileInputStream fis = new FileInputStream(LongSrc + art.getSrc());
                BufferedInputStream bis = new BufferedInputStream(fis);
                Image image = new Image(bis);
                this.GalleryImg.setImage(image);
                try {
                    bis.close();
                    fis.close();
                } catch (IOException var6) {
                    var6.printStackTrace();
                }

            } catch (FileNotFoundException var7) {
                var7.printStackTrace();
            }


            this.GalleryName.setText(art.getArtName());
            this.Explanation.setText(art.PrintArt().toString());
    }
}
