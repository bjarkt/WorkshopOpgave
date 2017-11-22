package Business;

import java.util.*;

public class Logger {
    private Map<Date, Measurement> measurementList;

    public Logger() {
        measurementList = new HashMap<>();
    }

    public Map<Date, Measurement> getLog() {
        return measurementList;
    }

    public void addLog(Measurement measurement) {
        Date date = new Date();
        measurementList.put(date, measurement);
    }
}
