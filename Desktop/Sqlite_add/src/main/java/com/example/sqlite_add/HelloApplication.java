package com.example.sqlite_add;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private static final String DATABASE_URL = "jdbc:sqlite:E:/Development-portfolio/Desktop/Sqlite_add/sample.db";

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        Button loadButton = new Button("Load Data");

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadData(textArea);
            }
        });


        VBox vbox = new VBox(loadButton, textArea);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setTitle("SQLite JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//using thread to run in background with lamda expression
//This is how you retrieve data from the database.
// The ResultSet allows you to iterate over the rows returned by the query.
private void loadData(TextArea textArea) {
    StringBuilder data = new StringBuilder();

    // Using an anonymous inner class instead of a lambda expression
    new Thread(new Runnable() {
        @Override
        public void run() {
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
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    textArea.setText(data.toString());
                }
            });
        }
    }).start();
}



    public static void main(String[] args) {
        launch(args);
    }
}