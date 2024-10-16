package com.example.studentmark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class HelloApplication extends Application {

    private static final String DATABASE_URL = "jdbc:sqlite:E:/Development-portfolio/Desktop/student_mark/student_mark.db";

    // UI Components
    private TextField nameField, rollField, physicsField, chemistryField, mathField;
    private TextArea resultArea;
    private Button addButton, getButton, updateButton, deleteButton, submitButton;
    private Label nameLabel, physicsLabel, chemistryLabel, mathLabel, rollLabel;

    private String currentOperation; // To track the current operation

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize UI Components
        nameField = new TextField();
        rollField = new TextField();
        physicsField = new TextField();
        chemistryField = new TextField();
        mathField = new TextField();
        resultArea = new TextArea();
        resultArea.setEditable(false);

        addButton = new Button("Add Student");
        getButton = new Button("Get Marks");
        updateButton = new Button("Update Marks");
        deleteButton = new Button("Delete Student");
        submitButton = new Button("Submit");

        // Labels for adding student information
        rollLabel = new Label("Roll Number:");
        nameLabel = new Label("Name:");
        physicsLabel = new Label("Physics:");
        chemistryLabel = new Label("Chemistry:");
        mathLabel = new Label("Math:");

        // Set up layout
        GridPane grid = new GridPane();

        // Add buttons
        grid.add(addButton, 0, 0);
        grid.add(getButton, 1, 0);
        grid.add(updateButton, 0, 1);
        grid.add(deleteButton, 1, 1);

        // Result area
        grid.add(resultArea, 0, 2,2,1); // Adjusted position for clarity

        // Add student fields (hidden by default)
        grid.add(nameLabel, 0, 3);
        grid.add(nameField, 1, 3);
        grid.add(rollLabel, 0, 4);
        grid.add(rollField, 1, 4);
        grid.add(physicsLabel, 0, 5);
        grid.add(physicsField, 1, 5);
        grid.add(chemistryLabel, 0, 6);
        grid.add(chemistryField, 1, 6);
        grid.add(mathLabel, 0, 7);
        grid.add(mathField, 1, 7);
        grid.add(submitButton, 0, 8); // Adjusted position for submit button

        // Initially hide student input fields
        hideStudentInputFields();

        // Set button actions
        addButton.setOnAction(e -> showAddStudentFields());
        getButton.setOnAction(e -> showRollNumberFieldForGet());
        updateButton.setOnAction(e -> showUpdateFields());
        deleteButton.setOnAction(e -> showRollNumberFieldForDelete());
        submitButton.setOnAction(e -> handleSubmit());

        // Set up the scene
        Scene scene = new Scene(grid, 400, 500);
        primaryStage.setTitle("Student Marks Management");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the database
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                    "name TEXT NOT NULL, " +
                    "roll INTEGER PRIMARY KEY, " +
                    "physics INTEGER, " +
                    "chemistry INTEGER, " +
                    "math INTEGER, " +
                    "totalmark INTEGER, " +
                    "averagemark REAL)";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAddStudentFields() {
        currentOperation = "ADD";
        hideStudentInputFields();
        rollLabel.setVisible(true);
        rollField.setVisible(true);
        rollField.setEditable(true);
        nameLabel.setVisible(true);
        nameField.setVisible(true);
        physicsLabel.setVisible(true);
        physicsField.setVisible(true);
        chemistryLabel.setVisible(true);
        chemistryField.setVisible(true);
        mathLabel.setVisible(true);
        mathField.setVisible(true);
        submitButton.setVisible(true);
        resultArea.clear();
        resultArea.setText("Enter student details and click Submit.");
    }

    private void showRollNumberFieldForGet() {
        currentOperation ="GET";
        hideStudentInputFields();
        rollLabel.setVisible(true);
        rollField.setVisible(true);
        rollField.setEditable(true);
        submitButton.setVisible(true);
        resultArea.clear();
        resultArea.setText("Enter Roll Number and click Submit.");
    }
    private void showRollNumberFieldForDelete() {
        currentOperation ="DELETE";
        hideStudentInputFields();
        rollLabel.setVisible(true);
        rollField.setVisible(true);
        rollField.setEditable(true);
        submitButton.setVisible(true);
        resultArea.clear();
        resultArea.setText("Enter Roll Number and click Submit.");
    }

    private void showUpdateFields() {
        currentOperation = "UPDATE";
        hideStudentInputFields();
        rollLabel.setVisible(true);
        rollField.setVisible(true);
        rollField.setEditable(true);
        physicsLabel.setVisible(true);
        physicsField.setVisible(true);
        chemistryLabel.setVisible(true);
        chemistryField.setVisible(true);
        mathLabel.setVisible(true);
        mathField.setVisible(true);
        submitButton.setVisible(true);
        resultArea.clear();
        resultArea.setText("Enter Roll Number and new marks to update.");
    }

    private void hideStudentInputFields() {
        rollLabel.setVisible(false);
        rollField.setVisible(false);
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        physicsLabel.setVisible(false);
        physicsField.setVisible(false);
        chemistryLabel.setVisible(false);
        chemistryField.setVisible(false);
        mathLabel.setVisible(false);
        mathField.setVisible(false);
        submitButton.setVisible(false);
    }

    private void handleSubmit() {
        switch (currentOperation) {
            case "ADD":
                addStudent();
                break;
            case "GET":
                getMarks();
                break;
            case "UPDATE":
                updateMarks();
                break;
            case "DELETE":
                deleteStudent();
                break;
        }
        resetUI(); // Reset the UI to its initial state
    }

    private void resetUI() {
        rollField.clear();
        nameField.clear();
        physicsField.clear();
        chemistryField.clear();
        mathField.clear();
        hideStudentInputFields();
        resultArea.clear();
    }

    private void addStudent() {
        String name = nameField.getText();
        if (rollField.getText().isEmpty() || physicsField.getText().isEmpty() ||
                chemistryField.getText().isEmpty() || mathField.getText().isEmpty()) {
            resultArea.setText("Please fill in all fields.");
            return;
        }

        int roll, physics, chemistry, math;
        try {
            roll = Integer.parseInt(rollField.getText());
            physics = Integer.parseInt(physicsField.getText());
            chemistry = Integer.parseInt(chemistryField.getText());
            math = Integer.parseInt(mathField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid numeric values for roll and marks.");
            return;
        }

        int totalMark = physics + chemistry + math;
        double averageMark = totalMark / 3.0;

        String sql = "INSERT INTO students (name, roll, physics, chemistry, math, totalmark, averagemark) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, roll);
            pstmt.setInt(3, physics);
            pstmt.setInt(4, chemistry);
            pstmt.setInt(5, math);
            pstmt.setInt(6, totalMark);
            pstmt.setDouble(7, averageMark);
            pstmt.executeUpdate();
            resultArea.setText("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error adding student: " + e.getMessage());
        }
    }

    private void getMarks() {
        if (rollField.getText().isEmpty()) {
            resultArea.setText("Please enter a roll number.");
            return;
        }

        int roll;
        try {
            roll = Integer.parseInt(rollField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid roll number format. Please enter a numeric value.");
            return;
        }

        String sql = "SELECT * FROM students WHERE roll = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, roll);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Fetch and display data
                String name = rs.getString("name");
                int physics = rs.getInt("physics");
                int chemistry = rs.getInt("chemistry");
                int math = rs.getInt("math");
                int totalMark = rs.getInt("totalmark");
                double averageMark = rs.getDouble("averagemark");

                resultArea.setText("Name: " + name + "\n" +
                        "Roll: " + roll + "\n" +
                        "Physics: " + physics + "\n" +
                        "Chemistry: " + chemistry + "\n" +
                        "Math: " + math + "\n" +
                        "Total Mark: " + totalMark + "\n" +
                        "Average Mark: " + averageMark);
            } else {
                resultArea.setText("No student found with roll number: " + roll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error retrieving marks: " + e.getMessage());
        }
    }

    private void updateMarks() {
        if (rollField.getText().isEmpty()) {
            resultArea.setText("Please enter a roll number.");
            return;
        }

        int roll;
        try {
            roll = Integer.parseInt(rollField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid roll number format. Please enter a numeric value.");
            return;
        }

        StringBuilder sql = new StringBuilder("UPDATE students SET ");
        boolean hasUpdate = false;

        if (!physicsField.getText().isEmpty()) {
            sql.append("physics = ?, ");
            hasUpdate = true;
        }
        if (!chemistryField.getText().isEmpty()) {
            sql.append("chemistry = ?, ");
            hasUpdate = true;
        }
        if (!mathField.getText().isEmpty()) {
            sql.append("math = ?, ");
            hasUpdate = true;
        }

        if (hasUpdate) {
            // Remove trailing comma and add WHERE clause
            sql.setLength(sql.length() - 2); // Remove last comma and space
            sql.append(" WHERE roll = ?");

            try (Connection connection = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
                int index = 1;
                if (!physicsField.getText().isEmpty()) {
                    stmt.setInt(index++, Integer.parseInt(physicsField.getText()));
                }
                if (!chemistryField.getText().isEmpty()) {
                    stmt.setInt(index++, Integer.parseInt(chemistryField.getText()));
                }
                if (!mathField.getText().isEmpty()) {
                    stmt.setInt(index++, Integer.parseInt(mathField.getText()));
                }
                stmt.setInt(index, roll); // Set roll number for WHERE clause

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    resultArea.setText("Marks updated successfully.");
                } else {
                    resultArea.setText("No student found with roll number: " + roll);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resultArea.setText("Error updating marks: " + e.getMessage());
            }
        } else {
            resultArea.setText("Please enter at least one mark to update.");
        }
    }

    private void deleteStudent() {
        if (rollField.getText().isEmpty()) {
            resultArea.setText("Please enter a roll number.");
            return;
        }

        int roll;
        try {
            roll = Integer.parseInt(rollField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid roll number format. Please enter a numeric value.");
            return;
        }

        String sql = "DELETE FROM students WHERE roll = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roll);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                resultArea.setText("Student deleted successfully.");
            } else {
                resultArea.setText("No student found with roll number: " + roll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error deleting student: " + e.getMessage());
        }
    }
}
