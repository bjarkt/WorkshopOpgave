package Business;

import Acq.IBuilding;
import Acq.SensorType;

import java.util.*;

public class Building implements IBuilding {

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
            if (sensor.getSensorType().toString().equalsIgnoreCase(unit) && !sensor.hasMeasurement()) {
                sensor.setMeasurement(measurement);
            }
        }
    }

    public String getDataCollection() {
        StringBuilder sb = new StringBuilder();
        for (Sensor sensor : sensors) {
            sb.append(sensor);
            sb.append(System.lineSeparator());
            sb.append("--------------");
            sb.append(System.lineSeparator());
            sb.append(sensor.getSensorType());
            sb.append(System.lineSeparator());
            for (Map.Entry<Date, Measurement> entry : sensor.getLog().entrySet()) {
                sb.append(entry.getKey());
                sb.append(" - ");
                sb.append(entry.getValue()).append("\n");
            }
            sb.append("--------------");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(SensorType type, int howMany, String name) {
        for (int i = 0; i < howMany; i++) {
            sensors.add(new Sensor(type, name));
        }
    }

    public void removeSensor(SensorType type, int ID) {
        for (int i = 0; i < sensors.size(); i++) {
            if (i == ID && sensors.get(i).getSensorType().equals(type)) {
                sensors.remove(sensors.get(i));
            }
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
