package Business;

import Acq.ISensor;

import java.util.Date;
import java.util.Map;

public class Sensor implements ISensor {
    private String unit;
    private double measurement;
    private Logger logger;

    public Sensor(String unit) {
        this.unit = unit;
        this.logger = new Logger();
        this.measurement = Double.MIN_VALUE;
    }

    public Measurement getMeasurement() {
        Measurement m = new Measurement(this.unit, this.measurement);
        logger.addLog(m);
        return m;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public Map<Date, Measurement> getLog() {
        return logger.getLog();
    }

    public boolean hasMeasurement() {
        return measurement != Double.MIN_VALUE;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "unit='" + unit + '\'' +
                '}';
    }
}
