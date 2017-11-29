package Business;

import Acq.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessFacade implements IBusiness {
    private BuildingManager buildingManager;
    private IData dataFacade;

    public BusinessFacade() {
        buildingManager = new BuildingManager();
    }

    public void addBuilding(String name, String address, String city) {
        buildingManager.addBuilding(name, address, city);
    }

    public void removeBuilding(String name) {
        buildingManager.removeBuilding(name);
    }

    public List<IBuilding> getBuildings() {
        return new ArrayList<>(buildingManager.getBuildings());
    }

    public void addMeasurement(String buildingName, double measurement, int whichSensor) {
        buildingManager.addMeasurement(buildingName, measurement, whichSensor);
    }

    public void addSensor(String buildingName, SensorType type, int howMany, String name) {
        buildingManager.addSensor(buildingName, type, howMany, name, dataFacade);
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

    public List<ISensor> getSensorsLists() {
        List<ISensor> lists = new ArrayList<>();
        for (Building building : buildingManager.getBuildings()) {
            lists.addAll(getSensorsForBuilding(building.getName()));
        }
        return lists;
    }

    public void injectData(IData data) {
        this.dataFacade = data;
    }


}
