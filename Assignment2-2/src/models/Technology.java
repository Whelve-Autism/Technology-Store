package models;

import utils.Utilities;

/**
 * 此类用于创建 Technology 对象。
 * This class is used to create Technology objects.
 *
 * @author Fan Xinkang
 * @version 1.0
 * @since version 1,0
 */
public abstract class Technology {

    private String modelName;
    private double price;
    private Manufacturer manufacturer;
    private String id;

    private boolean modelNameSet = false;
    private boolean priceSet = false;
    private boolean idSet = false;

    /**
     * 创建 Technology 对象。
     * Constructor for Technology.
     *
     * @param modelName 型号。
     *                  Model name.
     * @param price 价格。
     *              Price.
     * @param manufacturer 生产商。
     *                     Manufacturer.
     * @param id ID.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public Technology(String modelName, double price, Manufacturer manufacturer, String id) {
        setModelName(modelName);
        setPrice(price);
        this.manufacturer = manufacturer;
        setId(id);
    }

    /*
      封装。
      Encapsulation.
     */
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
            if (Utilities.validStringLength(id, 10)) {
                this.id = id;
            } else {
                this.id = "unknown";
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
            } else {
                Utilities.truncateString(modelName, 30);
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

    /**
     * 抽象方法，用于连接到互联网。
     * Abstract method to connect to the internet.
     *
     * @return 连接结果。
     *         Connection result.
     * @author Fan Xinkang
     * @since version 3.1
     */
    public abstract String connectToInternet();

    /**
     * 抽象方法，用于获取保险保费。
     * Abstract method to get insurance premium.
     *
     * @return 保险保费。
     *         Insurance premium.
     * @author Fan Xinkang
     * @since version 3.1
     */
    public abstract double getInsurancePremium();

    /**
     * 重写 toString 方法，以便打印 Technology 对象。
     * Override the toString method to print Technology objects.
     *
     * @return 字符串表示。
     *         String representation.
     * @author Fan Xinkang
     * @since version 1.0
     */

    @Override
    public String toString() {
        return STR."Model Name: \{modelName}, Price: $\{price}, \{manufacturer}, ID: \{id}";
    }
}
/*
 * End of models.Technology Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */