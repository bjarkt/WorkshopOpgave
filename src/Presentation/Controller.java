package Presentation;

import Acq.IBuilding;
import Acq.IBusiness;
import Acq.IUI;
import Acq.SensorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements IUI, Initializable {

    private IBusiness business;

    @FXML
    private Label statusLabel;

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
        AlertBox.displayList("Building overview", "Building overview", business.getBuildings());
    }

    @FXML
    void handleAddSensorButton(ActionEvent event) {
        HashMap<String, String> sensorOptions = AlertBox.displaySensorInputFields("Add sensor", "Add sensor", business.getBuildings());
        if (sensorOptions.size() == 4) {
            System.out.println(sensorOptions);
            business.addSensor(sensorOptions.get("BuildingName"), SensorType.stringToEnum(sensorOptions.get("type")), Integer.parseInt(sensorOptions.get("amount")), sensorOptions.get("sensorName"));
            statusLabel.setText("Sensor added.");
        }
    }

    @FXML
    void handleOverviewSensorButton(ActionEvent event) {
        AlertBox.displayList("Sensor overview", "Sensor overview", business.getLogs());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business.addBuilding("SDU", "Campusvej 55", "Odense");
        business.addBuilding("Det Hvide Hus", "1600 Pennsylvania Avenue", "Washington D.C.");
        business.addSensor("SDU", SensorType.TEMPERATURE, 1, "bla bla");
        business.addSensor("Det Hvide Hus", SensorType.CO2, 1, "bla ");
    }
}
