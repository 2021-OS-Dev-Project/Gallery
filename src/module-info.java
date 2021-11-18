module Exhibition_Map {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires org.jsoup;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml;
}
