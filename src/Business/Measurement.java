package Business;

public class Measurement {
    private String unit;
    private double measurement;

    public Measurement(String unit, double measurement) {
        this.unit = unit;
        this.measurement = measurement;
    }

    public String getUnit() {
        return unit;
    }

    public double getMeasurement() {
        return measurement;
    }

    @Override
    public String toString() {
        return measurement + " " + unit;
    }
}
