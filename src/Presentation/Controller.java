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
import java.util.ResourceBundle;

public class Controller implements IUI, Initializable {

    private IBusiness business;

    @FXML
    private Label statusLabel;

    public Controller() {
    }

    @FXML
    void handleAddBuildingButton(ActionEvent event) {
        business.addBuilding("SDU", "Campusvej 55", "Odense");
        statusLabel.setText("Building added.");
    }

    @FXML
    void handleOverviewBuildingButton (ActionEvent e) {
        AlertBox.display("Building overview", business.getBuildings());
    }

    @FXML
    void handleAddSensorButton(ActionEvent event) {
        business.addSensor("SDU", SensorType.TEMPERATURE, 1);
        statusLabel.setText("Sensor added.");
    }

    @FXML
    void handleOverviewSensorButton(ActionEvent event) {
        System.out.println(business.getLogs());
    }

    @FXML
    void handleRemoveBuildingButton(ActionEvent event) {

    }

    @FXML
    void handleRemoveSensorButton(ActionEvent event) {

    }

    @Override
    public void injectBusiness(IBusiness business) {
        this.business = business;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        business.addBuilding("SDU", "Campusvej 55", "Odense");
        business.addSensor("SDU", SensorType.TEMPERATURE, 1);
    }
}
