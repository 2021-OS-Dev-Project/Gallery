module application{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.jfoenix;
    requires TrayTester;
    requires org.apache.commons.collections4;

    opens application.gallery to javafx.fxml;
    exports application.gallery;


    opens application.map to javafx.fxml;
    exports application.map;

    opens application.Subscribe to javafx.fxml;
    exports application.Subscribe;
}