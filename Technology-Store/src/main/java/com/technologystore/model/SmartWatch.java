package com.technologystore.model;

public class SmartWatch extends WearableDevice {

    private String connectionType;

    public SmartWatch(String name, int id, Manufacturer manufacturer, String measurement, String connectionType) {
        super(name, id, manufacturer, measurement);
        this.connectionType = connectionType;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public void update(Technology updatedTechnology) {
        super.update(updatedTechnology);
        if (updatedTechnology instanceof SmartWatch updatedSmartWatch) {
            this.setMeasurement(updatedSmartWatch.getMeasurement());
            this.setConnectionType(updatedSmartWatch.getConnectionType());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Connection Type: " + connectionType;
    }
}