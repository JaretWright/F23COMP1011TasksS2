package com.example.f23comp1011taskss2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.io.IOException;

public class ChartViewController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    void viewTable(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "table-view.fxml");
    }

    @FXML
    private void initialize(){
        barChart.getData().addAll(DBUtility.getBarChartData(Status.CREATED));
        barChart.getData().addAll(DBUtility.getBarChartData(Status.INPROGRESS));
        barChart.getData().addAll(DBUtility.getBarChartData(Status.DONE));
    }

}
