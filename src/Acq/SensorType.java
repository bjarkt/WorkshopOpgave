package Acq;

public enum SensorType {
    TEMPERATURE("Temperature"), CO2("CO2");

    private String description;

    SensorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isInEnum(String value) {
        for (SensorType direction : SensorType.values()) {
            if (direction.description.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return description;
    }
}
