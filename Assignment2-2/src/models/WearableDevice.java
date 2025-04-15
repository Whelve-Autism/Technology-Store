package models;

import utils.Utilities;

public abstract class WearableDevice extends Technology {

    private String material;
    private String size;

    private boolean materialSet = false;
    private boolean sizeSet = false;

    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String material, String size) {
        super(modelName, price, manufacturer, id);
        setMaterial(material);
        setSize(size);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (!materialSet) {
            if (Utilities.validRange(material.length(), 0, 20)) {
                this.material = material;
            } else {
                this.material = material.substring(0, 20);
            }
            materialSet = true;
        } else {
            if (Utilities.validRange(material.length(), 0, 20)) {
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
        return super.toString() + "\n" + STR."Material: \{material}, Size: \{size}";
    }
}
