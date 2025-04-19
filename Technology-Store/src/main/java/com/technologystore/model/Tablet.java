package com.technologystore.model;

public class Tablet extends ComputingDevice {

    private String operationSystem;

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public Tablet(String name, int id, Manufacturer manufacturer, String displaySize, String operationSystem) {
        super(name, id, manufacturer, displaySize);
        this.operationSystem = operationSystem;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "operationSystem: " + operationSystem;
    }
}