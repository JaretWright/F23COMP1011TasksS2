package com.example.f23comp1011taskss2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserViewController implements Initializable {

    @FXML
    private TextField emailTextField;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void submitPressed(ActionEvent event) {
        String email = emailTextField.getText();
        String userName = userNameTextField.getText();
        String phone = phoneTextField.getText();

        try {
            User newUser = new User(email, userName, phone);
            msgLabel.setText(DBUtility.addUserToDB(newUser));
        } catch (IllegalArgumentException e)
        {
            msgLabel.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setText("");
    }
}

