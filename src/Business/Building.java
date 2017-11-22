package Business;

import Acq.IBuilding;

import java.util.ArrayList;
import java.util.List;

public class Building implements IBuilding{
    private List<Sensor> sensors;
    private String name;
    private String address;
    private String city;

    public Building(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
        sensors = new ArrayList<>();
    }

    public void addMeasurements(String unit, double measurement) {
        for (Sensor sensor : sensors) {
            if (sensor.getUnit().equals(unit) && !sensor.hasMeasurement()) {
                sensor.setMeasurement(measurement);
            }
        }
    }

    public String getDataCollection() {
        String s = "";
        for (Sensor sensor : sensors) {
            s += sensor.getUnit();
            s += sensor.getLog();
            s += System.lineSeparator();
        }
        return s;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(String unit, int howMany) {
        for (int i = 0; i < howMany; i++) {
            sensors.add(new Sensor(unit));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }
}
