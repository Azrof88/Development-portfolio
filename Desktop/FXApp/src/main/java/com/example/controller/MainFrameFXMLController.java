package com.example.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameFXMLController implements Initializable {
    @FXML
    private Button btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void buttonClicked(ActionEvent event) {
        System.out.println("button clicked");


    }
}
