package Acq;

import Business.Measurement;

import java.util.Date;
import java.util.LinkedHashMap;

public interface ISensor {
    SensorType getSensorType();
    LinkedHashMap<Date, IMeasurement> getLog();
    String getName();
}
