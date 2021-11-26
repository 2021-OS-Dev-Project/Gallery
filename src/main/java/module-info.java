module application{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens application.gallery to javafx.fxml;
    exports application.gallery;


    opens application.map to javafx.fxml;
    exports application.map;
}