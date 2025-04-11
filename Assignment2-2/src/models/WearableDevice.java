package models;

import utils.Utilities;

public abstract class WearableDevice extends Technology {

    private String material;
    private String size;

    private boolean materialSet = false;
    private boolean sizeSet = false;

    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String material, String size) {
        super(modelName, price, manufacturer, id);
        this.material = material;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (!materialSet) {
            this.material = Utilities.truncateString(material, 20);
            materialSet = true;
        } else {
            if (Utilities.validRange(material.length(), 1, 20)) {
                this.material = material;
            }
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (!sizeSet) {
            this.size = Utilities.truncateString(size, 10);
            sizeSet = true;
        } else {
            if (Utilities.validRange(size.length(), 1, 10)) {
                this.size = size;
            }
        }
    }

    public abstract String connectToInternet();

    public abstract double getInsurancePremium();

    @Override
    public String toString() {
        return super.toString() + "\n" + STR."material='\{material}', size='\{size}'";
    }
}
