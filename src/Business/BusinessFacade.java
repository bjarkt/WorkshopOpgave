package Business;

import Acq.IBuilding;
import Acq.IBusiness;
import Acq.ISensor;
import Acq.SensorType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessFacade implements IBusiness {
    private BuildingManager buildingManager;

    public BusinessFacade() {
        buildingManager = new BuildingManager();
    }

    public void addBuilding(String name, String address, String city) {
        buildingManager.addBuilding(name, address, city);
    }

    public void removeBuilding(String name) {
        // TODO
    }

    public List<IBuilding> getBuildings() {
        return new ArrayList<>(buildingManager.getBuildings());
    }

    public void addMeasurement(String buildingName, double measurement, int whichSensor) {
        buildingManager.addMeasurement(buildingName, measurement, whichSensor);
    }

    public void addSensor(String buildingName, SensorType type, int howMany) {
        buildingManager.addSensor(buildingName, type, howMany);
    }

    public void removeSensor(String buildingName, SensorType type, int ID) {
        buildingManager.removeSensor(buildingName, type, ID);
    }

    public List<ISensor> getSensorsForBuilding(String buildingName) {
        for (Building building : buildingManager.getBuildings()) {
            if (building.getName().equals(buildingName)) {
                return new ArrayList<>(building.getSensors());
            }
        }
        return new ArrayList<>();
    }

    public List<String> getLogs() {
        List<String> l = new ArrayList<>();
        for (Building building : buildingManager.getBuildings()) {
            l.add(building.getDataCollection());
        }
        return l;
    }





}
