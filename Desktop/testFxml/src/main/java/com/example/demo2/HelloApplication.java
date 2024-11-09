package com.example.demo2;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Corrected full path to the FXML file
            //String fxmlFilePath = "E:\\Desktop app dev\\JavaFXApplication3\\src\\javafxapplication3\\FXMLDocument.fxml";
            String fxmlFilePath="E:\\Development-portfolio\\Desktop\\demo2\\src\\main\\resources\\com\\example\\demo2\\hello-view.fxml";
            File fxmlFile = new File(fxmlFilePath);
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Parent root = loader.load();

            primaryStage.setTitle("My JavaFX Application");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
