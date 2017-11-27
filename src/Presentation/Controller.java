package Presentation;

import Acq.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

public class Controller implements IUI, Initializable {

    private IBusiness business;

    @FXML
    private Label statusLabel;

    @FXML
    private Pane chartPane;

    public Controller() {

    }

    @FXML
    void handleAddBuildingButton(ActionEvent event) {
        HashMap<String, String> buildingOptions = AlertBox.displayBuildingInputFields("Add building", "Add building");
        if (buildingOptions.size() == 3) {
            business.addBuilding(buildingOptions.get("name"), buildingOptions.get("address"), buildingOptions.get("city"));
            statusLabel.setText("Building added.");
        }
    }

    @FXML
    void handleOverviewBuildingButton(ActionEvent e) {
        AlertBox.displayBuildingList("Building overview", "Building overview", business.getBuildings());
    }

    @FXML
    void handleAddSensorButton(ActionEvent event) {
        HashMap<String, String> sensorOptions = AlertBox.displaySensorInputFields("Add sensor", "Add sensor", business.getBuildings());
        if (sensorOptions.size() == 4) {
            business.addSensor(sensorOptions.get("BuildingName"), SensorType.stringToEnum(sensorOptions.get("type")), Integer.parseInt(sensorOptions.get("amount")), sensorOptions.get("sensorName"));
            statusLabel.setText("Sensor added.");
        }
    }

    @FXML
    void handleOverviewSensorButton(ActionEvent event) {
        AlertBox.displaySensorList("Sensor overview", "Sensor overview", business.getSensorsLists(), this);
    }

    @FXML
    void handleRemoveBuildingButton(ActionEvent event) {
        statusLabel.setText("Not implemented");
    }

    @FXML
    void handleRemoveSensorButton(ActionEvent event) {
        HashMap<String, String> sensorInputs = AlertBox.displaySensorInputFieldsRemove("Remove sensor", "Remove sensor", business.getBuildings(), business);
        if (sensorInputs != null && sensorInputs.size() == 3) {
            business.removeSensor(sensorInputs.get("BuildingName"), SensorType.stringToEnum(sensorInputs.get("type")), Integer.valueOf(sensorInputs.get("id")));
            statusLabel.setText("Removed sensor.");
        }
    }

    @Override
    public void injectBusiness(IBusiness business) {
        this.business = business;
    }

    public void createChart(ISensor sensor) {
        chartPane.getChildren().clear();
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time [m]");
        yAxis.setLabel("Sensor reading - "+sensor.getSensorType());
        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Overview for " + sensor.toString());
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName(sensor.toString());
        //populating the series with data
        int i = 0;
        for (Map.Entry<Date, IMeasurement> entry : sensor.getLog().entrySet()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(entry.getKey());
            series.getData().add(new XYChart.Data(i++, entry.getValue().getMeasurement()));
        }

        lineChart.getData().add(series);
        chartPane.getChildren().add(lineChart);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business.addBuilding("SDU", "Campusvej 55", "Odense");
        business.addBuilding("Det Hvide Hus", "1600 Pennsylvania Avenue", "Washington D.C.");
        business.addSensor("SDU", SensorType.TEMPERATURE, 1, "bla bla");
        business.addSensor("Det Hvide Hus", SensorType.CO2, 1, "bla ");
        createChart(business.getSensorsForBuilding("SDU").get(0));
    }
}
