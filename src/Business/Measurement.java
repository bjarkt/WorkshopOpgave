package Business;

import Acq.IMeasurement;
import Acq.SensorType;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Measurement implements IMeasurement {
    private SensorType sensorType;
    private String unit;
    private double measurement;

    public Measurement(SensorType sensorType, double measurement) {
        this.sensorType = sensorType;
        this.measurement = measurement;
        determinesUnit();
    }

    private void determinesUnit() {
        switch (this.sensorType) {
            case TEMPERATURE:
                this.unit = "°C";
                break;
            case CO2:
                this.unit = "PPM^2";
                break;
            default:
                this.unit = "no unit";
                break;
        }
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public double getMeasurement() {
        return measurement;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        return decimalFormat.format(measurement) + " " + unit;
    }
}
