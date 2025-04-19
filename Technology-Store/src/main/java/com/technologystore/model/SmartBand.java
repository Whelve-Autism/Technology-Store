package com.technologystore.model;

public class SmartBand extends WearableDevice {
    private int batteryCapacity;

    public SmartBand(String name, int id, Manufacturer manufacturer, String measurement, int batteryCapacity) {
        super(name, id, manufacturer, measurement);
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void update(Technology updatedTechnology) {
        super.update(updatedTechnology);
        if (updatedTechnology instanceof SmartBand) {
            SmartBand updatedSmartBand = (SmartBand) updatedTechnology;
            this.setMeasurement(updatedSmartBand.getMeasurement());
            this.setBatteryCapacity(updatedSmartBand.getBatteryCapacity());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "batteryCapacity: " + batteryCapacity;
    }
}
