package com.example.f23comp1011taskss2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TableViewController {

    @FXML
    private TableColumn<Task, LocalDate> dueDateColumn;

    @FXML
    private TableColumn<Task, Integer> estimateColumn;

    @FXML
    private TableColumn<Task, Status> statusColumn;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, Integer> taskIDColumn;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, User> userColumn;

    @FXML
    void viewCharts(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"chart-view.fxml" );
    }

    private ArrayList<Task> allTasks;

    @FXML
    private void initialize()
    {
        allTasks = DBUtility.getTasksFromDB();

        //configure the tablecolumns to work with the Task methods
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate")); //call getDueDate()
        estimateColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedLengthInMin"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        taskIDColumn.setCellValueFactory(new PropertyValueFactory<>("taskID"));

        //add the tasks to the tableview
        tableView.getItems().addAll(allTasks);
    }

}

