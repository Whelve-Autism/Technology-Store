package models;

import utils.OperatingSystemUtility;

public class Tablet extends ComputingDevice {

    private String operatingSystem = "Windows";

    private boolean operatingSystemSet = false;

    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        setOperatingSystem(operatingSystem);
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if (!operatingSystemSet) {
            if (OperatingSystemUtility.isValidOperatingSystem(operatingSystem)) {
                this.operatingSystem = operatingSystem;
                operatingSystemSet = true;
            } else {
                this.operatingSystem = "Windows";
            }
        } else {
            if (OperatingSystemUtility.isValidOperatingSystem(operatingSystem)) {
                this.operatingSystem = operatingSystem;
            }
        }
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
