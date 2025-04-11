package models;

public class Tablet extends ComputingDevice {

    private String operatingSystem = "Windows";

    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    @Override
    public String connectToInternet() {
        return "Connects to the internet via Wi-Fi";
    }

    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.01;
    }

    @Override
    public String toString() {
        return STR."""
                \{super.toString()}
                Operating System: \{getOperatingSystem()}
                \{connectToInternet()}
                Insurance Premium: $\{getInsurancePremium()}
                """;
    }
}
