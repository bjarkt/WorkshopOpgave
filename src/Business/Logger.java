package Business;

import Acq.IData;

import java.util.*;

public class Logger {
    private LinkedHashMap<Date, Measurement> measurementList;
    private IData data;

    public Logger(IData data) {
        measurementList = new LinkedHashMap<>();
        this.data = data;
    }

    public LinkedHashMap<Date, Measurement> getLog() {
        return measurementList;
    }

    public void addLog(Measurement measurement) {
        addLog(measurement, new Date());
    }

    public void addLog(Measurement measurement, Date date) {
        measurementList.put(date, measurement);
        data.save(prepareMeasurementsForSaving());
    }

    private HashMap<String, String> prepareMeasurementsForSaving() {
        HashMap<String, String> map = new LinkedHashMap<>();
        for (Map.Entry<Date, Measurement> entry : measurementList.entrySet()) {
            String timeKey = String.valueOf(entry.getKey().getTime());
            String measurementValue = entry.getValue().getMeasurement() + " - " + entry.getValue().getSensorType();
            map.put(timeKey, measurementValue);
        }
        return map;
    }
}
