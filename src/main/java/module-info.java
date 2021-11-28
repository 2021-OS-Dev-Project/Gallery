module application.gallery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayTester;
    requires com.jfoenix;
    requires javafx.web;
    exports application.gallery;
    opens application.gallery to
            javafx.fxml;
}