module application{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires TrayTester;
    requires commons.collections4;
    requires com.jfoenix;

    opens application.gallery to javafx.fxml;
    exports application.gallery;


    opens application.map to javafx.fxml;
    exports application.map;

    opens application.Subscribe to javafx.fxml;
    exports application.Subscribe;
}