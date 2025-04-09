package models;

public abstract class WearableDevice extends Technology {

    private String material;
    private String size;

    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String material, String size) {
        super(modelName, price, manufacturer, id);
        this.material = material;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material.length() < 20) {
            this.material = material;
        } else {
            this.material = material.substring(0, 20);
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size.length() < 10) {
            this.size = size;
        } else {
            this.size = size.substring(0, 10);
        }
    }

    public abstract String connectToInternet();

    public abstract double getInsurancePremium();

    @Override
    public String toString() {
        return super.toString() + "\n" + STR."WearableDevice{material='\{material}', size='\{size}'} ";
    }
}
