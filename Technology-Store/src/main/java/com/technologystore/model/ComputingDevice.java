package com.technologystore.model;

public class ComputingDevice extends Technology {

    private String displaySize;

    public ComputingDevice() {
    }

    public ComputingDevice(String name, int id, Manufacturer manufacturer, String displaySize) {
        super(name, id, manufacturer);
        this.displaySize = displaySize;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Display Size: " + displaySize;
    }
}