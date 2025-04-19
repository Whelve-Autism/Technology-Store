package com.technologystore.model;

public class WearableDevice extends Technology {

    private String measurement;

    public WearableDevice() {
    }

    public WearableDevice(String name, int id, Manufacturer manufacturer, String measurement) {
        super(name, id, manufacturer);
        this.measurement = measurement;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "measurement: " + measurement;
    }
}
