package application.gallery;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SelectController implements Runnable {
    @FXML
    private Label exhibitionName;
    @FXML
    private Label museumName;
    @FXML
    private Label message;

    GalleryListController galleryListController;

    public SelectController(){
        galleryListController = new GalleryListController();
    }

    @Override
    public void run() {
        while (true) {
            if(galleryListController.getFlag()){
                System.out.println(galleryListController.getN1());
                System.out.println(galleryListController.getN2());
                System.out.println(galleryListController.getN3());
                //exhibitionName.setText(galleryListController.getN1());
                //museumName.setText(galleryListController.getN2());
                //message.setText(galleryListController.getN3());
            }
            galleryListController.setFlag(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

