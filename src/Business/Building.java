package Business;

import Acq.IBuilding;
import Acq.SensorType;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
            if (sensor.getSensorType().toString().equalsIgnoreCase(unit) && !sensor.hasMeasurement()) {
                sensor.setMeasurement(measurement);
            }
        }
    }

    public String getDataCollection() {
        StringBuilder sb = new StringBuilder();
        for (Sensor sensor : sensors) {
            sb.append("--------------");
            sb.append(sensor.getSensorType());
            sb.append("\n");
            for (Map.Entry<GregorianCalendar,Measurement> entry : sensor.getLog().entrySet()) {
                sb.append(entry.getKey().get(GregorianCalendar.HOUR_OF_DAY));
                System.out.println(entry.getKey().get(GregorianCalendar.MINUTE));
                sb.append(":");
                sb.append(entry.getKey().get(GregorianCalendar.MINUTE));
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

    public void addSensor(SensorType type, int howMany) {
        for (int i = 0; i < howMany; i++) {
                sensors.add(new Sensor(type));
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
