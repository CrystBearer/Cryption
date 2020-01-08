module org.vin {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.vin to javafx.fxml;
    exports org.vin;
}