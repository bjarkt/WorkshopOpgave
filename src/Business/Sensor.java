package Business;

import Acq.IData;
import Acq.IMeasurement;
import Acq.ISensor;
import Acq.SensorType;

import java.util.*;

public class Sensor implements ISensor {
    private double measurement;
    private Logger logger;
    private SensorType sensorType;
    private String name;

    public Sensor(SensorType sensorType, String name, IData data) {
        this.sensorType = sensorType;
        this.logger = new Logger(data);
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

    public LinkedHashMap<Date, IMeasurement> getLog() {
        return new LinkedHashMap<>(logger.getLog());
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean hasMeasurement() {
        return measurement != Double.MIN_VALUE;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    @Override
    public String toString() {
        return name + " - " + sensorType;
    }
}
