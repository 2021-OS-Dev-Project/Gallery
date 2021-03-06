package application.gallery;

import javafx.fxml.FXML;
import java.io.IOException;
import application.model.Exhibitions;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.input.MouseEvent;

public class EachGalleryController {
    @FXML
    private ImageView GalleryImg;

    @FXML
    private Label GalleryName;

    @FXML
    private Label Explanation;


    public void setData(Exhibitions exhibitions){
        try {
            FileInputStream fis=new FileInputStream(exhibitions.getCover());
            BufferedInputStream bis=new BufferedInputStream(fis); //스트림준비
            Image image =new Image(bis);
            if(GalleryImg != null)
                GalleryImg.setImage(image);
            try {
                bis.close();
                fis.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        if(GalleryName != null)
            GalleryName.setText(exhibitions.getName());
        Explanation.setText(exhibitions.getExplanation());
    }
}
