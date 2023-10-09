package com.example.f23comp1011taskss2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewController {

    @FXML
    private TableColumn<?, ?> dueDateColumn;

    @FXML
    private TableColumn<?, ?> estimateColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> taskIDColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> userColumn;

    @FXML
    void viewCharts(ActionEvent event) {

    }

}

