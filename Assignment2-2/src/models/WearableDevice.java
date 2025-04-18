package models;

import utils.Utilities;

/**
 * 此类用于表示智能穿戴设备，包括智能手环和智能手表，提供了通用的属性和方法。
 * This class is used to represent wearable devices such as smart bands and smart watches, providing common properties and methods.
 *
 * @author Fan Xinkang
 * @version 1.0
 * @since version 1.0
 */
public abstract class WearableDevice extends Technology {

    private String material;
    private String size;

    private boolean materialSet = false;
    private boolean sizeSet = false;

    /**
     * 创建 WearableDevice 对象。
     * Constructor for WearableDevice.
     *
     * @param modelName 型号。
     *                  Model name.
     * @param price 价格。
     *              Price.
     * @param manufacturer 生产商。
     *                     Manufacturer.
     * @param id ID.
     * @param material 材质。
     *                 Material.
     * @param size 大小。
     *             Size.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public WearableDevice(String modelName, double price, Manufacturer manufacturer, String id, String material, String size) {
        super(modelName, price, manufacturer, id);
        setMaterial(material);
        setSize(size);
    }

    /*
      封装。
      Encapsulation.
     */
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (!materialSet) {
            this.material = Utilities.truncateString(material, 20);
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

    /**
     * 抽象方法，用于连接到互联网。
     * Abstract method to connect to the internet.
     *
     * @return 连接结果。
     *         Connection result.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public abstract String connectToInternet();

    /**
     * 抽象方法，用于获取保险保费。
     * Abstract method to get insurance premium.
     *
     * @return 保险保费。
     *         Insurance premium.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public abstract double getInsurancePremium();

    /**
     * 重写 toString 方法，以便打印 WearableDevice 对象。
     * Override the toString method to print WearableDevice objects.
     *
     * @return 字符串表示。
     *         String representation.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + STR."Material: \{material}, Size: \{size}";
    }
}
/*
 * End of models.WearableDevice Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */