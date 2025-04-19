package com.technologystore.model;

public class Laptop extends ComputingDevice {

    private String processor;

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public Laptop(String name, int id, Manufacturer manufacturer, String displaySize, String processor) {
        super(name, id, manufacturer, displaySize);
        this.processor = processor;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "processor: " + processor;
    }
}