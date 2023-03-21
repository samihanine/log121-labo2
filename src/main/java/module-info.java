module labo2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens labo2.view to javafx.fxml;
    opens labo2.controller to javafx.fxml;

    exports labo2.controller;
    exports labo2;
}
