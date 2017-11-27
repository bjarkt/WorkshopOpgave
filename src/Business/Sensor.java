package Business;

import Acq.ISensor;
import Acq.SensorType;

import java.util.*;

public class Sensor implements ISensor {
    private double measurement;
    private Logger logger;
    private SensorType sensorType;
    private String name;

    public Sensor(SensorType sensorType, String name) {
        this.sensorType = sensorType;
        this.logger = new Logger();
        this.measurement = Double.MIN_VALUE;
        this.name = name;
        generateDummyData();
    }

    public Measurement getMeasurement() {
        Measurement m = new Measurement(this.sensorType, this.measurement);
        logger.addLog(m);
        return m;
    }

    public Measurement getMeasurement(Date date) {
        Measurement m = new Measurement(this.sensorType, this.measurement);
        logger.addLog(m, date);
        return m;
    }

    private void generateDummyData() {
        System.out.println("generateDummyData metode bruges kun til test af sensor");
        Calendar now = Calendar.getInstance();
        for (int i = 0; i < 25; i++) {
            now.add(Calendar.MINUTE, 1);
            Date date = now.getTime();
            setMeasurement(Math.random() * 100);
            getMeasurement(date);
        }
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

    public SensorType getSensorType() {
        return sensorType;
    }

    @Override
    public String toString() {
        return sensorType + " - " + name;
    }
}
