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
        List<String> buildingOptions = AlertBox.displayBuildingInputFields("Add building", "Add building");
        business.addBuilding(buildingOptions.get(0), buildingOptions.get(1), buildingOptions.get(2));
        statusLabel.setText("Building added.");
    }

    @FXML
    void handleOverviewBuildingButton (ActionEvent e) {
        AlertBox.displayList("Building overview", "Building overview", business.getBuildings());
    }

    @FXML
    void handleAddSensorButton(ActionEvent event) {
        List<String> sensorOptions = AlertBox.displaySensorInputFields("Add sensor", "Add sensor", business.getBuildings());
        business.addSensor(sensorOptions.get(0), SensorType.valueOf(sensorOptions.get(1)), Integer.parseInt(sensorOptions.get(2)));
        statusLabel.setText("Sensor added.");
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
        statusLabel.setText("Not implemented");
    }

    @Override
    public void injectBusiness(IBusiness business) {
        this.business = business;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business.addBuilding("SDU", "Campusvej 55", "Odense");
        business.addBuilding("Det Hvide Hus", "1600 Pennsylvania Avenue", "Washington D.C.");
        business.addSensor("SDU", SensorType.TEMPERATURE, 1);
        business.addSensor("Det Hvide Hus", SensorType.CO2, 1);
    }
}
