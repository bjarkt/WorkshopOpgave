package Acq;

import java.util.List;

public interface IBusiness {
    List<ISensor> getSensorsForBuilding(String buildingName);
    void addSensor(String buildingName, SensorType type, int howMany, String name);
    void removeSensor(String buildingName, SensorType type, int ID);

    void addBuilding(String name, String address, String city);
    void removeBuilding(String name);

    List<String> getLogs();

    List<IBuilding> getBuildings();
}
