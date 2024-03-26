module eat.neilson.cafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens eat.neilson.Cafe to javafx.fxml;
    exports eat.neilson.Cafe;
}