package com.example.fxApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AppStart extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
// Use relative path to load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/fxApp/MainFrameFXML.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My JavaFX Application"); // Optional: Set the title
        stage.show();

    }
}

