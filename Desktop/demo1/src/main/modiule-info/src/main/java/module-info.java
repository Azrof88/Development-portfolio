module com.example.modiuleinfo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modiuleinfo to javafx.fxml;
    exports com.example.modiuleinfo;
}