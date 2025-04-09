package models;

public class Technology {

    private double price = 20;
    private String id = "unknown";
    private String modelName;
    private Manufacturer manufacturer;

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
        if (price >= 20) {
            this.price = price;
        } else {
            this.price = 20;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length() <= 10) {
            this.id = id;
        } else {
            this.id = id.substring(0, 10);
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (modelName.length() <= 30) {
            this.modelName = modelName;
        } else {
            this.modelName = modelName.substring(0, 30);
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
        return STR."Technology{id=\{id}, modelName=\{modelName}, manufacturer=\{manufacturer}, price=\{price}}";
    }
}
