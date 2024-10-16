module com.example.studentmark {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studentmark to javafx.fxml;
    exports com.example.studentmark;
}