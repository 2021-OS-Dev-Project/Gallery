module application {
    requires javafx.controls;
    requires javafx.fxml;

    opens application.gallery to javafx.fxml;
    exports application.gallery;

    opens application.Subscribe to javafx.fxml;
    exports application.Subscribe;
}