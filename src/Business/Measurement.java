package Business;

import Acq.SensorType;

public class Measurement {
    private SensorType sensorType;
    private double measurement;

    public Measurement(SensorType sensorType, double measurement) {
        this.sensorType = sensorType;
        this.measurement = measurement;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public double getMeasurement() {
        return measurement;
    }

    @Override
    public String toString() {
        return measurement + " " + sensorType;
    }
}
