package models;

import utils.Utilities;

public class Technology {

    private double price;
    private String id;
    private String modelName;
    private Manufacturer manufacturer;

    private boolean priceSet = false;
    private boolean idSet = false;
    private boolean modelNameSet = false;

    public Technology(String modelName, double price, Manufacturer manufacturer, String id) {
        this.modelName = modelName;
        this.price = price;
        this.manufacturer = manufacturer;
        this.id = id;
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
            if (id == null || id.isEmpty()) {
                this.id = "unknown";
            } else if (Utilities.validStringLength(id, 10)) {
                this.id = id;
            }
            idSet = true;
        } else {
            if (Utilities.validStringLength(id, 10)) {
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
        return STR."modelName=\{modelName}, price=\{price}, manufacturer=\{manufacturer}, id=\{id}";
    }
}
