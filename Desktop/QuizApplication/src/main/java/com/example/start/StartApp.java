package com.example.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class StartApp extends Application {

    public static void main(String[] args)  {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/quizApplication/AdminLoginFXML.fxml")));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Quiz Application");
        stage.show();


    }
}
