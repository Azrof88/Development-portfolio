module com.example.sqlite_add {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sqlite_add to javafx.fxml;
    exports com.example.sqlite_add;
}