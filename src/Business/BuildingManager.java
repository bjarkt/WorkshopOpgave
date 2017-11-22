package Business;

import java.util.ArrayList;
import java.util.List;

public class BuildingManager {
    private List<Building> buildings;

    public BuildingManager() {
        buildings = new ArrayList<>();
    }

    public void addBuilding(String name, String address, String city) {
        buildings.add(new Building(name, address, city));
    }

    public void removeBuilding(String name) {
        for (int i = buildings.size()-1; i > 0; i--) {
            if (buildings.get(i).getName().equals(name)) {
                buildings.remove(i);
            }
        }
    }

    public void addMeasurement(String buildingName, double measurement, int whichSensor) {
        for (Building building : buildings) {
            if (building.getName().equals(buildingName)) {
                building.getSensors().get(whichSensor).setMeasurement(measurement);
            }
        }
    }

    public void addSensor(String buildingName, String unit, int howMany) {
        for (Building building : buildings) {
            if (building.getName().equals(buildingName)) {
                building.addSensor(unit, howMany);
            }
        }
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
