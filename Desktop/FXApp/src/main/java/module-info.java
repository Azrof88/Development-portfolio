module com.example.fxApp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxApp to javafx.fxml;
    opens com.example.controller to javafx.fxml;
   // opens com.example.add to javafx.fxml;
    exports com.example.fxApp;
    exports com.example.controller;

}