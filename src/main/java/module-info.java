module application.gallery {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.gallery to javafx.fxml;
    exports application.gallery;
}