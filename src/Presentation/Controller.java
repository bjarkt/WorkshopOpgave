package Presentation;

import Acq.IBusiness;
import Acq.IUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements IUI, Initializable {

    private IBusiness business;

    public Controller() {
    }

    @FXML
    void handleAddBuildingButton(ActionEvent event) {
        business.addBuilding("Test", "test", "Test");
    }

    @FXML
    void handleOverviewBuildingButton (ActionEvent e) {
        AlertBox.display("hej", business.getBuildings());
    }

    @FXML
    void handleAddSensorButton(ActionEvent event) {

    }

    @FXML
    void handleOverviewSensorButton(ActionEvent event) {

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

    }
}
