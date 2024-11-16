module com.example.quizApplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.quizApplication to javafx.fxml;
    opens com.example.start to javafx.fxml;

    opens com.example.constants to javafx.fxml;
    opens com.example.controllers to javafx.fxml;
    exports com.example.quizApplication;
    exports com.example.start;
    exports com.example.constants;
    exports com.example.controllers;

}