package models;

public class SmartBand extends WearableDevice {

    private boolean heartRateMonitor;

    public SmartBand(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, boolean heartRateMonitor) {
        super(modelName, price, manufacturer, id, material, size);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public String getHeartRateMonitor() {
        if (heartRateMonitor) {
            return "Includes Heart Rate Monitor.";
        } else {
            return "No Heart Rate Monitor included.";
        }
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    @Override
    public String connectToInternet() {
        return "Connects to the internet via Companion App.";
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.07;
    }

    @Override
    public String toString() {
        return STR."""
                \{super.toString()}
                \{getHeartRateMonitor()}
                \{connectToInternet()}
                Insurance Premium: $\{getInsurancePremium()}
                """;
    }
}
