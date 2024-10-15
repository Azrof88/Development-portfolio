package com.example.sqlite_add;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
public class HelloApplication extends Application {
    private static final String DATABASE_URL = "jdbc:sqlite:E:/Desktop/Sqlite_add/sample.db";

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        Button loadButton = new Button("Load Data");

        loadButton.setOnAction(event -> loadData(textArea));

        VBox vbox = new VBox(loadButton, textArea);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setTitle("SQLite JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadData(TextArea textArea) {
        StringBuilder data = new StringBuilder();
        new Thread(() -> {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL);
                 Statement statement = connection.createStatement()) {

                String query = "SELECT * FROM Azrof"; // Replace with your table name
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    data.append(resultSet.getString("Shuchi")).append("\n"); // Replace with your column name
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for debugging
                data.append("Error: ").append(e.getMessage());
            }
            // Update the UI on the JavaFX Application Thread
            javafx.application.Platform.runLater(() -> textArea.setText(data.toString()));
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}