module application.gallery {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens application.gallery to javafx.fxml;
    exports application.gallery;
}