package models;

import utils.Utilities;

public class Technology {

    private String modelName;
    private double price;
    private Manufacturer manufacturer;
    private String id;

    private boolean modelNameSet = false;
    private boolean priceSet = false;
    private boolean idSet = false;

    public Technology(String modelName, double price, Manufacturer manufacturer, String id) {
        setModelName(modelName);
        setPrice(price);
        this.manufacturer = manufacturer;
        setId(id);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (!priceSet) {
            if (Utilities.validRange(price, 20, Double.MAX_VALUE)) {
                this.price = price;
            } else {
                this.price = 20;
            }
            priceSet = true;
        } else {
            if (Utilities.validRange(price, 20, Double.MAX_VALUE)) {
                this.price = price;
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!idSet) {
            if (id.length() <= 10) {
                this.id = id;
            } else {
                this.id = "unknown";
            }
            idSet = true;
        } else {
            if (id.length() <= 10) {
                this.id = id;
            }
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (!modelNameSet) {
            if (modelName == null || modelName.isEmpty()) {
                this.modelName = "unknown";
            } else if (Utilities.validStringLength(modelName, 30)) {
                this.modelName = modelName;
            } else {
                this.modelName = modelName.substring(0, 30);
            }
            modelNameSet = true;
        } else {
            if (Utilities.validStringLength(modelName, 30)) {
                this.modelName = modelName;
            }
        }
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return STR."Model Name: \{modelName}, Price: $\{price}, \{manufacturer}, ID: \{id}";
    }
}
