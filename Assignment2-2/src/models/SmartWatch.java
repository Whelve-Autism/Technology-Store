package models;

import utils.DisplayTypeUtility;

/**
 * 此类用于创建 SmartWatch 对象。
 * This class is used to create SmartWatch objects.
 *
 * @author Fan Xinkang
 * @version 1.0
 * @since version 1.0
 */
public class SmartWatch extends WearableDevice {

    private String displayType = "LCD";

    /**
     * 创建 SmartWatch 对象。
     * Constructor for SmartWatch.
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
     * @param displayType 显示类型。
     *                    Display type.
     * @author Fan Xinkang
     * @since version 1.0
     */
    public SmartWatch(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, String displayType) {
        super(modelName, price, manufacturer, id, material, size);
        this.displayType = displayType;
    }

    /*
      封装。
      Encapsulation.
     */
    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        if (DisplayTypeUtility.isValidDisplayType(displayType)) {
            this.displayType = displayType;
        } else {
            this.displayType = "LCD";
        }
    }

    /**
     * 连接到互联网。
     * Connect to the internet.
     *
     * @return 连接成功。
     *         Connected successfully.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String connectToInternet() {
        return "Connects to the internet via bluetooth.";
    }

    /**
     * 获取保险保费。
     * Get insurance premium.
     *
     * @return 保险保费。
     *         Insurance Premium.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public double getInsurancePremium() {
        return getPrice() * 0.06;
    }

    /**
     * 重写 toString 方法，以便打印 SmartWatch 对象。
     * Override the toString method to print SmartWatch objects.
     *
     * @return 字符串表示。
     *         String representation.
     * @author Fan Xinkang
     * @since version 1.0
     */
    @Override
    public String toString() {
        return STR."""
                \{super.toString()}
                Display Type: \{getDisplayType()}
                \{connectToInternet()}
                Insurance Premium: $\{getInsurancePremium()}
                """;
    }
}
/*
 * End of models.SmartWatch Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */