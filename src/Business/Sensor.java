package Business;

import Acq.ISensor;
import Acq.SensorType;

import java.util.*;

public class Sensor implements ISensor {
    private double measurement;
    private Logger logger;
    private SensorType sensorType;

    public Sensor(SensorType sensorType) {
        this.sensorType = sensorType;
        this.logger = new Logger();
        this.measurement = Double.MIN_VALUE;
        generateDummyData();
    }

    public Measurement getMeasurement() {
        Measurement m = new Measurement(this.sensorType, this.measurement);
        logger.addLog(m);
        return m;
    }

    public Measurement getMeasurement(GregorianCalendar gc) {
        Measurement m = new Measurement(this.sensorType, this.measurement);
        logger.addLog(m, gc);
        return m;
    }

    private void generateDummyData() {
        System.out.println("generateDummyData metode bruges kun til test af sensor");
        GregorianCalendar gc = new GregorianCalendar();
        for (int i = 0; i < 25; i++) {
            gc.add(GregorianCalendar.MINUTE, 1);
            setMeasurement(Math.random()*100);
            getMeasurement(gc);
        }
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public Map<GregorianCalendar, Measurement> getLog() {
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
        return "Sensor{" +
                "sensorType=" + sensorType +
                '}';
    }
}
