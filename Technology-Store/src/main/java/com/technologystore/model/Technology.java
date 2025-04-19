package com.technologystore.model;

public class Technology {

    private String name;
    private int id;
    private Manufacturer manufacturer;

    public Technology() {
    }

    public Technology(String name, int id, Manufacturer manufacturer) {
        this.name = name;
        this.id = id;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void update(Technology updatedTechnology) {
        this.setName(updatedTechnology.getName());
        this.setManufacturer(updatedTechnology.getManufacturer());
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", " + manufacturer.toString();
    }
}