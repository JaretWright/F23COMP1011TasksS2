package com.example.f23comp1011taskss2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateTaskController implements Initializable {

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Spinner<Integer> durationSpinner;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private ComboBox<User> userComboBox;

    @FXML
    void saveTask(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configure the date picker to automatically have tomorrow as a due date
        dueDatePicker.setValue(LocalDate.now().plusDays(1));

        //configure the userComboBox to hold users from the DB
        userComboBox.getItems().addAll(DBUtility.getUsersFromDB());
    }
}
