package Acq;

import java.util.List;

public interface IBusiness {
    List<ISensor> getSensorsForBuilding(String buildingName);
    void addSensor(String buildingName, String unit, int howMany);
    void removeSensor(String buildingName, String unit);

    void addBuilding(String name, String address, String city);
    void removeBuilding(String name);

    List<IBuilding> getBuildings();
}
