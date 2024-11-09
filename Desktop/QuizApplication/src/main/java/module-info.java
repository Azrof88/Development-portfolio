module com.example.quizApplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quizApplication to javafx.fxml;
    opens com.example.start to javafx.fxml;
    exports com.example.quizApplication;
    exports com.example.start;
}