package Business;

import java.util.*;

public class Logger {
    private Map<Date, Measurement> measurementList;

    public Logger() {
        measurementList = new LinkedHashMap<>();
    }

    public Map<Date, Measurement> getLog() {
        return measurementList;
    }

    public void addLog(Measurement measurement) {
        addLog(measurement, new Date());
    }

    public void addLog(Measurement measurement, Date date) {
        measurementList.put(date, measurement);
    }
}
