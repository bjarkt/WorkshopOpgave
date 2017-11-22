package Acq;

import java.util.List;

public interface IBusiness {
    List<ISensor> getSensorsForBuilding(String buildingName);
    void addSensor(String buildingName, SensorType type, int howMany);
    void removeSensor(String buildingName, SensorType type);

    void addBuilding(String name, String address, String city);
    void removeBuilding(String name);

    List<String> getLogs();

    List<IBuilding> getBuildings();
}