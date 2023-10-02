package com.example.f23comp1011taskss2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        //1. try to create a task object
//        try{
//            Task task = new Task();
//        }
        //save it to the database
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configure the date picker to automatically have tomorrow as a due date
        dueDatePicker.setValue(LocalDate.now().plusDays(1));

        //configure the userComboBox to hold users from the DB
        userComboBox.getItems().addAll(DBUtility.getUsersFromDB());

        //configure the spinner to only allow valid durations
        //0 - 120
        //i = minimum
        //i1 = max value
        //i2 = default value
        SpinnerValueFactory<Integer> spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,120,60);
        durationSpinner.setValueFactory(spinnerValueFactory);
        durationSpinner.setEditable(true);
        TextField spinnerTextField = durationSpinner.getEditor();

        spinnerTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.isEmpty())
            {
                spinnerValueFactory.setValue(0);
            }
            else
            {
                try{
                    Integer.parseInt(newValue);
                } catch (NumberFormatException e)
                {
                    spinnerTextField.setText(oldValue);
                }
            }
        });
    }
}
