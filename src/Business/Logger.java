package Business;

import java.util.*;

public class Logger {
    private Map<GregorianCalendar, Measurement> measurementList;

    public Logger() {
        measurementList = new HashMap<>();
    }

    public Map<GregorianCalendar, Measurement> getLog() {
        return measurementList;
    }

    public void addLog(Measurement measurement) {
        addLog(measurement, new GregorianCalendar());
    }

    public void addLog(Measurement measurement, GregorianCalendar date) {
        measurementList.put(date, measurement);
    }
}
