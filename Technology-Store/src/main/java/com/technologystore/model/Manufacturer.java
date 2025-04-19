package com.technologystore.model;

public class Manufacturer {

    private String name;
    private String location;

    public Manufacturer() {
    }

    public Manufacturer(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Manufacturer: " + name + ", Location: " + location;
    }
}