package com.example.quizApplication;

import com.example.constants.AdminEmailPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {
    @FXML
    private TextField adminEmail;

    @FXML
    private Button adminLoginButton;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private TextField studentEmail;

    @FXML
    private Button studentLoginButton;

    @FXML
    private PasswordField studentPassword;

    @FXML
    void loginAdmin(ActionEvent event) throws IOException {
        String email = adminEmail.getText();
        String password = adminPassword.getText();
        if(email.trim().equalsIgnoreCase(AdminEmailPassword.email) && password.trim().equalsIgnoreCase(AdminEmailPassword.password)){
            try {
                Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/quizApplication/AdminHomeScreenFXML.fxml")));
                Stage stage=(Stage)studentPassword.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
            }catch (IOException e){
                e.printStackTrace();
                System.exit(0);
            }
            System.out.println("Admin Login Successful");
        }
        else
        {
            System.out.println("Admin Login Failed");
        }

        System.out.println("Admin Login");

    }

    @FXML
    void loginStudent(ActionEvent event) {
        System.out.println("Student Login");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
