package application.gallery;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationController {
    @FXML
    private VBox st;

    @FXML
    private JFXButton Home;

    @FXML
    private JFXButton Login;

    @FXML
    private JFXButton Exhibition;

    @FXML
    private JFXButton Board;

    public void Move2Home(ActionEvent event){
        System.out.println(Home.getId());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeDefault.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) Home.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Move2Login(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) Login.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Move2Exhibition(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GalleryList.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) Exhibition.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Move2Board(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage =(Stage) Board.getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
